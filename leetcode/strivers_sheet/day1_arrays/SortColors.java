package leetcode.strivers_sheet.day1_arrays;

public class SortColors {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int[] counts = new int[3];
        for (int i = 0; i < nums.length; i++) {
            counts[nums[i]] += 1;
        }
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i]-- > 0) {
                nums[index++] = i;
            }
        }
    }
}
