package assignments.assignment4_unconditional_statements;

import java.util.Scanner;

public class SecondOccurence {
    public static int returnIndexOfSecondOccurence(int[] arr, int n) {
        int count = 0;
        for(int i=0;i<arr.length;i++) {
            if(arr[i]==n) count++;
            if(count == 2) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements: ");
        int s = sc.nextInt();
        int[] arr = new int[s];
        System.out.println("Enter the elements of the array: ");
        for(int i=0;i<s;i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Enter the number to find the second occurence of: ");
        int n =sc.nextInt();
        System.out.println("The second occurence of " + n + " is at index: " + returnIndexOfSecondOccurence(arr, n));
    }
}
