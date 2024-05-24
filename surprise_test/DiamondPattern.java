package surprise_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiamondPattern {
    
    
    /******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<List<Integer>> pattern = new ArrayList<>();
		int n = sc.nextInt();
		if(n%2==0) return;
		int limit = 0;
// 		System.out.println("Before loops");
		while(limit <= n/2) {
		    List<Integer> sub = new ArrayList<>();
		    for(int j=limit;j>=0;j--) {
		        sub.add(j+1);
		    }
		    for(int j=1;j<=limit;j++) {
		        sub.add(j+1);
		    }
		    pattern.add(sub);
		    limit++;
		  //  System.out.println("Limit: " + limit);
		}
// 		System.out.println(pattern);
		int space = n/2;
		for(int i=0;i<pattern.size();i++) {
		  //  System.out.println("Inside top pattern");
		    for(int j=0;j<pattern.size()-i;j++) {
		        System.out.print(" ");
		    }
		    for(int j: pattern.get(i)) {
		        System.out.print(j+"");
		    }
		    System.out.println();
		}
		for(int i=pattern.size()-2;i>=0;i--) {
		  //  System.out.println("Inside bottom pattern");
		    for(int j=0;j<pattern.size()-i;j++) {
		        System.out.print(" ");
		    }
		    for(int j: pattern.get(i)) {
		        System.out.print(j+"");
		    }
		    System.out.println();
		}
	}
}
