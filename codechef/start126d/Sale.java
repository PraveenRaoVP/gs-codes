package codechef.start126d;

import java.util.Scanner;

// public class Sale {
//     public static int findIndexOfMaxElement(int[] arr) {
//         int maxIndex = 0;
//         int maxElement = arr[0];
//         for(int i=1;i<arr.length;i++) {
//             if(arr[i] > maxElement) {
//                 maxElement = arr[i];
//                 maxIndex = i;
//             }
//         }
//         return maxIndex;
//     }
    
// 	public static void main (String[] args) throws java.lang.Exception
// 	{
//         Scanner sc = new Scanner(System.in);
        
//         int t = sc.nextInt();
//         while(t-- > 0) {
//             int n = sc.nextInt();
//             int[] arr = new int[n];
//             for(int i=0;i<n;i++) {
//                 arr[i] = sc.nextInt();
//             }
//             int maxIndex = findIndexOfMaxElement(arr);
//             int maxSales = 0;
//             if(maxIndex == 0) {
//                 System.out.println(arr[0]*2);
//                 continue;
//             }
//             for(int i=0;i<maxIndex;i++) {
//                 maxSales += arr[i];
//             }
//             maxSales+=arr[maxIndex]*2;
//             System.out.println(maxSales);
        
//         }
// 	}
// }

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    public static long maximumSubsequenceSum(int[] arr) {
        List<Long> subSum = new ArrayList<>();
        int limit = 0;
        
        while(limit < arr.length) {
            int i=0;
            long sum = 0;
            while(i < limit) {
                sum+=arr[i];
                i++;
            }
            sum+=arr[limit]*2;
            subSum.add(sum);
            limit++;
        }
        return Collections.max(subSum);
    } 
    

    
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner sc = new Scanner(System.in);
        
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            // int[] arr = new int[n];
            long sum = 0, max=0, val=0;
            for(int i=1;i<=n;i++) {
                long s = sc.nextLong();
                sum+=s;
                val = sum+s;
                if(val > max) {
                    max = val;
                }
            }
            System.out.println(max);
            // // int maxIndex = findIndexOfMaxElement(arr);
            // // long maxSales = 0;

            // // for(int i=0;i<maxIndex;i++) {
            // //     maxSales += arr[i];
            // // }
            // // maxSales+=arr[maxIndex]*2;
            // System.out.println(maximumSubsequenceSum(arr));
        }
	}
}

