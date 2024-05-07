package hackerrank;

import java.util.HashMap;
import java.util.Map;

public class GameOfThrones {
    public static String gameOfThrones(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int oddCount = 0;
        for(int count : map.values()) {
            if(count % 2 != 0) {
                oddCount++;
            }
        }
        return oddCount <= 1 ? "YES" : "NO";
    }

}
