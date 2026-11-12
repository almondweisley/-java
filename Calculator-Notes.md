# SimpleCalculator: How the Display Fixes Work

Notes for `SimpleCalculator.java` in this folder. They cover three goals:

1. Show more than one digit on the display.
2. Limit each number to 9 digits (e.g. `999999999`).
3. Replace the sum with its answer when `=` is pressed.

The complete working code is at the end of this file.

---

## 1. Showing more than one digit

**The problem:** each button did this:

```java
displayField.setText("1");
```

`setText` deletes whatever is on the screen and puts in only `"1"`.

**The fix:** add the new digit to the end of what is already there:

```java
displayField.setText(displayField.getText() + "1");
```

---

## 2. Stopping a number at 9 digits

A `for` loop inside a button's listener runs all its rounds in one click, so it
can't limit anything. Instead, each time a digit button is pressed, count the
digits in the number being typed. If there are already 9, ignore the press.

```java
static final int MAX_DIGITS = 9;

// Counts the digits at the end of the display: "12+345" -> 3
static int digitsInCurrentNumber(String text) {
    int count = 0;
    for (int i = text.length() - 1; i >= 0; i--) {
        char c = text.charAt(i);
        if (Character.isDigit(c)) count++;
        else if (c != '.') break;   // hit + - * / ( ) so the number has ended
    }
    return count;
}
```

Then in the button's code:

```java
if (digitsInCurrentNumber(display.getText()) >= MAX_DIGITS) {
    return;   // already 9 digits, so the press does nothing
}
display.setText(display.getText() + digit);
```

- You can type `999999999` but not a tenth 9.
- Each number in the sum gets its own limit, so `777777777+111111111` works.
- To limit the whole screen to 9 characters instead, use
  `display.getText().length() >= 9`.

---

## 3. Replacing the sum with the answer

When `=` is pressed, work out the sum and put the answer on the screen with
`setText`. This time replacing what's there is what we want. A `showingAnswer`
flag remembers that an answer is on screen, so the next digit typed starts a new
sum.

```java
static boolean showingAnswer = false;

static void pressEquals(JTextField display) {
    try {
        double result = new ExpressionParser(display.getText()).parse();
        if (Double.isInfinite(result) || Double.isNaN(result)) throw new ArithmeticException(); // e.g. 5/0
        display.setText(format(result));   // the answer replaces the sum
    } catch (Exception ex) {
        display.setText("Error");
    }
    showingAnswer = true;
}

static void pressNumber(JTextField display, String digit) {
    if (showingAnswer) {          // after "=", a new number starts a new sum
        display.setText("");
        showingAnswer = false;
    }
    // ...the 9-digit check and append from section 2...
}
```

- Pressing a **digit** after an answer starts a new sum.
- Pressing **`+ - * /`** after an answer carries on from it, so `10 = * 3 =` gives `30`.

**Why `ExpressionParser`?** Java has no built-in way to work out a typed sum
like `2+3*(4-1)`. The calculator has bracket buttons, so the code includes a
small parser class that handles brackets and does `*` and `/` before `+` and `-`.

---

## Other changes in the full version

- **One loop instead of ten copies.** All digit buttons do the same thing:
  ```java
  for (JButton btn : numberBtns) {
      btn.addActionListener(e -> pressNumber(displayField, btn.getText()));
  }
  ```
- **The flags are `static` fields, not variables inside `main`.** Java won't let
  a lambda (`e -> ...`) change a local variable, so `showingAnswer` has to live
  outside `main`.
- **The display can't be typed into.** `displayField.setEditable(false)` stops
  people typing on the keyboard and getting around the 9-digit limit.
- **AC and X work.** AC clears everything; X deletes the last character.

## What was tested

These all gave the right result when the buttons were pressed from code:

| Input | Display shows |
|---|---|
| `1`, `2`, `3` | `123` |
| `7` pressed 12 times | `777777777` (stops at 9) |
| `777777777 + 111111111 =` | `888888888` |
| then `5` | `5` (new sum) |
| `10 = * 3 =` | `30` |
| `2+3*(4-1) =` | `11` |
| `7/2 =` | `3.5` |
| `-4*-2 =` | `8` |
| `5/0 =` | `Error` |
| `5+ =` | `Error` |

---

## Full working code

```java
import javax.swing.*; //Provide GUI components
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator {
    static final int MAX_DIGITS = 9;      // the most digits one number can have
    static boolean showingAnswer = false; // true right after "=" is pressed

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
        displayField.setEditable(false); // only the buttons can change the display
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(new Font("Arial", Font.BOLD, 24));

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
        // Number buttons (and the point) all do the same thing, so one loop sets them up
        JButton[] numberBtns = {_0Btn, _1Btn, _2Btn, _3Btn, _4Btn,
                                _5Btn, _6Btn, _7Btn, _8Btn, _9Btn, pointBtn};
        for (JButton btn : numberBtns) {
            btn.addActionListener(e -> pressNumber(displayField, btn.getText()));
        }

        // Operator and bracket buttons
        JButton[] symbolBtns = {addBtn, subtractBtn, multBtn, divideBtn,
                                openBracketBtn, closeBracketBtn};
        for (JButton btn : symbolBtns) {
            btn.addActionListener(e -> pressSymbol(displayField, btn.getText()));
        }

        equalBtn.addActionListener(e -> pressEquals(displayField));

        // AC: wipe everything
        _acBtn.addActionListener(e -> {
            displayField.setText("");
            showingAnswer = false;
        });

        // X: delete the last character
        clearBtn.addActionListener(e -> {
            String text = displayField.getText();
            if (!text.isEmpty()) {
                displayField.setText(text.substring(0, text.length() - 1));
            }
            showingAnswer = false;
        });

        frame.setVisible(true);
    }

    static void pressNumber(JTextField display, String digit) {
        // After "=", typing a number starts a brand new sum
        if (showingAnswer) {
            display.setText("");
            showingAnswer = false;
        }
        // Stop the number at 9 digits (e.g. 999999999)
        if (digitsInCurrentNumber(display.getText()) >= MAX_DIGITS) {
            return;
        }
        // APPEND to what is already there instead of replacing it
        display.setText(display.getText() + digit);
    }

    static void pressSymbol(JTextField display, String symbol) {
        // After "=", pressing + - * / carries on from the answer
        showingAnswer = false;
        display.setText(display.getText() + symbol);
    }

    static void pressEquals(JTextField display) {
        String sum = display.getText();
        if (sum.isEmpty()) {
            return;
        }
        try {
            double result = new ExpressionParser(sum).parse();
            if (Double.isInfinite(result) || Double.isNaN(result)) { // e.g. 5/0
                throw new ArithmeticException();
            }
            display.setText(format(result)); // the answer REPLACES the sum
        } catch (Exception ex) {
            display.setText("Error");
        }
        showingAnswer = true;
    }

    // Counts the digits at the end of the display, i.e. in the number being typed.
    // "12+345" -> 3, "7*(12.5" -> 3
    static int digitsInCurrentNumber(String text) {
        int count = 0;
        for (int i = text.length() - 1; i >= 0; i--) {
            char c = text.charAt(i);
            if (Character.isDigit(c)) {
                count++;
            } else if (c != '.') {
                break; // reached an operator or bracket, so the number has ended
            }
        }
        return count;
    }

    // Show whole numbers without ".0"
    static String format(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    // Works out text like "2+3*(4-1)", following the normal order of operations
    static class ExpressionParser {
        private final String text;
        private int pos = 0;

        ExpressionParser(String text) {
            this.text = text;
        }

        double parse() {
            double value = parseAddSubtract();
            if (pos < text.length()) {
                throw new IllegalArgumentException("Unexpected " + text.charAt(pos));
            }
            return value;
        }

        // Handles + and - (done last)
        private double parseAddSubtract() {
            double value = parseMultiplyDivide();
            while (pos < text.length()) {
                char op = text.charAt(pos);
                if (op == '+') {
                    pos++;
                    value += parseMultiplyDivide();
                } else if (op == '-') {
                    pos++;
                    value -= parseMultiplyDivide();
                } else {
                    break;
                }
            }
            return value;
        }

        // Handles * and / (done before + and -)
        private double parseMultiplyDivide() {
            double value = parseNumberOrBrackets();
            while (pos < text.length()) {
                char op = text.charAt(pos);
                if (op == '*') {
                    pos++;
                    value *= parseNumberOrBrackets();
                } else if (op == '/') {
                    pos++;
                    value /= parseNumberOrBrackets();
                } else {
                    break;
                }
            }
            return value;
        }

        // Handles a single number, a negative sign, or a whole (bracketed) part
        private double parseNumberOrBrackets() {
            if (pos >= text.length()) {
                throw new IllegalArgumentException("Sum ends too early");
            }
            char c = text.charAt(pos);
            if (c == '-') {               // negative number, e.g. -5
                pos++;
                return -parseNumberOrBrackets();
            }
            if (c == '(') {
                pos++;
                double value = parseAddSubtract();
                if (pos >= text.length() || text.charAt(pos) != ')') {
                    throw new IllegalArgumentException("Missing )");
                }
                pos++;
                return value;
            }
            int start = pos;
            while (pos < text.length()
                    && (Character.isDigit(text.charAt(pos)) || text.charAt(pos) == '.')) {
                pos++;
            }
            if (start == pos) {
                throw new IllegalArgumentException("Expected a number");
            }
            return Double.parseDouble(text.substring(start, pos));
        }
    }
}
```
