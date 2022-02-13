package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 62. Unique Paths
 * Medium
 * #Array, #DP
 * Facebook meta
 *
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 10^9.
 *
 * 这里的 m = num of rows, n = num of column
 *
 * Example 1:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 *
 * Example 2:
 * Input: m = 3, n = 7
 * Output: 28
 *
 * Constraints:
 * - 1 <= m, n <= 100
 * - It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
 *
 * 此题类似 lintcode 114. Unique Paths
 */
public class _0062_Unique_Paths {

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
     * time: O(m * n), space: O(m * n)
     */
    public int uniquePaths_DP_2D(int m, int n) {
        if (m == 1 || n == 1)
            return 1;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 简化DP - 使用 1D array
     * time: O(m * n), space: O(n)
     */
    public int uniquePaths_DP_1D(int m, int n) {
        if (m == 0 || n == 0) { return 0; }
        if (m == 1 || n == 1) { return 1; }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];
    }





    /* ----------------------- */

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
    public int uniquePaths_bfs(int m, int n) {
        if (m == 0 || n == 0) { return 0; }
        if (m == 1 || n == 1) { return 1; }

        int ans = 0;
        Queue<ResultType> q = new LinkedList<>();
        q.offer(new ResultType(0, 0));

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                ResultType cur = q.poll();
                if (cur.x == m -1 && cur.y == n - 1) {
                    ans++;
                } else {
                    if (cur.x < m - 1) { q.offer(new ResultType(cur.x + 1, cur.y)); }
                    if (cur.y < n - 1) { q.offer(new ResultType(cur.x, cur.y + 1)); }
                }
            }
        }

        return ans;
    }


    /**
     * DFS - 也会 TLE
     */
    int ans = 0; // 这里 ans 不能通过参数传入 dfs, 因为其值不会返回到 caller

    public int uniquePaths_dfs(int m, int n) {
        if (m == 0 || n == 0) { return 0; }
        if (m == 1 || n == 1) { return 1; }

        dfs(m, n, 0, 0);

        return ans;
    }

    private void dfs(int m, int n, int x, int y) {
        if (x == m - 1 && y == n - 1) {
            ans++;
            return;
        }

        if (x < m - 1) {
            dfs(m, n, x + 1, y);
        }

        if (y < n - 1) {
            dfs(m, n, x, y + 1);
        }
    }
}
