package leetcode.strivers_sheet.day2_arrays2;

public class MissingAndRepeatingNumbers {
    public static int[] findMissingRepeatingNumbers(int[] a) {
        int[] ans = new int[2];
        int[] counts = new int[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            counts[a[i]] += 1;
        }
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] == 0) {
                ans[1] = i;
            }
            if (counts[i] == 2) {
                ans[0] = i;
            }
        }
        return ans;
    }
}
