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


@app.route("/")
def home():
    return render_template("accueil.html")

@app.route("/login")
def connexion():
    return render_template("page_connexion.html")

@app.route("/register")
def inscription():
    return render_template("page_inscription.html")

@app.route("/detail")
def detail():
    return render_template("detail.html")

@app.route("/profil")
def profil():
    return render_template("profil.html")

@app.route("/reservation")
def reservation():
    return render_template("reservation.html")

class LoginForm(FlaskForm):
    username = StringField("Username")
    password = PasswordField("Password")
    next = HiddenField()
    
    def get_authenticated_user(self):
        user = Client.query.get(self.username.data)
        if user is None:
            return None
        m = sha256()
        m.update(self.password.data.encode())
        passwd = m.hexdigest()
        return user if passwd == user.password else None

@app.route("/login/", methods =("GET","POST",))
def login():
    f = LoginForm()
    if not f.is_submitted():
        f.next.data = request.args.get("next")
    elif f.validate_on_submit():
        user = f.get_authenticated_user()
        if user:
            login_user(user)
            next = f.next.data or url_for("home")
            return redirect(next)
    return render_template("login.html",form=f)

@app.route("/login/", methods =("GET","POST",))
def register():
    f = LoginForm()
    if not f.is_submitted():
        f.next.data = request.args.get("next")
    elif f.validate_on_submit():
        user = f.get_authenticated_user()
        if user:
            login_user(user)
            next = f.next.data or url_for("home")
            return redirect(next)
    return render_template("login.html",form=f)

@app.route("/logout/")
def logout():
    logout_user()
    return redirect(url_for("home"))
