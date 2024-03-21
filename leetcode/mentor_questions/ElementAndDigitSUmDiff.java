package leetcode.mentor_questions;

class ElementAndDigitSUmDiff {

    public int digitSumRet(int n) {
        int sum = 0;
        while(n>0) {
            sum+=n%10;
            n/=10;
        }
        return sum;
    }

    public int differenceOfSum(int[] nums) {
        int sumNums = 0;
        int digitSums = 0;
        for(int i=0;i<nums.length;i++) {
            sumNums+=nums[i];
            digitSums += digitSumRet(nums[i]);
        }
        return sumNums - digitSums;
    }
}