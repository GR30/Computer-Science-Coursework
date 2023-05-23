import java.util.*;
public class ArrayDemonstration
{
   public static void main(String[] args){
       final int LENGTH = 100;
       double[] values = new double[LENGTH];
       int currentSize = 0;
       
       //read the input
       System.out.println("Please enter values, Press Q to Quit");
       Scanner in = new Scanner(System.in);
       
       while(in.hasNextDouble() && currentSize < values.length){
           values[currentSize] = in.nextDouble();
           currentSize++;
       }
       
       // find the largest value int the array
       double largest = values[0];
       double smallest = values[0];
       for (int i = 1; i < currentSize; i++){
           if (values[i] > largest){
               largest = values[i];
           }
           if (values[i] < smallest){
               smallest = values[i];
           }
       }
       
       // print out all values and mark largest
       for (int i = 0; i < currentSize; i++){
           System.out.print(values[i]);
           if (values[i] == largest){
               System.out.print(" <=== This one is the largest value.");
            }
           if (values[i] == smallest){
               System.out.print(" <=== This one is the smallest value.");
            }
           System.out.println();
        }
   }
}
