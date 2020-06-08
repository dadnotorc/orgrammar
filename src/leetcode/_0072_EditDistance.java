/*
Hard
#String, #DP
 */
package leetcode;

import java.util.Arrays;

/**
 * 72. Edit Distance
 *
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 * - Insert a character
 * - Delete a character
 * - Replace a character
 *
 * Example 1:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 *
 * Example 2:
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */
public class _0072_EditDistance {

    // todo 再读一次

    /**
     * DP解法 - bottom up
     * dp[i][j] 表示将word1从头到i位, 改变成word2从头到j位, 所需要的最小次数 (从前往后)
     *
     * time: O(n * m); space (n * m); n为word1长度, m为word2长度
     */
    public int minDistance_DP_bottom_up(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2 == null ? 0 : word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return word1.length();
        }

        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;

        int[][] dp = new int[len1 + 1][len2 + 1]; // 注意+1

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (chars1[i] == chars2[j]) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else  {
                    // insert 为 j + 1
                    // delete 为 i + 1
                    // replace 为
                    dp[i + 1][j + 1] = 1 + Math.min(dp[i][j + 1], Math.min(dp[i + 1][j], dp[i][j]));
                }
            }
        }

        return dp[len1][len2];
    }


    /**
     * DP解法 - top down
     * dp[i][j] 表示将word1从i位到结束, 改变成word2从j位到结束, 需要的最少次数 (从后往前)
     *
     * time: O(n * m); space (n * m); n为word1长度, m为word2长度
     */
    public int minDistance_DP_top_down(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2 == null ? 0 : word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return word1.length();
        }

        // dp[i][j] 为将i所指字符变成j所指字符
        int[][] dp = new int[word1.length()][word2.length()];
        for (int[] row : dp) {
            Arrays.fill(row, -1); // 初始值定为-1而不是0, 因为如果两个字符相同, 需要改变的步数即为0. 初始定为-1, 表示该位置仍未访问
        }

        return helper(word1.toCharArray(), word2.toCharArray(), 0, 0, dp);
    }

    private int helper(char[] chars1, char[] chars2, int i, int j, int[][] dp) {
        if (i == chars1.length) {
            return chars2.length - j;
        }
        if (j == chars2.length) {
            return chars1.length - i;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (chars1[i] == chars2[j]) {
            dp[i][j] = helper(chars1, chars2, i + 1, j + 1, dp); // 当前字符相同, 无需改变
        } else {
            int toInsert = helper(chars1, chars2, i, j + 1, dp); // 在s1中insert j所指字符, 所以i不变, j后移
            int toDelete = helper(chars1, chars2, i + 1, j, dp); // 在s1中delete i所指字符, 所以i后移, j不变
            int toReplace = helper(chars1, chars2, i + 1, j + 1, dp); // 在s1中将i所指字符替换成j所指字符, 所有i,j即后移
            dp[i][j] = 1 + Math.min(toInsert, Math.min(toDelete, toReplace)); // 别忘了+1
        }

        return dp[i][j];
    }



    /**
     * Recursion解法 - 会TLE
     */
    public int minDistance_TLE(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2 == null ? 0 : word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return word1.length();
        }

        return helper_TLE(word1, word2, 0, 0);
    }

    private int helper_TLE(String s1, String s2, int i, int j) {
        // 出口:　如果一个字符串已读完, 则将另一个字符串的剩余部分返回
        if (i == s1.length()) {
            return s2.length() - j;
        }
        if (j == s2.length()) {
            return s1.length() - i;
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return helper_TLE(s1, s2, i + 1, j + 1);
        } else {
            int toInsert = helper_TLE(s1, s2, i, j + 1); // 在s1中insert j所指字符, 所以i不变, j后移
            int toDelete = helper_TLE(s1, s2, i + 1, j); // 在s1中delete i所指字符, 所以i后移, j不变
            int toReplace = helper_TLE(s1, s2, i + 1, j + 1); // 在s1中将i所指字符替换成j所指字符, 所有i,j即后移
            return 1 + Math.min(toInsert, Math.min(toDelete, toReplace)); // 别忘了+1
        }
    }
}
