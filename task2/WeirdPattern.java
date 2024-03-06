package task2;

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
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(j<=n-i+1) {
                    System.out.print("*");
                } else {
                    System.out.print("_");
                }
            }
            for(int j=1;j<=n;j++) {
                if(j<=i) {
                    System.out.print("_");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(j<=i) {
                    System.out.print("*");
                } else {
                    System.out.print("_");
                }
            }
            for(int j=1;j<=n;j++) {
                if(j<=n-i+1) {
                    System.out.print("_");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        printWeirdPattern(4);
    }   
}
