
/**
 * Write a description of class Total here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Total{
    public static void main(String[] args) throws FileNotFoundException{
        Scanner console = new Scanner(System.in);
        System.out.println("Input File: ");
        String inputFileName = console.next();
        System.out.println("Output File: ");
        String outputFileName = console.next();
        
        File inpurFile = new File(inputFileName);
        Scanner in = new Scanner(System.in);
        
        PrintWriter out = new PrintWriter(outputFileName);
        
        double total = 0;
        while(in.hasNextDouble()){
            double value = in.nextDouble();
            out.printf(("%15.2f"),value);
            total = total + value;
        }
        
        out.printf("Total: %8.2f\n", total);
    }
}
