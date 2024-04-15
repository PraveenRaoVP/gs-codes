package leetcode.mentor_questions.april13;

import java.util.Arrays;

public class CloseStrings {
    public boolean closeStrings(String word1, String word2) {
        int[] occ1 = new int[26];
        int[] occ2 = new int[26];
        // Map<Character, Integer> map1 = new HashMap<>();
        // Map<Character, Integer> map2 = new HashMap<>();

        for (char c : word1.toCharArray()) {
            // map1.put(c, map1.getOrDefault(c,0)+1);
            occ1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            // map2.put(c, map2.getOrDefault(c,0)+1);
            occ2[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (occ1[i] == 0 && occ2[i] != 0)
                return false;
            if (occ1[i] != 0 && occ2[i] == 0)
                return false;
        }
        // for(char c: word2.toCharArray()) {
        // if(!map1.containsKey(c) && map2.get(c)!=0) return false;
        // }
        // for(char c: word1.toCharArray()) {
        // if(map1.get(c)!=0 && !map2.containsKey(c)) return false;
        // }
        Arrays.sort(occ1);
        Arrays.sort(occ2);
        // return map1.equals(map2);
        return Arrays.equals(occ1, occ2);
    }
}
