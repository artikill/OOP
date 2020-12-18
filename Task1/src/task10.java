public class task10 {
    public static void main(String[] args){
        func_10(42,5,10);
        func_10(5,2,1);
        func_10(3,3,3);
    }
    public static void func_10(int a,int b,int c){
        for (int i=0;i<b;i++){
            a+= a;
        }
        if (a % c==0)
            System.out.println("true");
        else System.out.println("false");
        System.out.println(a);
    }
}
