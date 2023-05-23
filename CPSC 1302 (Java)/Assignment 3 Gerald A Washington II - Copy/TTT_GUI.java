
/**
 * Write a description of class TTT_GUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TTT_GUI extends JFrame{
    // declaring public variables
    private static final int LABEL_WIDTH = 600;
    private static final int LABEL_HEIGHT = 100;

    public boolean xTurn = true;
    public static int numTurn = 0;
    public static boolean gameOver = false;

    JLabel messageBoard = new JLabel("");
    JPanel board = new JPanel();
    JPanel turnButton = new JPanel();
    JButton turn = new TurnButton();
    static Buttons XOs[][] = new XOButtons[3][3];
    static int buttonClicks[][] = new int[3][3];
    //     static String[][] previousTurnClicks = new String[3][3];
    //     static String[][] currentTurnClicks = new String[3][3];
    
    //static int clickNum = 0;
    public static XOButtons[][] getButtons(){ return XOs; }
    public static void setButtons(XOButtons[][] currentButtons){ XOs = currentButtons; }
    public static void setButtons(int x, int y, int z){ XOs[x][y].setImageNum(z);}
    
    public static int getButtonClicks(int x, int y){ return buttonClicks[x][y]; }
    public static void setButtonClicks(int x, int y, int z){ buttonClicks[x][y] = z; } 
    
    public static boolean buttonClicked(){
        boolean r = false;
        for (int x = 0; x == XOs.length; x++){
            for (int y = 0; y == XOs[x].length; y++){
                if (buttonClicks[x][y] != 0){ r = true;}
            }
        }
        return r;
    }
    
    public static void main(){ 
        new TTT_GUI();
    }
    
    public TTT_GUI(){
        // Build GUI
        super("Tic Tac Toe Game PVP");
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Center Label
        messageBoard.setLayout(new BorderLayout());
        messageBoard.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        messageBoard.setText("Tic Tac Toe Game");

        //NEW STUFF
        Font labelFont = messageBoard.getFont();
        String labelText = messageBoard.getText();

        int stringWidth = messageBoard.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = LABEL_WIDTH;

        // Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = LABEL_HEIGHT;

        // Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

        // Set the label's font size to the newly determined size.

        messageBoard.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));

        turnButton.setLayout(new BorderLayout());
        turnButton.setSize(600, 100);
        turnButton.add(turn);

        board.setLayout(new GridLayout(3, 3));
        for (int x = 0; x < XOs.length; x++){
            for (int y = 0; y < XOs[x].length; y++){
                XOs[x][y] = new XOButtons(x,y);   
                board.add(XOs[x][y]);

            }
        }



        
        //adding labels and panels to frame.
        add(messageBoard, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
        add(turnButton, BorderLayout.SOUTH);

        // XOs[0].getGridx();

        setVisible(true);
    }
    
    public static void resetButtonClicks(){
        for (int x = 0; x == XOs.length; x++){
            for (int y = 0; y == XOs[x].length; y++){
                buttonClicks[x][y] = 0;
            }
        }
    }
    
    public static void switchRemaining(int s){
        for (int x = 0; x == XOs.length; x++){
            for (int y = 0; y == XOs[x].length; y++){
                if ((XOs[x][y].getImageNum() != s)&&(buttonClicks[x][y] == 0)){
                    XOs[x][y].setImageNum(s);
                }
            }
        }
    }
}
    //     
    //     public static void setTracker(Buttons[] comp){
    //         comparison = comp;
    //     }
    //     
    //     public static Buttons[] getTracker(){
    //         return comparison;
    //     }
    //     
    // 
    //     public boolean gameOver()
    //     {
    //         //Check for vertical victory
    //         if ((XOs[0].getTurnImageNum() == XOs[3].getTurnImageNum() && XOs[0].getTurnImageNum() == XOs[6].getTurnImageNum())||
    //             (XOs[1].getTurnImageNum() == XOs[4].getTurnImageNum() && XOs[1].getTurnImageNum() == XOs[7].getTurnImageNum())||
    //             (XOs[2].getTurnImageNum() == XOs[5].getTurnImageNum() && XOs[2].getTurnImageNum() == XOs[8].getTurnImageNum()))
    //             return true;
    //         //Check for horizontal victory
    //         else if ((XOs[0].getTurnImageNum() == XOs[1].getTurnImageNum() && XOs[0].getTurnImageNum() == XOs[2].getTurnImageNum())||
    //             (XOs[3].getTurnImageNum() == XOs[4].getTurnImageNum() && XOs[3].getTurnImageNum() == XOs[5].getTurnImageNum())||
    //             (XOs[6].getTurnImageNum() == XOs[7].getTurnImageNum() && XOs[6].getTurnImageNum() == XOs[8].getTurnImageNum()))
    //             return true;
    //         //Check Diagonal victory
    //         else if (XOs[0].getTurnImageNum() == XOs[4].getTurnImageNum() && XOs[0].getTurnImageNum() == XOs[8].getTurnImageNum()
    //                 && XOs[0].getTurnImageNum() != 0 && XOs[4].getTurnImageNum() != 0 && XOs[8].getTurnImageNum() != 0)
    //             return true;
    //         else if (XOs[2].getTurnImageNum() == XOs[4].getTurnImageNum() && XOs[2].getTurnImageNum() == XOs[6].getTurnImageNum()
    //                 && XOs[2].getTurnImageNum() != 0 && XOs[4].getTurnImageNum() != 0 && XOs[6].getTurnImageNum() != 0)
    //             return true;
    //         else
    //             return false;
    //     }
//}