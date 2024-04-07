package extrapractice.hashmap_problems;

import java.util.HashMap;
import java.util.Map;

public class LongestSubarrayWithSumK {
    public static void main(String[] args) {
        int[] nums = { 10, 5, 2, 7, 1, 9 };
        int k = 15;
        System.out.println("Length of the longest subarray with sum " + k + ": " + longestSubarrayWithSumK(nums, k));
    }

    private static int longestSubarrayWithSumK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int maxLen = 0;
        int sum = 0;
        int count = 0;

        for(int i=0;i<nums.length;i++) {
            sum+=nums[i];
            if(sum == k) {
                maxLen = i+1;
                count ++;
            } else if(map.containsKey(sum-k)) {
                maxLen = Math.max(maxLen, i-map.get(sum-k));
            }

            if(!map.containsKey(sum)) {
                map.put(sum,i);
            }
        }
        System.out.println("No of subarrays adding to "+ k+": "+count);
        return maxLen;
    }
}
