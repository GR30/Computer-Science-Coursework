class Card{
    var id:String = "";
    var description = "";
    
    init(id:String){
        self.id = id;
    }
    
    init(id:String, description:String){
        self.id = id
        self.description = description
    }
    
    func getId() -> String{
        return id;
    }
    
    func setId(id:String){
        self.id = id;
    }
    
    func getDescription()->String{
        return description
    }
}

class CommunityChestCard:Card{
    var cardColor = ""
    
    init(cardColor:String, cardId:String, cardDescription:String){
        self.cardColor = cardColor
        
        super.init(id: cardId, description: cardDescription)
    }
    
    
}

class PropertyCard:Card{
    var propertyName:String;
    var rent:Int;
    var rentOneHouse:Int;
    var rentTwoHouse:Int;
    var rentThreeHouse:Int;
    var rentFourHouse:Int;
    var value:Int;
    
    init(propertyName:String, rent:Int, rentOneHouse:Int, rentTwoHouse:Int, rentThreeHouse:Int, rentFourHouse:Int, value:Int){
        self.propertyName = propertyName;
        self.rent = rent;
        self.rentOneHouse = rentOneHouse;
        self.rentTwoHouse = rentTwoHouse;
        self.rentThreeHouse = rentThreeHouse;
        self.rentFourHouse = rentFourHouse;
        self.value = value;
        super.init(id: propertyName);
    }
    
    func getPropertyName() -> String{
        return propertyName;
    }
    
    //Add accessors and mutators
    
    func getPurchasePrice() -> Int{
        return value
    }
    
}

class Currency{
    var value:Int;
    var denomination:String;
    
    init(value:Int, denomination:String){
        self.denomination = denomination;
        self.value = value;
    }
    
    func getValue() -> Int{
        return value;
    }
    
    func getDenomination() -> String {
        return denomination;
    }
    
    func isCurrencyMatch(denom: String) -> Bool{
        return denomination == denom
    }
}

class StackOfCurrency{
    var stackCurrency = [Currency]();
    
    init(){
        
    }
    
    init(currency:Currency){
        stackCurrency.append(currency);
    }
    
    init(stackCurrency:[Currency]){
        self.stackCurrency = stackCurrency;
    }
    
    func getTotalAmount() -> Int{
        var total = 0;
        
        for item in stackCurrency{
            total += item.getValue();
        }
        
        return total;
    }
    
    func getSmallest() -> Currency{
        var bill = stackCurrency[0]
        
        for item in stackCurrency{
            if item.getValue() < bill.getValue(){
                bill = item
            }
        }
        
        return bill
    }
    
    func getCurrency(denomination:String) -> Currency{
        return stackCurrency.removeFirst()
    }
    
    func removeCurrency(currency:Currency) -> Currency?{
        if let index = stackCurrency.index(where: { $0.isCurrencyMatch(denom:currency.getDenomination())}){
            return stackCurrency.remove(at: index)
        }
        
        return nil
    }
    
    
    
    
    
    func getCurrency() -> Currency{
        return stackCurrency.removeFirst()
    }
    
    func addCurrency(currency:Currency){
        stackCurrency.append(currency)
    }
    
    func addStackCurrency(sc:[Currency]){
        stackCurrency.append(contentsOf: sc);
    }
    
    func getStackCurrency() -> [Currency]{
        return stackCurrency
    }
}




class Bank{
    var propertyCards = [String:PropertyCard]();
    //var trayOfCurrency = [Int:StackOfCurrency]();
    
    var trayOfMoney:[Int:StackOfCurrency] = [1:StackOfCurrency(), 5:StackOfCurrency()]
    
    init(trayOfMoney:[Int:StackOfCurrency]) {
        self.trayOfMoney = trayOfMoney;
    }
    
    func getTotalAmount() -> Int{
        //var total = trayOfCurrency[1]!.getTotalAmount() + trayOfCurrency[5]!.getTotalAmount();
        var total = 0;
        
        for (_, value) in trayOfMoney {
            //total += trayOfMoney[key]!.getTotalAmount();
            total += value.getTotalAmount();
        }
        
        return total;
    }
    
    func purchaseProperty(propertyName:String, money:StackOfCurrency) -> (StackOfCurrency?, PropertyCard?){
        //Assignment -- Write purchase property method
        //return property card and currency
        
        //Verify property exists
        if(propertyCards[propertyName] != nil){
            //verify the purchase amount
            if(propertyCards[propertyName]!.getPurchasePrice() <= money.getTotalAmount()){
                //get change
                
                let amountChange = money.getTotalAmount() - propertyCards[propertyName]!.getPurchasePrice()
                
                let change = self.makeChange(amount: amountChange, stackCurrency: StackOfCurrency())
                
                
                //place money in tray
                for cur in money.getStackCurrency(){
                    trayOfMoney[cur.getValue()]!.addCurrency(currency: cur)
                }
                
                //return change and property card
                return(change, propertyCards.removeValue(forKey: propertyName))
            }else{
                return (money,nil)
            }
        }else{
            return (money, nil)
        }
        
    }
    
    func makeChange(amount:Int, stackCurrency:StackOfCurrency) -> StackOfCurrency{
        if(amount == 0){
            return stackCurrency
        } else {
            var maxKey = 0;
            
            //iterate through the tray to find the max key that is less than or equal to the amount of money still owed
            for (key, _) in trayOfMoney{
                if(key <= amount && key > maxKey){
                    maxKey  = key
                }
            }
            
            // if(trayOfMoney[maxKey]!=nil){
            let cur = trayOfMoney[maxKey]!.getCurrency()
            stackCurrency.addCurrency(currency: cur)
            
            return makeChange(amount: amount-maxKey, stackCurrency: stackCurrency)
        }
    }
    
    func addPropertyCard(propertyName:String, card:PropertyCard){
        propertyCards[propertyName] = card;
    }
    
    func addPropertyCard(card:PropertyCard){
        propertyCards[card.getId()] = card
    }
    
    private func getProperty(propertyName:String) -> PropertyCard{
        return propertyCards.removeValue(forKey: propertyName)!;
    }
    
    func setTray(trayOfMoney:[Int:StackOfCurrency]){
        self.trayOfMoney = trayOfMoney;
    }
    
}

enum PropertyCategories{
    case Property
    case Tax
    case Chance
    case CommunityChest
    case Jail
    case Start
    case Misc
}

class Property{
    var name:String;
    var numHouses = 0
    var hotel = 0
    var category:PropertyCategories;
    
    init(name:String, category:PropertyCategories){
        self.name = name
        self.category = category
    }
    
    init(name:String, hotel:Int, numHouses:Int, category:PropertyCategories) {
        self.hotel = hotel;
        self.name = name;
        self.numHouses = numHouses;
        self.category = category;
    }
    
    func getName() -> String {
        return name;
    }
    
    func addHouse() -> Bool {
        if (numHouses<4){
            numHouses = numHouses + 1;
            return true;
        }
        
        return false;
    }
    
    func removeHouse() -> Bool {
        if(numHouses>0){
            numHouses = numHouses - 1;
            return true;
        }
        
        return false;
    }
    
    func addHotel() -> Bool {
        if(hotel < 1){
            hotel = 1;
            return true;
        }
        
        return false;
    }
    
    
    func removeHotel() -> Bool {
        if(hotel > 0){
            hotel = 0;
            return true;
        }
        
        return false;
    }
    
    func getNumHouse() -> Int{
        return numHouses;
    }
    
    func getNumHotels() -> Int{
        return hotel;
    }
    
}

class Board{
    var start:Space
    
    init(){
        start = Space(property: Property(name: "Go", category:PropertyCategories.Start))
        start.setNext(space: start)
        self.createBoard()
    }
    
    private func createBoard(){
        self.appendSpace(space: Space(property: Property(name: "Baltic Ave", category:PropertyCategories.Property)))
    }
    
    private func appendSpace(space:Space){
        var currentSpace = start
        
        while currentSpace.getNext() !== start{
            currentSpace = currentSpace.getNext()!
        }
        
        space.setNext(space: start)
        currentSpace.setNext(space: space)
    }
    
    
    //move around board.... Assignment 6
    
    func moveAroundBoard(numOfSpaces:Int, playPiece:Pieces) -> Property{
        var currentSpace = start;
        var spacesMoved = 0;
        var newSpace:Space;

        while currentSpace.getNext() !== start{
            if (currentSpace.hasPeice(playPeice) && spacesMoved < numOfSpaces){
                currentSpace.getNext().addPeice(currentSpace.removePeice(playPeice))
                spacesMoved += 1
            } else if (spacesMoved == numOfSpaces) {
                newSpace = currentSpace
            }
            currentSpace = currentSpace.getNext()!

        }
         
        //move designated number of spaces and return the property you land on
        //Must only move players piece
        
        
        return newSpace.getProperty()
    }
    
}


enum Pieces{
    case Boot
    case Tophat
    
}

class Space{
    var pieces = [Pieces]()
    let property:Property
    var next:Space?
    
    init(property:Property){
        self.property = property
    }

    func getProperty() -> Property{
        return property
    }
    
    func getNext() -> Space?{
        return next
    }
    
    func setNext(space:Space){
        next = space
    }
    
    func addPiece(piece:Pieces){
        pieces.append(piece)
    }
    
    func removePiece(piece:Pieces)->Pieces?{
        if let index = pieces.index(where: {$0==piece}){
            return pieces.remove(at: index)
        }
        
        return nil
    }
    
    func hasPiece(piece:Pieces) -> Bool {
        return pieces.contains(piece)
    }
    
}



class Player{
    var name:String?
    var propertyOwn = [PropertyCard]()
    var stackOfMoney = StackOfCurrency()
    
    init(playerName:String){
        name = playerName
    }
    
    func addPropertyCard(propertyCard:PropertyCard){
        propertyOwn.append(propertyCard)
    }
    
    func removePropertyCard(propertyName:String)->PropertyCard?{
        for card in propertyOwn{
            if(card.getPropertyName() == propertyName){
                return card
            }
        }
        
        return nil
    }
    
    func addMoney(stack:StackOfCurrency){
        stackOfMoney.addStackCurrency(sc: stack.getStackCurrency())
    }
    
    func addMoney(money:Currency){
        stackOfMoney.addCurrency(currency: money)
    }
    
    func setStackMoney(stackOfMoney:StackOfCurrency){
        self.stackOfMoney = stackOfMoney
    }
    
    func getStackMoney() -> StackOfCurrency{
        return stackOfMoney
    }
    
    func getChangeBack(stackOfChange:StackOfCurrency){
        self.addMoney(stack:stackOfChange)
    }
    
    func rollDice() -> Int{
        return 0
    }
    
    
    
    func payRent(rentAmount:Int) -> StackOfCurrency?{
        if(rentAmount <= stackOfMoney.getTotalAmount()){
            let stack_money = self.makePayment(totalAmountOwed: rentAmount)
            
            return stack_money
        } else {
            return nil
        }
    }
    
    func buyProperty(purchaseAmount:Int) -> StackOfCurrency?{
        if(purchaseAmount <= stackOfMoney.getTotalAmount()){
            //handle logic
            
            let stack_money = self.makePayment(totalAmountOwed: purchaseAmount)
            
            return stack_money
        } else {
            return nil
        }
    }
    
    func payFine(fineAmount:Int) -> StackOfCurrency?{
        if(fineAmount <= stackOfMoney.getTotalAmount()){
            let stack_money = self.makePayment(totalAmountOwed: fineAmount)
            
            return stack_money
        } else {
            return nil
        }
    }
    
    
    func makePayment(totalAmountOwed:Int) -> StackOfCurrency {
        //return smallest stack of currency to pay for amountOwed
        return makePayment(totalAmountOwed: totalAmountOwed, payment: StackOfCurrency())
    }
    
    private func makePayment(totalAmountOwed:Int, payment:StackOfCurrency) -> StackOfCurrency{
        
        if(totalAmountOwed <= 0){
            return payment
        } else {
            var bestFit = stackOfMoney.getSmallest()
            
            for bill in stackOfMoney.getStackCurrency(){
                if bill.getValue() > bestFit.getValue() && bill.getValue() <= totalAmountOwed {
                    bestFit = bill
                }
            }
            
            payment.addCurrency(currency: bestFit)
            //Remove the bill from the players stackOfMoney
            stackOfMoney.removeCurrency(currency: bestFit)
            let total = totalAmountOwed - bestFit.getValue()
            
            return makePayment(totalAmountOwed: total, payment: payment)
        }
        
    }
    
}




class GameRunner{
    
    //var bank:Bank?;
    
    init(){
        self.loadCurrency();
        // bank = Bank(trayOfMoney: self.loadCurrency())
        //self.createPropertyCards();
        
    }
    
    
    func loadCurrency() -> [Int:StackOfCurrency]{
        var tray = [Int:StackOfCurrency]()
        
        tray[10] = StackOfCurrency()
        
        for _ in 1...10{
            tray[10]!.addCurrency(currency: Currency(value: 10, denomination: "ten"))
        }
        tray[1] = StackOfCurrency(stackCurrency: Array(repeating: Currency(value: 1, denomination: "One"), count: 10))
        tray[5] = StackOfCurrency(stackCurrency: Array(repeating: Currency(value: 5, denomination: "Five"), count: 10))
        //   tray[10] = StackOfCurrency(stackCurrency: Array(repeating: Currency(value: 10, denomination: "Ten"), count: 10))
        tray[20] = StackOfCurrency(stackCurrency: Array(repeating: Currency(value: 20, denomination: "Twenty"), count: 10))
        
        //bank.setTray(trayOfMoney: tray)
        
        return tray;
    }
    
    func createPropertyCards(bank: Bank) -> Bank{
        bank.addPropertyCard(propertyName: "Boardwalk", card: PropertyCard(propertyName: "Boardwalk", rent: 10, rentOneHouse: 20, rentTwoHouse: 30, rentThreeHouse: 40, rentFourHouse: 50, value: 25))
        
        return bank;
        
    }
    
}


let game = GameRunner();
//game.getBank()!.propertyCards

let thebank = Bank(trayOfMoney: game.loadCurrency());
game.createPropertyCards(bank: thebank)

thebank.propertyCards
thebank.trayOfMoney
thebank.getTotalAmount()

var myCash = StackOfCurrency()
myCash.addCurrency(currency: Currency(value: 10, denomination: "ten"))
myCash.addCurrency(currency: Currency(value: 10, denomination: "ten"))
myCash.addCurrency(currency: Currency(value: 10, denomination: "ten"))
myCash.addCurrency(currency: Currency(value: 10, denomination: "ten"))

myCash.getTotalAmount()
var cash:StackOfCurrency?;
var property:PropertyCard?;
//game.getBank()?.purchaseProperty(propertyName: "Boardwalk", money: myCash)


let p = Player(playerName: "Player One")
p.addMoney(stack: myCash)
var curr = p.makePayment(totalAmountOwed: 25)
curr.getTotalAmount()

(cash, property) = thebank.purchaseProperty(propertyName: "Boardwalk", money: curr);

cash?.getTotalAmount()
property?.getPropertyName()

p.addMoney(stack: cash!)
p.getStackMoney().getTotalAmount()

curr = p.makePayment(totalAmountOwed: 9)
curr.getTotalAmount()


let b = Board()

b.start.getNext()?.getNext()

























