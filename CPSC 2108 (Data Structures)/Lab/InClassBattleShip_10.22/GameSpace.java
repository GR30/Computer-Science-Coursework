
/**
 * Write a description of class GameSpace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameSpace
{
    public String Ship;
    public Boolean hit;
    public String Location;

    public GameSpace (String row, int col)
    {
        Ship = "none";
        hit = false;
        Location = row+col;
    }
    
    public void setShip(String Ship)
    {
        this.Ship = Ship;
    }
    
    public String getShip()
    {
        return Ship;
    }
    
   
    public String hitSpace()
    {
        hit = true;
        return Ship;
    }

    public Boolean canHit()
    {
        return !hit;
    }

    public Boolean getHit()
    {
        return hit;
    }

    public String GetLocation()
    {
        return Location;
    }

    public String toString()
    {
        if(!hit)
        {
            return ""+Ship.charAt(0);
            //return "N";
        }
        else
        {
            if(Ship.equals("none"))
            {
                return "E";
            }
            else
            {
                return "H";
            }
        }
    }
}








