package extrapractice.hashmap_problems;

import java.util.HashMap;
import java.util.Map;

public class DuplicatesInArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,1,4,5,1,3,4,5};
        printDuplicates(arr);
    }

    public static void printDuplicates(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i: arr) {
            map.put(i, map.getOrDefault(i,0)+1);
        } 
        for(Map.Entry<Integer, Integer> mp: map.entrySet()) {
            if(mp.getValue() > 1) {
                System.out.print(mp.getKey()+" ");
            }
        }
    }
}
