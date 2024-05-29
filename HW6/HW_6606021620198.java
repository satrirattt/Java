
//6606021620198 นางสาวสตรีรัตน์ สีวัน
import java.util.Random;
import javax.swing.JOptionPane;

public class HW_6606021620198 {
    private double money = 0;
    private double interest = 0;

    public HW_6606021620198() {
    }

    public void mainProgram() {
        String m;
        char m2;
        String menu = "=====Menu=====\n";
        menu += "(I)nput money\n";
        menu += "(R)andom interest(1-10%)\n";
        menu += "(C)alculate and show money received\n";
        menu += "Enter menu(Press N exit):";
        do {
            m = JOptionPane.showInputDialog(menu);
            m2 = m.charAt(0);
            switch (m2) {
                case 'I':
                    InputMpney();
                    break;
                case 'R':
                    randomInterest();
                    break;
                case 'C':
                    calMoney();
                    break;

                default:
                    break;
            }
        } while (m2 != 'N');
        JOptionPane.showMessageDialog(null, "Exit Program", "Bank",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void InputMpney() {
        String num = JOptionPane.showInputDialog("Enter money:");
        money = Double.parseDouble(num);
    }

    public void randomInterest() {
        Random rnd = new Random();
        interest = rnd.nextDouble() * 0.1;
        JOptionPane.showMessageDialog(null, "Random Interest = " + (interest * 100));
    }

    public void calMoney() {
        double receivedMoney = money + ((money * interest) / 100);
        JOptionPane.showMessageDialog(null,
                "Money = " + money + "\n" + "interest = " + interest * 100 + "\n" + "Money Received = " + receivedMoney,
                "Result",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public static void main(String[] args) {
        new HW_6606021620198().mainProgram();
    }
}