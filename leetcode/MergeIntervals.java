package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */

class MergeIntervals {
    public int[][] merge(int[][] intervals) {
       if(intervals.length == 1){
           return intervals;
       }

       Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

       List<List<Integer>> items = new ArrayList<>();

       for(int i=0;i<intervals.length;i++){
           int start = intervals[i][0];
           int end = intervals[i][1];
           while(i+1 < intervals.length && end >= intervals[i+1][0]){
               end = Math.max(end, intervals[i+1][1]);
               i++;
           }
           List<Integer> row = Arrays.asList(start,end);
           items.add(row);
       }
        int[][] result = new int[items.size()][2];
        for(int i=0;i<items.size();i++){
            result[i][0] = items.get(i).get(0);
            result[i][1] = items.get(i).get(1);
        }
        return result;
    }
}