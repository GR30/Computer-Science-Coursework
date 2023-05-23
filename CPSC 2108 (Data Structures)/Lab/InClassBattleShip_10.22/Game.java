
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game
{
    public static void main(String[] args)
    {
        GamePiece AircraftCarrier = new GamePiece("Aircraft Carrier", 5);
        
        PlayerBoard Player1 = new PlayerBoard();
        PlayerBoard Player2 = new PlayerBoard();
        
        System.out.println(Player1);
        System.out.println("Its Player1's turn.");
        Player1.addPiece(AircraftCarrier);
        
        System.out.println(Player1);
        System.out.println("Its Player2's turn.");
        Player2.addPiece(AircraftCarrier);
        
        System.out.println("Player1's turn.");
        boolean hitSomething = Player2.attackGameSpace();
        if (hitSomething){
            boolean won = Player1.addPoint();
            if (won){ System.out.println("Player 1 has won the game."); return; }
        }
        Player1.addPiece(AircraftCarrier);
        System.out.println(Player1);
        
        Player1.attackGameSpace();
        System.out.println(Player1);
    }
}
