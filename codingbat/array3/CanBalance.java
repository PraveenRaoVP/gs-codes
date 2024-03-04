
// Given a non-empty array, return true if there is a place to split the array so that the sum of the numbers on one side is equal to the sum of the numbers on the other side.


// canBalance([1, 1, 1, 2, 1]) → true
// canBalance([2, 1, 1, 2, 1]) → false
// canBalance([10, 10]) → true

public boolean canBalance(int[] nums) {
    int sum1 = 0;
    int sum2 = 0;
    for(int i=0;i<nums.length;i++){
      sum1 += nums[i];
      for(int j=nums.length-1;j>i;j--){
        sum2 += nums[j];
      }
      if(sum1==sum2) return true;
      sum2 = 0;
    }
    return false;
  }