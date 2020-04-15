/*
Easy
#DP
Google
 */
package lintcode;

/**
 * 514. Paint Fence
 *
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * You have to paint all the posts such that no more than 2 adjacent fence posts have the same color.
 * Return the total number of ways you can paint the fence.
 *
 * Notice
 * - n and k are non-negative integers.
 *
 * Example 1:
 * Input: n=3, k=2
 * Output: 6
 * Explanation:
 *           post 1,   post 2, post 3
 *     way1    0         0       1
 *     way2    0         1       0
 *     way3    0         1       1
 *     way4    1         0       0
 *     way5    1         0       1
 *     way6    1         1       0
 *
 * Example 2:
 * Input: n=2, k=2
 * Output: 4
 * Explanation:
 *           post 1,   post 2
 *     way1    0         0
 *     way2    0         1
 *     way3    1         0
 *     way4    1         1
 */
public class _0514_PaintFence {

    /**
     * 参考 https://www.iteye.com/blog/yuanhsh-2219891
     * 假设:
     * D(n) - 表示最后两个相邻posts为 不同 颜色的染色可能数
     * S(n) - 表示最后两个相邻posts为 相同 颜色的染色可能数
     * T(n) - 表示符合条件的所有染色可能总数 - T(n) = D(n) + S(n)
     *
     * D(n) = (k-1) * T(n-1) -- n时不同颜色的可能数 = (k-1)种颜色 * (n-1)时的所有可能数
     * S(n) = (k-1) * T(n-2) -- 因为连续相同不能超过两块, n与(n-1)相同, 则肯定与(n-2)不同 = (k-1)种颜色 * (n-2)时的所有可能数
     *
     * 所以 T(n) = D(n) + S (n) = (k-1) * (T(n-1) + T(n-2))
     *
     * 使用DP来维护
     */
    public int numWays(int n, int k) {

        // rolling - 只保留前两块+当前这块的值
        int[] dp = new int[3];
        // dp[0] = 0; 可以不用写
        dp[1] = k; // 第一块有k种可能
        dp[2] = k * k; // k + k * (k-1) -- 前两块相同颜色的可能 + 不同颜色的可能

        if (n <= 2)
            return dp[n];

        if (k == 1) // 有至少3块posts, 但只有一种颜色 - 无法完成
            return 0;

        for (int i = 3; i <= n; i++) { // 注意! 因为用的1作为index base, 所以边界为 <= n
            dp[i % 3] = (k - 1) * (dp[(i - 1) % 3] + dp[(i - 2) % 3]);
        }

        return dp[n % 3];
    }
}
