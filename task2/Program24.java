package task2;

import java.util.Scanner;

/*
Input: 10
 *        1
 *       2 3
 *      4 5 6
 *     7 8 9 10
 * 
 */

public class Program24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int num = 1;
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n-i;j++) {
                System.out.print(" ");
            }
            for(int j=1;j<=i;j++) {
                System.out.print(num+" ");
                num++;
            }
            if(num==n+1) break;
            System.out.println();
        }

        sc.close();
    }
}
