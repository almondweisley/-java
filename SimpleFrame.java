import java.awt.*;
import javax.swing.*;

public class SimpleFrame extends JFrame{
    public static void main(String args[]){
        JFrame frame = new JFrame("GUI Basics");

        JButton btn = new JButton("CLick me!");

        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        frame.add(btn);   
    }
}