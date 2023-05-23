
/**
 * Write a description of class TurnButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class TurnButton extends JButton implements ActionListener{

    private int playerTurn = 1;
    
    public TurnButton(){
        this.addActionListener(this);
    }
    
    public int getPlayerTurn(){
        return playerTurn;
    }
    
    public int newTurn(){
        if (playerTurn == 1)
            playerTurn = 2;
        else
            playerTurn = 1;
        return playerTurn;
    }
    
    public void actionPerformed(ActionEvent e){
        newTurn();
    }
}

