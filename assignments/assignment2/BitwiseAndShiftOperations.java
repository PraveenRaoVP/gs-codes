package assignments.assignment2;

import java.util.Scanner;

/* Get two numbers from user and perform bitwise AND< OR, XOR, left and right shift operations on them. */

public class BitwiseAndShiftOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt(), b = sc.nextInt();
        System.out.println("Bitwise AND: " + (a&b));
        System.out.println("Bitwise OR: " + (a|b));
        System.out.println("Bitwise XOR: " + (a^b));
        System.out.println("Left shift: " + (a<<1));
        System.out.println("Right shift: " + (a>>1));
    }
}
