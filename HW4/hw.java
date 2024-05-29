import java.util.Scanner;

public class hw {
    public static void main(String[] args) {
        double num, re = 0;
        int fr, sc, th, fo, cal = 0, num2;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter floting point number :");
        num = scan.nextDouble();
        num *= 10000;
        num2 = (int) num;
        if (num == num2) {
            if (num > 1110.9 && num < 9999.1) {
                fr = (int) num % 10;
                re = num / 10;
                sc = (int) re % 10;
                re = re / 10;
                th = (int) re % 10;
                re = re / 10;
                fo = (int) re % 10;
                re = re / 10;
                if (fo == 0) {
                    System.out.println("Integr number 1 = " + fo);

                    System.out.println("Sum = " + cal + " + " + fo + " = " + (cal + fo));
                    cal = cal + fo;
                } else {
                    System.out.println("Sum = " + cal + " - " + fo + " = " + (cal - fo));
                    cal = cal - fo;
                }

                System.out.println("Integr number 2 = " + th);
                if ((th % 2) == 0) {
                    System.out.println("Sum = " + cal + " + " + th + " = " + (cal + th));
                    cal = cal + th;
                } else {
                    System.out.println("Sum = " + cal + " - " + th + " = " + (cal - th));
                    cal = cal - th;
                }

                System.out.println("Integr number 3 = " + sc);
                if ((sc % 2) == 0) {
                    System.out.println("Sum = " + cal + " + " + sc + " = " + (cal + sc));
                    cal = cal + sc;
                } else {
                    System.out.println("Sum = " + cal + " - " + sc + " = " + (cal - sc));
                    cal = cal - sc;

                    System.out.println("Integr number 4 = " + fr);
                    if ((fr % 2) == 0) {
                        System.out.println("Sum = " + cal + " + " + fr + " = " + (cal + fr));
                        cal = cal + fr;
                    } else {
                        System.out.println("Sum = " + cal + " - " + fr + " = " + (cal - fr));
                        cal = cal - fr;
                    }
                }
            } else
                System.out.println("Number not changed ");

        } else
            System.out.println("Number not changed ");
    }
}