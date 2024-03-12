package leetcode;

public class ValidPalindrome {
        public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++) {
            if(Character.isAlphabetic(s.charAt(i)) || Character.isDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }
        System.out.println(sb.toString());
        String rev = sb.reverse().toString();
        System.out.println(sb.reverse().toString());
        return sb.toString().equals(rev);
    }
}
