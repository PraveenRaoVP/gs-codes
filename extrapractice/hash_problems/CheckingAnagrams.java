package extrapractice.hash_problems;

import java.util.HashMap;
import java.util.Map;

public class CheckingAnagrams {

    public static boolean isAnagram(String s1, String s2) {
        if(s1.length()!=s2.length()) return false;
        Map<Character, Integer> m1 = new HashMap<>();
        for(char c: s1.toCharArray()) {
            m1.put(c, m1.getOrDefault(c,0)+1);
        }
        for(char c: s2.toCharArray()) {
            if(!m1.containsKey(c) || m1.get(c) == 0) {
                return false;
            }
            m1.put(c,m1.get(c)-1);
        }
        
        return true;
    }

    public static void main(String[] args) {
        String s1 = "anagram";
        String s2 = "nagaram";

        System.out.println(isAnagram(s1,s2));
    }
}
