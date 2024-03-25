package leetcode.strivers_sheet.day2_arrays2;

public class NumberOfInversions {
    public static int numberOfInversions(int[] a, int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] > a[j])
                    count++;
            }
        }
        return count;
    }
}
