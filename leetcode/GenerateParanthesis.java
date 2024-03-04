package leetcode;

import java.util.ArrayList;
import java.util.List;

/*Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
  */

public class GenerateParanthesis {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generateBrackets(ans, 0,0,"", n);
        return ans;
    }

    public void generateBrackets(List<String> ans, int left, int right, String s, int n) {
        if(s.length() == n*2) {
            ans.add(s);
            return;
        }

        if(left < n) {
            generateBrackets(ans, left+1, right, s+"(",n);
        }
        if(right < left) {
            generateBrackets(ans, left, right+1, s+")",n);
        }
    }   
}
