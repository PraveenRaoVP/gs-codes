package week0;

public class IdenticalStar {
    public static String pairStar(String s) {
        if(s.length()<=1) return s;
        if(s.length() > 1 && s.charAt(0)==s.charAt(1)) {
            return s.charAt(0)+"*"+pairStar(s.substring(1)); 
        }
        return s.charAt(0) + pairStar(s.substring(1));
    }
    
    public static void main(String[] args) {
        String s = "aaaa";
        System.out.println(pairStar(s));
    }
}
