package evals.eval1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LargestSubarrayWithEqualZeroes {
    public static void main(String[] args) {
        int[] arr1 = {1, 0, 1, 1, 1, 0, 0};
        int[] arr2 = {1, 1, 1, 1};
        int[] arr3 = {0, 0, 1, 1, 0};

        System.out.println(Arrays.toString(findLargestSubarray(arr1))); // Output: [0, 1, 1, 1, 0, 0]
        System.out.println(Arrays.toString(findLargestSubarray(arr2))); // Output: No such subarray
        System.out.println(Arrays.toString(findLargestSubarray(arr3))); 
    }

    public static int[] findLargestSubarray(int[] arr) {
        int n = arr.length;
        for(int i=0;i<n;i++) {
            if(arr[i] == 0) {
                arr[i] = -1;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();

        int maxLen = 0;
        int sum = 0;
        int end = -1;

        for(int i=0;i<n;i++) {
            sum+=arr[i];
            
            if(sum == 0) {
                maxLen = i+1;
                end = i;
            }

            if(map.containsKey(sum)) {
                if(i-map.get(sum) > maxLen) {
                    maxLen = i-map.get(sum);
                    end = i;
                }
            } else {
                map.put(sum,i);
            }

        }

        if(maxLen == 0) {
            return new int[0];
        }

        int start = end-maxLen+1;
        
        for(int i=0;i<n;i++) {
            if(arr[i] == -1) {
                arr[i] = 0;
            }
        }

        int[] result = new int[maxLen];
        for(int i=0;i<maxLen;i++) {
            result[i] = arr[start+i];
        }
        return result;
    }
}
