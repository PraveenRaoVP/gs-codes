package evals.eval1;

import java.util.Scanner;

public class MoveZeroes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = sc.nextInt();
        }

        // int index = 0;
        // for(int i=0;i<n;i++) {
        //     if(arr[i] != 0) {
        //         arr[index++] = arr[i];
        //     }
        // }
        // while(index < n) {
        //     arr[index++] = 0;
        // }

        moveZerosToEnd(arr);

        for(int i=0;i<n;i++) {
            System.out.print(arr[i]+" ");
        }
        sc.close();
    }

    public static void moveZerosToEnd(int[] arr) {
        int n = arr.length;
        int nonZeroIndex = 0;
        
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                int temp = arr[nonZeroIndex];
                arr[nonZeroIndex] = arr[i];
                arr[i] = temp;
                
                nonZeroIndex++;
            }
        }
    }
}
