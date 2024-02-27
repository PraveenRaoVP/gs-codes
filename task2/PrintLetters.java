// 1) Write a program to print a letters from the user input character to 'Z' without using
// strings.
// Example 1:  input : X
// Output : XYZ
// Example 2:  Input : M
// Output : MNOPQRSTUWXYZ
package task2;

import java.util.Scanner;

public class PrintLetters {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char input = sc.next().charAt(0);
        for (char i = input; i <= 'Z'; i++) {
            System.out.print(i);
        }
    }
}