package week0;

public class JumbledNumber {
    public static boolean isJumbled(String n) {
        for(int i=1;i<n.length()-1;i++) {
            int k = Integer.parseInt(n.charAt(i-1)+"");
            int l = Integer.parseInt(n.charAt(i)+"");
            int m = Integer.parseInt(n.charAt(i+1)+"");
            if(Math.abs(l-k)>1 || Math.abs(l-m)>1) return false;
        }
        return true; 
    }
    public static void main(String[] args) {
        int n = 8988;
        System.out.println(isJumbled(Integer.toString(n)));
    }
}
