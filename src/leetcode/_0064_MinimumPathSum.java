/*
Medium
#Array, #DP
 */
package leetcode;

/**
 * Minimum Path Sum
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
public class _0064_MinimumPathSum {

    public int minPathSum_2(int[][] grid) {
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
     * DP
     */
    public int minPathSum(int[][] grid) {
        int[][] minSums = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    minSums[i][j] = grid[i][j];
                } else if (i == 0) {
                    minSums[i][j] = grid[i][j] + minSums[i][j - 1];
                } else if (j == 0) {
                    minSums[i][j] = grid[i][j] + minSums[i - 1][j];
                } else {
                    minSums[i][j] = grid[i][j] + Math.min(minSums[i][j - 1], minSums[i - 1][j]);
                }
            }
        }
        return minSums[grid.length - 1][grid[0].length - 1];
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
