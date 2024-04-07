package extrapractice.hashmap_problems;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String s = "babad";
        String result = longestPalindrome(s);
        System.out.println("Longest palindromic substring: " + result);
    }

    private static String longestPalindrome(String s) {
        if(s==null || s.length() < 2) {
            return s;
        }
        int left = 0, right = 0;

        for(int i=0;i<s.length();i++) {
            int l1 = expandAroundCenter(s,i,i);
            int l2 = expandAroundCenter(s,i,i+1);
            int len = Math.max(l1,l2);

            if(len > right-left) {
                left = i-(len-1)/2;
                right = i+len/2;
            }
        }
        return s.substring(left, right+1);
    }

    public static int expandAroundCenter(String s, int left, int right) {
        while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right-left-1;
    }
}
