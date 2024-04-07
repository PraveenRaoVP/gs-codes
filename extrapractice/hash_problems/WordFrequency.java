package extrapractice.hash_problems;

import java.util.HashMap;
import java.util.Map;

public class WordFrequency {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(); 
        String s = "This is a paragraph, and this is sparta.";

        String[] strs = s.split("\\s+");

        for(String word: strs) {
            map.put(word.toLowerCase(), map.getOrDefault(word.toLowerCase(),0)+1);
        }
        for(Map.Entry<String, Integer> mp: map.entrySet()) {
            System.out.println(mp.getKey() +": " + mp.getValue());
        }
    }
}
