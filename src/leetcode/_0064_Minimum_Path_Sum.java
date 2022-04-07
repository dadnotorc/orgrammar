package leetcode;

import java.util.Arrays;

/**
 * Minimum Path Sum
 * Medium
 * #Array, #DP
 *
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example1:
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class _0064_Minimum_Path_Sum {

    /**
     * DP - 1D 数组
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length;

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;

        for (int[] cur : grid) {
            for (int i = 1; i <= n; i++) {
                dp[i] = Math.min(dp[i - 1], dp[i]) + cur[i - 1];
            }
        }

        return dp[n];
    }

    /**
     * DP - 2D 数组
     */
    public int minPathSum_dp(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 递归
     */
    public int minPathSum_recursive(int[][] grid) {
        int[][] minSums = new int[grid.length][grid[0].length];
        return helper(grid, grid.length - 1, grid[0].length -1, minSums);
    }

    private int helper(int[][] grid, int x, int y, int[][] minSums) {
        if (x == 0 && y == 0)
            return grid[0][0];

        if (x < 0 || y < 0)
            return Integer.MAX_VALUE;

        if (minSums[x][y] > 0)
            return minSums[x][y];

        minSums[x][y] = grid[x][y] +
                Math.min(helper(grid, x - 1, y, minSums), helper(grid, x, y - 1, minSums));

        return minSums[x][y];
    }


    /**
     * 会TLE, Time Limit Exceeded
     */
    int min = Integer.MAX_VALUE;

    public int minPathSum_TLD(int[][] grid) {
        helper_TLE(grid, 0, 0, 0);
        return min;
    }

    private void helper_TLE(int[][] grid, int x, int y, int curSum) {
        if (x == grid.length - 1 && y == grid[0].length - 1) {
            min = Math.min(min, curSum + grid[x][y]);
            return;
        }

        if (curSum + grid[x][y] > min)
            return;

        if (x + 1 < grid.length)
            helper_TLE(grid, x + 1, y, curSum + grid[x][y]);
        if (y + 1 < grid[0].length)
            helper_TLE(grid, x, y + 1, curSum + grid[x][y]);
    }
}
