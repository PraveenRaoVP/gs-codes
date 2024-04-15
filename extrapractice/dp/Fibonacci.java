package extrapractice.dp;

import java.util.Arrays;

public class Fibonacci {
    public static int fibo(int n, int[] dp) {
        if(n<=1) return n;
        if(dp[n]!=-1) return dp[n];
        return fibo(n-1,dp) + fibo(n-2,dp);
    }

    public static void main(String[] args) {
        int n = 5;
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        System.out.println(fibo(n,dp));

        // or
        int[] dp2 = new int[n+1];
        dp2[0] = 0;
        dp2[1] = 1;
        for(int i=2;i<=n;i++) {
            dp2[i] = dp2[i-1] + dp2[i-2];
        }

        System.out.println(dp2[n]);  
    }
}
