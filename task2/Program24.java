package task2;

import java.util.Scanner;

/*
Input: 10
 *        1
 *       2 3
 *      4 5 6
 *     7 8 9 10
 * 
 * Input: 11
 *       1
 *      2 3
 *     4 5 6
 *   7 8 9 10
 *  11
 */

public class Program24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int num = 1;

        // calculate no of rows required, eg n=11, noOfRows = 5, n=10, noOfRows = 4
        int noOfRows = 0;
        int temp = n;
        
        while(temp>0) {
            noOfRows++;
            temp -= noOfRows;
        }

        System.out.println(noOfRows);  

        for(int i=1;i<=noOfRows;i++) {
            for(int j=1;j<=noOfRows-i;j++) {
                System.out.print(" ");
            }
            for(int j=1;j<=i;j++) {
                System.out.print(num+" ");
                num++;
                if(num>n) break;
            }
            if(num>n) break;
            System.out.println();
        }    
        sc.close();
    }
}

