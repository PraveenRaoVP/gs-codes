package task2;

/*
   Write a program to display the number in reverse order without use of String
functions.
Eg 1:  Input: 12345
Output :The number in reverse order is : 54321

 */

import java.util.Scanner;

public class ReverseNumberWithoutString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int rev = 0;
        while(n > 0) {
            rev = rev*10 + n%10;
            n/=10;
        }
        System.out.println("The number in reverse order: "+rev);
    }
}
