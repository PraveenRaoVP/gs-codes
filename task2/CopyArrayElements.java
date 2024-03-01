package task2;

import java.util.Arrays;

public class CopyArrayElements {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int[] copy = new int[arr.length];

        for(int i=0;i<arr.length;i++) {
            copy[i] = arr[i];
        }
        for(int i: arr) {
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i: copy) {
            System.out.print(i+" ");
        }
        System.out.println();

        // or 

        int[] arrCopy = Arrays.copyOf(arr, arr.length);
        for(int i: arrCopy) {
            System.out.print(i+" ");
        }
    }
}
