package task2;

import java.util.Scanner;

public class SmallestElement {

    public static int findSmallest(int[] arr, int n) {
        if(n==1) return arr[0];

        return Math.min(arr[n-1], findSmallest(arr, n-1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i++) {
            arr[i] = sc.nextInt();
        }

        int smallest = arr[0];
        for(int i=0;i<n;i++) {
            if(arr[i] < smallest) {
                smallest = arr[i];
            }
        }
        System.out.println("Smallest element is: " + smallest);
        System.out.println("Smallest element using recursion is: " + findSmallest(arr, n));
    }
}
