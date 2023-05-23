import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;

public class Bank{
    private static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
    
    public static void main() throws FileNotFoundException{
        Scanner readerIn = new Scanner(new File("FileProvider.txt"));
        while(readerIn.hasNext()){
            int accountNum = readerIn.nextInt();
            double balance = readerIn.nextDouble();
            accounts.add(new BankAccount(accountNum, balance));
        }
        
        highestBalance();
    }
    
    public static void highestBalance(){
        double[] accounts2 = new double[5];

        for (int i = 0; i < accounts.size(); i++){
            accounts2[i] = accounts.get(i).getBalance();
        }

        Arrays.sort(accounts2);
        
        for (int i = 0; i < accounts.size(); i++){
            if (accounts.get(i).getBalance() == accounts2[4]){
                System.out.println( "Highest Account Balance: " + accounts.get(i).getBalance() + 
                                    "\nMatching Account#: " + accounts.get(i).getAccountNumber());
            }
        }
    }
}