from flask import Flask, request, abort, url_for, redirect, session

app = Flask(__name__)

users = {"alice": "qwert", "bob": "asdfg", "charlie": "zxcvb"}

loginPage = """<!DOCTYPE html>
<html>
    <head>
        <title>Basic form</title>
    </head>
    <body>
        <form action="" method="post">
            Username:  <input type="text" name="user" />
            <br />
            Password:  <input type="text" name="pass" />
            <br />
            <input type="submit" value="submit" />
        </form>
    </body>
</html>
"""

curProfile = """<!DOCTYPE html>
<html>
    <head>
        <title>Your profile!</title>
    </head>
    <body>
        Welcome back!
        <a href="{}">click here to logout</a>
    </body>
</html>
"""

otherProfile = """<!DOCTYPE html>
<html>
    <head>
        <title>{0}'s profile!</title>
    </head>
    <body>
        This is {0}'s profile page.
    </body>
</html>
"""

logoutPage = """<!DOCTYPE html>
<html>
    <head>
        <title>Logged out</title>
    </head>
    <body>
        You have successfully been logged out!
    </body>
</html>
"""

@app.route("/")
def default():
    return redirect(url_for("login_controller"))

@app.route("/login/", methods=["GET", "POST"])
def login_controller():
    if "username" in session:
        return redirect(url_for("profile", username=session["username"]))
    
    elif request.method == "POST":
        if(
            request.form["user"] in users
            and users[request.form["user"]] == request.form["pass"]
        ):
            session["username"] = request.form["user"]
            return redirect(url_for("profile", username=session["userna,e"]))
        abort(401)
    
    return loginPage

@app.route("/profile/")
@app.route("/profile/<username>")
def profile(username=None):
    if not username:

        if "username" in session:
            return redirect(url_for("profile", username=session["username"]))
        