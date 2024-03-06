
// Given an array of ints, is it possible to divide the ints into two groups, so that the sum of the two groups is the same, with these constraints: all the values that are multiple of 5 must be in one group, and all the values that are a multiple of 3 (and not a multiple of 5) must be in the other. (No loops needed.)


// split53([1, 1]) → true
// split53([1, 1, 1]) → false
// split53([2, 4, 2]) → true

public boolean split53(int[] nums) {
    return splitArray(0, nums, 0, 0);
  }
  
  public boolean splitArray(int start, int[] nums, int sum1, int sum2) {
    if(start >= nums.length) return sum1 == sum2;
    if(nums[start]%5==0) {
      return splitArray(start+1, nums, sum1+nums[start], sum2);
    } else if(nums[start]%3==0 && nums[start]%5!=0) {
      return splitArray(start+1, nums, sum1, sum2+nums[start]);
    }
    if(splitArray(start+1, nums, sum1+nums[start], sum2)) return true;
    if(splitArray(start+1, nums, sum1, sum2+nums[start])) return true;
    return false;
  }