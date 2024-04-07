package extrapractice.hashmap_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagrams {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> indices = findAllAnagrams(s, p);
        System.out.println("Start indices of p's anagrams in s: " + indices);
    }

    public static List<Integer> findAllAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if(p.length() > s.length() || p == null || s == null || p.length()==0 || s.length()==0) {
            return result;
        }
        Map<Character, Integer> map = new HashMap<>();
        for(char c: p.toCharArray()) {
            map.put(c, map.getOrDefault(c,0)+1);
        }
        int left = 0, right = 0, count = map.size();
        
        while(right < s.length()) {
            char c = s.charAt(right);
            if(map.containsKey(c)) {
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0) {
                    count--;
                }
            }
            right ++;

            while(count == 0) {
                char temp = s.charAt(left);
                if(map.containsKey(temp)) {
                    map.put(temp, map.get(temp)+1);
                    if(map.get(temp) > 0) {
                        count++;
                    }
                }
                if(right - left == p.length()) {
                    result.add(left);
                }
                left++;
            }
        }
        return result;
    }
}
