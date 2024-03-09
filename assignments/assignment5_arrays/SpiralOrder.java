package assignments.assignment5_arrays;

import java.util.Scanner;

public class SpiralOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the 2d array: ");
        int n = sc.nextInt();
        printSpiralOrder(n);
    }

    public static void printSpiralOrder(int n) {
        int[][] matrix = new int[n][n];
        int top=0, bottom=n-1, left=0, right=n-1;
        int count = 1;
        while(top<=bottom && left<=right)  {
            for(int i=left;i<=right;i++) {
                matrix[top][i] = count++;
            }
            top++;
            for(int i=top;i<=bottom;i++) {
                matrix[i][right] = count++;
            }
            right--;
            for(int i=right;i>=left;i--) {
                matrix[bottom][i] = count++;
            }
            bottom--;
            for(int i=bottom;i>=top;i--) {
                matrix[i][left] = count++;
            }
            left++;
        }
        for(int[] i: matrix) {
            for(int j: i) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
}
