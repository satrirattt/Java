import java.util.Scanner;

public class TestRectangle {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int w, h;
        System.out.print("Enter width : ");
        w = scan.nextInt();
        System.out.print("Enter height : ");
        h = scan.nextInt();
        Rectangle rec = new Rectangle(w, h);
        System.out.println(rec.showRectangle());
    }
}