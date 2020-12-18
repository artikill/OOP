import java.util.Scanner;
public class task3 {
    public static void main(String[] args) {
    Scanner vhod = new Scanner(System.in);
    int animals;
    int CH = vhod.nextInt();
    int CO = vhod.nextInt();
    int PI = vhod.nextInt();
    animals=CH*2+CO*4+PI*4;
    System.out.println(animals);
}
}
