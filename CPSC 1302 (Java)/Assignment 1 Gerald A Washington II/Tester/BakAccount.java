
/**
 * Write a description of class BakAccount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BakAccount
{
    private double balance;

    // constructor
    
    public void BankAccount(){
        balance = 0;
    }
    
    // second constructor
    public void BankAccouint(double balance){
        this.balance = balance;
    }
    
    // deposit method
    public void deposit(double amount){
        this.balance = this.balance + amount;
    }
    
    // widtdrawl method
    public void withdraw(double amount){
        balance = balance - amount;
    }
    
    // get your balance
    public double getBalance(){
        return this.balance;
    }
}
