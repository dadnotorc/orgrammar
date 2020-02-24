/*
Easy
#Array, #DP
Amazon
 */
package lintcode;

import java.util.Arrays;

/**
 * 115. Unique Paths II
 *
 * Follow up for "Unique Paths":
 *
 * Now consider if some obstacles are added to the grids.
 * How many unique paths would there be?
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * Notice
 * - m and n will be at most 100.
 *
 * Example 1:
 * Input: [[0]]
 * Output: 1
 *
 * Example 2:
 * Input:  [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation:
 * Only 2 different path.
 */
public class _0115_UniquePaths2 {


    /**
     * DP - 简化 使用1D array
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) // 出发/终点有障碍, 则无法通行
            return 0;

        int[] dp = new int[n]; // 一行 - 此array长度由第59行, 两层循环中的内循环决定

        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 0)
                dp[j] = 1;
            else
                break;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    if (j == 0) {
                        dp[0] = dp[0] == 0 ? 0 : 1;
                    } else {
                        dp[j] = dp[j-1] + dp[j];
                    }
                } else {
                    dp[j] = 0;
                }
            }
        }

        return dp[n - 1];
    }


    /**
     * DP - 使用2D array
     * 某一点有障碍时, 该点可达该点路径数为0
     *
     * 注意, 在第一行/第一列时, 如果其中某点有障碍, 不止该点路径数为0, 其后所有点路径数皆为0
     *
     * time:  O(m*n)
     * space: O(m*n)
     */
    public int uniquePathsWithObstacles_dp_2d(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) // 出发/终点有障碍, 则无法通行
            return 0;

        // 如果只有1行/1列, 且中间存在障碍, 则无法通行, 返回0

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = 1;
            } else {
                break; // array 初始值即为0. 所以在第一列, 遇到障碍时, 停止即可
            }
        }

        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 0) {
                dp[0][j] = 1;
            } else {
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return dp[m-1][n-1];



        // 下列方法亦可, 只是时间花费较多

//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (obstacleGrid[i][j] == 0) { // 无障碍
//
//                    // 在第一行/第一列, 如果存在障碍, 不止障碍点无法通行, 其之后的点也无法通行
//                    if (i == 0) {
//                        dp[0][j] = (j > 0 && dp[0][j-1] == 0) ? 0 : 1;
//                    } else if (j == 0) {
//                        dp[i][0] = (dp[i-1][0] == 0) ? 0 : 1; // 此时无需判断 i>0, 因为always true
//                    } else {
//                        dp[i][j] = dp[i-1][j] + dp[i][j-1];
//                    }
//                } else {
//                    dp[i][j] = 0;
//                }
//            }
//        }
//        return dp[m-1][n-1];
    }

}
