import java.util.Scanner;

public class hw4_5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long num;
        System.out.print("Enter positive number : ");
        num = scan.nextLong();

        int oddDigits = 0;
        int evenDigits = 0;
        int zeroDigits = 0;

        while (num > 0) {
            int digit = (int) (num % 10);

            if (digit % 2 == 0 && digit != 0) {
                evenDigits++;

            } else if (digit == 0) {
                zeroDigits++;
            } else {
                oddDigits++;
            }
            num /= 10;
        }
        System.out.println("Odd digit = " + oddDigits);
        System.out.println("Even digit = " + evenDigits);
        System.out.println("Zero digit = " + zeroDigits);

    }

}
