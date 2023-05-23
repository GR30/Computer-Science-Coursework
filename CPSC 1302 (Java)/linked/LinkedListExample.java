
/**
 * Write a description of class LinkedListExample here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class LinkedListExample
{
    public static void main(){
        LinkedList<String> linkedlist = new LinkedList<String>();
        
        linkedlist.add("Item 1");
        linkedlist.add("Item 5");
        linkedlist.add("Item 3");
        linkedlist.add("Item 6");
        linkedlist.add("Item 2");
        
        System.out.println("Linked Lis Content:" + linkedlist);
        
        linkedlist.addFirst("First item");
        linkedlist.addLast("Last item");
        
        System.out.println("Advance for loop");
        for(String str: linkedlist){
            System.out.println(str);
        }
        
        Iterator itr = linkedlist.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
