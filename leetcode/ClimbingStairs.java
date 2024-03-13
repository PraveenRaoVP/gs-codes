package leetcode;

public class ClimbingStairs {
    static int fibo(int n) {
        int n1 = 0;
        int n2 = 1;
        int n3 = 0;
        while (n-- > 0) {
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
        }
        return n2;
    }

    public int climbStairs(int n) {
        return fibo(n);
    }
}
