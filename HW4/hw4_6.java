import java.util.Scanner;

public class hw4_6 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int l, t;
        String c;
        System.out.print("Input number line : ");
        l = scan.nextInt();
        while (l > 10 || l <= 0) {
            System.out.println("Please enter line 1 to 10");
            return;
        }

        System.out.print("Input character : ");
        c = scan.next();
        System.out.print("Input type : ");
        t = scan.nextInt();

        if (t == 1) {
            for (int i = 1; i <= l; i++) {
                for (int j = 1; j <= i; j++) {
                    System.out.print(c);
                }
                System.out.println();
            }

        } else if (t == 2) {
            for (int i = l; i >= 1; i--) {
                for (int j = 1; j <= i; j++) {
                    System.out.print(c);
                }
                System.out.println();
            }

        }

    }
}
