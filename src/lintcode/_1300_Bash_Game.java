package lintcode;

import java.util.Arrays;

/**
 * 1300 · Bash Game
 * Easy
 * #DP, #Math
 * Adobe
 *
 * You are playing the following game with your friend:
 * There is a pile of stones on the table, each time one of you take turns to remove 1 to 3 stones.
 * The one who removes the last stone will be the winner. You will take the first turn to remove stones.
 *
 * Both of you are very clever and have optimal strategies for the game.
 * Write a function to determine whether you can win the game given the number of stones.
 *
 * For example, if there are 4 stones, then you will never win the game:
 * no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.
 *
 * Example 1：
 * Input：n = 4
 * Output：False
 * Explanation：Take 1, 2 or 3 first, the other party will take the last one
 *
 * Example 2：
 * Input：n = 5
 * Output：True
 * Explanation：Take 1 first，Than，we can win the game
 */
public class _1300_Bash_Game {

    /*
       这道题的关键在于, 找到规律, 即 n 为 4 的倍数时, 后手赢, 其他时间, 先手赢
     */

    /**
     * 当 n <= 3 时, 先手赢
     * 当 n = 4 时, 后手赢
     * 当  5 <= n <= 7 时, 先手赢
     * 当 n = 8 时, 后手赢
     *
     * 规律: 当 n 为 4 的倍数时, 后手赢; 否则就是先手赢
     */
    public boolean canWinBash(int n) {
        return n % 4 != 0;

        // 或者  return ((n & 3) != 0);
    }


    /**
     * 这道题用不到这么复杂, 直接判断 n % 4 即可.
     */
    public boolean canWinBash_recursive_3(int n) {
        if (n < 4) {
            return true;
        }
        boolean[] dp = new boolean[4];
        Arrays.fill(dp, true);
        dp[0] = false;

        return dp[n % 4];
    }


    /**
     * 递归 - 但是会 TLE
     */
    public boolean canWinBash_recursive(int n) {
        if (n < 4) {
            return true;
        }
        if (n == 4) {
            return false;
        }

        return !canWinBash_recursive(n - 1) ||  // 这里取 ! 是为了递归下一层是对手先手
                !canWinBash_recursive(n - 2) ||
                !canWinBash_recursive(n -3);
    }


    /**
     * 仍会TLE - input n = 325688796
     */
    public boolean canWinBash_recursive_2(int n) {
        if (n < 4) { return true; }

        boolean[] dp = new boolean[n + 1];
        Arrays.fill(dp, true);

        for (int i = 4; i <= n; i++) {
            dp[i] = !(dp[i - 1] && dp[i - 2] && dp[i - 3]);
        }

        return dp[n];
    }
}
