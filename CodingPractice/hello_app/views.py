import os
from flask import Flask, render_template, send_from_directory
from datetime import datetime
from flask_sqlalchemy import SQLAlchemy
from . import app

@app.route("/")
def home():
    return render_template("home.html")

@app.route("/about/")
def about():
    return render_template("about.html")

@app.route("/contact/")
def contact():
    return render_template("contact.html")

@app.route("/hello/")
@app.route("/hello/<name>")
def hello_there(name = None):
    return render_template(
        "hello_there.html",
        name=name,
        date=datetime.now()
    )

@app.route("/api/data")
def get_data():
    return app.send_static_file("data.json")

@app.route("/blog/")
def blog():
    return render_template("blog.html")

@app.route("/games/guessthenumber")
def guessthenumber():
    return render_template("guessTheNumber")

@app.route("/BlogPosts/<filename>")
def getBlogPost(filename = None):
    return render_template(f"BlogPosts/{filename}")
if __name__ == '__main__':
    app.run(debug=True)
