
/**
 * Write a description of class Pair here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pair<A,B>
{
    // instance variables - replace the example below with your own
    private A first;
    private B second;

    /**
     * Constructor for objects of class Pair
     */
    public Pair(A firstE, B secondE)
    {
        first = firstE;
        second = secondE;
    }

    public A getFirst(){ return first; }
    public B getSecond(){return second; }
    
    public String toString(){ return "(" + first + ", " + second + ")"; }
}

