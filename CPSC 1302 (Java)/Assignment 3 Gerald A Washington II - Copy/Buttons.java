import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Buttons extends JButton{
    public static boolean unchecked;
    public static int numOfButtons;
    public static ArrayList<Integer> ButtonArray = new ArrayList<Integer>();
    
    public Buttons(){ new Buttons(); }
    public static void setNumButtons(int x){ numOfButtons = x;}
    public static int getNumOfButtons(){ return numOfButtons;}
    
}
