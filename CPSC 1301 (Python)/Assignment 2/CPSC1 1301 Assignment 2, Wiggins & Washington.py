#Purpose: This program calculates the number of square feet per
#person for an island
#Authors: <Nathaniel Wiggins, Gerald Washington>
#Date: <September 11, 2014>


widthRectangleB  = int(input("1st Rectangle Width: "))
heightRectangleB = int(input("1st Rectanlge Height: "))

widthRectangleC  = int(input("2nd Rectangle Width: "))
heightRectangleC = int(input("2nd Rectanlge Height: "))

widthRectangleD  = int(input("3rd Rectangle Width: "))
heightRectangleD = int(input("3rd Rectanlge Height: "))

baseTriangleA   = int(input("1st Trianlge Base: "))
heightTriangleA = int(input("1st Trianlge Height: "))

baseTriangleE   = int(input("2nd Trianlge Base: "))
heightTriangleE = int(input("2nd Trianlge Height: "))

islandPopulation = int(input("Island Population: "))

areaRectangleB = widthRectangleB * heightRectangleB
areaRectangleC = widthRectangleC * heightRectangleC
areaRectangleD = widthRectangleD * heightRectangleD

areaTriangleA = (baseTriangleA * heightTriangleA) / 2
areaTriangleE = (baseTriangleE * heightTriangleE) / 2

areaInSquareMiles = int(areaRectangleB + areaRectangleC + areaRectangleD + areaTriangleA + areaTriangleE)
print("Area in Square Miles: " + str(areaInSquareMiles))

areaInSquareFeet = int(areaRectangleB + areaRectangleC + areaRectangleD + areaTriangleA + areaTriangleE) * (5280**2)
print("Area in Square Feet: " + str(areaInSquareFeet))

areaPerPerson = areaInSquareFeet / islandPopulation
print("Area Per Person (sq ft.): " + str(areaPerPerson))

import turtle

turtle.showturtle()
turtle.title("Map of Tuvaru")
turtle.color("black")

turtle.penup()
turtle.goto(150,155)
turtle.right(180)
turtle.pendown()
turtle.pencolor("green")
turtle.speed(3)
turtle.fd(widthRectangleB*20)
#V1 to V2

turtle.left(45)
turtle.fd((((heightTriangleA**2)+(baseTriangleA**2))**(1/2))*20)
#used a**2 + b**2 = C**2 math wise to solve for the length of the longest side
turtle.left(135)
turtle.fd(baseTriangleA*20)
# #V2 to V3
 
turtle.right(90)
turtle.fd(heightRectangleC*20)
turtle.right(90)
turtle.forward(widthRectangleC*10)
# #V4 to #V5

turtle.left(90)
turtle.fd(heightRectangleD*20)
turtle.left(90)
# #V6 to V7

turtle.fd(widthRectangleD*20)
# #V7 to V2

turtle.fd(baseTriangleE*20)
turtle.left(90)
# #V8 to V9

turtle.fd(heightTriangleE*20)
turtle.left(135)
# #V9 to V10
 
turtle.fd((((heightTriangleE**2)+(baseTriangleE**2))**(1/2))*20)
turtle.right(135)
# #V10 to V8

turtle.fd(heightRectangleD*20)
turtle.fd(heightRectangleC*20)
# #V8 to V11
 
turtle.right(90)
turtle.fd(heightRectangleB*20)
# #V11 to V12

turtle.left(90)
turtle.fd(heightRectangleB*20)
# #12 to V1

turtle.penup()
turtle.goto(-135,-160)
turtle.pendown()
turtle.pencolor("red")
turtle.write("Island of Tuvauru - 2090 AD - 3484.8 sq. ft. per person")

turtle.done()
