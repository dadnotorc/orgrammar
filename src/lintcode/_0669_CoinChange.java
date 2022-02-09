package lintcode;

import org.junit.Test;

import java.util.HashMap;
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
 * Input: [1, 2, 5], 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example2
 * Input: [2], 3
 * Output: -1
 *
 * 雷同 leetcode 322
 */
public class _0669_CoinChange {

    /**
     * DP 解法 - dp[i] 为能构成当前 amount 的最少硬币数
     */
    public int coinChange_dp(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount == 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            dp[i] = -1; // 也可以在 for 循环之前使用 Arrays.fill(dp, -1);

            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != -1) {

                    // 这里注意 别忘了 dp[i - coin] + 1, 之前错在忘记 + 1 了
                    if (dp[i] == -1 || dp[i] > dp[i - coin] + 1) {
                        dp[i] = dp[i - coin] + 1;
                    }
                }
            }
        }

        return dp[amount];
    }


    /**
     * 不推荐使用
     *
     * 用 hashmap 替代 int array, 但是会慢很多
     *
     f(x) - 求最少需要多少硬币, 总和为 $x
     先计算 f(0), f(1), f(2), ..., f(x-1), f(x)
     假设硬币总类为 [1,2,k]
     1 = 0+1       -> f(1) = f(0)+1
     2 = 1+1 = 0+2 -> f(2) = f(1)+1 = f(0)+1
     3 = 2+1 = 1+2 -> f(3) = f(2)+1 = f(1)+1
     ...
     i = (i-1)+1 = (i-2)+2 = (i-k)+k
     -> f(i) = f(i-1)+1
     = f(i-2)+1
     = f(i-k)+1
     */
    public int coinChange(int[] coins, int amount) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // key为金额, value为硬币枚数
        map.put(0, 0); // need 0 coin to reach $0

        for (int i = 1; i <= amount; i++) {
            for (int k : coins) { // 当前硬币面额, k
                if (map.containsKey(i - k)) {
                    int value = map.get(i - k) + 1;
                    if (map.containsKey(i)) {
                        value = Math.min(map.get(i), value);
                    }
                    map.put(i, value);
                }
            }
        }

        return map.getOrDefault(amount,-1);
    }


    /**
     * 九章 解法 - 也是 dp, 写法不同
     */
    public int coinChange1(int[] coins, int amount) {
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
