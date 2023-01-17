import os

import flask
from .app import app
from flask import render_template, url_for, redirect, request
from flask_wtf import FlaskForm
from wtforms import StringField , HiddenField
from wtforms . validators import DataRequired
from wtforms import PasswordField
from hashlib import sha256
from flask_login import login_user, current_user,login_required,logout_user
from .models import *

app.config['SECRET_KEY'] = "4829a5df-b098-419a-9f38-a428997cfedb"

@app.route("/")
def home():
    return render_template("home.html")

@app.route("/detail")
def detail():
    f = SearchForm()
    poneys = get_poneys()
    return render_template("detail.html",poneys=poneys,f=f)

@app.route("/detail_trier",methods=["POST"])
def trier_poneys():
    f = SearchForm()
    poneys = get_poneys_poids2(f.poids.data)
    return render_template("detail.html",poneys=poneys,f=f)


@app.route("/profil")
def profil():
    return render_template("profil.html")

@app.route("/reservation")
def reservation():
    return render_template("reservation.html")

@app.route("/cours/<username>")
def cours_dispo(username):
    coursDispo = get_liste_cours_dispo(username)
    return render_template("cours.html",cours= coursDispo)

@app.route("/choix_poney/<int:idCours>/<username>")
def choix_poney(username,idCours):
    userConnecte = Client.query.filter(Client.username == username).first()
    poids = userConnecte.getPoids()
    listePoney = get_poneys_poids(poids)
    coursRes = Cours.query.filter(Cours.idCours == idCours).first()
    jmaCours = coursRes.get_jma()
    heureCours = coursRes.get_heure()
    for poney in listePoney:
        reservation = Reserver.query.filter(Reserver.idP == poney.get_idP()).all()
        for reserv in reservation:
            idC = reserv.getIdCours()
            cours = Cours.query.filter(Cours.idCours == idC).first()
            jma = cours.get_jma()
            heure = cours.get_heure()
            print(jma,jmaCours,heure,heureCours,cours.get_type())
            print(jma == jmaCours and heure == heureCours)
            if jma == jmaCours and heure == heureCours:
                listePoney.remove(poney)
    return render_template("choix_poney.html",poneys= listePoney,user=userConnecte,idCours = idCours)

@app.route("/reserver/<int:idClient>/<int:idCours>/<int:idPoney>")
def reserv(idClient,idCours,idPoney):
    reserv = Reserver(idCours= idCours,idClient= idClient,idP=idPoney)
    print(idCours,idClient,idPoney)
    db.session.add(reserv)
    db.session.commit()
    return render_template("home.html")

@app.route("/coursDe/<username>")
def mes_cours(username):
    userConnecte = Client.query.filter(Client.username == username).first()
    idClient = userConnecte.getId()
    res = Reserver.query.filter(Reserver.idClient == idClient).all()
    cours = list()
    for reservation in res:
        idCours = reservation.getIdCours()
        cour = Cours.query.filter(Cours.idCours == idCours).first()
        cours.append(cour)
    return render_template("mes_cours.html",cours= cours,idClient = idClient)

@app.route("/sup_res/<int:idClient>/<int:idCours>")
def supprimer_reservation(idCours,idClient):
    Reserver.query.filter(Reserver.idCours == idCours, Reserver.idClient == idClient).delete()
    db.session.commit()
    return render_template("home.html")


class LoginForm(FlaskForm):
    username = StringField("Username")
    mdp = PasswordField("Password")
    next = HiddenField()
    
    def get_authenticated_user(self):
        user = Client.query.filter(Client.username == self.username.data).first()
        if user is None:
            return None
        return user if self.mdp.data == user.mdp else None

@app.route("/login", methods =["GET","POST"])
def login():
    f = LoginForm()
    print(f.validate_on_submit())
    if not f.is_submitted():
        f.next.data = request.args.get('next')
    elif f.validate_on_submit():
        user = f.get_authenticated_user()
        print(user)
        if user is not None:
            login_user(user)
            next = f.next.data or url_for("home")
            return redirect(next)
    return render_template("page_connexion.html",form=f)
# def login():
#     form = LoginForm()
#     if form.validate_on_submit():
        
#         login_user(form.get_authenticated_user())
#         print("enfin")
#         flask.flash('Logged in successfully.')

#         next = flask.request.args.get('next')

#         return flask.redirect(next or flask.url_for('accueil'))
#     return flask.render_template('page_connexion.html', form=form)

@app.route("/logout/")
def logout():
    logout_user()
    return redirect(url_for("home"))



@app.route("/register", methods=("POST",))
def register():
    nomC = request.form.get("nom")
    prenomC = request.form.get("prenom")
    poids = request.form.get("poids")
    username = request.form.get("identifiant")
    mdp = request.form.get("mdp")
    cotisation = request.form.get("paye-coti")

    user = Client.query.filter(Client.username==username).first() 

    if user:
        return redirect(url_for('page_connexion.html'))

    id = max_id_client()
    if id is None:
        id = 1
    new_user = Client(idClient=id,nomC=nomC, prenomC=prenomC, poids=poids, cotisation=cotisation, username=username, password=mdp)

    db.session.add(new_user)
    db.session.commit()

    return redirect(url_for('home'))
    