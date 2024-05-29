import javax.swing.JOptionPane;

public class Example6_7 {
    public Example6_7() {
    }

    public void mainProgram() {
        String i = JOptionPane.showInputDialog("Enter integer number 1 :");
        String i2 = JOptionPane.showInputDialog("Enter integer number 2 :");
        int integer = Integer.parseInt(i);
        int integer2 = Integer.parseInt(i2);
        int result = calInteger(integer, integer2);
        JOptionPane.showMessageDialog(null, "Result integer number = " + result);

        String f = JOptionPane.showInputDialog("Enter floating number 1 :");
        String f2 = JOptionPane.showInputDialog("Enter floating number 2 :");
        float floating = Float.parseFloat(f);
        float floating2 = Float.parseFloat(f2);
        float result2 = calFloating(floating, floating2);
        JOptionPane.showMessageDialog(null, "Result floating number = " + result2);
    }

    public int calInteger(int i, int i2) {
        int result = i + i2;
        return (result);
    }

    public float calFloating(float f, float f2) {
        float result2 = f + f2;
        return (result2);
    }

    public static void main(String[] args) {
        new Example6_7().mainProgram();
    }

}