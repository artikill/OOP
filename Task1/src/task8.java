public class task8 {
    public static void main(String[] args) {
        func_8(8, 10);
        func_8(5, 7);
        func_8(9, 2);
    }

    public static void func_8(int a, int b) {
        int c = 0;
        for (int i = 0; i < a + b; i++) {
            c = i;
        }
        System.out.println(c);
    }
}