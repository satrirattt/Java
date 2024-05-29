import java.util.Scanner;

public class hw4_4 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int y;
        double i, p;
        System.out.println("Program Calculate Money");
        System.out.println("+++++++++++++++++++++++");
        System.out.print("Enter principle : ");
        p = scan.nextDouble();
        System.out.print("Enter interest rate : ");
        i = scan.nextDouble();
        System.out.print("Enter year : ");
        y = scan.nextInt();
        System.out.println("Year\tPrinciple\tInterest\tTotal");
        System.out.println("===============================================");
        int year = 1;
        while (year <= y) {
            double interest = p * (i / 100);
            double total = p + interest;

            System.out.printf("%d\t%,.2f\t%,.2f\t\t%,.2f\n", year, p, interest, total);

            p = total;

            year++;
        }

    }
}
