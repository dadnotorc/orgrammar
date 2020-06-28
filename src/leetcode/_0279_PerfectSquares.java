/*
Medium
#Math, #DP, #BFS
 */
package leetcode;

/**
 * 279. Perfect Squares
 *
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class _0279_PerfectSquares {

    /**
     * dp[0] = 0
     * dp[1] = dp[1 - 1 * 1] + 1 = dp[0] + 1 = 1
     * dp[2] = 2
     * dp[3] = 3
     * dp[4] = min(dp[4 - 1 * 1] + 1, dp[4 - 2 * 2] + 1) = min(dp[3] + 1, dp[0] + 1) = 1
     * dp[5] = min(dp[5 - 1 * 1] + 1, dp[5 - 2 * 2] + 1) = min(dp[4] + 1, dp[1] + 1) = 2
     * dp[9] = min(dp[9 - 1 * 1] + 1, dp[9 - 2 * 2] + 1, , dp[9 - 3 * 3] + 1) = min(dp[4] + 1, dp[1] + 1, dp[0] + 1) = 1
     * dp[n] = min(dp[n - j * j] + 1);  n - j * j >= 0, j >= 1
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            int j = 2;
            while (i - j * j >= 0) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                j++;
            }
        }

        return dp[n];
    }
}
