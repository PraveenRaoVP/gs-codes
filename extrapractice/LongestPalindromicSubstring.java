package extrapractice;

public class LongestPalindromicSubstring {
    
    public static String findLongestPalindromicSubstring(String s) {
        for(int i=s.length();i>=0;i--) {
            for(int j=0;j<=s.length()-i;j++) {
                if(checkIsPalindrome(j,i+j,s)) {
                    return s.substring(j,i+j);
                }
            }
        }
        return "";
    }

    public static boolean checkIsPalindrome(int i, int j, String s) {
        int left = i, right = j-1;
        while(left < right) {
            if(s.charAt(left)!=s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    public static void main(String[] args) {
        String s = "ababba";
        System.out.println(findLongestPalindromicSubstring(s));
    }
}
