import javax.swing.*; //Provide GUI components
import java.awt.*;
import java.awt.event.*;
import java.math.*;


public class SimpleCalculator {
    static final int max_digits = 9;

    static public int digitsInCurrentNumber(String text) {
        int count = 0;
        for(int i = text.length() -1; i >= 0; i--){
            char c = text.charAt(i);
            if (Character.isDigit(c)) count ++;
            else if (c != '.') break;
        } 
        return count;
    }

   
    public static void main(String args[]) {
        JFrame frame = new JFrame(); //Creating the MainFrame

        frame.setTitle("Simple Calculator");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setLayout(new BorderLayout());

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.DARK_GRAY); 
        buttons.setLayout(new GridLayout(5, 4, 2, 2));
        JTextField displayField = new JTextField();

        //Buttons
        JButton _1Btn = new JButton("1");
        JButton _2Btn = new JButton("2");
        JButton _3Btn = new JButton("3");
        JButton _4Btn = new JButton("4");
        JButton _5Btn = new JButton("5");
        JButton _6Btn = new JButton("6");
        JButton _7Btn = new JButton("7");
        JButton _8Btn = new JButton("8");
        JButton _9Btn = new JButton("9");
        JButton _0Btn = new JButton("0");
        JButton addBtn = new JButton("+");
        JButton subtractBtn = new JButton("-");
        JButton divideBtn = new JButton("/");
        JButton multBtn = new JButton("*");
        JButton _acBtn = new JButton("AC");
        JButton clearBtn = new JButton("X");
        JButton pointBtn = new JButton(".");
        JButton equalBtn = new JButton("=");
        JButton openBracketBtn = new JButton("(");
        JButton closeBracketBtn = new JButton(")");


        //Adding buttons to the panel
        frame.add(buttons, BorderLayout.CENTER);
        frame.add(displayField, BorderLayout.NORTH);
        buttons.add(_acBtn);
        buttons.add(openBracketBtn);
        buttons.add(closeBracketBtn);
        buttons.add(divideBtn);
        buttons.add(_7Btn);
        buttons.add(_8Btn);
        buttons.add(_9Btn);
        buttons.add(multBtn);
        buttons.add(_4Btn);
        buttons.add(_5Btn);
        buttons.add(_6Btn);
        buttons.add(subtractBtn);
        buttons.add(_1Btn);
        buttons.add(_2Btn);
        buttons.add(_3Btn);
        buttons.add(addBtn);
        buttons.add(_0Btn);
        buttons.add(pointBtn);
        buttons.add(clearBtn);
        buttons.add(equalBtn);

        //Button Actions
        _0Btn.addActionListener(e ->{
            try {
                if (digitsInCurrentNumber(displayField.getText()) < max_digits) {
                displayField.setText(displayField.getText() + "0");
                }
            } catch (StringIndexOutOfBoundsException ex) {
                System.out.println("You have reached the maximum number of digits!");
            }
        });
        _1Btn.addActionListener(e ->{
            try {
                if (digitsInCurrentNumber(displayField.getText()) < max_digits) {
                displayField.setText(displayField.getText() + "1");
                }
            } catch (Exception ex) {
            }
        });
        _2Btn.addActionListener(e ->{
            try {
                if (digitsInCurrentNumber(displayField.getText()) < max_digits) {
                displayField.setText(displayField.getText() + "2");
                }
            } catch (Exception ex) {
            }
        });
        _3Btn.addActionListener(e ->{
            try {
                if (digitsInCurrentNumber(displayField.getText()) < max_digits) {
                displayField.setText(displayField.getText() + "3");
                }
            } catch (Exception ex) {
            }
        });
        _4Btn.addActionListener(e ->{
            try {
                if (digitsInCurrentNumber(displayField.getText()) < max_digits) {
                displayField.setText(displayField.getText() + "4");
                }
            } catch (Exception ex) {
            }
        });
        _5Btn.addActionListener(e ->{
            try {
                if (digitsInCurrentNumber(displayField.getText()) < max_digits) {
                displayField.setText(displayField.getText() + "5");
                }
            } catch (Exception ex) {
            }
        });
        _6Btn.addActionListener(e ->{
            try {
                displayField.setText("6");
            } catch (Exception ex) {
            }
        });
        _7Btn.addActionListener(e ->{
            try {
                displayField.setText("7");
            } catch (Exception ex) {
            }
        });
        _8Btn.addActionListener(e ->{});
        _9Btn.addActionListener(e ->{});
        _acBtn.addActionListener(e ->{});
        equalBtn.addActionListener(e ->{});
        clearBtn.addActionListener(e ->{});
        pointBtn.addActionListener(e ->{});
        addBtn.addActionListener(e ->{});
        subtractBtn.addActionListener(e ->{});
        multBtn.addActionListener(e ->{});
        divideBtn.addActionListener(e ->{});
        closeBracketBtn.addActionListener(e ->{});
        openBracketBtn.addActionListener(e ->{});

        frame.setVisible(true); 
    }

}