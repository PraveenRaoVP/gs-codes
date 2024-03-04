package leetcode;

// The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

// P   A   H   N
// A P L S I I G
// Y   I   R
// And then read line by line: "PAHNAPLSIIGYIR"

// Write the code that will take a string and make this conversion given a number of rows:

// string convert(string s, int numRows);
 

// Example 1:

// Input: s = "PAYPALISHIRING", numRows = 3
// Output: "PAHNAPLSIIGYIR"
// Example 2:

// Input: s = "PAYPALISHIRING", numRows = 4
// Output: "PINALSIGYAHRPI"
// Explanation:
// P     I    N
// A   L S  I G
// Y A   H R
// P     I
// Example 3:

// Input: s = "A", numRows = 1
// Output: "A"

public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if(numRows < 2){
            return s;
        }

        StringBuilder ans = new StringBuilder();
        for(int i=0;i<numRows;i++){
            int inc = 2*(numRows - 1);
            for(int j=i;j<s.length();j+=inc){
                ans.append(s.charAt(j));
                if(i > 0 && i<numRows-1 && j+inc-2*i < s.length()){
                    ans.append(s.charAt(j+inc-2*i));
                }
            }
        }
        return ans.toString();
    }    
}
