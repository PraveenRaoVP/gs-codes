package week0;

public class ParanthesisPairs {
    public static boolean nestParan(String s) {
        if(s.length()<=2) return true;
        if(s.charAt(0)=='(' && s.charAt(s.length()-1)==')') {
            return nestParan(s.substring(1,s.length()-1));
        } else {
            return false;
        }
    }
    public static void main(String[] args) {
        String s = "((()x))";
        System.out.println(nestParan(s));
    }
}
