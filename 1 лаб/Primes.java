package labs.lab1;

public class Primes {
    public static void main(String[] args) {
        for (int i = 2; i<100; i++){
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }
    public static boolean isPrime(int n){
        boolean bool = true;
        for(int i = 2; i <= n/i; i++) {
            if(n%i == 0) bool = false;
        }
        return bool;
    }
}