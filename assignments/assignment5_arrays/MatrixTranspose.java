package assignments.assignment5_arrays;

import java.util.Scanner;

public class MatrixTranspose {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the dimensions of the matrix (no of rows and no of columns in that order): ");
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] matrix = new int[n][m];

        System.out.println("Enter the elements of the matrix: ");
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                matrix[i][j] = sc.nextInt();
            }
            System.out.println();
        }

        int[][] transposeMatrix = new int[m][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                transposeMatrix[j][i] = matrix[i][j];
            }
        }

        System.out.println("The original matrix: ");
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("The transpose of the matrix: ");
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                System.out.print(transposeMatrix[i][j] + " ");
            }
            System.out.println();
        }

    }
}
