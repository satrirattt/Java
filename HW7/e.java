import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;

public class e {
    public static void main(String[] args) {
        new MyCal();
    }
}

class MyCal implements ActionListener {
    JFrame window;
    JTextField numberField;
    JButton[] btn;
    JPanel panel1, panel2;
    Font myFont = new Font("Tahoma", Font.BOLD, 18);
    double num1, num2, result;
    char operator;

    public MyCal() {
        window = new JFrame("Calculator");
        Container c = window.getContentPane();
        c.setLayout(new FlowLayout());

        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(270, 40));
        panel1.setLayout(new FlowLayout());
        panel1.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        numberField = new JTextField(14);
        numberField.setEditable(false);
        numberField.setFont(myFont);
        numberField.setHorizontalAlignment(JTextField.RIGHT);
        panel1.add(numberField);

        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(250, 200));
        panel2.setLayout(new GridLayout(5, 3));
        panel2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        String[] textBtn = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "C", "+", "-", "=" };
        btn = new JButton[textBtn.length];
        for (int n = 0; n < btn.length; n++) {
            btn[n] = new JButton(textBtn[n]);
            btn[n].setFont(myFont);
            btn[n].addActionListener(this);
            panel2.add(btn[n]);
        }

        c.add(panel1);
        c.add(panel2);
        window.setSize(300, 300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        String str = numberField.getText();
        String buttonText = ((JButton) event.getSource()).getText();

        switch (buttonText) {
            case "C":
                num1 = 0;
                num2 = 0;
                result = 0;
                operator = '\0';
                str = "";
                break;
            case "+":
            case "-":
                if (!str.isEmpty() && operator == '\0') {
                    num1 = Double.parseDouble(str);
                    str = "";
                }
                operator = buttonText.charAt(0);
                break;
            case "=":
                if (!str.isEmpty() && operator != '\0') {
                    num2 = Double.parseDouble(str);
                    calculate();
                    if (result == (int) result) {
                        str = String.format("%.0f", result);
                    } else {
                        str = String.format("%.1f", result);
                    }
                    num1 = 0;
                    num2 = 0;
                    result = 0;
                    operator = '\0';
                }
                break;
            default:
                str += buttonText;
                break;
        }

        numberField.setText(str);
    }

    public void calculate() {
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            default:
                break;
        }
    }
}
