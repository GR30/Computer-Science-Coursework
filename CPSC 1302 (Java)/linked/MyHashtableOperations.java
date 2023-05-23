
/**
 * Write a description of class MyHashtableOperations here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class MyHashtableOperations
{
    public static void main(String args[])
    {
        Hashtable<String, String> ht = new Hashtable<String, String>();
        ht.put("first","First inserted");
        ht.put("second","Second inserted");
        ht.put("third","Third inserted");
        ht.put("fourth","Fourth inserted");
        
        System.out.println(ht);
        
        System.out.println("Value of Key 'second': " + ht.get("second"));
        ht.remove("second");
        System.out.println("After removed: ");
        
        Set<String> keys = ht.keySet();
        for(String key: keys){
            System.out.println(key);
        }
    }
}
