import java.util.Scanner;
/**
 * Write a description of class PlayerBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerBoard extends Scoreable
{
    public String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
    public int[] cols =    {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public GameSpace[][] GameBoard = new GameSpace[rows.length][cols.length];
    public PlayerBoard()
    {
        super(0);
        for(int row = 0; row < rows.length; row++)
        {
            for(int col = 0; col < cols.length; col++)
            {
                GameBoard[row][col] =new GameSpace(rows[row], cols[col]);
            }
        }
    }

    public void addPiece(GamePiece gamePiece)
    {
        Scanner key = new Scanner(System.in);
        String ship = gamePiece.getShip();
        int size = gamePiece.getSize();

        int row = -1;
        while(row == -1)
        {
            System.out.println("What row? A to " + rows[rows.length-1]);
            row = getRowIndex(key.next());
        }

        int col = -1;
        while(col == -1)
        {
            System.out.println("What col? 1 to " + cols[cols.length-1]);
            int input = key.nextInt();
            if(input >= 1 && input <= cols[cols.length-1])
            {
                col = input;
            }
        }
        col = col - 1;//This moves it back a column
        int direction = -1;
        while(direction == -1)
        {
            System.out.println("What direction? 1:horizontal 2:vertical");
            int input = key.nextInt();
            if(input == 1 || input ==2)
            {
                direction = input;
            }
        }

        if(addPiece(row, col, direction, size ,ship))
        {
            System.out.println("Piece was added successfully");
        }
        else
        {
            System.out.println("Piece failed");
            addPiece(gamePiece);
        }
    }

    public Boolean addPiece(int row, int col, int direction, int sizeLeft, String ship)
    {
        int rowChange = 0;
        int colChange = 0;

        if(sizeLeft == 0)//Moved this down from the space empty if
        {
            return true;
        }

        if(direction == 1)
        {
            colChange = 1;
        }
        if(direction == 2)
        {
            rowChange = 1;
        }

        if(row >= rows.length || col >= cols.length)
        {
            return false;
        }
        if(GameBoard[row][col].getShip().equals("none"))//space is empty
        {
            if(addPiece(row+rowChange, col+colChange, direction, sizeLeft-1, ship))//true
            {
                GameBoard[row][col].setShip(ship);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    
    public boolean attackGameSpace(){
        int row = inputRow();
        int col = inputCol();
        
        if (getGameSpace(row,col).canHit()){ 
            String space = getGameSpace(row,col).hitSpace(); 
            if (space.equals("none") == false){
                System.out.println("You have hit a ship.");
                return true;
            }else{
                System.out.println("You missed.");
                return false;
            }
        }else{
            System.out.println("This space has already been hit.\nChoose again.");
            return attackGameSpace();
        }
    }
    
    public GameSpace getGameSpace(){
        return GameBoard[row][col];
    }

    public int getRowIndex(String row)
    {
        int index = -1;
        for(int i = 0; i < rows.length; i++)
        {
            if(rows[i].equals(row))
            {
                index = i;
            }
        }
        return index;
    }

    public String toString()
    {
        String newString = " x ";
        for(int col = 0; col < cols.length; col++)
        {
            newString = newString + " " + cols[col] + " ";
        }
        newString = newString + "\n";
        for(int row = 0; row < rows.length; row++)
        {
            newString = newString + " " + rows[row] + " ";
            for(int col = 0; col < cols.length; col++)
            {
                newString = newString + "[" + GameBoard[row][col] + "]";
            }
            newString = newString + "\n";
        }
        return newString;
    }
}

