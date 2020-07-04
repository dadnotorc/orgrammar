/*
Medium
#Array, #DP
 */
package leetcode;

/**
 * 62. Unique Paths
 *
 * A robot is located at the top-left corner of a m x n grid.
 *
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid.
 *
 * How many possible unique paths are there?
 *
 * 这里的 m = num of columns, n = num of rows
 *
 * Example 1:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 *
 * Example 2:
 * Input: m = 7, n = 3
 * Output: 28
 *
 * Constraints:
 * - 1 <= m, n <= 100
 * - It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
 *
 * 此题类似 lintcode 114. Unique Paths
 */
public class _0062_UniquePaths {

    // todo 读读 lintcode 114. Unique Paths 数学解法

    /**
     * DP - dp[i][j] 表示从[0][0]走到[i][j]共用多少走法
     * dp[i][j] = dp[i-1][j] + dp[i][j-1]
     *
     * 例如 m=4, n=5, dp[][]内容如下
     * 1 1 1  1
     * 1 2 3  4
     * 1 3 6  10
     * 1 4 10 20
     * 1 5 15 35
     *
     * time:  O(m*n)
     * space: O(m*n)
     */
    public int uniquePaths_DP_2D(int m, int n) {
        if (m == 1 || n == 1)
            return 1;

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[n - 1][m - 1];
    }


    /**
     * 简化DP - 使用 1D array
     */
    public int uniquePaths_DP_1D(int m, int n) {
        if (m == 1 || n == 1)
            return 1;

        int[] dp = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[j] = 1;
                } else {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[m - 1];
    }
}
