/**
 * Created by Blake on 2/17/2015.
 */
import javax.swing.*;
import java.awt.event.*;

public class XOButton extends JButton implements ActionListener{
    ImageIcon X,O;
    byte value = 0;
    //0 = nothing, 1 = X, 2 = O;
    public XOButton(){
        X = new ImageIcon(this.getClass().getResource("x.png"));
        O = new ImageIcon(this.getClass().getResource("O.png"));
        this.addActionListener(this);
        this.addMouseListener(new MouseAdapter() {
            boolean pressed;
            @Override
            public void mousePressed(MouseEvent e){
                pressed = true;
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(pressed){
                    if(SwingUtilities.isRightMouseButton(e)){
                        setIcon(null);
                    }
                }
            }
        });


    }
    public void actionPerformed(ActionEvent e){
        value ++;
        value%=3;
        switch(value){
            case 0:
                setIcon(null);
                break;
            case 1:
                setIcon(X);
                break;
            case 2:
                setIcon(O);
                break;
        }

    }

}