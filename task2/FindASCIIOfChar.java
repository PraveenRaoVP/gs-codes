package task2;

/*
 *  Program to Find ASCII Value of a Character
 */

import java.util.Scanner;

public class FindASCIIOfChar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a character: ");
        char c = sc.next().charAt(0);
        System.out.println((int)c);
    }   
}
