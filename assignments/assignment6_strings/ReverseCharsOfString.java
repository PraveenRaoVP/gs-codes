package assignments.assignment6_strings;

import java.util.Scanner;
// 2. Write a program to reverse the characters of a string.

public class ReverseCharsOfString {

    public static String reverseString(String str) {
        String reversed = "";
        for(int i=str.length()-1;i>=0;i--) {
            reversed += str.charAt(i);
        }
        return reversed;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String str = sc.nextLine();
        System.out.println("The reversed string is: " + reverseString(str));
    }
}
