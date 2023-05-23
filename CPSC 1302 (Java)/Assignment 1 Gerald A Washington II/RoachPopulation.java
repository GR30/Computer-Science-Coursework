
/**
 * Write a description of class RoachPopulation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RoachPopulation
{
    double population;
//     public RoachPopulation(){
//         population = 2;
//     }
    
    public RoachPopulation(int x){
        population = x;
    }

    public void breed(){
        population *= 2;
    }
    
    public void spray(double percent){
        population = population - (population / percent);
    }
    
    public double getRoaches(){
        return population;
    }
}
