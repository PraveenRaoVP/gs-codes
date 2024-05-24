package surprise_test;
// Online Java Compiler
// Use this editor to write, compile and run your Java code online

/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.Scanner;
public class SpiralMatrix
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int i=1;
		int top = 0, bottom = n-1, left = 0, right = n-1;
		
		int[][] matrix = new int[n][n];
		
		while(i <= n*n) {
		    for(int j=left;j<=right;j++) {
		        matrix[top][j] = i++;
		    }
		    top++;
		    for(int j=top;j<=bottom;j++) {
		        matrix[j][right] = i++;
		    }
		    right--;
		    for(int j=right;j>=left;j--) {
		        matrix[bottom][j] = i++;
		    }
		    bottom--;
 		    for(int j=bottom;j>=top;j--) {
 		        matrix[j][left] = i++;
 		    }
 		    left++;
		}
		for(int x=0;x<n;x++){
		    for(int y=0;y<n;y++) {
		        System.out.print(matrix[x][y]+" ");
		    }
		    System.out.println();
		}
	}
}
