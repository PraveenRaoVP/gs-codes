package extrapractice;

public class PushZeroes {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 2, 0, 3, 0, 4, 0, 5, 0};

        int nonZeroIndex = 0;

        for(int i=0;i<arr.length;i++) {
            if(arr[i]!=0) {
                swap(arr, nonZeroIndex, i);
                nonZeroIndex++;
            }
        }

        for(int i=0;i<arr.length;i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
