package assignments.assignment12_exception_handling;

import java.util.InputMismatchException;
import java.util.Scanner;

/*Create a program to get phone number from the user and throw InputMismatchException if the user enters non-number input */

public class Problem3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter phone number: ");
        try {
            long phoneNumber = sc.nextLong();
            System.out.println("Phone number: "+phoneNumber);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid phone number.");
        }
    }
}
