import java.util.Scanner;
public class task4 {
    public static void main(String[] args) {
        Scanner vhod = new Scanner(System.in);
        float prob = vhod.nextFloat();
        float prize = vhod.nextFloat();
        float pay = vhod.nextFloat();
        if (prob*prize>pay) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
}
