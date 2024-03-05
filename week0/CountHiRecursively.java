package week0;

public class CountHiRecursively {
    public static int countHi(String s) {
        if(s.length()==0) return 0;
        if(s.length() >= 2 && s.substring(0,2).equalsIgnoreCase("hi")) {
            return 1+countHi(s.substring(2));
        }
        return countHi(s.substring(1));
    }
    public static void main(String[] args) {
        String s = "hixxhixhixhi";
        System.out.println(countHi(s));
    }
}
