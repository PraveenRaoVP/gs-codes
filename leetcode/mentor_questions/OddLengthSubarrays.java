package leetcode.mentor_questions;

public class OddLengthSubarrays {
    public int sumOddLengthSubarrays(int[] arr) {
        int ans = 0;
        for(int i=0;i<arr.length;i++) {
            ans+=((i+1)*(arr.length-i)+1)/2 * arr[i];
        }
        return ans;
    }
}
