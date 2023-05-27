//: # Introduction to Object Oriented Programming and Swift
//:## CPSC 3175 - Spring 2017

/*:
 
 ## Six Concepts of Object Oriented Design [1]

 
 1. Everything is an *object*.
 2. Objects communicate by sending and recieving *messages* which allows computation to be performed.
 3. Each object has it's own *memory* which consists of other objects.
 4. Each object is an *instance* of a class. A *class* is made of a grouping of similar objects such as integers or lists.
 5. The class is a repository for *behaviors* associated with an object. This means all instances of objects of the same class behave the same.
 6. Class are organized into a single rooted tree structure, called the *inheritance heirachy*. Memory and behavior associated with instances of a class are automatically available to any class associated with a *descendant* in the tree structure.
 
  [1] Budd, Timothy (2002), *An Introduction to Object-Oriented Programming*, 3rd Edition, Addison-Wesley Publishing
 
*/

/*:
## Introduction to Swift
### To create a constant, use keyword 'let'

 */
 let x:Int = 3;
 let y:String = "Hello Class";

//:### To create a variable, us keyword 'var'
 var example:Int = 5;
 example = x;

//:### Example from Class -> Clothing Class... Int Size, String Material
 
 class Clothing{
 
    let size:Int = 10;
    let material:String = "cloth";
 
    // function without return statement
    func getSize() {
        print(size);
    }
 
    // function that returns data, -> tells data type to return
    func getMaterial() -> String {
        return material;
    }
 
 }
 
//:### Shirt inherits from clothing and requires a length and color to be instantied

class Shirt:Clothing{
    var sleeveLength:Int;
    var color:String;
 
    //Example of initializing a function with parameters
    init(newLength:Int, newColor:String){
        sleeveLength=newLength;
        color = newColor;
    }
 
    func getLength() -> Int {
        return sleeveLength;
    }
 }



 class Pants:Clothing{
    var inseam:Int;
 
    init(newInseam:Int){
        inseam=newInseam;
    }
 
    func getInseam() -> Int {
        return inseam;
    }
 }




class Closet{
    var item:Clothing;
    
    init(newItem:Clothing){
        item=newItem;
    }
    
    func getItem() -> Clothing {
        return item;
    }
}

//:### Instantiate clothing object
let s = Shirt(newLength:5, newColor:"Yellow");
let p = Pants(newInseam:32);

let c = Closet(newItem: s);

//Typecast our Clothing item to a Shirt. Note: This is only done as an example of typecasting. We will further discuss polymorphism and typecasting in the future.
var i:Shirt = c.getItem() as! Shirt;

s.getSize();
print(s.getMaterial());
print(s.getLength());
print(p.getInseam());

print(i.getLength());
