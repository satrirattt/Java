import java.util.Scanner;

public class hw4_3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int sale;
        double comrate, com;
        System.out.print("Enter sale amount : ");
        sale = scan.nextInt();

        if (sale <= 10000) {
            comrate = 0.0;

        } else if (sale <= 20000) {
            comrate = 0.015;

        } else if (sale <= 40000) {
            comrate = 0.0275;

        } else if (sale <= 60000) {
            comrate = 0.05;

        } else if (sale <= 80000) {
            comrate = 0.07;

        } else if (sale <= 100000) {
            comrate = 0.09;

        } else {
            comrate = 0.10;
        }

        com = sale * comrate;
        System.out.println("You got rate = " + (comrate * 100) + "%");
        System.out.println("Your sale " + sale + " , " + "commission = " + com);

    }
}
