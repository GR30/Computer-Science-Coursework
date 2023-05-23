
/**
 * Write a description of class PairDemo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PairDemo
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class PairDemo
     */
    public static void main(String[] args)
    {
        String[] names = {"Tom","Diana","Harry"};
        Pair<String, Integer> result = firstContaining(names, "a");
        
        System.out.println(result.getFirst());
        System.out.println("Expected: Diana");
        System.out.println(result.getSecond());
        System.out.println("Expescted: 1");
    }
    
    public static Pair<String, Integer> firstContaining(String[] strings, String sub){
        for (int i = 0; i < strings.length; i++){
            if (strings[i].contains(sub)){
                Pair<String, Integer> pair_obj = new Pair<String, Integer>(strings[i], i);
                // returns new pair string
                return pair_obj;
            }
        }
        
        return new Pair<String, Integer>(null, -1);
    }
}
