import java.util.Scanner;
public class task2 {
    public static void main(String[] args) {
        Scanner vhod = new Scanner(System.in);
        int H;
        int D = vhod.nextInt();
        int A = vhod.nextInt();
        H = (D * A) / 2;
        System.out.println(H);
    }
}
