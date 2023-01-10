from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_login import LoginManager
import os.path

app = Flask(__name__)
# login_manager = LoginManager(app)
# login_manager.login_view = "login"

def mkpath(p):
    return os.path.normpath(
    os.path.join(
    os.path.dirname(__file__),
    p))

app.config['SQLALCHEMY_DATABASE_URI'] = ("mysql+pymysql://martins:martins@servinfo-mariadb/DBmartins")
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)
    