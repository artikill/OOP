import java.util.Arrays;
import java.util.Scanner;

public class task3 {
    public static boolean rightTriangle(double x, double y, double z) {
        boolean Tr=false;
        if (x != 0 && y != 0 && z != 0) {
            if(x != 3 && y != 4 && z != 5 | x != 3 && y != 5 && z != 4 | x != 4 && y != 3 && z != 5 | x != 4 && y != 5 && z != 3 | x != 5 && y != 4 && z != 3 | x != 5 && y != 3 && z != 4){
                if(x * x + y * y == z * z || x * x == y * y + z * z || x * x + z * z == y * y) {
                    System.out.println("This is a less famous example.");
                    Tr= true;
                }else {
                    System.out.println("This isn't a right angled triangle.");
                    Tr=false;
                }

            }else  if((x == 3 && y == 4 && z == 5 )| (x == 3 && y == 5 && z == 4) |( x == 4 && y == 3 && z == 5) | (x == 4 && y == 5 && z == 3) | (x == 5 && y == 4 && z == 3) | (x == 5 && y == 3 && z == 4)){
                System.out.println("This is the classic example of a 'nice' right angled triangle.");
                Tr= true;
            }
        }
        return Tr;
    }

    public static int nextPrime(int a){
        int b=2;
        do {
            b++;
            for (int i = 2; i < a; i++)
                if (a % i == 0) {
                    a++;

                }
        } while (a/b!=1);


        return a;
    }
    public static String longestZero(String a) {
        StringBuilder nullif = new StringBuilder();
        String count = "";

        for(int i = 0; i < a.length() - 1; i++) {
            if (a.charAt(i) == '0') {
                nullif.append("0");
            } else {
                if (count.length() < nullif.length()) {
                    count = nullif.toString();
                }
                nullif = new StringBuilder();

            }
        }

        if (count.length() < nullif.length()) {
            count = nullif.toString();
        }

        return count;
    }


    public static boolean isKaprekar(int a){
        boolean res = false;
        int b = a*a;
        String D= String.valueOf(b);
        System.out.println(D);
        if (D.length() == 1) {
            int a0 = 0;
            if(a0+ b ==a){
                res=true;
            }
        } else  {
            int a1 = Integer.parseInt(D.substring(0, D.length() / 2));
            int b1 = Integer.parseInt(D.substring(D.length() / 2));
            if(a1+b1==a ){
                res = true;
            }
        }
        return res;
    }

    public static boolean same(int [] array, int[] array1 ){
        int collect=0;
        int collect1=0;
        Arrays.sort(array);
        Arrays.sort(array1);
        boolean same= false;
        for(int i=0;i<array.length-1 ;i++)
            if ((array[i] != array[i + 1])) {
                collect++;

            }
        for(int i=0;i<array1.length-1 ;i++){
            if ((array1[i] != array1[i + 1])){
                collect1++;

            }
        }
        if (collect==collect1){
            same = true;
        }
        return same;
    }

    public static boolean isValidHexCode(String code){
        boolean verify= false;
        boolean match = code.matches("#[a-fA-F0-9]+");
        if (code.indexOf(35) != 0 ) {
            code ="# is missing";
            System.out.println(code);
        }else if((code.length()-1) != 6){
            code ="Length exceeds 6";
            System.out.println(code);
        }else if (!match) {
            code= "Contains unacceptable character or Not all alphabetic characters in A-F";
            System.out.println(code);
        }else{
            System.out.println("Является шестнадцатеричным кодом");
            verify = true;
        }
        return verify;
    }

    public static String flipEndChars(String sentence){
    if (sentence.length()<2){
        sentence="Несовместимо";
    } else if (sentence.charAt(0) == sentence.charAt(sentence.length() - 1)){
        sentence= "Два - это пара";
    } else{
        char [] sen = sentence.toCharArray();
        sen [0] = sentence.charAt(sentence.length() - 1);
        sen [sentence.length() - 1] = sentence.charAt(0) ;
        sentence = new String(sen);
    }
        return sentence;
    }
    public static boolean CheckPerfect(int a){
        int sum = 0;
        boolean check= false;
        for (int i=1; i<a;i++){
            if (a%i==0) {
                System.out.println(i);
                sum +=i;
                            }

        }
        if (a == sum){
             check = true;
        }

        return check;
    }

    public static int findZip(String line) {
        int n = -1;

        for(int i = 0; i < line.length() - 2; ++i) {
            if (line.charAt(i) == 'z' && line.charAt(i + 1) == 'i' && line.charAt(i + 2) == 'p' && n == 1) {
                n = i - 1;
            }if (line.charAt(i) == 'z' && line.charAt(i + 1) == 'i' && line.charAt(i + 2) == 'p' && n == -1) {
                n = 1;
            }
        }

        if (n == 1) {
            n = -1;
        }
        System.out.print("Позиция второго вхождения в строку ");
        return n;
    }

        public static int Solutions (double a, double b, double c){
            double D = b * b - 4 * a * c;
            int A=0;
            if (D>0){
                A=2;
                double x1=((-b+Math.sqrt(D))/(2*a)) ;
                double x2=((-b-Math.sqrt(D))/(2*a)) ;
                System.out.println("Два решения уравнения");
                System.out.println("Первый кореньуравнения:"+ x1);
                System.out.println("Второй кореньуравнения:"+ x2);
            }else if (D == 0) {
                double x1=(-b)/(2*a) ;
                System.out.println("Первый кореньуравнения:"+ x1);
                A=1;
            }else {
                System.out.println("Нет корней");

            }
            System.out.println("Дискриминант:"+ D);
            System.out.print("Количество корней: ");
            return A;
        }
    public static void main(String[] args) {
        Scanner value = new Scanner(System.in);
        System.out.println("Введите номер задачи: ");
        int V = value.nextInt();
        if (V==1) {
            value = new Scanner(System.in);
            System.out.println("Введите коэффициенты уравнения: ");
            double a = value.nextDouble();
            double b = value.nextDouble();
            double c = value.nextDouble();
            System.out.print(Solutions(a,b,c));
        } else if(V==2){
            Scanner value1 = new Scanner(System.in);
            System.out.println("Введите строку: ");
            String line = value1.nextLine();
            System.out.print(findZip(line));
        } else if(V==3){
            System.out.println("Введите число: ");
            int a= value.nextInt();
            System.out.print(CheckPerfect(a));
        }else if(V==4){
            Scanner value1 = new Scanner(System.in);
            System.out.println("Введите Предложение: ");
            String sentence= value1.nextLine();
            System.out.print(flipEndChars(sentence));
        }else if(V==5){
            Scanner value1 = new Scanner(System.in);
            System.out.println("Введите возможный шестнадцатеричный код: ");
            String code= value1.nextLine();
            System.out.print(isValidHexCode(code));
        }else if(V==6){
            System.out.println("Введите размер массива: ");
            int size= value.nextInt();
            int[] array = new int[size];
            System.out.println("Введите размер второго массива: ");
            int size1= value.nextInt();
            int[] array1 = new int[size1];
            System.out.println("Введите элементы первого массива: ");
            for (int i = 0; i < size; i++) {
                array[i] = value.nextInt();
            }
            System.out.println("Введите элементы второго массива: ");
            for (int i = 0; i < size1; i++) {
                array1[i] = value.nextInt();
            }
            System.out.print(same(array, array1));
        }else if(V==7){
            System.out.println("Введите число: ");
            int a= value.nextInt();
            System.out.print(isKaprekar(a));
        }else if(V==8){
            Scanner value1 = new Scanner(System.in);
            System.out.println("Введите последовательность: ");
            String a= value1.nextLine();
            System.out.print(longestZero(a));
        }else if(V==9){
            System.out.println("Введите число: ");
            int a= value.nextInt();
            System.out.print(nextPrime(a));
        }else if(V==10){
            System.out.println("Введите первое ребро: ");
            double x = value.nextDouble();
            System.out.println("Введите второе ребро: ");
            double y = value.nextDouble();
            System.out.println("Введите третье ребро: ");
            double z = value.nextDouble();
            System.out.print(rightTriangle(x,y,z));
        }else {
            System.out.println("Введи номер задачи, а не рандомные цифры");
            System.out.println("ПОЕХАВШИЙ");
        }
    }
}