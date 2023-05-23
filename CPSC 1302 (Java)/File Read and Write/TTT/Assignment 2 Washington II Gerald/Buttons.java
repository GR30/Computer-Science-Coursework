/*
Created by Blake Ledford 20 Feb 2015
 */
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Buttons extends JButton implements ActionListener{
    ImageIcon X, O;
    public int value = 0;

    public Buttons(){
    X = new ImageIcon(this.getClass().getResource("x.png"));
    O = new ImageIcon(this.getClass().getResource("O.png"));
    this.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        value++;
        if(value%2 == 1) {
            setIcon(X);
        }
        else if(value%2 == 0) {
            setIcon(O);
        }

    }
}
