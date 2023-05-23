
/**
 * Write a description of class RoachSimluation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RoachSimluation
{
    public static void main(String args[]){
        RoachPopulation Simulation = new RoachPopulation(10);
        int i = 0;
        while (i < 3){
            Simulation.breed();
            Simulation.spray(10);
            System.out.println(Simulation.getRoaches());
            i += 1;
        }
    }
}
