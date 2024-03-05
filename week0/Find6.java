package week0;

public class Find6 {
    public static boolean array6(int[] arr, int index) {
        if(index >= arr.length) return false;
        if(arr[index]==6) return true;
        return array6(arr, index+1); 
    }

    public static void main(String[] args) {
        int[] arr = {1,4};
        System.out.println(array6(arr,0));
    }
}
