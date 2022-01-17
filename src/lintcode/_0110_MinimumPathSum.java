/*
Easy
#DP
 */
package lintcode;

import java.util.Arrays;

/**
 * Given a m * n grid filled with non-negative numbers, find a path from top left to bottom right
 * which minimizes the sum of all numbers along its path.
 *
 * The robot can only move either down or right at any point in time.
 *
 * Example 1:
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Path is: 1 -> 3 -> 1 -> 1 -> 1
 *
 * Example 2:
 * Input: grid = [[1,3,2]]
 * Output: 6
 * Explanation: Path is: 1 -> 3 -> 2
 */
public class _0110_MinimumPathSum {

    /**          [M, 0, M, M]    M 代表 Integer.MAX_VALUE
     * [1,3,1] - [M, 1, 4, 5]
     * [1,5,1]   [M, 2, 7, 6]
     * [4,2,1]   [M, 6, 8, 7]
     *                     ! 这个是答案
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int n = grid[0].length;

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;

        for (int[] row : grid) {
            for (int i = 0; i < n; i++) {
                dp[i + 1] = Math.min(dp[i], dp[i + 1]) + row[i];
            }
        }

        return dp[n];
    }
}
