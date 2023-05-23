
/**
 * Write a description of class GamePiece here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GamePiece
{
    public String Ship;
    public int Size;

    public GamePiece(String Ship, int Size)
    {
        this.Ship = Ship;
        this.Size = Size;
    }
    
    public String getShip()
    {
        return Ship;
    }
    
    public int getSize()
    {
        return Size;
    }
}
