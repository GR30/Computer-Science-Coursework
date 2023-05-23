
/**
 * Write a description of class XOButtons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XOButtons extends Buttons implements ActionListener{
    ImageIcon X, O;
    public int gridx;
    public int gridy;
    public static int ImageNum = 1;
    public static String turnImage;
    
    public XOButtons(int gridx, int gridy) {
        X = new ImageIcon(this.getClass().getResource("x.png"));
        O = new ImageIcon(this.getClass().getResource("O.png"));
        this.addActionListener(this);
        this.gridx = gridx;
        this.gridy = gridy;
    }
    
    public static int getImageNum(){ return ImageNum; }
    public static void setImageNum(int x){ ImageNum = x; }
    
    public void actionPerformed(ActionEvent e){
        if ((TTT.getBoard(gridx, gridy) != "X" && TTT.getBoard(gridx, gridy) != "O")){
            if (ImageNum == 2){
                setIcon(X);     turnImage = "X";    
            }else if(ImageNum == 1){
                setIcon(O);     turnImage = "O";
            }
            TTT.setBoard(gridx, gridy, turnImage);
            TTT_GUI.setButtonClicks(gridx, gridy, 1);
            
            int opposite;
            if (ImageNum == 1){ opposite = 2;} else { opposite = 1; }
            TTT_GUI.switchRemaining(opposite);
        }
    }
}

