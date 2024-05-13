package assignments.assignment5_arrays;

import java.util.Scanner;
// 3. Write a program to implement matrix multiplication
// 

public class MatrixMultiplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the dimensions of the first matrix (no of rows and no of columns in that order): "); 
        int n1 = sc.nextInt();
        int m1 = sc.nextInt();
        System.out.println("Enter the dimensions of the second matrix (no of rows and no of columns in that order): ");
        int n2 = sc.nextInt();
        int m2 = sc.nextInt();

        if(m1!=n2) {
            System.out.println("Matrix multiplication is not possible with this dimensions.");
            return;
        }
    
        int[][] matrix1 = new int[n1][m1];
        int[][] matrix2 = new int[n2][m2];
    
        System.out.println("Enter the elements of the first matrix of dimensions " + n1 + "x" + m1+ ":");
        for(int i=0;i<n1;i++) {
            for(int j=0;j<m1;j++) {
                matrix1[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter the elements of the second matrix of dimensions " + n2 + "x" + m2+ ":");
        for(int i=0;i<n2;i++) {
            for(int j=0;j<m2;j++) {
                matrix2[i][j] = sc.nextInt();
            }
        }
        int[][] ans = new int[n1][n2];
        for(int i=0;i<n1;i++) {
            for(int j=0;j<n2;j++) {
                for(int k=0;k<m1;k++) {
                    ans[i][j] += matrix1[i][k] + matrix2[k][j];
                }
            }
        }
        System.out.println("The first matrix: ");
        printMatrix(matrix1);
        System.out.println("The second matrix: ");
        printMatrix(matrix2);
        System.out.println("The product of the two matrices: ");
        printMatrix(ans);
    }

    public static void printMatrix(int[][] matrix) {
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[0].length;j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}