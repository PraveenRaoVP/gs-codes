package extrapractice;

import java.util.HashMap;
import java.util.Map;

public class ZigZagConversion {
    public static void main(String[] args) {
        String str = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println(convert(str, numRows)); // Output: PAHNAPLSIIGYIR
    }

    public static String convert(String s, int numRows) {
        if(numRows < 2) return s;
        if(s.length() < 2) return s;

        Map<Integer, StringBuilder> map = new HashMap<>();

        boolean inc = true;
        int row = 0;

        for(int i=0;i<s.length();i++) {
            if(row == numRows-1) {
                inc = false;
            } else if(row == 0) {
                inc = true;
            }
            if(!map.containsKey(row)) {
                map.put(row, new StringBuilder());
            }
            map.get(row).append(s.charAt(i));
            row = inc ? row+1 : row-1;
        }
        StringBuilder result = new StringBuilder();
        for(int i=0;i<numRows;i++) {
            result.append(map.get(i));
        }  

        return result.toString();
    }
}
