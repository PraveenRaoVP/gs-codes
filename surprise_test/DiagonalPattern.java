package surprise_test;

import java.util.Random;
import java.util.Scanner;

public class DiagonalPattern {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] matrix = new int[n][n];
		int x=1;
		Random r = new Random();
		for(int i=0;i<n;i++) {
		    for(int j=0;j<n;j++) {
		        matrix[i][j] = r.nextInt(100);
		        System.out.print(matrix[i][j]+" ");
		    }
		    System.out.println();
		}
		
		System.out.println();
		
		int limit = 0;
		int i=0,j=n-1;
		while(limit < n) {
		    i=0;j=limit;
		    while(i<=limit) {
		        System.out.print(matrix[j][i]+" ");
		        i++;j--;
		    }
		    System.out.println();
		    limit++;
		}
		limit = n/2-1;
		while(limit<n) {
		    i = limit; j=n-1;
		    while(i<=n-1) {
		        System.out.print(matrix[j][i]+" ");
		        i++;
		        j--;
		    }
		    System.out.println();
		    limit++;
		}
	}
}
    
