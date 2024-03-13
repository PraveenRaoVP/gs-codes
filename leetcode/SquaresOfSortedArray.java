package leetcode;

public class SquaresOfSortedArray {
    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        int i = 0;
        int k = 0;
        int j = nums.length-1;
        while(i<=j) {
            if(nums[i]*nums[i] > nums[j]*nums[j]) {
                ans[k++] = nums[i]*nums[i];
                i++;
            } else {
                ans[k++] = nums[j]*nums[j];
                j--;
            }
        }
        i = nums.length-1;
        j=0;
        while(i>=0) {
            nums[j++] = ans[i--];
        }
        return nums;
    }
}
