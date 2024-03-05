package week0;

import java.util.Scanner;

public class LexicographicallyLargestStr {
    public static String returnLexicographicOrder(String s) {
        char[] arr = s.toCharArray();
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr.length;j++) {
                if(arr[i] > arr[j]) {
                    char temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return String.valueOf(arr);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of strings: ");
        int n = sc.nextInt();
        int i=1;
        while(n>0) {
            System.out.println("Enter the string"+i+": ");
            String s = sc.next();
            System.out.println("String"+i+": "+returnLexicographicOrder(s));
            n--;
            i++;
        }
    }
}
