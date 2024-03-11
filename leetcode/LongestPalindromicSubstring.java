package leetcode;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        for(int i=s.length();i>=0;i--)  {
            for(int j=0;j<=s.length()-i;j++) {
                if(checkIsPalindrome(j,j+i,s)) {
                    return s.substring(j,j+i);
                }
            }
        }
        return s ;       
    }

    public static boolean checkIsPalindrome(int i, int j, String s) {
        int left = i, right = j-1;
        while(left < right) {
            if(s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    } 
}
