import java.util.Scanner;

public class hw4_2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        float num, num1, num2, num3, num4;
        int sum = 0;

        System.out.print("Enter floating-point number : ");
        num = scan.nextFloat();

        if (num >= 0.1111 && num <= 0.9999) {
            num1 = (int) (num * 10);
            num2 = (int) (num * 100) % 10;
            num3 = (int) (num * 1000) % 10;
            num4 = (int) (num * 10000) % 10;

            System.out.println("Integer number 1 : " + (int) num1);
            if ((num1 % 2) == 0) {
                System.out.println("Sum = " + sum + " + " + (int) num1 + " = " + (int) (sum + num1));
                sum = sum + (int) num1;

            } else {
                System.out.println("Sum = " + "0" + " - " + (int) num1 + " = " + (int) (sum - num1));
                sum = sum - (int) num1;

            }

            System.out.println("Integer number 2 : " + (int) num2);
            if ((num2 % 2) == 0) {
                System.out.println("Sum = " + sum + " + " + (int) num2 + " = " + (int) (sum + num2));
                sum = sum + (int) num2;
            } else {
                System.out.println("Sum = " + sum + " - " + (int) num2 + " = " + (int) (sum - num2));
                sum = sum - (int) num2;
            }

            System.out.println("Integer number 3 : " + (int) num3);
            if ((num3 % 2) == 0) {
                System.out.println("Sum = " + sum + " + " + (int) num3 + " = " + (int) (sum + num3));
                sum = sum + (int) num3;

            } else {
                System.out.println("Sum = " + sum + " - " + (int) num3 + " = " + (int) (sum - num3));
                sum = sum - (int) num3;
            }

            System.out.println("Integer number 4 : " + (int) num4);
            if ((num4 % 2) == 0) {
                System.out.println("Sum = " + sum + " + " + (int) num4 + " = " + (int) (sum + num4));
                sum = sum + (int) num4;

            } else {
                System.out.println("Sum = " + sum + " - " + (int) num4 + " = " + (int) (sum - num4));
                sum = sum - (int) num4;
            }

        } else {
            System.out.println("Number not changed");
        }

    }
}