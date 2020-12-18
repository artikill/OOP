public class task9 {
    public static void main(String[] args){
    int [] arr_1 = {3,4,4};
    func_9(arr_1);
}
    public static void func_9(int[] arr){
        int sum = 0;
        for(int i=0;i<arr.length;i++){
            sum += arr[i]*arr[i]*arr[i];
        }
        System.out.print(sum);
    }
}
