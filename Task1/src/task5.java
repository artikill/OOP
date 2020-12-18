import java.util.Scanner;
public class task5 {
    public static void main(String[] args) {
        Scanner vhod = new Scanner(System.in);
        float N = vhod.nextFloat();
        float a = vhod.nextFloat();
        float b = vhod.nextFloat();
        if (a * b == N) {
            System.out.println("Умножение");
        } else if (a + b == N) {
            System.out.println("Сложение");
        } else if (a / b == N) {
            System.out.println("Деление");
        } else if (a - b == N) {
            System.out.println("Вычитание");
        } else {
            System.out.println("Невозможно получить результат");
        }
    }
}