package extrapractice.dp;

public class FreedomTrail {
    public static void main(String[] args) {
        String ring = "godding";
        String key = "gd";
        System.out.println(findRotateSteps(ring, key));
    }

    public static int findRotateSteps(String ring, String key) {
        int n = ring.length();
        int m = key.length();

        int[][] dp = new int[m + 1][n]; // dp[i][j] means the minimum steps to match key[i:] with ring[j]
        for (int i = m - 1; i >= 0; i--) { // start from the end of key
            for (int j = 0; j < n; j++) { // start from the beginning of ring
                dp[i][j] = Integer.MAX_VALUE; // initialize to the maximum value
                for (int k = 0; k < n; k++) { // iterate through the ring
                    if (ring.charAt(k) == key.charAt(i)) { // if the character matches
                        int diff = Math.abs(j - k); // calculate the distance
                        int step = Math.min(diff, n - diff); // take the minimum distance, clockwise or counter-clockwise
                        dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][k]); // update the minimum steps
                    }
                }
            }
        }

        return dp[0][0] + m; // return the minimum steps to match the key

    }
}