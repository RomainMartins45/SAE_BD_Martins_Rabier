from flask_wtf import FlaskForm
from wtforms import StringField , HiddenField
from wtforms . validators import DataRequired
from flask_login import UserMixin
from .app import *
from .connexionPythonSQL import *

class Client:
    def __init__(self,id,nom,prenom,poids,cotisation) -> None:
        self.id = id
        self.nom = nom
        self.prenom = prenom
        self.poids = poids
        self.cotisation = cotisation

class Poney:
    def __init__(self,id,nom,poids_max) -> None:
        self.id_p = id
        self.nom_p = nom
        self.poids_max = poids_max
    
    def __repr__(self) -> str:
        return str(self.id_p) + " " + self.nom_p + " " + str(self.poids_max)

class Moniteur:
    def __init__(self,id,nom,prenom,age) -> None:
        self.id = id
        self.nom = nom
        self.prenom = prenom
        self.age = age
        
class Type_cours:
    def __init__(self,id,intitule) -> None:
        self.id = id
        self.intitule = intitule

class Cours:
    def __init__(self,idC,intituleCours,prix,nbPersonnes,typeC,moniteur,duree,heure,date) -> None: 
        self.idC = idC
        self.intituleCours = intituleCours
        self.prix = prix
        self.nbPersonnes = nbPersonnes
        self.typeC = typeC
        self.moniteur = moniteur
        self.duree = duree
        self.heure = heure
        self.date = date

class Reservation:
    def __init__(self,cours,poney,client) -> None:
        self.cours = cours
        self.poney = poney
        self.client = client
        
def ListePoneys(connexion):
    resultat=connexion.execute("select * from Poney")
    liste = list()
    for idP,nomP,poids in resultat:
        liste.append(Poney(idP,nomP,poids))
    return liste
