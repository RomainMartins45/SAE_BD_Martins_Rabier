from flask_wtf import FlaskForm
from wtforms import StringField , HiddenField
from wtforms . validators import DataRequired
from flask_login import UserMixin
from .app import *

class Client(db.Model):
    idClient = db.Column(db.Integer , primary_key=True)
    nomC = db.Column(db.String(42))
    prenomC = db.Column(db.String(42))
    poids = db.Column(db.Numeric(6,2))
    cotisation = db.Column(db.Boolean,default = False)

    def __repr__(self) -> str:
        return self.prenomC + " " + self.nomC + " " + str(self.idClient) 

class Poney(db.Model):
    idP = db.Column(db.Integer , primary_key=True)
    nomP = db.Column(db.String(42))
    poidsMax = db.Column(db.Numeric(6,2))

    def getPoids(self):
        return self.poidsMax
    
    def __repr__(self) -> str:
        return str(self.idP) + " " + self.nomP + " " + str(self.poidsMax)

class SearchForm(FlaskForm):
    poids = StringField("Poids",validators =[DataRequired()])

class Moniteur(db.Model):
    idM = db.Column(db.Integer , primary_key=True)
    nomM = db.Column(db.String(42))
    prenomM = db.Column(db.String(42))
    ageM = db.Column(db.Integer)
        
class TypeC(db.Model):
    idType = db.Column(db.Integer , primary_key=True)
    intituleType = db.Column(db.String(80))

class Cours(db.Model):
    idCours = db.Column(db.Integer , primary_key=True)
    type_cours = db.Column(db.String(42))
    prix = db.Column(db.Numeric(6,2))
    nbPersonnes = db.Column(db.Integer)
    idType = db.Column(db.Integer,db.ForeignKey("typec.idType"))
    idM = db.Column(db.Integer,db.ForeignKey("moniteur.idM"))
    duree = db.Column(db.Integer)
    jma = db.Column(db.DateTime)
    heure = db.Column(db.Integer)

    def get_id(self):
        return self.idCours

    def get_nb_pers(self):
        return self.nbPersonnes

class Reserver(db.Model):
    idCours = db.Column(db.Integer,db.ForeignKey("cours.idCours") , primary_key=True)
    idClient = db.Column(db.Integer,db.ForeignKey("client.idClient") , primary_key=True)
    idP = db.Column(db.Integer,db.ForeignKey("poney.idP") , primary_key=True)

@login_manager.user_loader
def load_user(idClient):
    return Client.query.filter(Client.idClient == idClient).first()

def get_poneys():
    poneys = Poney.query.all()
    return poneys
    
def get_poneys_poids(poids):
    poneys_tri = []
    poneys = Poney.query.all()
    for poney in poneys:
        if str(poney.getPoids()) >= poids:
            poneys_tri.append(poney)
    return poneys_tri

def get_nb_reserv(idCours):
    listeReserve = Reserver.query.filter(Reserver.idCours == idCours).all()
    return len(listeReserve)

def get_liste_cours_dispo():
    listeCoursDispo = list()
    listeCours = Cours.query.all()
    for cours in listeCours:
        if get_nb_reserv(cours.get_id()) < cours.get_nb_pers():
            listeCoursDispo.append(cours)
    return listeCoursDispo
