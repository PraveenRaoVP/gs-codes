package week0;

public class RecursiveParanthesis {
    static int flag = 0;
    public static String parenBit(String s) {
        if(s.charAt(0)==')') {
            flag=0;
            return ")";
        }
        if(s.charAt(0)=='(') {
            flag=1;
            return "("+ parenBit(s.substring(1));
        }
        if(flag==1) {
            return s.charAt(0)+parenBit(s.substring(1));
        }
        return parenBit(s.substring(1));
    }
    public static void main(String[] args) {
        String s = "x(hello)(lesgoo)";
        System.out.println(parenBit(s));
    }
}
