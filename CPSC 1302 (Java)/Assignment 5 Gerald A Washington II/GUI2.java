import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Scanner;

//import java.lang.Math;

public class GUI2 extends JFrame implements ActionListener{
    public static final int WINDOW_LENGTH = 600;
    public static final int WINDOW_HEIGHT = 450;
    
    public static ArrayList<Double> numbers = new ArrayList<Double>();
    
    public static JPanel titlePanel = new JPanel();
    public static JLabel title = new JLabel("NUMBER SET CALCULATION MAKER");
    
    public static JPanel buttonPanel = new JPanel();
    public static JButton calcButton = new JButton("Calculate");
    public static JButton clearButton = new JButton("Clear All");
    public static JButton estimateButton = new JButton("Estimation: OFF");
    
    public static JPanel TF = new JPanel();
    public static JLabel TFText = new JLabel("Enter Numbers Here: ");
    public static JTextField textField = new JTextField();
    
    public static JPanel displays = new JPanel();
    public static JLabel num_dis_title = new JLabel("Numbers Set");
    public static JLabel num_dis = new JLabel("{  }");
    
    public static JLabel calc_dis_title = new JLabel("Calculations");
    public static JLabel min_dis = new JLabel("Min: ");
    public static JLabel max_dis = new JLabel("Max: ");
    public static JLabel avg_dis = new JLabel("Avg: ");
    
    public boolean decimal = false;
    public boolean estimation = false;
    
    public static void main(String[] args){new GUI2();}
        
    public GUI2(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_LENGTH, WINDOW_HEIGHT);
        setResizable(false);
        
        buttonPanel.setSize(WINDOW_LENGTH/6, WINDOW_HEIGHT/4);
        buttonPanel.setLayout(new GridLayout(1,3));
        
        buttonPanel.add(calcButton);   
        calcButton.addActionListener(this);
        
        buttonPanel.add(clearButton);
        clearButton.addActionListener(this);
        
        buttonPanel.add(estimateButton);
        estimateButton.addActionListener(this);
        
        TF.setLayout(new GridLayout(2,2));
        TF.add(title);
        TF.add(new JLabel());
        TF.setSize(new Dimension(WINDOW_LENGTH, 40));
        TF.add(TFText);
        TF.add(textField);    
        textField.addActionListener(this);
        
        displays.setLayout(new GridLayout(4,1));
        displays.setLayout(new GridLayout(4,1));
        
        displays.add(num_dis_title);
        displays.add(calc_dis_title);
        displays.add(num_dis);
        displays.add(max_dis);
        displays.add(new JLabel());
        displays.add(min_dis);
        displays.add(new JLabel());
        displays.add(avg_dis);
        
        add(TF);
        add(displays);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == textField){
            numbers.add(Double.parseDouble(textField.getText()));
            num_dis.setText("{ " + getDisplayNums() + " }");
        }
        else if (e.getSource() == calcButton){
            if(estimation == false){
                min_dis.setText("Min: " + min_num2());
                max_dis.setText("Max: " + max_num2());
                avg_dis.setText("Avg: " + avg_num2());
            }else{
                min_dis.setText("Min: " + min_num());
                max_dis.setText("Max: " + max_num());
                avg_dis.setText("Avg: " + avg_num());
            }
        }
        else if (e.getSource() == clearButton){
            numbers.clear();
            min_dis.setText("Min: ");
            max_dis.setText("Max: ");
            avg_dis.setText("Avg: ");
            num_dis.setText("{ " + getDisplayNums() + " }");
        }
        else if (e.getSource() == estimateButton){
            if (estimation == true){
                estimateButton.setText("Estimate: OFF");
                estimation = false;
                min_dis.setText("Min: " + min_num2());
                max_dis.setText("Max: " + max_num2());
                avg_dis.setText("Avg: " + avg_num2());
            }else{
                estimateButton.setText("Estimate: ON");
                estimation = true;
                min_dis.setText("Min: " + min_num());
                max_dis.setText("Max: " + max_num());
                avg_dis.setText("Avg: " + avg_num());
            }
            num_dis.setText("{ " + getDisplayNums() + " }"); 
        }
    }
    
    public String getDisplayNums(){
        String s = "";
        for (int x = 0; x < numbers.size(); x++){
            if (estimation == true){
                if (x != 0)
                    s = s + "," + Math.round(numbers.get(x)) + " "; 
                else 
                    s = s + Math.round(numbers.get(x)) + " ";
            }else{
                if (x != 0)
                    s = s + "," + numbers.get(x) + " "; 
                else 
                    s = s + numbers.get(x) + " ";
            }
        }
        return s;
    }
    
    public long min_num(){
        ArrayList<Long> numbers2 = new ArrayList<Long>();
        for (int i = 0; i < numbers.size(); i++){
            numbers2.add(Math.round(numbers.get(i)));
        }
        
        long min = numbers2.get(0);
        for (int x = 0; x < numbers.size(); x++){
            if (min > numbers2.get(x)){ min = numbers2.get(x); }
        }
        return min;
    }
    
    public double min_num2(){
        Double min = numbers.get(0);
        for (int x = 0; x < numbers.size(); x++){
            if (min > numbers.get(x)){ min = numbers.get(x); }
        }
        return min;
    }
    
        public long max_num(){
        ArrayList<Long> numbers2 = new ArrayList<Long>();
        for (int i = 0; i < numbers.size(); i++){
            numbers2.add(Math.round(numbers.get(i)));
        }
        
        long min = numbers2.get(0);
        for (int x = 0; x < numbers.size(); x++){
            if (min < numbers2.get(x)){ min = numbers2.get(x); }
        }
        return min;
    }
    
    public double max_num2(){
        Double min = numbers.get(0);
        for (int x = 0; x < numbers.size(); x++){
            if (min < numbers.get(x)){ min = numbers.get(x); }
        }
        return min;
    }
    
    public long avg_num(){
        ArrayList<Long> numbers2 = new ArrayList<Long>();
        for (int i = 0; i < numbers.size(); i++){
            numbers2.add(Math.round(numbers.get(i)));
        }
        
        long avg = 0;
        for (int x = 0; x < numbers.size(); x++){
            avg += numbers2.get(x);
        }
        avg = avg / numbers2.size();
        return avg;
    }
    
    public double avg_num2(){
        Double avg = 0.0;
        for (int x = 0; x < numbers.size(); x++){
            avg += numbers.get(x); 
        }
        avg = avg / numbers.size();
        return avg;
    }
}