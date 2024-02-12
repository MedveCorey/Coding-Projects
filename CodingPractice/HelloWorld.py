import random

# basic syntax overview
r = random.randint(0,100)
while r < 85:
    if r > 70:
        print(r, ":so close!", sep="")
    elif r > 45:
        print(r, end="")
        print(": Getting there...")
    else:
        print(f"{r}: Still so far away!")
    r = random.randint(0, 100)
print("OUT!")

#string slicing
s = "slicing is fun!!"

print(s[0])
print(s[2:7])
print(s[-5])
print(s[-5:-2])
print(s[11:])
print(s[:7])
print(s[-5:])

#functions
def say_hi():
    print("Hi")
def shout(message = "Hi"):
    print(message, "!", sep="")

print()
shout()
shout("I love python")
shout(message="And keyword arguments")

def quacker(an_animal):
    an_animal.quack()

#Tuples
t = ("CS", 1520, "Farnan")
#t[2] = "Garrison" #ERROR
s = "CS", 1520, "Farnan"
print(t==s) #TRUE

a,b,c = t
a == "CS"
b == 1520
c == "Farnan"

def mult_ret():
    return "one", "two", "three"
a,b,c = mult_ret()

#Lists
#Mutable sequences
l = [1, 2, 5]
l[0] = 0
l.append(3)

if 3 in l:
    print(3, "is in", l)
#Dictionaries
d = {"Farnan":1520, "Garrison":8}
d["Ramirez"] = 1501
d["Garrison"] = "0008"

"0008" in d 
"Garrison" in d
#Sets unordered collections with no duplicate elements
s = {1, 2, 2, 3, 3, 3}
print(s)

#Looping
crazy_list = ["a", 1, 1.0, "d"]
for item in crazy_list:
    print(item)
for i in range(10):
    print(i)
for i in range(-10, 10):
    print(i)
for i in range (-1, -10, -3):
    print(i)
crazy_dict = {1:"one", 2:"two", 3:"three"}

for k in crazy_dict:
    print(k)
#list comprehension
squares = [x**2 for x in range(10)]
print(squares)

cubes = [x**3 for x in range(11)]
print(cubes)

names = ["NICK", "FARNAN"]
low = [n.lower() for n in names if n == "NICK"]

#iterators
#__next__()
#iter()
#Exceptions and try statements

try:
    result = x // y
except ZeroDivisionError:
    print("division by zero!")
else:
    print("result is", result)
finally:
    print("executing finally clause")
try:
    raise Exception("foo", "bar")
except Exception as e:
    print(e)
    print(type(e))
    print()

#for loop
#this
crazy_list
for item in crazy_list:
    print()
#handy functions
len()
sorted()
min(), max()
int(), str(), bool(), float()
repr()
type()
help()