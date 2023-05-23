import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class LastNameComparator
{
    public static void main(Student array[], int x){
        for (int y = 0; y < array.length-1; y++){
            char[] tL1 = array[y].getLastName().toLowerCase().toCharArray(); 
            char[] tL2 = array[y+1].getLastName().toLowerCase().toCharArray();
            for (int i = 0; i < tL1.length; i++){
                if (i < tL2.length){
                    if (tL1[i] < tL2[i]){
                        Student temp = array[y];
                        array[y] = array[y+1];
                        array[y+1] = temp;
                    }
                }else if (tL1.equals(tL2)){
                    if ((array[y].getGrade() < array[y+1].getGrade())&&!(array[y+1].getLastName().equals(array[y+2].getLastName()))){
                        Student temp = array[y];
                        array[y] = array[y+1];
                        array[y+1] = temp;            
                    }                
                }
            }
        }
        
        if (x < array.length){x++; main(array, x);}
        
        if (x == array.length){
            System.out.println("After:");
            for(int in = 0; in < array.length; in++){ System.out.println("First Name: " + array[in].getFirstName() + "     Last Name: " + array[in].getLastName() + "     Grade: " + array[in].getGrade()); }
        }
    
    }
}