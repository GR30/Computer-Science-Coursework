import java.io.*;
import java.util.*;
/**
 * Write a description of class TaxCalculator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TaxCalculator
{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);        
        String c = "y";
        while(c.equals("y")||c.equals("Y")){
        System.out.print("Please input your income: ");
        double income = in.nextDouble();
        System.out.print("Are you married:(Y/N) ");
        String input = in.next();
        int status ;
        
        
        if(input.equals("Y")||input.equals("y")){
            status = TaxReturn.MARRIED;
        }
        else{
            status = TaxReturn.SINGLE;   
        }
        
        TaxReturn aTaxReturn = new TaxReturn(income,status);
        System.out.println("My tax for 2014: " + aTaxReturn.getTax());
        System.out.print("Continue? (Y/N)");
        c = in.next();
    }
    
    System.out.println("Please exit Tax Calculator");
}
}
