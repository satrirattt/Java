import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;

public class hw8_1 {
    public static void main(String[] args) {
        new MyCalculator();
    }
}

class MyCalculator implements ActionListener {
    JFrame win;
    JTextField fnum;
    JButton[] bBotton;
    JPanel jpanel1, jpanel2;
    Font myFont = new Font("Tahoma", Font.BOLD, 18);
    double dnum1, dnum2, result;
    char op;

    public MyCalculator() {
        win = new JFrame("Calculator");
        Container c = win.getContentPane();
        c.setLayout(new FlowLayout());

        jpanel1 = new JPanel();
        jpanel1.setPreferredSize(new Dimension(270, 40));
        jpanel1.setLayout(new FlowLayout());
        jpanel1.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        fnum = new JTextField(14);
        fnum.setEditable(false);
        fnum.setFont(myFont);
        fnum.setHorizontalAlignment(JTextField.RIGHT);
        jpanel1.add(fnum);

        jpanel2 = new JPanel();
        jpanel2.setPreferredSize(new Dimension(250, 200));
        jpanel2.setLayout(new GridLayout(5, 3));
        jpanel2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        String[] textBtn = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "C", "+", "-", "=" };
        bBotton = new JButton[textBtn.length];
        for (int n = 0; n < bBotton.length; n++) {
            bBotton[n] = new JButton(textBtn[n]);
            bBotton[n].setFont(myFont);
            bBotton[n].addActionListener(this);
            jpanel2.add(bBotton[n]);
        }

        c.add(jpanel1);
        c.add(jpanel2);
        win.setSize(300, 300);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        String str = fnum.getText();
        String buttonText = ((JButton) event.getSource()).getText();

        switch (buttonText) {
            case "C":
                dnum1 = 0;
                dnum2 = 0;
                result = 0;
                op = '\0';
                str = "";
                break;
            case "+":
            case "-":
                if (!str.isEmpty() && op == '\0') {
                    dnum1 = Double.parseDouble(str);
                    str = "";
                }
                op = buttonText.charAt(0);
                break;
            case "=":
                if (!str.isEmpty() && op != '\0') {
                    dnum2 = Double.parseDouble(str);
                    calculate();
                    if (result == (int) result) {
                        str = String.format("%.0f", result);
                    } else {
                        str = String.format("%.1f", result);
                    }
                    dnum1 = 0;
                    dnum2 = 0;
                    result = 0;
                    op = '\0';
                }
                break;
            default:
                str += buttonText;
                break;
        }

        fnum.setText(str);
    }

    public void calculate() {
        switch (op) {
            case '+':
                result = dnum1 + dnum2;
                break;
            case '-':
                result = dnum1 - dnum2;
                break;
            default:
                break;
        }
    }
}
