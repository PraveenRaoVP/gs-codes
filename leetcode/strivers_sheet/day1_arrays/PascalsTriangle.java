package leetcode.strivers_sheet.day1_arrays;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    public static int ncrCalc(int n, int r) {
        int ans = 1;
        for (int i = 0; i < r; i++) {
            ans *= (n - i);
            ans /= (i + 1);
        }
        return ans;
    }

    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0)
            return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                temp.add(ncrCalc(i, j));
            }
            ans.add(temp);
        }
        return ans;
    }
}
