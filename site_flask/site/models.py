from .app import db

class Poney(db.Model):
    id_p = db.Column(db.Integer , primary_key=True)
    nom_p = db.Column(db.String(100))
    poids_max = db.Column(db.Float)

    def __repr__(self) -> str:
        return super().__repr__()

class Moniteur(db.Model):
    id_m = db.Column(db.Integer , primary_key=True)
    nom_m = db.Column(db.String(100))
    prenom_m = db.Column(db.String(100))
    age_m = db.Column(db.Integer)

class Type_cours(db.Model):
    id = db.Column(db.Integer , primary_key=True)
    intitule_type = db.Column(db.String(100))

class Cours(db.Model):
    id_c = db.Column(db.Integer , primary_key=True)
    intitule_cours = db.Column(db.String(100))
    prix = db.Column(db.Float)
    nb_personnes = db.Column(db.Integer)
    type_cours = db.relationship('Type_cours', backref='cours')
    moniteur = db.relationship('Moniteur', backref='cours')
    duree = db.Column(db.Integer)
    heure = db.Column(db.Integer)
    date = db.Column(db.DateTime)

class Client(db.Model):
    id_client = db.Column(db.Integer , primary_key=True)
    nom_client = db.Column(db.String(100))
    prenom_client = db.Column(db.String(100))
    poids = db.Column(db.Float)
    cotisation = db.Column(db.Boolean)

class Reservation(db.Model):
    cours = db.relationship('Cours', backref='reservation',primary_key = True)
    client = db.relationship('Client', backref='reservation',primary_key = True)
    poney = db.relationship('Poney', backref='reservation',primary_key = True)

