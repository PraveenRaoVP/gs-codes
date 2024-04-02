package evals.eval1;

import java.util.HashMap;
import java.util.Map;

/*
 * Given an array containing only 0s and 1s, find the largest subarray which contains
equal no of 0s and 1s. The expected time complexity is O(n).

Sample INput: arr[] = {1, 0, 1, 1, 1, 0, 0}
Output: 0, 1, 1, 1, 0, 0

 */

public class EqualZeroesAndOnes {

    public static void maxLenUsingBruteForce(int[] arr) {
        int maxLen = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < arr.length; i++) {
            int zeroes = 0;
            int ones = 0;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] == 0) {
                    zeroes++;
                } else {
                    ones++;
                }
                if (zeroes == ones && maxLen < j - i + 1) {
                    maxLen = j - i + 1;
                    start = i;
                    end = j;
                }
            }
        }
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void maxLenOptimized(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        int count = 0;
        for(int i=0;i<arr.length;i++) {
            count += arr[i]==1 ? 1:-1;
            if(map.containsKey(count)) {
                map.put(count, i-map.get(count));
            } else {
                map.put(count, i);
            }
        }
        
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 1, 1, 1, 0, 0};
        maxLenUsingBruteForce(arr);
        maxLenOptimized(arr);
    }
}
