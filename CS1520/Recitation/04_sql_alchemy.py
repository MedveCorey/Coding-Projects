from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.sql.expression import func
from sqlalchemy.orm import DeclarativeBase, mapped_column
from sqlalchemy import Integer, String, Column, ForeignKey


class Base(DeclarativeBase):
    pass


app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///test.db'

db = SQLAlchemy(model_class=Base)
db.init_app(app)


'''
Consider the following schema:
	Forest(forest_no, forest_name, area)
	State(state_name, area)
	Coverage(entry_no, forest_no, state_name, area)

Notice how a forest can span two states
'''


'''
(1) create the tables/models, make sure you set the primary and 
    foreign keys. Look at the '04_db.txt' file to find out
    what the types of each column should be. I only used either
    an integer and a string
'''
class Forest(db.Model):
	__tablename__ = 'forest'
	forest_no = mapped_column(Integer, primary_key = True)
	forest_name = mapped_column(String(100))
	area = mapped_column(Integer)

	def __init__(self, forest_no, forest_name, area):
		self.forest_no = forest_no
		self.forest_name = forest_name
		self.area = area


class State(db.Model):
	__tablename__ = 'state'
	state_name = mapped_column(String(100), primary_key=True)
	area = mapped_column(Integer)

	def __init__(self, state_name, area):
		self.state_name = state_name
		self.area = area

class Coverage(db.Model):
	__tablename__ = 'coverage'
	entry_no = mapped_column(Integer, primary_key=True)
	forest_no = mapped_column(Integer, ForeignKey('forest.forest_no'))
	state_name = mapped_column(String(100), ForeignKey('state.state_name'))
	area = mapped_column(Integer)

	def __init__(self, entry_no, forest_no, state_name, area):
		self.entry_no = entry_no
		self.forest_no = forest_no
		self.state_name = state_name
		self.area = area

'''
(2) populate the tables you created above, you can find the data for 
	the tables in the '04_db.txt' file. The delimiter for an entry/record 
	is ',' and for the tables it is an empty line ('\n'). Remember to 
	drop all any previosuly created tables to avoid any problems
'''
@app.cli.command('initdb')
def initdb_command():
	db.drop_all()
	db.create_all()

	with open('04_db.txt', 'r') as file:
		lines = file.readlines()
	forests = []
	states = []
	coverage = []

	for line in lines:
		values = line.split(',')
		if len(values) == 3:
			forests.append(int(values[0]))
			forests.append(values[1])
			forests.append(int(values[2]))
		elif len(values) == 2:
			states.append(values[0])
			states.append(int(values[1]))
		elif len(values) == 4:
			coverage.append(int(values[0]))
			coverage.append(int(values[1]))
			coverage.append(values[2])
			coverage.append(int(values[3]))

	# Populate Forests
	db.session.add(Forest(1,'Allegheny National Forest',3500))
	db.session.add(Forest(2,'Pennsylvania Forest',2700))
	db.session.add(Forest(3,'Stone Valley',5000))	
	db.session.add(Forest(4,'Big Woods',3000))
	db.session.add(Forest(5,'Crooked Forest',2400))


    # Populate States
	db.session.add(State('PA',50000))
	db.session.add(State('OH',45000))
	db.session.add(State('VA',35000))

    # Populate Coverage
	db.session.add(Coverage(1,1,'OH',3500))
	db.session.add(Coverage(2,2,'OH',2700))
	db.session.add(Coverage(3,3,'OH',1500))
	db.session.add(Coverage(4,3,'PA',2100))
	db.session.add(Coverage(5,3,'VA',1400))
	db.session.add(Coverage(6,4,'PA',1200))
	db.session.add(Coverage(7,4,'VA',1800))
	db.session.add(Coverage(8,5,'VA',2400))

	db.session.commit()
	print("Initialized the database.")

@app.cli.command('check')
def check_command():

	'''
	(3) find and print the forest name(s) with the largest area (hint: use the func.max)
	Refer to https://docs.sqlalchemy.org/en/20/core/functions.html#selected-known-functions
	'''
	largest_area = db.session.query(func.max(Forest.area)).scalar()
	forests_with_largest_area = Forest.query.filter_by(area = largest_area).all()
	for forest in forests_with_largest_area:
		print(f"The forest with the largest area is: {forest.forest_name}")

	'''
	(4) find and print names of all forests that are located in PA (hint: might have to join 2 tables)
	'''
	forests_in_pa = db.session.query(Forest).join(Coverage).filter(Coverage.state_name=='PA').all()
	for forest in forests_in_pa:
		print(f"Forest located in VA: {forest.forest_name}")

	'''
	(5) find and print the number of forests for each state in descending order (hint: use func.count)
	'''
	forest_count_by_state = db.session.query(Coverage.state_name, func.count(Coverage.forest_no)).group_by(Coverage.state_name).order_by(func.count(Coverage.forest_no).desc()).all()
	for state, count in forest_count_by_state:
		print(f"State: {state}, Number of Forests: {count}")

	'''
	(6) find and print the percentage of area covered by forests in all states (hint: use func.sum)
	'''
	total_area_covered = db.session.query(func.sum(Coverage.area)).scalar()
	total_area_states = db.session.query(func.sum(State.area)).scalar()
	percentage_covered = (total_area_covered / total_area_states) * 100
	print(f"Percentage of area covered by forests in all states: {percentage_covered}%")
