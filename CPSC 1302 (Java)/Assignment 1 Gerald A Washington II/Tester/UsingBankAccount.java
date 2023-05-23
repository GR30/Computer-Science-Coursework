
/**
 * Write a description of class UsingBankAccount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UsingBankAccount
{
    // test my bank account
    public static void main(String[] args){
        BakAccount harrysChecking = new BakAccount();
        harrysChecking.deposit(2000);
        System.out.print("The balance is: " + harrysChecking.getBalance());
        harrysChecking.withdraw(500);
        System.out.println("The balance is: " + harrysChecking.getBalance());
    }
}

