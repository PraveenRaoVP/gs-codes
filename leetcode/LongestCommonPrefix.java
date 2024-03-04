package leetcode;

import java.util.Arrays;

/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

 

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
 */

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder ans = new StringBuilder();
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length-1];
        for(int i=0; i<Math.min(first.length(), last.length()); i++) {
            if(first.charAt(i)!=last.charAt(i))
                return ans.toString();
            ans.append(first.charAt(i));
        }
        return ans.toString();
    }
}
