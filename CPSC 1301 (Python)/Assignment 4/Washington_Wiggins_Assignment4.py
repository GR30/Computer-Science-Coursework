# Program name: Assignment 4.py
# Description: Turtles start from Asia and travel to America
# Avoiding a vertex
# Author: Nathaniel Wiggins & Gerald Washington
# Date: October 9, 2014


import turtle
import random
import math
#These 3 librarys are imported for Turtle
#Find Distance
#And use random functions

turtle.setup(800,800)
turtle.bgcolor("light blue")
turtle.title("Washington and Wiggins, NW & GW: Turtles and Trash")
#Title window, window resolution, and background color

turtle.hideturtle()
turtle.speed("fast")
turtle.up()
turtle.setposition(0,50)
turtle.pd()
turtle.left(180)
turtle.begin_fill()
turtle.circle(50)
#Circle centered at (0,0) starting the diameter at 

turtle.end_fill()
turtle.up()
#We have a separate turtle to draw the circle


Mario = turtle.Turtle('turtle')
Mario.color('red')
Mario.speed("normal")
Mario.hideturtle()
Luigi = turtle.Turtle('turtle')
Luigi.color('green')
Luigi.speed("normal")
Luigi.hideturtle()
#Mario and Luigi speed is set to normal/medium speed.

Mario.penup()
Luigi.penup()
Mario.setposition(-400,0)
Luigi.setposition(-400,0)
Mario.showturtle()
Luigi.showturtle()
#Mario and Luigi are the Turtles to start from Asia and make it to America

# Mario.goto(-400,0)
# Luigi.goto(-400,0)
# This is the starting screen setup.


Mario.pd()
Luigi.pd()
MarioAlive = True
LuigiAlive = True
Mario_Reach_America = False
Luigi_Reach_America = False
distance_Traveled_Mario = 0
distance_Traveled_Luigi = 0
#Mario and Luigi are set to Boolean expression to be evaluated through its process

while (MarioAlive or LuigiAlive) and not (Mario_Reach_America or Luigi_Reach_America):
   
      if round(math.sqrt(((Mario.xcor()-0)**2)+((Mario.ycor()-0)**2)))<=50:
         fd = 0
         turn = Mario.seth(0)
         MarioAlive = False
         #sets expression if Mario's distance is inside the circle 
      else:
         fd = random.randint(10,20)
         turn = Mario.seth(random.randint(-60,60))
         
      if round(math.sqrt(((Luigi.xcor()-0)**2)+((Luigi.ycor()-0)**2)))<=50:
         fd2 = 0
         turn2 = Luigi.seth(0)
         LuigiAlive = False
         #sets expression if Luigi's distance is inside the circle 
      else:
         fd2 = random.randint(10,20)
         turn2 = Luigi.seth(random.randint(-60,60))
         
      Mario.fd(fd)
      Luigi.fd(fd2)
      turn
      turn2
      distance_Traveled_Mario += fd
      distance_Traveled_Luigi += fd2
      
      if Mario.xcor() >= 380 and not LuigiAlive:
         Mario_Reach_America = True
         #Mario reaches America is true, even if Luigi died 
         
      elif Luigi.xcor() >= 380 and not MarioAlive:
         Luigi_Reach_America = True
         #Luigi reaches America is true, even if Mario died
         
      elif Luigi.xcor() >= 380 and Mario.xcor() >= 380:
         Mario_Reach_America = True
         Luigi_Reach_America = True 
         #Combination with and operator if Mario and Luigi reach America
        
      if MarioAlive == False and LuigiAlive == False:
         break
      elif Mario_Reach_America and Luigi_Reach_America == True:
         break
         #break statments are called to stop loop if they make it to America
         #Or they die in the whirlpool
      
turtle.goto(-160,-200)
turtle.write("Total distance covered by red turtle: \t\t" + str(distance_Traveled_Mario))
turtle.goto(turtle.xcor(),(turtle.ycor()-10))
turtle.write("Total distance covered by green turtle:\t\t" + str(distance_Traveled_Luigi))
turtle.goto(turtle.xcor(),(turtle.ycor()-10))
turtle.write("Final position of red turtle(estimated):\t\tX = " + str(round(Mario.xcor())) + ",\tY = " + str(round(Mario.ycor())))
turtle.goto(turtle.xcor(),(turtle.ycor()-10))
turtle.write("Final position of green turtle (estimated)):\tX = " + str(round(Luigi.xcor())) + ",\tY = " + str(round(Luigi.ycor())))
#The third turtle writes the messages

if Mario.xcor() >= 380 and not Luigi.xcor() >= 380:
   turtle.goto(turtle.xcor(),(turtle.ycor()-30))
   turtle.write("Green turtle died in the trash vortex.")
   turtle.goto(turtle.xcor(),(turtle.ycor()-10))
   turtle.write("Red turtle made it to America!")
elif Luigi.xcor() >= 380 and not Mario.xcor() >= 380:
   turtle.goto(turtle.xcor(),(turtle.ycor()-30))
   turtle.write("Red turtle died in the trash vortex.")
   turtle.goto(turtle.xcor(),(turtle.ycor()-10))
   turtle.write("Green turtle made it to America!")
elif Mario.xcor() >= 380 and Luigi.xcor() >= 380:
   turtle.goto(turtle.xcor(),(turtle.ycor()-20))
   turtle.write("Both turtles made it to America!")
else:
   turtle.goto(turtle.xcor(),(turtle.ycor()-20))
   turtle.write("Both turtles died...")
   
turtle.goto(turtle.xcor(),(turtle.ycor()-20))
turtle.write("CLICK TO EXIT...")
    
turtle.exitonclick()



