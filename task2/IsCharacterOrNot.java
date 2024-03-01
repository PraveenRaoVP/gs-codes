package task2;
/*
 * Program to Check Whether a Character is an Alphabet or not
 */

import java.util.Scanner;

public class IsCharacterOrNot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char ch = sc.next().charAt(0);
        if(Character.isLetter(ch)) {
            System.out.println(ch + " is an alphabet");
        } else {
            System.out.println(ch + " is not an alphabet");
        }
        //or
        if((ch >='a' && ch<='z') || (ch>='A' && ch<='Z')) {
            System.out.println(ch + " is an alphabet");
        } else {
            System.out.println(ch + " is not an alphabet");
        }
    }
}
