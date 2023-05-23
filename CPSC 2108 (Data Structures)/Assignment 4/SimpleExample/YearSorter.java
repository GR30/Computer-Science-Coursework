import java.util.Comparator;

public class YearSorter implements Comparator<RockStar>
{
    public int compare(RockStar A, RockStar B){ 
        int result = Integer.compare(A.getYear(), B.getYear()); 
        if(result == 0){ return A.getName().compareTo(B.getName()); }
        return result;
    }
}
