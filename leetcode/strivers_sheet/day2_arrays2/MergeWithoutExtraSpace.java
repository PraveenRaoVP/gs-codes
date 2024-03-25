package leetcode.strivers_sheet.day2_arrays2;

import java.util.Arrays;

public class MergeWithoutExtraSpace {
    public static void mergeTwoSortedArraysWithoutExtraSpace(long []a, long []b){
        int left = a.length-1;
        int right = 0;
       while (left >= 0 && right < b.length) {
            if (a[left] > b[right]) {
                long temp = a[left];
                a[left] = b[right];
                b[right] = temp;
                left--;
                right++;
            } else {
                break;
            }
        }
        Arrays.sort(a);
        Arrays.sort(b);
    }
}
