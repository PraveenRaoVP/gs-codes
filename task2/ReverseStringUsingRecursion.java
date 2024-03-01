package task2;

import java.util.Scanner;

/*
   Using Recursion, reverse the string such as
Eg 1:  Input: one two three
       Output: three two one
Eg 2:  Input: I love india
       Output: india love I
 */

public class ReverseStringUsingRecursion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(reverseString(str));
    }

    public static String reverseString(String str) {
        String[] words = str.split(" ");
        return reverseString(words, words.length-1);
    }

    public static String reverseString(String[] words, int index) {
        if (index == 0) {
            return words[index];
        }
        return words[index] + " " + reverseString(words, index-1);
    }
}
