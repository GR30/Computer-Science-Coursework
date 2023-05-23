 

/**
 * Created by Blake on 2/20/2015.
 */
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class clickerTest extends JFrame {
    JPanel panel = new JPanel();
    public static void main(String[] args){
        new clickerTest();
    }
    public clickerTest(){
        super("Clicker test class");
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("clicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Released");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Entered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
            }
        });
        //Buttons click = new Buttons();

        add(panel);

        setVisible(true);
    }
}
