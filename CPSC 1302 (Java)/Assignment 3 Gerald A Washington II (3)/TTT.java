
/**
 * Name: Gerald A Washington II
 * Program Name: TTT_GUI
 * Date: 3/30/15 
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TTT extends JFrame implements ActionListener{
    private static final int LABEL_WIDTH = 600;
    private static final int LABEL_HEIGHT = 100;

    public static boolean xTurn;
    public static int numTurn = 0;
    public static boolean playing = true;

    static JLabel messageBoard = new JLabel("");
    JPanel board = new JPanel();
    JPanel turnButton = new JPanel();
    static int gT[] = new int[9];
    
    ImageIcon X = new ImageIcon(this.getClass().getResource("x.png"));
    ImageIcon O = new ImageIcon(this.getClass().getResource("O.png"));
    
    public static JButton button0 = new JButton();
    public static JButton button1 = new JButton();
    public static JButton button2 = new JButton();
    public static JButton button3 = new JButton();
    public static JButton button4 = new JButton();
    public static JButton button5 = new JButton();
    public static JButton button6 = new JButton();
    public static JButton button7 = new JButton();
    public static JButton button8 = new JButton();
    public static JButton button9 = new JButton("Reset Button");
    
    public static void main(){ 
        new TTT();
    }
    
    public TTT(){
        // Build GUI
        //super("Tic Tac Toe Game PVP");
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
        button9.addActionListener(this);
        turnButton.add(button9);

        board.setLayout(new GridLayout(3, 3));
        
        board.add(button0);     button0.addActionListener(this);
        board.add(button1);     button1.addActionListener(this);
        board.add(button2);     button2.addActionListener(this);
        board.add(button3);     button3.addActionListener(this);
        board.add(button4);     button4.addActionListener(this);
        board.add(button5);     button5.addActionListener(this);
        board.add(button6);     button6.addActionListener(this);
        board.add(button7);     button7.addActionListener(this);
        board.add(button8);     button8.addActionListener(this);

        //adding labels and panels to frame.
        add(messageBoard, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
        add(turnButton, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    public int value = 0;
    public void actionPerformed(ActionEvent e){
        if (!gameOver()){
            if (value%2 == 1){ xTurn = true;   messageBoard.setText("It's O's turn.");}
                         else{ xTurn = false;  messageBoard.setText("It's X's turn.");}

            if (e.getSource().equals(button0)){
                if (gT[0] == 0){
                    if (xTurn){
                        button0.setIcon(X); gT[0] = 2;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }else{
                        button0.setIcon(O); gT[0] = 1;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }
                    value++;
                }
            }
            else if (e.getSource()== button1){
                if (gT[1] == 0){
                    if (xTurn){
                        button1.setIcon(X); gT[1] = 2;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }else{
                        button1.setIcon(O); gT[1] = 1;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }
                    value++;
                }
            }
            else if (e.getSource()== button2){
                if (gT[2] == 0){
                    if (xTurn){
                        button2.setIcon(X); gT[2] = 2;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }else{
                        button2.setIcon(O); gT[2] = 1;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }
                    value++;
                }
            }
            else if (e.getSource()== button3){
                if (gT[3] == 0){
                    if (xTurn){
                        button3.setIcon(X); gT[3] = 2;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this); 
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }else{
                        button3.setIcon(O); gT[3] = 1;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }
                    value++;
                }
            }
            else if (e.getSource()== button4){
                if (gT[4] == 0){
                    if (xTurn){
                        button4.setIcon(X); gT[4] = 2;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }else{
                        button4.setIcon(O); gT[4] = 1;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }
                    value++;
                }
            }
            else if (e.getSource()== button5){
                if (gT[5] == 0){
                    if (xTurn){
                        button5.setIcon(X); gT[5] = 2;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }else{
                        button5.setIcon(O); gT[5] = 1;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }
                    value++;
                }
            }
            else if (e.getSource()== button6){
                if (gT[6] == 0){
                    if (xTurn){
                        button6.setIcon(X); gT[6] = 2;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this); 
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }else{
                        button6.setIcon(O); gT[6] = 1;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }
                    value++;
                }
            }
            else if (e.getSource()== button7){
                if (gT[7] == 0){
                    if (xTurn){
                        button7.setIcon(X); gT[7] = 2;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!"); }
                    }else{
                        button7.setIcon(O); gT[7] = 1;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!");}
                    }
                    value++;
                }
            }
            else if (e.getSource()== button8){
                if (gT[8] == 0){
                    if (xTurn){
                        button8.setIcon(X); gT[8] = 2;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!"); }
                    }else{
                        button8.setIcon(O); gT[8] = 1;
                        if (gameOver()){        button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                                                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                                                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                                                }
                        else if (Tie() && !gameOver()){messageBoard.setText("It's a tie!"); }
                    }
                    value++;
                }
            }
            else if (e.getSource() == button9){
                for (int i = 0; i < 9; i++){
                    gT[i] = 0;
                }
                
                button0.setIcon(null);   button1.setIcon(null);   button2.setIcon(null);
                button3.setIcon(null);   button4.setIcon(null);   button5.setIcon(null);
                button6.setIcon(null);   button7.setIcon(null);   button8.setIcon(null);
                value = 0;
            }
        }else{
            value=0;
            if (e.getSource() != button9){
                button0.removeActionListener(this);     button1.removeActionListener(this);     button2.removeActionListener(this);
                button3.removeActionListener(this);     button4.removeActionListener(this);     button5.removeActionListener(this);
                button6.removeActionListener(this);     button7.removeActionListener(this);     button8.removeActionListener(this);
                
            } else{
                
                for (int i = 0; i < 9; i++){
                    gT[i] = 0;
                }
                
                button0.addActionListener(this);    button1.addActionListener(this);    button2.addActionListener(this);
                button3.addActionListener(this);    button4.addActionListener(this);    button5.addActionListener(this);
                button6.addActionListener(this);    button7.addActionListener(this);    button8.addActionListener(this);
                
                button0.setIcon(null);   button1.setIcon(null);   button2.setIcon(null);
                button3.setIcon(null);   button4.setIcon(null);   button5.setIcon(null);
                button6.setIcon(null);   button7.setIcon(null);   button8.setIcon(null);
            }
        }
    }
    
    public static boolean gameOver(){
        boolean r = false;
        //check for perpendicular victory
        if (((gT[0] == gT[1]) && (gT[0] == gT[2]) && gT[0] != 0)||
            ((gT[3] == gT[4]) && (gT[3] == gT[5]) && gT[3] != 0)||
            ((gT[6] == gT[7]) && (gT[6] == gT[8]) && gT[6] != 0)){
            r = true;   if (xTurn){messageBoard.setText("O's win!");}else{ messageBoard.setText("X's win!");}}
        else if (((gT[0] == gT[3]) && (gT[0] == gT[6]) && gT[0] != 0)||
                 ((gT[1] == gT[4]) && (gT[1] == gT[7]) && gT[1] != 0)||
                 ((gT[2] == gT[5]) && (gT[2] == gT[8]) && gT[2] != 0)){
            r = true;   if (xTurn){messageBoard.setText("O's win!");}else{ messageBoard.setText("X's win!");}}
        //Check Diagonal victory
        else if ((gT[0] == gT[4]) && (gT[0] == gT[8]) && (gT[0] != 0)){
            r = true;   if (xTurn){messageBoard.setText("O's win!");}else{ messageBoard.setText("X's win!");}}
        else if ((gT[2] == gT[4]) && (gT[2] == gT[6]) && (gT[2] != 0)){
            r = true;   if (xTurn){messageBoard.setText("O's win!");}else{ messageBoard.setText("X's win!");}}
        return r;
    }
    
    public static boolean Tie(){
        int sum = 0;
        for (int i = 0; i < gT.length; i++){ sum += gT[i];}
        if (sum >= 13){
            return true;
        }else{
            return false;}
    }
        
}
