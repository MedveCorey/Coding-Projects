from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy import Column, ForeignKey, Integer, String, and_
from sqlalchemy.orm import relationship, declarative_base
from sqlalchemy.sql.expression import func



app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///test5.db'
Base=declarative_base()
db = SQLAlchemy(model_class=Base)
db.init_app(app)



'''
This exercise is very similar to last weeks exercise but we will focus
more on the many to many relationships among different tables (models). 
'''


'''
Consider the following schema:
	Forest(forest_no, forest_name, area)
	State(state_name, area)
	Coverage(entry_no, forest_no, state_name, area)
	Worker(ssn, name, employing_state)

A forest can span more than one state and a worker can work in multiple
forests (notice how both of these define a many to many relationship)
'''


'''
(1) create the tables/models, make sure you set the primary and 
    foreign keys. Look at the '05_db.txt' file to find out
    what the types of each column should be. I only used either
    an integer and a string
'''
class Forest(db.Model):
    __tablename__ = 'forest'
    forest_no = Column(Integer, primary_key=True)
    forest_name=Column(String)
    area= Column(Integer)
    
    def __init__(self, forest_no, forest_name, area):
        self.forest_no = forest_no
        self.forest_name = forest_name
        self.area = area

class State(db.Model):
    __tablename__ = 'state'
    state_name = Column(String, primary_key=True)
    area=Column(Integer)
    def __init__(self, state_name, area):
        self.state_name= state_name
        self.area = area

class Coverage(db.Model):
    __tablename__ = 'coverage'
    entry_no= Column(Integer, primary_key=True)
    forest_no= Column(Integer, ForeignKey('forest.forest_no'))
    state_name= Column(String, ForeignKey('state.state_name'))
    area= Column(Integer)
    forest= relationship("Forest")
    state= relationship("State")
    
    def __init__(self, entry_no, forest_no, state_name, area):
        self.entry_no = entry_no
        self.forest_no = forest_no
        self.state_name = state_name
        self.area = area
    
class Worker(db.Model):
    __tablename__ = 'worker'
    ssn= Column(Integer, primary_key=True)
    name=Column(String)
    employing_state= Column(String)
    
    def __init__(self, ssn, name, employing_state):
        self.ssn = ssn
        self.name = name
        self.employing_state = employing_state
    

'''
(2) populate the tables you created above, you can find the data for 
	the tables in the '05_db.txt' file. The delimiter for an entry/record 
	is ',' and for the tables it is an empty line ('\n'). Remeber to 
	drop all any previosuly created tables to avoid any problems
'''
@app.cli.command('initdb')
def populate_tables():
    db.drop_all()
    db.create_all()
    
    with open('05_db.txt', 'r') as file:
        lines = file.readlines()
        table_data = []
        for line in lines:
            line = line.strip()
            if line:
                table_data.append(line.split(','))
            else:
                if table_data:
                    table_name = table_data[0][0]
                    data = table_data[1:]
                    if table_name == 'Forest':
                        for entry in data:
                            db.session.add(Forest(forest_no=int(entry[0]), forest_name=entry[1], area=int(entry[2])))
                    elif table_name == 'State':
                        for entry in data:
                            db.session.add(State(state_name=entry[0], area=int(entry[1])))
                    elif table_name == 'Coverage':
                        for entry in data:
                            db.session.add(Coverage(entry_no=int(entry[0]), forest_no=int(entry[1]), state_name=entry[2], area=int(entry[3])))
                    elif table_name == 'Worker':
                        for entry in data:
                            db.session.add(Worker(ssn=int(entry[0]), name=entry[1], employing_state=entry[2]))
                    db.session.commit()
                    table_data = []
    print("Initialized DB")
    
'''
(3) As a warmup, find the name(s) of all workers that are employed by the largest state
'''
@app.cli.command('check')
def check_command():
    state_count = db.session.query(State).count()
    print("Total states:", state_count)
    states = db.session.query(State).all()
    for state in states:
        print(state.state_name, state.area)
    largest_state = db.session.query(State).order_by(State.area.desc()).first()
    if largest_state:
        workers = db.session.query(Worker).filter_by(employing_state=largest_state.state_name).all()
        for worker in workers:
            print(f"Workers employed by the largest state:{worker.name}")
    '''
	(4) Find the names of all workers that work in more than one forest
	'''           
    #workers = db.session.query(Worker).join(State, Worker.employing_state == State.state_name).all()

    #for worker in workers:
    #    print(worker.name)
    '''
	(5) Find the name(s) of the workers that work in the largest forest
	'''
    #largest_forest = db.session.query(Forest).order_by(Forest.area.desc()).first()
    #if largest_forest:
    #   workers = db.session.query(Worker).join(Coverage).filter_by(forest_no=largest_forest.forest_no).all()
    #   for worker in workers:
     #       print(worker.name)
    '''
	(6) Find the name(s) of the workers that manage the largest area of forests
	Remeber workers can work in multiple forests hence you might need to sum
	the areas of those forests
	'''
    #worker_areas = db.session.query(Worker.name, func.sum(Forest.area)).join(Coverage).join(Forest).group_by(Worker.ssn).order_by(func.sum(Forest.area).desc()).all()
    #largest_area = worker_areas[0][1]
    #for worker, area in worker_areas:
    #    if area == largest_area:
    #        print(worker)