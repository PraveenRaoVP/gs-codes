package extrapractice.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingChars {
    public static void main(String[] args) {
        String s = "abcabcdbb";
        int result = lengthOfLongestSubstring(s);
        System.out.println("Length of the longest substring without repeating characters: " + result);
    }

    private static int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<>();

        while(right < s.length()) {
            char curr = s.charAt(right);
            if(map.containsKey(curr) && map.get(curr) >= left) {
                left = map.get(curr) + 1;
            }
            map.put(curr, right);

            maxLen = Math.max(maxLen, right-left+1);

            right++;
        }
        return maxLen;
    }
}
