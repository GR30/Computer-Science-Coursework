/**
 * Author: Gerald A Washington II
 * Date: 3/10/15
 */

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Buttons extends JButton implements ActionListener{
    ImageIcon X, O;
    public int value = 0;
    public int gridx;
    public int gridy;
    public int turnImage;
    
    public Buttons(){
        X = new ImageIcon(this.getClass().getResource("x.png"));
        O = new ImageIcon(this.getClass().getResource("O.png"));
        this.addActionListener(this);
    }
    
    public Buttons(String label, int gridx, int gridy) {
        super(label);
        this.gridx = gridx;
        this.gridy = gridy;
    }

    public int getGridx() {
        return gridx;
    }

    public int getGridy() {
        return gridy;
    }
    
    public int getTurnImageNum(){
        if ((turnImage != 1)||(turnImage != 2)){
            return 0;
        }else{
            return turnImage;
        }
    }

    public void setTurnImageNum(int x){
        turnImage = x;
    }
    
    public void actionPerformed(ActionEvent e){
        if ((turnImage != 1)&&(turnImage != 2)){
            value++;
            if(value%2 == 1){ 
                setIcon(O);
                turnImage = 1;
            }else if(value%2 == 0){ 
                setIcon(X);
                turnImage = 2;
            }
        }
    }
}
