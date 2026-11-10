import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sum {
    public static void main(String args[]){
        JFrame frame = new JFrame();


        JLabel num1Label = new JLabel("Num1");
        JTextField num1TextField = new JTextField();
        JLabel num2Label = new JLabel("Num2");
        JTextField num2TextField = new JTextField();
        JButton addBtn = new JButton("Add");
        JTextField sumField = new JTextField();

        frame.setSize(400, 300);
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 2));

        frame.add(num1Label);
        frame.add(num1TextField);
        frame.add(num2Label);
        frame.add(num2TextField);
        frame.add(addBtn);
        frame.add(sumField);
        sumField.setEditable(false);


        addBtn.addActionListener( e -> {
                    try {
                int number1 = Integer.parseInt(num1TextField.getText());
                int number2 = Integer.parseInt(num2TextField.getText());
                int sum = number1 + number2;
                sumField.setText(String.valueOf(sum));
            } catch(NumberFormatException ex) {
                sumField.setText("Enter valid numbers");
            }

        
    } );
    frame.setVisible(true);
}
}