package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 63. Unique Paths II
 * Medium
 * #Array, #DP, #Matrix
 *
 * A robot is located at the top-left corner of a m x n grid
 *
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid.
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and space is marked as 1 and 0 respectively in the grid.
 *
 * Example 1:
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 *
 * Example 2:
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 *
 * Constraints:
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] is 0 or 1.
 */
public class _0063_Unique_Paths_II {

    /**
     * DP - 2D array
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) { return 0; }
        int m = obstacleGrid.length, n = obstacleGrid[0].length;

        int[][] dp = new int[m + 1][n + 1];
        dp[0][1] = 1; // 只赋值这一个, 其他 dp 值保持为 0.

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j + 1];
                } else {
                    dp[i + 1][j + 1] = 0;
                }
            }
        }

        return dp[m][n];
    }


    /**
     * DP - 简化 1D array
     */
    public int uniquePathsWithObstacles_2(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) { return 0; }

        if (obstacleGrid[0][0] == 1) { return 0; } // 把特殊情况写出来, 之后就不用考虑

        int m = obstacleGrid.length, n = obstacleGrid[0].length;

        int[] dp = new int[n + 1];
        dp[1] = 1; // 注意, 不是修改 dp[0], 而是在循环中会遇到的 dp[1] (就像 2D 写法中, 修改 dp[0][1])

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[j + 1] += dp[j];
                } else {
                    dp[j + 1] = 0;
                }
            }
        }

        return dp[n];

        // 另一写法, 但是会不停的做 if (j != 0)
        /*
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    if (j != 0) {
                        dp[j] += dp[j - 1];
                    } // 不要在这里设置 dp[0]. 因为当 [[0,0],[1,1],[0,0]], 最后一排 如果设置 dp[0] = 1, 就错了
                } else {
                    dp[j] = 0;
                }
            }
        }

        return dp[n - 1];
         */
    }






    /**
     * DFS 会 TLE
     */

    int ans = 0; // 这里 ans 不能通过参数传入 dfs, 因为其值不会返回到 caller

    public int uniquePathsWithObstacles_DFS(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) { return 0; }

        dfs(obstacleGrid, 0, 0);

        return ans;
    }

    private void dfs(int[][] obstacleGrid, int x, int y) {
        if (obstacleGrid[x][y] == 1) {
            return;
        }

        if (x == obstacleGrid.length - 1 && y == obstacleGrid[0].length - 1) {
            ans++;
            return;
        }

        if (x < obstacleGrid.length - 1) {
            dfs(obstacleGrid, x + 1, y);
        }

        if (y < obstacleGrid[0].length - 1) {
            dfs(obstacleGrid, x, y + 1);
        }
    }



    /**
     * BFS - 会 TLE
     */
    class ResultType {
        int x, y;
        public ResultType(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int uniquePathsWithObstacles_BFS(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) { return 0; }

        int m = obstacleGrid.length, n = obstacleGrid[0].length;

        int ans = 0;
        Queue<ResultType> q = new LinkedList<>();
        q.offer(new ResultType(0, 0));

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                ResultType cur = q.poll();
                if (obstacleGrid[cur.x][cur.y] == 0) {
                    if (cur.x == m - 1 && cur.y == n - 1) {
                        ans++;
                    } else {
                        if (cur.x < m - 1) { q.offer(new ResultType(cur.x + 1, cur.y)); }
                        if (cur.y < n - 1) { q.offer(new ResultType(cur.x, cur.y + 1)); }
                    }
                }
            }
        }

        return ans;
    }
}
