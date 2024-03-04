package leetcode;

import java.util.HashSet;
import java.util.Set;

/*
 Given a string s, find the length of the longest 
substring
 without repeating characters.

 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * 
 */

public class LongestSubstringWithoutRepeatingChars {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        
        Set<Character> charSet = new HashSet<>();
        int j=0;

        for(int i=0;i<n;i++){
            if(!charSet.contains(s.charAt(i))){
                charSet.add(s.charAt(i));
                maxLength = Math.max(maxLength, i-j+1);
            } else{
                while(charSet.contains(s.charAt(i))){
                    charSet.remove(s.charAt(j));
                    j++;
                }
                charSet.add(s.charAt(i));
            }
        }
        return maxLength;
    }
}
