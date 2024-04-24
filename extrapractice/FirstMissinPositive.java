package extrapractice;

import java.util.Arrays;

public class FirstMissinPositive {

    public void swap(int[] nums, int i , int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
   public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; ){
            if(nums[i] >= 1 && nums[i] <= n){
                if(nums[i] != nums[nums[i]-1]){
                  swap(nums,i, nums[i]-1);
                  continue;
                }
            }
            i++;
        }
        for(int i = 0; i < n; i++) if(nums[i] != i+1) return i+1;
        return n+1;
    }

    public static void main(String[] args) {
        FirstMissinPositive firstMissinPositive = new FirstMissinPositive();
        // System.out.println(firstMissinPositive.firstMissingPositive(new int[] { 1, 2, 0 })); // Output: 3
        System.out.println(firstMissinPositive.firstMissingPositive(new int[] { 3, 4, -1, 1 })); // Output: 2
        // System.out.println(firstMissinPositive.firstMissingPositive(new int[] { 7, 8, 9, 11, 12 })); // Output: 1
    }
}