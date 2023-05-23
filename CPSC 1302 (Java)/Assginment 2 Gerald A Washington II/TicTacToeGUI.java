 

/**
 * Author: Gerald A Washington
 * Date: 3/10/2015
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TicTacToeGUI extends JFrame{
    // declaring public variables
    private static final int LABEL_WIDTH = 600;
    private static final int LABEL_HEIGHT = 100;

    public boolean xTurn = true;
    public static int numTurn = 0;
    public static boolean gameOver = false;

    JLabel messageBoard = new JLabel("");
    JPanel board = new JPanel();
    JPanel turnButton = new JPanel();
    JButton turn = new JButton("End Turn");
    static Buttons XOs[] = new Buttons[9];

    public static void main(String[] args)
    {

        new TicTacToeGUI();
        System.out.print(XOs[0].getIcon());


    }
    
    public TicTacToeGUI(){
        // Build GUI
        super("Tic Tac Toe Game PVP");
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Center Label
        messageBoard.setLayout(new BorderLayout());
        messageBoard.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        messageBoard.setText("this is to test the resizing ability");

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
        for(int i = 0; i < 9; i++){
            XOs[i] = new Buttons();
            board.add(XOs[i]);
        }

        
        //adding labels and panels to frame.
        add(messageBoard, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
        add(turnButton, BorderLayout.SOUTH);

        XOs[0].getGridx();

        setVisible(true);
    }
    
    public static Buttons[] getButtons(){
        return XOs;
    }
    
    public boolean gameOver()
    {
        //Check for vertical victory
        if ((XOs[0].getTurnImageNum() == XOs[3].getTurnImageNum() && XOs[0].getTurnImageNum() == XOs[6].getTurnImageNum())||
            (XOs[1].getTurnImageNum() == XOs[4].getTurnImageNum() && XOs[1].getTurnImageNum() == XOs[7].getTurnImageNum())||
            (XOs[2].getTurnImageNum() == XOs[5].getTurnImageNum() && XOs[2].getTurnImageNum() == XOs[8].getTurnImageNum()))
            return true;
        //Check for horizontal victory
        else if ((XOs[0].getTurnImageNum() == XOs[1].getTurnImageNum() && XOs[0].getTurnImageNum() == XOs[2].getTurnImageNum())||
            (XOs[3].getTurnImageNum() == XOs[4].getTurnImageNum() && XOs[3].getTurnImageNum() == XOs[5].getTurnImageNum())||
            (XOs[6].getTurnImageNum() == XOs[7].getTurnImageNum() && XOs[6].getTurnImageNum() == XOs[8].getTurnImageNum()))
            return true;
        //Check Diagonal victory
        else if (XOs[0].getTurnImageNum() == XOs[4].getTurnImageNum() && XOs[0].getTurnImageNum() == XOs[8].getTurnImageNum()
                && XOs[0].getTurnImageNum() != 0 && XOs[4].getTurnImageNum() != 0 && XOs[8].getTurnImageNum() != 0)
            return true;
        else if (XOs[2].getTurnImageNum() == XOs[4].getTurnImageNum() && XOs[2].getTurnImageNum() == XOs[6].getTurnImageNum()
                && XOs[2].getTurnImageNum() != 0 && XOs[4].getTurnImageNum() != 0 && XOs[6].getTurnImageNum() != 0)
            return true;
        else
            return false;
    }
        /*public void turn(){
        numTurn++;
        if(numTurn%2 == 0) {
            xTurn = false;
        }
        else if(numTurn%2 == 1) {
            xTurn = true;
        }*/
        }


