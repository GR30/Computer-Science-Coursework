import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

public class MainTester
{
    static List<RockStar> HallOfFame = new LinkedList<RockStar>();
    
    public static void main(String[] args){
        RockStar Ozzy = new RockStar("Ozzy", 3000);
        RockStar Blake = new RockStar("Blake", -1);
        RockStar Nicole = new RockStar("Nicole", 2015);
        RockStar Kiwi = new RockStar("Kiwi the Master", 2015);
        RockStar Obando = new RockStar("Alquement", 2015);
        RockStar Scott = new RockStar("The Scrub", -2);
        
        HallOfFame.add(Ozzy);
        HallOfFame.add(Blake);
        HallOfFame.add(Nicole);
        HallOfFame.add(Kiwi);
        HallOfFame.add(Scott);
        HallOfFame.add(Obando);
        
        for (RockStar star: HallOfFame){System.out.println(star);}
        System.out.println();
        
        Collections.sort(HallOfFame, new NameSorter());
        for (RockStar star: HallOfFame){System.out.println(star);}
        System.out.println();
        
        Collections.sort(HallOfFame, new YearSorter());
        for (RockStar star: HallOfFame){System.out.println(star);}
    }
}
