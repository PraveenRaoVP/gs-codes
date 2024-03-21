package leetcode.mentor_questions;

public class SubSetXORSum {
    public int subSetSums(int[] nums, int index, int sum) {
        if(index == nums.length) {
            return sum;
        }

        int with = subSetSums(nums, index+1, sum^nums[index]);
        int without = subSetSums(nums, index+1, sum);
        return with+without;       
    }
    public int subsetXORSum(int[] nums) {
        return subSetSums(nums,0,0);
    }
}
