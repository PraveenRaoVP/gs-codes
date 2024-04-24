package evals.eval2;

import java.util.Scanner;

/* diamond pattern */

public class Porgram1 {

    public static void printPattern(char n) {
        int size = (n-'A'+1) * 2;
        int mid = size / 2;
        for(int i=0;i<mid;i++) {
            for(int j=0;j<size;j++) {
                if(j == mid-i || j == mid+i) {
                    System.out.print((char)('A'+i));
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        for(int i=mid-2;i>=0;i--) {
            for(int j=0;j<size;j++) {
                if(j == mid-i || j == mid+i) {
                    System.out.print((char)('A'+i));
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char n = sc.next().charAt(0);

        printPattern(n);

    }
}
