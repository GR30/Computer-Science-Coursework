# Program Name: Washington_II_Wiggins_Assignment3.py
# Program Description: Drunk Turtle randomly moving
# Author Name: Gerald A Washington II, Nathaniel D Wiggins
# Date: September 26, 2014

import turtle
import random
import math

totalDistance = 0
turtle.screensize(1000,1000)
#Allows scrolling if turtle goes off screen

turtle.ht()
turtle.speed("slowest")
#Allows turtle to move at a distance
turtle.shape("turtle")
turtle.color("green")
#Shapes turtle and allows turtle to look like turtle

turtle.pencolor("black")
turtle.penup()
turtle.goto(-40,-35)
turtle.right(45)
turtle.pendown()
turtle.circle(50, steps = 4)
#This draws the cage the turtle gets out of

turtle.penup()
turtle.goto(0,0)
turtle.pencolor("blue")

x1 = turtle.xcor()
y1 = turtle.ycor()

turtle.st()
turtle.speed("slow")
turtle.pendown()
turtle.left(45)

for i in range (1,10):
   number = random.randint(1,4)
   distance = random.randint(50,100)
   totalDistance = totalDistance + distance
   if number == 1:
      heading = 0
   elif number == 2:
      heading = 90
   elif number == 3:
      heading = 180
   elif number == 4:
      heading = 270
   turtle.fd(distance)
   turtle.right(heading)
   x2 = int(turtle.xcor())
   y2 = int(turtle.ycor())
distnceFromStart = round(math.sqrt(((x2-x1)**2)+((y2-y1)**2)))
#Distance Formula for calulating distance
writing = "Distance Traveled: " + str(totalDistance) + "\nFinal poisition: x = " + str(x2) + " y = " + str(y2) + "\nDistance from Starting Point: " + str(distnceFromStart)
turtle.stamp()
turtle.pencolor("red")
turtle.write(writing)
turtle.done()
print ("Distance Traveled:", totalDistance, "\nFinal poisition: x =", x2, ", y =", y2, "\nDistance from Starting Point:", distnceFromStart)