import java.util.Comparator;

public class NameSorter implements Comparator<RockStar>
{
    public int compare(RockStar A, RockStar B){ return A.getName().compareTo(B.getName()); }
}
