/**
 * Created by Blake on 2/19/2015.
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TicTacToeGUI extends JFrame{

    // declaring public variables
    private static final int LABEL_WIDTH = 600;
    private static final int LABEL_HEIGHT = 100;

    JLabel messageBoard = new JLabel("");
    JPanel board = new JPanel();
    JPanel turnButton = new JPanel();
    JButton turn = new JButton();
    Buttons XOs[] = new Buttons[9];


    public static void main(String[] args)
    {
        new TicTacToeGUI();
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

        setVisible(true);
    }

}
