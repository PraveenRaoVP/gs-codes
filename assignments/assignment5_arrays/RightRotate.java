package assignments.assignment5_arrays;

import java.util.Scanner;

// 3 1 2 4 5 7 1. 2 -> 7 1 3 1 2 4 5

public class RightRotate {

    public static void rotateArray(int[] arr, int shift) {
        for(int i=0;i<shift;i++) {
            int j, last = arr[arr.length-1];
            for(j=arr.length-1;j>0;j--) {
                arr[j] = arr[j-1];
            }
            arr[0] = last;
        }

        for(int i: arr) {
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements of the array: ");
        for(int i=0;i<n;i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("Enter the shift value: ");
        int shift = sc.nextInt();
        for(int i: arr) {
            System.out.print(i+" ");
        }
        System.out.println();
        rotateArray(arr, shift);
    }
}
