package task2;

import java.util.Scanner;

/*
 * Write a program to print the following pattern for the given input number.
Eg 1:  Input: 4
        Output:
********
***__***
**____**
*______*
**____**
***__***
********

 */

public class WeirdPattern {
    public static void printWeirdPattern(int n) {
        int totalLength = 2*n;
        for(int i=0;i<totalLength;i++) {
            for(int j=0;j<totalLength;j++) {
                if(i<n) {
                    if(j<n) {
                        if(j<n-i) {
                            System.out.print("*");
                        } else {
                            System.out.print("_");
                        }
                    } else {
                        if(j>=n+i) {
                            System.out.print("*");
                        } else {
                            System.out.print("_");
                        }
                    }
                } else {
                    if(j<n) {
                        if(j<i-n) {
                            System.out.print("*");
                        } else {
                            System.out.print("_");
                        }
                    } else {
                        if(j>=totalLength-(i-n)) {
                            System.out.print("*");
                        } else {
                            System.out.print("_");
                        }
                    }
                }
            }
            System.out.println();
        }        
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        printWeirdPattern(4);
    }   
}
