package evals.eval1;

import java.util.Scanner;

public class IsArraySubset {
    public static boolean isSubset(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int count = 0;
        for(int i=0;i<n1;i++) {
            for(int j=0;j<n2;j++) {
                if(arr1[i] == arr2[j]) {
                    count++;
                    break;
                }
            }
        }
        if(count == n2) {
            return true;
        }

        return false;
    } 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int[] arr1 = new int[n1];
        for(int i=0;i<n1;i++) {
            arr1[i] = sc.nextInt(); 
        }
        int n2 = sc.nextInt();
        int[] arr2 = new int[n2];
        for(int i=0;i<n2;i++) {
            arr2[i] = sc.nextInt();
        }
        System.out.println(isSubset(arr1, arr2) ? "arr2[] is a subset of arr1[]" : "arr2[] is not a subset of arr1[]");
    }
}
