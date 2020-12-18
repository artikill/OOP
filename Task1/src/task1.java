import java.util.Scanner;
public class task1 {
    public static void main(String[] args) {
        Scanner vhod = new Scanner(System.in);
        int H ;
        int D = vhod.nextInt();
        int A = vhod.nextInt();
        H = D%A;
        System.out.println(H);
    }
}