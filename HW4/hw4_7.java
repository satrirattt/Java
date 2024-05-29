import java.util.Scanner;

public class hw4_7 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num;
        System.out.print("Enter positive number : ");
        num = scan.nextInt();

        if (num <= 1) {
            System.out.println("Number " + num + " is not prime number.");
        } else {
            boolean isPrime = true;

            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.println("Number " + num + " is prime number.");
            } else {
                System.out.println("Number " + num + " is not prime number.");
            }

        }

    }
}
