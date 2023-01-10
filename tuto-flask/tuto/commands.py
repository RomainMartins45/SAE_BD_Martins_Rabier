import click
from .app import app, db
from .models import *

@app.cli.command()
def syncdb():
    """Creates all missing tables ."""
    db.create_all()
