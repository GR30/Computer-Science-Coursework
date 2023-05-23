import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;
/**
 * Write a description of class RecursionExercises here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecursionExercises
{
    public static void main(String[] args)
    {
        boolean quit = false;
        while(!quit){
            System.out.println("1: Multiply\n2: FindMin\n3: Reverse\n4: CountPaths\n5: OrdString\nQ: Quit");
            Scanner response = new Scanner(System.in);
            String res = response.next();
            
            switch(res){
                case "1": System.out.print("\nFirst number: ");
                          Scanner number1 = new Scanner(System.in);
                          int num1 = number1.nextInt();
                          
                          System.out.print("\nSecond number: ");
                          Scanner number2 = new Scanner(System.in);
                          int num2 = number2.nextInt();
                          
                          System.out.println(multiply(num1, num2));
                          break;
                          
                case "2": int[] array = new int[10]; int index = 0; String resp;
                          System.out.println("Enter numbers for set (Max: 10). If done, enter \"stop\")");
                          do{
                              Scanner nums = new Scanner(System.in);
                              resp = nums.next();
                              if (!resp.equalsIgnoreCase("stop")){ 
                                  array[index]=Integer.parseInt(resp); 
                                  index++;
                              }
                          }while((!resp.equalsIgnoreCase("stop"))&&(index < 10));
                          System.out.println(findMin(array));
                          break;
                          
                case "3": System.out.print("\nEnter whole number: ");
                          Scanner in = new Scanner(System.in);
                          int num = in.nextInt();
                          System.out.println("Reverse number: "+reverse(num));
                          break;
                          
                case "4": System.out.print("\nDimension X: ");
                          Scanner dim1 = new Scanner(System.in);
                          int x = dim1.nextInt();
                          
                          System.out.print("\nDimension y: ");
                          Scanner dim2 = new Scanner(System.in);
                          int y = dim2.nextInt();
                          
                          System.out.println("Number of Paths: "+countPaths(x,y));
                          break;
                    
                case "5": System.out.print("Input number: ");
                          Scanner number = new Scanner(System.in);
                          int input = number.nextInt();

                          System.out.println(ordString(input));
                          break;
                    
                case "q":
                case "Q": quit = true;
                          break;
                
                default:  System.out.print("1, 2, 3, 4, 5, or Q only.");
                          break;
            }
        }
    }
    
    public static int multiply(int a, int b){
        if (b != 1){ 
            return a + multiply(a, b-1);
        }
        return a ;
    }
    
    public static int findMin(int array[]){
        Arrays.sort(array);
        if(array.length < 2){
            int min = array[0];
            return min;
        }
        int[] newArray = Arrays.copyOf(array,array.length-1);
        int min = findMin(newArray);
        return min;
    }
     
    public static int reverse(int number){
        int total = 0;
        int digits = (int)Math.log10(number);
        if (digits >= 0){    
            int last = number%10;
            total = (int)Math.pow(10,digits)*last;
            total += reverse(number/10);
        }
        return total;
    }
    
    public static int countPaths(int n, int m){
        int total = 0;
        if (n < 0){ return 0; }
        else if (m < 0){ return 0; }
        else if ((n==0)&&(m==0)){ return 1; }
        return total + (countPaths(n-1,m) + countPaths(n,m-1));
    }
    
    public static String ordString(int number){
        String ans = "";
        if (number != 0){
            for (int i = 0; i < number; i++){
                if (i > 0){ ans = ans + ","; }
                ans = ans + ordString(i);
            }
        }
        return "{"+ ans +"}";
    }
}
