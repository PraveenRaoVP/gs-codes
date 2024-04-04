package android;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program1 {
    public static Map<String, int[]> addingInput() {
        Map<String, int[]> map = new HashMap<>();
        map.put("A", new int[]{1920, 1990});
        map.put("B", new int[]{1950, 1990});
        map.put("C", new int[]{1910,1940});    
        map.put("D", new int[]{1905,1948});    
        map.put("E", new int[]{1991,1999});    
        map.put("F", new int[]{1931,1958});    
        map.put("G", new int[]{1967,1991});     
        map.put("H", new int[]{1946,1996});
        map.put("I", new int[]{1970,1989});     
        map.put("J", new int[]{1902,1956});          
        return map;
    }

    public static void main(String[] args) {
        Map<String, int[]> map = addingInput();
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();
        for(Map.Entry<String, int[]> mp: map.entrySet()) {
            if(year>=mp.getValue()[0] && year<=mp.getValue()[1]) {
                System.out.println(mp.getKey());
            }
        }
    }
}
