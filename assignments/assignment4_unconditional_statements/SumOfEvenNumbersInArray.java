package assignments.assignment4_unconditional_statements;

import java.util.Scanner;

public class SumOfEvenNumbersInArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements of the array: ");
        int sum = 0;
        for(int i=0;i<n;i++) {
            arr[i] = sc.nextInt();
            if(arr[i]%2!=0) {
                continue;
            }
            sum += arr[i];
        }
        System.out.println("The sum of even numbers in the array is: " + sum);        
    }
}
