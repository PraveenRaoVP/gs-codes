package extrapractice.sliding_window;

import java.util.Collection;

public class MaximumSumSubarray {
    public static void main(String[] args) {
        int[] nums = {2, 1, 3, -4, -1, 2};
        int result = maxSubArraySum(nums);
        System.out.println("Maximum sum subarray: " + result);
        System.out.println("Maximum sum subarray using Kadane's algorithm: " + maxSubArraySumUsingKadaneAlgo(nums));
    }

    private static int maxSubArraySum(int[] nums) {
        int left = 0;
        int right = 0;
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;

        while(right < nums.length) {
            sum+=nums[right];

            maxSum = Math.max(maxSum, sum);

            if(sum < 0) {
                sum = 0;
                left = right + 1;
            } 
            right++;
        }

        return maxSum;
    }

    private static int maxSubArraySumUsingKadaneAlgo(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0;i<nums.length;i++) {
            sum+=nums[i];
            maxSum = Math.max(maxSum, sum);
            if(sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }
}
