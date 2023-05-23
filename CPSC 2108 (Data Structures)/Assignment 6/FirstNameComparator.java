import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class FirstNameComparator
{
    public static void main(Student array[], int x){
        for (int y = 0; y < array.length-1; y++){
            char[] tF1 = array[y].getFirstName().toLowerCase().toCharArray(); 
            char[] tF2 = array[y+1].getFirstName().toLowerCase().toCharArray();
            char[] tL1 = array[y].getLastName().toLowerCase().toCharArray();
            char[] tL2 = array[y+1].getLastName().toLowerCase().toCharArray();
            for (int i = 0; i < tL1.length; i++){
                if (i < tF2.length){
                    if (tF1[i] > tF2[i]){
                        Student temp = array[y];
                        array[y] = array[y+1];
                        array[y+1] = temp;
                    }
                }else if ((tF2.equals(tF1))&&(tF2.equals(array[y+2].getFirstName().toLowerCase().toCharArray()))){
                    if (tL1[i] > tL2[i]){
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
