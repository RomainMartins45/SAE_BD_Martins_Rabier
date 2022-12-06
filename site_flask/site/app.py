from distutils.dir_util import mkpath
import os.path
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flaskext.mysql import MySQL

app = Flask(__name__)

mysql = MySQL()
username = 'martins'
password = 'martins'
database = 'DBmartins'
app.config['MYSQL_DATABASE_USER'] = username
app.config['MYSQL_DATABASE_PASSWORD'] = password
app.config['MYSQL_DATABASE_DB'] = database
app.config['MYSQL_DATABASE_HOST'] = 'localhost'
app.config['SQLALCHEMY_DATABASE_URI'] = ("mysql://" + username + ":" + password + "@servinfo-mariadb/" + database)
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
mysql.init_app(app)
conn = mysql.connect()
cursor = conn.cursor()
db = SQLAlchemy(app)
