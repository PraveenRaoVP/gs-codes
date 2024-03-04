package task2;

import java.util.Scanner;

/*
 *        9
 *      9 8 9
 *     9 8 7 8 9
 * ... 
 */

public class Program21 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n-i;j++) {
                System.out.print("  ");
            }
            for(int j=n;j>=n-i+1;j--) {
                System.out.print(j+" ");
            }
            for(int j=n-i+2;j<=n;j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
        sc.close();
    }
}
