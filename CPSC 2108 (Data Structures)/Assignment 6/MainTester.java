import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class MainTester
{
    public static void main(String[] args){
        Student[] array = new Student[0];
        for(int x=0; x < 20; x++){
            array = Arrays.copyOf(array, array.length + 1); 
            
            Random grade = new Random();        double g = grade.nextDouble()*100;
            Random firstName = new Random();    int fN = firstName.nextInt(25)+97;
            Random lastName = new Random();     int lN = lastName.nextInt(25)+97;
            
            array[x] = new Student(x, Character.toString((char)fN), Character.toString((char)lN), g);
        }
        
        System.out.println("Before:");
        for(int in = 0; in < array.length; in++){ System.out.println("First Name: " + array[in].getFirstName() + "     Last Name: " + array[in].getLastName() + "     Grade: " + array[in].getGrade()); }
        
        System.out.println("\nCompare by\n1: Grade\n2:First Name\n3:Last Name");
        Scanner response = new Scanner(System.in);
        String resp = response.next();
        switch(resp){
            case "1":
                GradeComparator.main(array);
                break;
            case "2":
                FirstNameComparator.main(array,0);
                break;
            case "3":
                LastNameComparator.main(array,0);
                break;
            default:
                System.out.println("1, 2, or 3 only.\n");
                break;
        }
    }
}
