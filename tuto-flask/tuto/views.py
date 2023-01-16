import os
from .app import app
from flask import render_template, url_for, redirect, request
from flask_wtf import FlaskForm
from wtforms import StringField , HiddenField
from wtforms . validators import DataRequired
from wtforms import PasswordField
from hashlib import sha256
from flask_login import login_user, current_user,login_required,logout_user
from .models import *

SECRET_KEY = os.urandom(32)
app.config['SECRET_KEY'] = SECRET_KEY

@app.route("/")
def home():
    return render_template("home.html")

@app.route("/login")
def connexion():
    return render_template("page_connexion.html")

@app.route("/register")
def inscription():
    return render_template("page_inscription.html")

@app.route("/detail")
def detail():
    f = SearchForm()
    poneys = get_poneys()
    return render_template("detail.html",poneys=poneys,f=f)

@app.route("/detail_trier",methods=["POST"])
def trier_poneys():
    f = SearchForm()
    poneys = get_poneys_poids(f.poids.data)
    return render_template("detail.html",poneys=poneys,f=f)


@app.route("/profil")
def profil():
    return render_template("profil.html")

@app.route("/reservation")
def reservation():
    return render_template("reservation.html")

@app.route("/cours")
def cours_dispo():
    coursDispo = get_liste_cours_dispo()
    return render_template("cours.html",cours= coursDispo)

@app.route("/choix_poney/<username><int:idCours>")
def choix_poney(username,idCours):
    userConnecte = Client.query.filter(Client.username == username).first()
    poids = userConnecte.getPoids()
    listePoney = get_poneys_poids(poids)
    return render_template("choix_poney.html",poneys= listePoney)

class LoginForm(FlaskForm):
    username = StringField("Username")
    password = PasswordField("Password")
    next = HiddenField()
    
    def get_authenticated_user(self):
        user = Client.query.get(self.username.data)
        if user is None:
            return None
        return user if self.password.data == user.mdp else None

@app.route("/login", methods =("GET","POST",))
def login():
    f = LoginForm()
    if not f.is_submitted():
        f.next.data = request.args.get("next")
    elif f.validate_on_submit():
        user = f.get_authenticated_user()
        if user:
            login_user(user)
            next = f.next.data or url_for("accueil")
            return redirect(next)
    if (current_user.is_authenticated):
        return render_template("accueil.html",form=f)

@app.route("/logout/")
def logout():
    logout_user()
    return redirect(url_for("accueil.html"))

@app.route("/register", methods=["POST"])
def register():
    nomC = request.form.get("nom")
    prenomC = request.form.get("prenom")
    poids = request.form.get("poids")
    username = request.form.get("identifiant")
    mdp = request.form.get("mdp")
    cotisation = request.form.get("paye-coti")

    user = Client.query.filter(Client.username==username).first() 

    if user:
        return redirect(url_for('auth.inscription'))

    id = max_id_client()
    if id is None:
        id = 1
    new_user = Client(idClient=id,nomC=nomC, prenomC=prenomC, poids=poids, cotisation=cotisation, username=username, password=mdp)

    db.session.add(new_user)
    db.session.commit()

    return redirect(url_for('auth.login'))
    