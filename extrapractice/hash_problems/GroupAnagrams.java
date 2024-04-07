package extrapractice.hash_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> groupedAnagrams = groupAnagrams(strs);
        System.out.println(groupedAnagrams);    
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for(String str: strs) {
            char[] sArr = str.toCharArray();
            Arrays.sort(sArr);
            String sortedStr = new String(sArr);
            if(!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            map.get(sortedStr).add(str);
        }

        return new ArrayList<>(map.values());
    } 
}
