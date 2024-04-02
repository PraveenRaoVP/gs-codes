package evals.eval1;

import java.util.Scanner;

public class StockPrices {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] prices = new int[n];
        for(int i=0;i<n;i++) {
            prices[i] = sc.nextInt();
        }

        int ans = 0;
        int min = prices[0];
        for(int i=1;i<n;i++) {
            if(prices[i] - min > ans) {
                ans = prices[i] - min;
            }
            if(prices[i] < min) {
                min = prices[i];
            }
        }
        System.out.println(ans);
    }
}
