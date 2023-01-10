import sqlalchemy  
from .models import *
# pour avoir sqlalchemy :
# sudo apt-get update 
# sudo apt-get install python3-sqlalchemy
# pip3 install mysql-connector-python

def ouvrir_connexion(user,passwd,host,database):
    """
    ouverture d'une connexion MySQL
    paramètres:
       user     (str) le login MySQL de l'utilsateur
       passwd   (str) le mot de passe MySQL de l'utilisateur
       host     (str) le nom ou l'adresse IP de la machine hébergeant le serveur MySQL
       database (str) le nom de la base de données à utiliser
    résultat: l'objet qui gère le connection MySQL si tout s'est bien passé
    """
    try:
        #creation de l'objet gérant les interactions avec le serveur de BD
        engine=sqlalchemy.create_engine('mysql+mysqlconnector://'+user+':'+passwd+'@'+host+'/'+database)
        #creation de la connexion
        cnx = engine.connect()
    except Exception as err:
        print(err)
        raise err
    print("connexion réussie")
    return cnx

def listePoneys(connexion):
    resultat=connexion.execute("select * from Poney")
    liste = list()
    for idP,nomP,poids in resultat:
        liste.append(Poney(idP,nomP,poids))
    return liste

if __name__ == "__main__":
    # login=input("login MySQL ")
    # passwd=getpass.getpass("mot de passe MySQL ")
    # serveur=input("serveur MySQL ")
    # bd=input("nom de la base de données ")
    cnx=ouvrir_connexion("martins","martins","servinfo-mariadb","DBmartins")
    # ici l'appel des procédures et fonctions
    print(listePoneys(cnx))
