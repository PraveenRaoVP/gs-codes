// Given an array of ints, is it possible to divide the ints into two groups, so that the sums of the two groups are the same. Every int must be in one group or the other. Write a recursive helper method that takes whatever arguments you like, and make the initial call to your recursive helper from splitArray(). (No loops needed.)


// splitArray([2, 2]) → true
// splitArray([2, 3]) → false
// splitArray([5, 2, 3]) → true

public boolean splitArray(int[] nums) {
    return splitArray(0,nums,0,0);
  }
  
  public boolean splitArray(int start, int[] nums, int sum1, int sum2) {
    if(start>=nums.length) return sum1==sum2;
    if(splitArray(start+1, nums, sum1+nums[start], sum2)) return true;
    if(splitArray(start+1, nums, sum1, sum2+nums[start])) return true;
    return false;
  }
  
