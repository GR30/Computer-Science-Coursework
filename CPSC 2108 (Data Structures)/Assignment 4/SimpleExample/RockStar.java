
public class RockStar
{
    public String name;
    public int year;
    
    public RockStar(String name, int year){
        this.name = name;
        this.year = year;
    }
    
    public String getName(){ return name; }
    
    public int getYear(){ return year; }
    
    public String toString(){ return name + " " + year; }
}
