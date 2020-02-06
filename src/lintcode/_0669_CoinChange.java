package lintcode;

import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.assertEquals;

/**
 * 669. Coin Change
 * Medium
 * Uber
 *
 * You are given coins of different denominations 面额 and a total amount of
 *  money amount. Write a function to compute the fewest number of coins that
 *  you need to make up that amount. If that amount of money cannot be made up
 *  by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example1
 * Input:
 * [1, 2, 5]
 * 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example2
 * Input:
 * [2]
 * 3
 * Output: -1
 */
public class _0669_CoinChange {

    /**
     * f(x) - 求最少需要多少硬币, 总和为 $x
     * 先计算 f(0), f(1), f(2), ..., f(x-1), f(x)
     * 假设硬币总类为 [1,2,k]
     * 1 = 0+1       -> f(1) = f(0)+1
     * 2 = 1+1 = 0+2 -> f(2) = f(1)+1 = f(0)+1
     * 3 = 2+1 = 1+2 -> f(3) = f(2)+1 = f(1)+1
     * ...
     * i = (i-1)+1 = (i-2)+2 = (i-k)+k
     *  -> f(i) = f(i-1)+1
     *          = f(i-2)+1
     *          = f(i-k)+1
     */
    public int coinChange(int[] coins, int target) {
        Hashtable<Integer, Integer> table = new Hashtable<>();
        // key为金额, value为硬币枚数
        table.put(0, 0); // need 0 coin to reach $0

        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < coins.length; j++) {
                int k = coins[j]; // 当前硬币面额, k
                /**
                 * 注意必须判断table中value是否为-1, 说明未找到满足相应金额的硬币组合
                 */
                if (table.containsKey(i - k) && table.get(i-k) != -1) {
                    // 找到f(i-k)
                    int value = table.get(i - k) + 1;
                    if (table.containsKey(i)) {
                        value = Math.min(table.get(i), value);
                    }
                    table.put(i, value);
                }
            }

            if (!table.containsKey(i)) {
                table.put(i, -1);
            }
        }
        return table.get(target);
    }

    /* 九章算法 */
    public int coinChange1(int[] coins, int amount) {
        // write your code here
        int[] dp = new int[amount + 1];
        int coinTypes = coins.length;

        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = -1;

            for (int j = 0; j < coinTypes; j++) {

                if (i >= coins[j] && dp[i - coins[j]] != -1) {

                    if (dp[i] == -1 || dp[i - coins[j]] + 1 < dp[i]) {

                        dp[i] = dp[i - coins[j]] + 1;
                    }
                }
            }
        }
        return dp[amount];
    }

    @Test
    public void test1() {
        int[] coins = {1,2,5};
        int amount = 11;
        int expected = 3;
        int actual = new _0669_CoinChange().coinChange(coins, amount);
        assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        int[] coins = {2};
        int amount = 3;
        int expected = -1;
        int actual = new _0669_CoinChange().coinChange(coins, amount);
        assertEquals(expected, actual);
    }
}
