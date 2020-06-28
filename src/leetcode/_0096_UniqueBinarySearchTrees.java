/*
Medium
#DP, #Tree
 */
package leetcode;

/**
 * 96. Unique Binary Search Trees
 *
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 *
 * Example:
 *
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class _0096_UniqueBinarySearchTrees {

    /**
     * n=0    null
     * dp[0]=1
     *
     * n=1     1
     *       /  \
     *    dp[0] dp[0]
     * dp[1] = dp[0] x dp[0] = 1
     *
     * n=2     1     +     2
     *       /  \        /  \
     *    dp[0] dp[1] dp[1] dp[0]
     * dp[2] = dp[0] x dp[1] + dp[0] x dp[1] = 1 + 1 = 2
     *
     * n=3     1     +     2     +     3
     *       /  \        /  \        /  \
     *    dp[0] dp[2] dp[1] dp[1] dp[2] dp[0]
     * dp[3] = dp[0] x dp[2] + dp[1] x dp[1] + dp[2] x dp[0] = 2 + 1 + 2 = 5
     *
     * n=4     1     +     2     +     3     +     3
     *       /  \        /  \        /  \        /  \
     *    dp[0] dp[3] dp[1] dp[2] dp[2] dp[1] dp[3] dp[0]
     * dp[4] = dp[0] x dp[3] + dp[1] x dp[2] + dp[2] x dp[1] + dp[3] x dp[0] = 5 + 2 + 2 + 5 = 14
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }





    public int numTrees_2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = i; j >= 1; j--) {
                dp[i] += dp[i - j] * dp[j - 1];
            }
        }

        return dp[n];
    }
}
