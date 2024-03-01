package task2;

import java.util.HashMap;
import java.util.Map;

public class DuplicateElements {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,1,2,3,4,5,6};
        Map<Integer, Integer> map = new HashMap<>();
        for(int i: arr) {
            if(map.containsKey(i)) {
                map.put(i, map.get(i)+1);
            } else {
                map.put(i,1);
            }
        }
        System.out.println("Duplicate elements are: ");
        for(int i: map.keySet()) {
            if(map.get(i) > 1) {
                System.out.print(i+" ");
            }
        }
        System.out.println();
    }
}
