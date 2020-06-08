/*
Medium
#DP, #Knapsack
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 518. Coin Change 2
 *
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 *
 * Example 1:
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * Example 2:
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 *
 * Example 3:
 * Input: amount = 10, coins = [10]
 * Output: 1
 *
 * Note: You can assume that
 * - 0 <= amount <= 5000
 * - 1 <= coin <= 5000
 * - the number of coins is less than 500
 * - the answer is guaranteed to fit into signed 32-bit integer
 *
 * Knapsack problem 背包问题
 */
public class _0518_CoinChange2 {


    /**
     * DP解法 - 在bottom up基础上, 将2D array换成1D array
     * dp[i] 记录余额为i时, combination数量
     *
     * dp[i] += dp[i - 硬币面值]  i必须 >= 面值
     *
     * 例如 amount = 5, coins = [1,2,5]
     *              0 1 2 3 4 5
     *  使用$1硬币时  1 1 1 1 1 1
     *  使用$2硬币时  1 1 2 2 3 3
     *  使用$5硬币时  1 1 2 2 3 4
     *
     *  注意:
     *  1. 别忘了先定义dp[0] = 1, 余额为0, 仍有1种方法, 即不取任何硬币
     *  2. 将每次i初始定义为当前面值, 则可以保证 i >= 面值
     */
    public int change_DP_bottom_up_2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) { // 将i从当前面值开始, 跳过面值 > 余额的部分
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }


    /**
     * DP解法 - bottom up
     * dp[i][j] 记录使用第i个coin, 且余额为j时, combination的数量
     * 注意 与top down不同的时, i现在是1-indexed
     *
     * 当硬币面值coins[i-1] >  余额j时, dp[i][j] = dp[i- 1][j] 因为无法使用当前硬币, 总值会超过余额
     * 当硬币面值coins[i-1] <= 余额j时, dp[i][j] = dp[i- 1][j] + dp[i][j - coins[i - 1]]
     * 例如[2,2]=[1,2]+[2,0]; [2,3]=[1,3]+[2,1]; [2,4]=[1,4]+[2,2]; [2,5]=[1,5]+[2,3]; [5,5]=[2,5]+[5,0]
     *
     * 例如 amount = 5, coins = [1,2,5]
     *      0 1 2 3 4 5
     *      - - - - - -
     *  0 | 1 0 0 0 0 0     不取任何coin
     *  1 | 1 1 1 1 1 1     coins[0]
     *  2 | 1 1 2 2 3 3     coins[1]
     *  5 | 1 1 2 2 3 4     coins[2]
     *
     *  time: O(n * amount); space: O(n * amount). n为coins长度
     *
     *  易错点
     *  1. 别忘了先定义dp[0][0] = 1, 特殊情况amount=0,coins为空
     *  2. for循环, i从1开始, j从0开始
     *  3. 比较当前硬币面值vs余额时, 因为i是1-indexed, 但是coins是0-indexed, 所以coins[i-1]
     */
    public int change_DP_bottom_up(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= coins.length; i++) { // 注意i从1开始, j从0开始
            for (int j = 0; j <= amount; j++) {
                if (coins[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }

        return dp[coins.length][amount];
    }




    /**
     * DP解法 - top down
     * dp[i][j] 记录coins下标为i, 且余额为j时, combination的数量
     * 避免change_TLE中的重复计算
     * i是0-indexed, j是1-indexed
     *
     * time: O(n * amount); space: O(n * amount). n为coins长度
     */
    public int change_DP_top_down(int amount, int[] coins) {

        // dp[i][j] - i范围是[0, coins.length - 1], j范围是[0, amount]. 所以后者+1
        int[][] dp = new int[coins.length][amount + 1];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1); // 初始值-1表示当前dp[i][j]仍未访问
        }

        return helper(amount, coins, 0, dp);
    }

    private int helper(int amount, int[] coins, int index, int[][] dp) {
        if (amount == 0) {
            return 1; // 找到$0只有一种方法
        }
        if (amount < 0 || index >= coins.length) {
            return 0; // 金额为负, 或者已无硬币可用
        }

        if (dp[index][amount] != -1) {
            return dp[index][amount];
        }

        // 两种可能
        // 1. 下层递归仍选取相同的硬币
        // 2. 下层递归选取不同的硬币, 硬币币值从小到大
        int sameCoin = helper(amount - coins[index], coins, index, dp);
        int nextCoin = helper(amount, coins, index + 1, dp); // 注意 这里amount不能减少, 不然后续递归会少
        dp[index][amount] = sameCoin + nextCoin;

        return dp[index][amount];
    }

    /**
     * 会TLE - 因为会多次重复计算
     */
    public int change_TLE(int amount, int[] coins) {
        return helper(amount, coins, 0);
    }

    private int helper(int amount, int[] coins, int index) {
        if (amount == 0) {
            return 1; // 找到$0只有一种方法
        }
        if (amount < 0 || index >= coins.length) {
            return 0; // 金额为负, 或者已无硬币可用
        }

        // 两种可能
        // 1. 下层递归仍选取相同的硬币
        // 2. 下层递归选取不同的硬币, 硬币币值从小到大
        int sameCoin = helper(amount - coins[index], coins, index);
        int nextCoin = helper(amount, coins, index + 1); // 注意 这里amount不能减少, 不然后续递归会少

        return sameCoin + nextCoin;
    }
}
