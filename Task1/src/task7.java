public class task7 {
    public static void main(String[] args){
        func_7((7));
        func_7((10));
        func_7((98354));
    }
    public static void func_7(int a) {
        int[] ar= new int [a];
        //int s = list.size()-1;
        int s = 0 ;
        for (int i=1;i<a+1;i++){
            s += i;
            //System.out.print(i+"    ");
            //System.out.println(s);
        }
        System.out.println(s);
        //int s;
        //for (int i=0;i<s;i++){
        //s += list.get(i);
        //}
    }
}