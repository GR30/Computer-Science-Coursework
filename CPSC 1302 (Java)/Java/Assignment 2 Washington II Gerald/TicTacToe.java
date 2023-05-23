/**
 * Tic-Tac-Toe Game
 * 
 * Gerald A Washington II 
 * 2/20/15
 */
import java.util.Scanner;
import java.util.InputMismatchException;
public class TicTacToe
{
    public static int row, col;
    public static Scanner scan = new Scanner(System.in);
    public static String[][] board = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
    public static String turn = "O";
    public static String response = "";
    
    public static void main(String[] args){
        do{
            System.out.println("Welome to Tic Tac Toe. Whoever matches three X's or O's vertically, \nhorizontally, or diagonally wins. \nO's go first.");
            Play();
            Scanner resp = new Scanner(System.in);
            System.out.println("Would you like to play again?");
            response = resp.next().toLowerCase();
        }while(response.equals("yes")||response.equals("y"));
    }
    public static void Play(){
        boolean playing = true;
        while(playing){
            PrintBoard();
            do{
                System.out.println("Enter the row number: ");
                row = scan.nextInt() - 1;
                System.out.println("Enter the column number: ");
                col = scan.nextInt() - 1;
                if((row > 2)||(col > 2)){
                    System.out.println("Only input integers from 1 to 3.");
                }
            }while((!(row >= 0)&&!(row < 3))||((!(col < 3)&&!(col >= 0))));
            
            if (!((board[row][col] == "X")||(board[row][col] == "O"))){
                board[row][col] = turn;
            }
            
            if (GameOver(row, col)){
                System.out.println("Game Over! " + turn + "'s win!");
                playing = false;
            }else if(((board[0][0] != "1" && board[0][1] != "2" && board[0][2] != "3" && board[1][0] != "4" && 
                       board[1][1] != "5" && board[1][2] != "6" && board[2][0] != "7" && board[2][1] != "8" && board[2][2] != "9")
                       && !(GameOver(row,col)))){
                PrintBoard();
                System.out.println("Game Over! It's a tie!");
                playing = false;
            }

            if (turn == "X"){
                turn = "O";
            }else{
                turn = "X";
            }
        }
    }
    public static void PrintBoard(){
        System.out.println("      1   2   3");
        for (int i = 0; i < 3; i++){
            System.out.print((i+1) +"   | ");
            for (int j = 0; j < 3; j++){
                String alt = "_";
                if ((board[i][j] == "X")||(board[i][j] == "O")){
                    alt = board[i][j];
                }
                System.out.print(alt + " | ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static boolean GameOver(int y, int x){
        boolean r = false;
        //check for perpendicular victory
        if ((board[0][y] == board[1][y]) && (board[0][y] == board[2][y]))
            r = true;
        else if ((board[x][0] == board[x][1]) && (board[x][0] == board[x][2]))
            r = true;
        //Check Diagonal victory
        else if ((board[0][0] == board[1][1]) && (board[0][0] == board[2][2]) && (board[1][1] != "_"))
            r = true;
        else if ((board[0][2] == board[1][1]) && (board[0][2] == board[2][0]) && (board[1][1] != "_"))
            r = true; 
        return r;
    }
}


