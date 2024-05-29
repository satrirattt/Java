import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Example8 implements ActionListener {
    JLabel lNum1, lNum2, lResult;
    JTextField fNum1, fNum2, fResult;
    JButton bAdd, bSub;
    JPanel jPanel1, jPanel2;

    public Example8() {
        JFrame window = new JFrame("Calculate");
        Container c = window.getContentPane();
        c.setLayout(new FlowLayout());

        jPanel1 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(320, 100));
        jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        jPanel1.setBorder(new LineBorder(Color.RED, 1));

        jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(320, 50));
        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        jPanel2.setBorder(new LineBorder(Color.blue, 1));

        lNum1 = new JLabel("Enter number 1 ");
        jPanel1.add(lNum1);
        fNum1 = new JTextField(10);
        jPanel1.add(fNum1);

        lNum2 = new JLabel("Enter number 2 ");
        jPanel1.add(lNum2);
        fNum2 = new JTextField(10);
        jPanel1.add(fNum2);

        lResult = new JLabel("Result value is ");
        jPanel1.add(lResult);
        fResult = new JTextField(15);
        fResult.setEditable(false);
        jPanel1.add(fResult);
        c.add(jPanel1);

        bAdd = new JButton(" + ");
        bAdd.addActionListener(this);
        jPanel2.add(bAdd);
        bSub = new JButton(" - ");
        bSub.addActionListener(this);
        jPanel2.add(bSub);
        c.add(jPanel2);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(350, 200);
        window.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        int n1 = Integer.parseInt(fNum1.getText());
        int n2 = Integer.parseInt(fNum2.getText());
        int total = 0;
        if (event.getSource() == bAdd) {
            total = n1 + n2;
            fResult.setText("" + total);
        } else if (event.getSource() == bSub) {
            total = n1 - n2;
            fResult.setText("" + total);
        }
    }

    public static void main(String[] args) {
        new Example8();

    }
}