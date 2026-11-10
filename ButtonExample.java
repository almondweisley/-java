import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonExample {
    public static void main(String[] args){
    JFrame frame = new JFrame();
    
    JButton cButton = new JButton("Click me");
    JLabel cLabel = new JLabel("Waiting to be clicked...");


    frame.setTitle("Button Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setBackground(Color.ORANGE);
    frame.setSize(400, 300);
    frame.setLayout(new FlowLayout());

    frame.add(cButton);
    frame.add(cLabel);

    cButton.addActionListener(
        new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            cLabel.setText("Button Clicked!");
        }
    });
    
    frame.setVisible(true);
    }
}

