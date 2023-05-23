import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class GradeComparator
{
    public static void main(Student array[]){
        for (int y = 0; y < array.length; y++){
            for (int i = 0; i < array.length; i++){
                if (i+1 < array.length){
                    if (array[i].getGrade() > array[i+1].getGrade()){
                        Student temp = array[i];
                        array[i] = array[i+1];
                        array[i+1] = temp;
                    }
                }
            }
        }
        
        for(int in = 0; in < array.length; in++){ System.out.println("First Name: " + array[in].getFirstName() + "     Last Name: " + array[in].getLastName() + "     Grade: " + array[in].getGrade()); }
    }
}
