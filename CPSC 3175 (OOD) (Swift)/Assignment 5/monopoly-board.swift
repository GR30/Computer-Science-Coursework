//: Playground - noun: a place where people can play

import Darwin

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
    
    init(){
        
    }
    
    init(trayOfMoney:[Int:StackOfCurrency]) {
        self.trayOfMoney = trayOfMoney;
    }
    
    func getCurrency(value:Int) -> Currency{
        return trayOfMoney[value]!.getCurrency()
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
        self.appendSpace(space: Space(property: Property(name: "Mediteranean Ave", category: PropertyCategories.Property)))
        self.appendSpace(space: Space(property: Property(name: "Community Chest", category: PropertyCategories.CommunityChest)))
        self.appendSpace(space: Space(property: Property(name: "Baltic Ave", category:PropertyCategories.Property)))
        self.appendSpace(space: Space(property: Property(name: "Income Tax", category: PropertyCategories.Tax)))
        self.appendSpace(space: Space(property: Property(name: "Reading Railroad", category: PropertyCategories.Property)))
        self.appendSpace(space: Space(property: Property(name: "Oriental Ave", category: PropertyCategories.Property)))
        self.appendSpace(space: Space(property: Property(name: "Chance", category: PropertyCategories.Chance)))
        self.appendSpace(space: Space(property: Property(name: "Vermont Ave", category: PropertyCategories.Property)))
        self.appendSpace(space: Space(property: Property(name: "Connecticut Ave", category: PropertyCategories.Property)))
        self.appendSpace(space: Space(property: Property(name: "Park Place", category:PropertyCategories.Property)))
        self.appendSpace(space: Space(property: Property(name: "Boardwalk", category: PropertyCategories.Property)))
    }
    
    private func appendSpace(space:Space){
        var currentSpace = start
        
        while currentSpace.getNext() !== start{
            currentSpace = currentSpace.getNext()!
        }
        
        space.setNext(space: start)
        currentSpace.setNext(space: space)
    }

    func addPiece(newPiece:Pieces){
        start.addPiece(piece: newPiece)
    }
    
    //move around board.... Assignment 6
    
    func moveAroundBoard(numOfSpaces:Int, playPiece:Pieces) -> Property{
        //move designated number of spaces and return the property you land on
        //Must only move players piece
        
        var currentSpace = start
        
        while(!currentSpace.hasPiece(piece: playPiece)){
            currentSpace = currentSpace.getNext()!
        }
        
        for _ in 1 ... numOfSpaces{
            currentSpace.getNext()!.addPiece(piece: currentSpace.removePiece(piece: playPiece)!)
            currentSpace = currentSpace.getNext()!
        }
        
        return currentSpace.getProperty()
    }
    
    
    func printBoard(){
        
        var currentSpace = start
        var totalSpaces = 1
        var listPropertyNames = [String]()
        var longestName =  0
        
        while currentSpace.getNext() !== start{
            totalSpaces += 1
            
            if currentSpace.getProperty().getName().characters.count > longestName{
                longestName = currentSpace.getProperty().getName().characters.count
            }
            
            listPropertyNames.append(currentSpace.getProperty().getName())
            
            currentSpace = currentSpace.getNext()!
        }
        listPropertyNames.append(currentSpace.getProperty().getName())
        
        
        totalSpaces
        
        var tempString = ""
        
        //print top line
        
        for _ in (0...(totalSpaces/4)) {
            tempString += printSpace(name: listPropertyNames.removeFirst(), totalLen: longestName+2)
        }
        
        print(tempString)
        tempString = ""
        // print edge
        
        for _ in (0...1){
            tempString += printSpace(name: listPropertyNames.removeLast(), totalLen: longestName+2)
            
            for _ in (0...1){
                tempString += printSpace(name: "", totalLen: longestName+2)
            }
            
            tempString += printSpace(name: listPropertyNames.removeFirst(), totalLen: longestName+2)
            print(tempString)
            
            tempString = ""
        }
        
        // print bottom
        
        for _ in (0...(totalSpaces/4)){
            tempString += printSpace(name: listPropertyNames.removeLast(), totalLen: longestName+2)
        }
        
        print(tempString)
        
    }
    
    func printSpace(name:String, totalLen:Int) -> String{
        let nameLen = name.characters.count
        var returnString = "|" + name
        for _ in (0...totalLen-nameLen){
            returnString += " "
        }
        
        return returnString + "|"
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
    
    func getProperty() -> Property{
        return property
    }
    
}



class Player{
    var name:String?
    var propertyOwn = [PropertyCard]()
    var stackOfMoney = StackOfCurrency()
    let piece:Pieces
    
    init(playerName:String, piece:Pieces){
        name = playerName
        self.piece=piece
    }
    
    func getPiece() -> Pieces{
        return piece
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
    
    func rollDice() -> (Int, Int){
        return (Int(arc4random_uniform(6)+1), Int(arc4random_uniform(6)+1))
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
    
    
    //Assignment 5 -- complete makePayment function
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


class GroupOfPlayers{
    var firstSeat:Seat
    
    init(player:Player){
        firstSeat = Seat(player: player)
        firstSeat.setNext(seat: firstSeat)
    }
    
    func addPlayer(player:Player){
        var seat = firstSeat
        while seat.getNext() !== firstSeat{
            seat = seat.getNext()!
        }
        seat.setNext(seat: Seat(player: player))
        seat = seat.getNext()!
        seat.setNext(seat: firstSeat)
    }
}


class Seat{
    var next:Seat?
    var player:Player
    
    init(player:Player){
        self.player = player
    }
    
    func setNext(seat:Seat){
        next = seat
    }
    
    func getNext() -> Seat?{
        return next
    }
    
    func getPlayer() -> Player{
        return player
    }
}


class GameRunner{
    
    var theBank:Bank
    var gameBoard:Board
    var groupOfPlayers:GroupOfPlayers?
    
    init(){
        theBank = Bank()
        gameBoard = Board()
        theBank.setTray(trayOfMoney: self.loadCurrency())
        self.createPropertyCards()
        
        //Prompt the user for number of players
        //Ask for player Names and Pieces
        
        print("Enter number of Players:")
        
        let numOfPlayers = Int(readLine()!)
        
        for _ in (1...numOfPlayers!){
            print("Enter player name")
            let playerName = readLine()
            print("Enter play piece")
            let playerPiece = readLine()
            
            print(playerName! + " " + playerPiece!)
        }
        
    
    }
    
    private func addPlayers(name:String, piece:String){
        
        if piece == "Boot"{
            let p = Player(playerName: name, piece: Pieces.Boot)
            
            p.addMoney(stack: givePlayerMoney())
            groupOfPlayers = GroupOfPlayers(player: p)

            
        }else if piece == "Tophat"{
            let p = Player(playerName: name, piece: Pieces.Tophat)
            
            p.setStackMoney(stackOfMoney: givePlayerMoney())
            groupOfPlayers = GroupOfPlayers(player: p)
        }
        
        

    }
    
    private func loadCurrency() -> [Int:StackOfCurrency]{
        var tray = [Int:StackOfCurrency]()
        
        tray[10] = StackOfCurrency()
        
        for _ in 1...10{
            tray[10]!.addCurrency(currency: Currency(value: 10, denomination: "ten"))
        }
        tray[1] = StackOfCurrency(stackCurrency: Array(repeating: Currency(value: 1, denomination: "One"), count: 100))
        tray[5] = StackOfCurrency(stackCurrency: Array(repeating: Currency(value: 5, denomination: "Five"), count: 75))
        tray[10] = StackOfCurrency(stackCurrency: Array(repeating: Currency(value: 10, denomination: "Ten"), count: 75))
        tray[20] = StackOfCurrency(stackCurrency: Array(repeating: Currency(value: 20, denomination: "Twenty"), count: 50))
        tray[100] = StackOfCurrency(stackCurrency: Array(repeating: Currency(value: 100, denomination: "One Hundred"), count: 50))
        
        //bank.setTray(trayOfMoney: tray)
        return tray;
    }
    
    private func createPropertyCards(){
        theBank.addPropertyCard(propertyName: "Boardwalk", card: PropertyCard(propertyName: "Boardwalk", rent: 100, rentOneHouse: 200, rentTwoHouse: 300, rentThreeHouse: 400, rentFourHouse: 500, value: 250))
        theBank.addPropertyCard(propertyName: "Mediteranean Ave", card: PropertyCard(propertyName: "Mediteranean Ave", rent: 10, rentOneHouse: 20, rentTwoHouse: 30, rentThreeHouse: 40, rentFourHouse: 50, value: 25))
        theBank.addPropertyCard(propertyName: "Baltic Ave", card: PropertyCard(propertyName: "Baltic Ave", rent: 15, rentOneHouse: 25, rentTwoHouse: 35, rentThreeHouse: 45, rentFourHouse: 55, value: 30))
        theBank.addPropertyCard(propertyName: "Oriental Ave", card: PropertyCard(propertyName: "Oriental Ave", rent: 45, rentOneHouse: 50, rentTwoHouse: 60, rentThreeHouse: 70, rentFourHouse: 80, value: 45))
        theBank.addPropertyCard(propertyName: "Conneticut Ave", card: PropertyCard(propertyName: "Connecticut Ave", rent: 50, rentOneHouse: 60, rentTwoHouse: 70, rentThreeHouse: 80, rentFourHouse: 90, value: 50))
        theBank.addPropertyCard(propertyName: "Vermont Ave", card: PropertyCard(propertyName: "Vermont Ave", rent: 70, rentOneHouse: 100, rentTwoHouse: 130, rentThreeHouse: 140, rentFourHouse: 150, value: 75))
        theBank.addPropertyCard(propertyName: "Park Place", card: PropertyCard(propertyName: "Park Place", rent: 90, rentOneHouse: 120, rentTwoHouse: 230, rentThreeHouse: 340, rentFourHouse: 450, value: 200))
    }
    
    private func givePlayerMoney() -> StackOfCurrency{
        let stackOfMoney = StackOfCurrency()
        
        let bills = ["One":1,"Five":5, "Ten":10,"Fifty":50,"One Hundred":100]
        
        
        for (_, value) in bills{
            for _ in 1...10{
                stackOfMoney.addCurrency(currency: theBank.getCurrency(value: value))
            }
        }
        return stackOfMoney
    }
    
    
}


let game = GameRunner();
////game.getBank()!.propertyCards
//
//let thebank = Bank(trayOfMoney: game.loadCurrency());
//game.createPropertyCards(bank: thebank)
//
//thebank.propertyCards
//thebank.trayOfMoney
//thebank.getTotalAmount()
//
//var myCash = StackOfCurrency()
//myCash.addCurrency(currency: Currency(value: 10, denomination: "ten"))
//myCash.addCurrency(currency: Currency(value: 10, denomination: "ten"))
//myCash.addCurrency(currency: Currency(value: 10, denomination: "ten"))
//myCash.addCurrency(currency: Currency(value: 10, denomination: "ten"))
//myCash.addCurrency(currency: Currency(value: 20, denomination: "twenty"))
//myCash.addCurrency(currency: Currency(value: 20, denomination: "twenty"))
//myCash.addCurrency(currency: Currency(value: 20, denomination: "twenty"))
//myCash.addCurrency(currency: Currency(value: 20, denomination: "twenty"))
//myCash.addCurrency(currency: Currency(value: 20, denomination: "twenty"))
//myCash.addCurrency(currency: Currency(value: 5, denomination: "five"))
//myCash.addCurrency(currency: Currency(value: 5, denomination: "five"))
//myCash.addCurrency(currency: Currency(value: 5, denomination: "five"))
//myCash.addCurrency(currency: Currency(value: 5, denomination: "five"))
//myCash.addCurrency(currency: Currency(value: 5, denomination: "five"))
//myCash.addCurrency(currency: Currency(value: 5, denomination: "five"))
//myCash.addCurrency(currency: Currency(value: 5, denomination: "five"))
//myCash.addCurrency(currency: Currency(value: 5, denomination: "five"))
//myCash.addCurrency(currency: Currency(value: 5, denomination: "five"))
//myCash.addCurrency(currency: Currency(value: 1, denomination: "one"))
//myCash.addCurrency(currency: Currency(value: 1, denomination: "one"))
//myCash.addCurrency(currency: Currency(value: 1, denomination: "one"))
//myCash.addCurrency(currency: Currency(value: 1, denomination: "one"))
//myCash.addCurrency(currency: Currency(value: 1, denomination: "one"))
//myCash.addCurrency(currency: Currency(value: 1, denomination: "one"))
//myCash.addCurrency(currency: Currency(value: 1, denomination: "one"))
//myCash.addCurrency(currency: Currency(value: 1, denomination: "one"))
//myCash.addCurrency(currency: Currency(value: 1, denomination: "one"))
//myCash.addCurrency(currency: Currency(value: 1, denomination: "one"))
//myCash.addCurrency(currency: Currency(value: 1, denomination: "one"))
//myCash.addCurrency(currency: Currency(value: 1, denomination: "one"))
//myCash.addCurrency(currency: Currency(value: 100, denomination: "hundred"))
//myCash.addCurrency(currency: Currency(value: 100, denomination: "hundred"))
//myCash.addCurrency(currency: Currency(value: 100, denomination: "hundred"))
//myCash.addCurrency(currency: Currency(value: 100, denomination: "hundred"))
//myCash.addCurrency(currency: Currency(value: 100, denomination: "hundred"))
//myCash.addCurrency(currency: Currency(value: 100, denomination: "hundred"))
//
//myCash.getTotalAmount()
//var cash:StackOfCurrency?;
//var property:PropertyCard?;
////game.getBank()?.purchaseProperty(propertyName: "Boardwalk", money: myCash)
//
//
//let p = Player(playerName: "Player One", piece: Pieces.Boot)
//p.addMoney(stack: myCash)
//var curr = p.makePayment(totalAmountOwed: 25)
//curr.getTotalAmount()
//
//(cash, property) = thebank.purchaseProperty(propertyName: "Boardwalk", money: curr);
//
//cash?.getTotalAmount()
//property?.getPropertyName()
//
//p.addMoney(stack: cash!)
//p.getStackMoney().getTotalAmount()
//
//curr = p.makePayment(totalAmountOwed: 9)
//curr.getTotalAmount()
//
//
let b = Board()
b.printBoard()
//b.addPiece(newPiece: p.getPiece())
//
//
//b.start.getNext()?.getNext()
//
//var dice1 = 0
//var dice2 = 0
//
//(dice1, dice2) = p.rollDice()
//(dice1, dice2) = p.rollDice()
//(dice1, dice2) = p.rollDice()
//
//b.moveAroundBoard(numOfSpaces: dice1+dice2, playPiece: p.getPiece())
//
//
//(dice1, dice2) = p.rollDice()
//
//var prop = b.moveAroundBoard(numOfSpaces: dice1+dice2, playPiece: p.getPiece())
//
////thebank.


















