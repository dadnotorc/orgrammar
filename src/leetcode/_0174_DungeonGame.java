/*
Hard
#Binary Search, #DP
 */
package leetcode;

/**
 * 174. Dungeon Game
 *
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially
 * positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer.
 * If at any point his health point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers)
 * upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase
 * the knight's health (positive integers).
 *
 * In order to reach the princess as quickly as possible,
 * the knight decides to move only rightward or downward in each step.
 *
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 *
 * For example, given the dungeon below, the initial health of the knight must be at least 7
 * if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 *
 * -2(K)  -3    3
 * -5	 -10    1
 * 10     30   -5(P)
 *
 * Note:
 * - The knight's health has no upper bound.
 * - Any room can contain threats or power-ups, even the first room the knight enters
 *   and the bottom-right room where the princess is imprisoned.
 */
public class _0174_DungeonGame {

    /**
     * DP - bottom up - tabulation
     * 骑士到达右下角之前, 如果右下角是回血, 则1点hp足以, 否则需要 1 - dungeon[m-1][n-1]
     * 从右下往左上计算当前点所需最少血量. 如果当前点是回血, 保持1点hp即可
     * 取从下往上 以及 从右往左 两者间较小值
     */
    public int calculateMinimumHP_DP_bottom_up(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 1;
        }

        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n -1], 1); // 右下角出口处

        for (int i = m - 2; i >= 0; i--) { // last column
            dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - dungeon[i][n - 1], 1);
        }
        for (int j = n - 2; j >= 0; j--) { // last row
            dp[m - 1][j] = Math.max(dp[m - 1][j + 1] - dungeon[m - 1][j], 1);
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
//                dp[i][j] = Math.min(
//                        Math.max(dp[i + 1][j] - dungeon[i][j], 1), // 往上走
//                        Math.max(dp[i][j + 1] - dungeon[i][j], 1)); // 往左走

                // 上面的写法可信
                // 或者先取下点与右点中较小值, 再计算当前点所需值
                dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 1);
            }
        }

        return dp[0][0];
    }



    /**
     * DP - top down - memorization
     * 在前一个解法(TLE)的基础上, 加入DP memorization
     */
    public int calculateMinimumHP_DP_top_down(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 1;
        }

        Integer[][] dp = new Integer[dungeon.length + 1][dungeon[0].length + 1];

        return helper(dungeon, dp, 0, 0);
    }

    private int helper(int[][] dungeon, Integer[][] dp, int i, int j) {
        // base case
        if (i == dungeon.length - 1 && j == dungeon[0].length - 1) {
            return Math.max(1 - dungeon[i][j], 1);
        }
        if (dp[i][j] != null)
            return dp[i][j];

        // last row
        if (i == dungeon.length - 1) {
            return dp[i][j] = Math.max(helper(dungeon, dp, i, j + 1) - dungeon[i][j], 1);
        }
        // last column
        if (j == dungeon[0].length - 1) {
            return dp[i][j] = Math.max(helper(dungeon, dp, i + 1, j) - dungeon[i][j], 1);
        }

        int down = helper(dungeon, dp, i + 1, j);
        int right = helper(dungeon, dp, i, j + 1);

        return dp[i][j] = Math.max(Math.min(down, right) - dungeon[i][j], 1);
    }



    /**
     * 会TLE
     */
    public int calculateMinimumHP_TLE(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 1;
        }

        return helper_TLE(dungeon, 0, 0);
    }

    private int helper_TLE(int[][] dungeon, int i, int j) {
        // base case
        if (i == dungeon.length - 1 && j == dungeon[0].length - 1) {
            return Math.max(1 - dungeon[i][j], 1);
        }

        // last row
        if (i == dungeon.length - 1) {
            return Math.max(helper_TLE(dungeon, i, j + 1) - dungeon[i][j], 1);
        }
        // last column
        if (j == dungeon[0].length - 1) {
            return Math.max(helper_TLE(dungeon, i + 1, j) - dungeon[i][j], 1);
        }

        int down = helper_TLE(dungeon, i + 1, j);
        int right = helper_TLE(dungeon, i, j + 1);

        return Math.max(Math.min(down, right) - dungeon[i][j], 1);
    }

}
