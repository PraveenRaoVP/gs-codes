package evals.eval2;

import java.util.Scanner;

public class Program5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        printPattern(n);
    }

    public static void printPattern(int n) {
        int[][] matrix = new int[n][n];
        int noOfKuttyBorders = (int)Math.sqrt(n);

        int top = 0;
        int bottom = n-1;
        int left = 0;
        int right = n-1;

        int border = 1;

        while(border <= noOfKuttyBorders) {
            for(int i=left;i<=right;i++) {
                matrix[top][i] = 1;
            }
            top++;

            for(int i=top;i<=bottom;i++) {
                matrix[i][right] = 1;
            }
            right--;
            
            for(int i=right;i>=left;i--) {
                matrix[bottom][i] = 1;
            }
            bottom--;

            for(int i=bottom;i>=top;i--) {
                matrix[i][left] = 1;
            }

            left+=2;
            top++;
            right--;
            bottom--;
            border++;
        }


        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        
    }
}
