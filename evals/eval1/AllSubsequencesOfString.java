package evals.eval1;

public class AllSubsequencesOfString {
    public static void main(String[] args) {
        String str = "abc";
        printSubsequences(str, "");
    }

    public static void printSubsequences(String str, String ans) {
        if(str.length() == 0) {
            System.out.print(ans+" ");
            return;
        }
        char ch = str.charAt(0);
        String ros = str.substring(1);
        printSubsequences(ros, ans);
        printSubsequences(ros, ans+ch);
    }
}
