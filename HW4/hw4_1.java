import java.util.Scanner;

public class hw4_1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num, num1, num2, num3, num4;
        char choice;
        System.out.print("Enter integer number(4 digit) : ");
        num = scan.nextInt();
        num1 = num / 1000 % 10;
        num2 = num / 100 % 10;
        num3 = num / 10 % 10;
        num4 = num % 10;

        if (num4 % 2 == 0) {
            System.out.println(num4 + " is Even");

        } else {
            System.out.println(num4 + " is Odd");
        }
        if (num3 % 2 == 0) {
            System.out.println(num3 + " is Even");

        } else {
            System.out.println(num3 + " is Odd");
        }
        if (num2 % 2 == 0) {
            System.out.println(num2 + " is Even");

        } else {
            System.out.println(num2 + " is Odd");
        }

        if (num1 % 2 == 0) {
            System.out.println(num1 + " is Even");

        } else {
            System.out.println(num1 + " is Odd");
        }

        // 4
        if (num % 2 == 0) {
            System.out.println("Number " + num + " is Even. ");
        } else {
            System.out.println("Number " + num + " is Odd.");
        }

        // exit
        System.out.print("Do you want to exit(Y/N) : ");
        choice = scan.next().charAt(0);

        if (choice == 'Y' || choice == 'y') {
            System.out.println("Exit");

        }

    }
}