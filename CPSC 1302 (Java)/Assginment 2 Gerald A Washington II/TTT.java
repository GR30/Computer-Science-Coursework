 


/**
 Blake Ledford
 Tic Tac Toe GUI
 17 February 2015
 */
import javax.swing.*;
import java.awt.GridLayout;

public class TTT extends JFrame
{
    JPanel p = new JPanel();
    //XOButton buttons[] = new XOButton[9];

    public static void main(String[] args)
    {
        new TTT();

    }
    public TTT(){
        super("Tic Tac Toe");
        setSize(600,600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(3,3));
        for(int i = 0; i < 9; i++){
            //buttons[i] = new XOButton();
            //p.add(buttons[i]);

        }
        add(p);

        setVisible(true);
    }
    
}