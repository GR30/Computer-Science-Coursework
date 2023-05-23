import java.util.Scanner;
import java.util.Arrays;
/**
 * Write a description of class BinarySearch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BinarySearch
{
    public static void main(String[] args){
        Integer[] array = new Integer[10]; int index = 0; String resp;
        System.out.println("Enter numbers for set (Max: 10). If done, enter \"stop\")");
        do{
            Scanner nums = new Scanner(System.in);
            resp = nums.next();
            if (!resp.equalsIgnoreCase("stop")){ 
                array[index]=Integer.parseInt(resp); 
                index++;
            }
        }while((!resp.equalsIgnoreCase("stop"))&&(index < 10));
        
        while(array[array.length - 1] == null){
            array = Arrays.copyOf(array, array.length - 1);
        }
        System.out.println("What number are you looking for? ");
        Scanner t = new Scanner(System.in);
        Integer tar = t.nextInt();
        
        if (binarySearch(array, 0, array.length - 1, tar) == true){ System.out.print("true"); }
        else{ System.out.print("false"); }
    }
    
    public static <T extends Comparable<T>>  
    boolean binarySearch(T[] data, int min, int max, T target){
        int mid, result;
        do{
            mid = (int)(min+max)/2;
            result = target.compareTo(data[mid]);
            if (result < 0) { max = mid - 1; }
            else if (result > 0) { min = mid + 1; }
        }while ((result != 0)&&(min < max));
        
        if (result == 0){ return true; }
        else{ return false; }
    }
}

