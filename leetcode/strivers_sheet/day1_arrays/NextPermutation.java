package leetcode.strivers_sheet.day1_arrays;

public class NextPermutation {
    public void reverseIntArray(int[] nums, int start, int end) {
        while (start <= end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public void nextPermutation(int[] nums) {
        int breakingPoint = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                breakingPoint = i;
                break;
            }
        }

        if (breakingPoint == -1) {
            reverseIntArray(nums, 0, nums.length - 1);
            return;
        }
        int s = breakingPoint;
        int e = nums.length - 1;
        int smallest = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > nums[breakingPoint]) {
                smallest = i;
                break;
            }
        }
        int temp = nums[breakingPoint];
        nums[breakingPoint] = nums[smallest];
        nums[smallest] = temp;

        reverseIntArray(nums, breakingPoint + 1, nums.length - 1);
    }
}
