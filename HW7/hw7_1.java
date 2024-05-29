import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class hw7_1 {
    public static void main(String[] args) {
        int n;
        String str;
        str = JOptionPane.showInputDialog("Enter the size of the multiplication table: ");
        n = Integer.parseInt(str);
        printMultiplicationTable(n);
    }

    public static void printMultiplicationTable(int n) {
        String stext = "";

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                stext += (i * j) + "\t";
            }
            stext += "\n";
        }
        JTextArea output = new JTextArea();
        output.setText(stext);
        JOptionPane.showMessageDialog(null, output, "Multiplication Table", JOptionPane.INFORMATION_MESSAGE);
    }
}
