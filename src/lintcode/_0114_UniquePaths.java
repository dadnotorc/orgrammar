/*
Easy
#Array, #DP
 */
package lintcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 114. Unique Paths
 *
 * A robot is located at the top-left corner of a m x n grid.
 *
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid.
 *
 * How many possible unique paths are there?
 *
 * Notice
 * - m and n will be at most 100.
 * - The answer is guaranteed to be in the range of 32-bit integers
 *
 * Example 1:
 * Input: n = 1, m = 3
 * Output: 1
 * Explanation: Only one path to target position.
 *
 * Example 2:
 * Input:  n = 3, m = 3
 * Output: 6
 * Explanation:
 * 	D : Down
 * 	R : Right
 * 	1) DDRR
 * 	2) DRDR
 * 	3) DRRD
 * 	4) RRDD
 * 	5) RDRD
 * 	6) RDDR
 *
 * 此题类似 leetcode 62. Unique Paths
 */
public class _0114_UniquePaths {


    /**
     * 数学解法 - m x n, 从左上到右下, 需要向右移动(m-1)次, 向下移动(n-1)次
     * 例如, 7 x 3, 无论哪条路线, 都需要向右移动6次, 向下移动2次
     * 可用路线总数 = total permutations = (m-1 + n-1)! / ((m-1)! * (n-1)!)
     * 假设 x = m-1; y = n-1;
     * total permutations = (x + y)! / (x! * y!)
     * 假设 x > y, 这样能减少后续计算
     * (x + y)! = (x+y) * (x+y-1) * ... * (x+1) * (x) * (x-1) * ... * 1 = (x+y) * ... (x+1) * x!
     * 约掉 x!
     * total permutations = ((x+y) * ... * (x+1)) / y!
     *
     * time: O(n-1)
     * space: O(1)
     */
    public int uniquePaths_math(int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (m == 1 || n == 1)
            return 1;

        int x = m - 1;
        int y = n - 1;

        // swap - ensure x > y
        if (x < y) {
            int xor = x ^ y;
            x = xor ^ x;
            y = xor ^ y;
        }

        // 从1开始, 因为后续计算用乘除法;
        // 先用long, 返回时转成int
        long ans = 1;

        // i 从 x+1 递增到 x+y
        // j 从 1   递增到 y
        for (int i = x + 1, j = 1; i <= x + y; i++, j++) {
            ans *= i;
            ans /= j;
        }

        return (int)ans;
    }


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
    public int uniquePaths_dp_2DArray(int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (m == 1 || n == 1)
            return 1;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j]  = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];
    }


    /**
     * DP - 更加简化, 使用1D array. 数组记录前一纵列内容. 例如, 3 * 3
     * i=0 | i=1 | i=2
     * 1   | 1   | 1
     * 1   | 2   | 3
     * 1   | 3   | 6
     * dp[]记录每一纵列内容
     *
     * time:  O(m*n)
     * space: O(n)
     */
    public int uniquePaths_dp(int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (m == 1 || n == 1)
            return 1;

        int[] dp = new int[n]; // 一列
        Arrays.fill(dp, 1);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j-1] + dp[j];
            }
        }
        return dp[n-1];
    }


    /**
     * 用BFS或者DFS - 会超时
     */

    class Coord{
        int x, y;
        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private boolean isValid(Coord coord, int m, int n) {
        return coord.x >=0 && coord.x < m && coord.y >= 0 && coord.y < n;
    }

    public int uniquePaths_BFS(int m, int n) {
        Queue<Coord> queue = new LinkedList<>();
        int ans = 0;

        queue.offer(new Coord(0, 0));

        while (!queue.isEmpty()) {
            Coord cur = queue.poll();
            if (cur.x == m - 1 && cur.y == n - 1) { // arrive at bottom right
                ans++;
            } else {
                Coord neighborDown = new Coord(cur.x + 1, cur.y);
                Coord neighborRight = new Coord(cur.x, cur.y + 1);

                if (isValid(neighborDown, m, n)) // 可以这里判断, 或者在刚获得cur时判断, 不然会无限制增加无效坐标
                    queue.offer(neighborDown);
                if (isValid(neighborRight, m, n))
                    queue.offer(neighborRight);
            }
        }

        return ans;
    }
}
