
/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
public class Board
{
    public static int gt[][] = {{0,0,0},{0,0,0},{0,0,0}};
    
    public static void main(){
        
    }
    
    public Board(){
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
        XOs[x][y] = new XOButtons(x,y);   board.add(XOs[x][y]);

        //adding labels and panels to frame.
        add(messageBoard, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
        add(turnButton, BorderLayout.SOUTH);

        // XOs[0].getGridx();

        setVisible(true);
    }
    
    public class Button_0_0 extends Buttons implements ActionListener{
        ImageIcon X, O;
        public int gridx;
        public int gridy;
        public static int ImageNum = 1;
        public static String turnImage;
        
        public Button_0_0(){
            X = new ImageIcon(this.getClass().getResource("x.png"));
            O = new ImageIcon(this.getClass().getResource("O.png"));
            this.addActionListener(this);
            this.gridx = 0;
            this.gridy = 0;
        }
        
        public void actionPerformed(ActionEvent e){
            if (ImageNum == 2){
                setIcon(X);     turnImage = "X";    
            }else if(ImageNum == 1){
                setIcon(O);     turnImage = "O";
            }else{ setIcon(null);}
        } 
        
        ActionListener listener = new AddInterestListener();
    }
}