/*
Medium
#Array, #String, #DP, #0-1背包

 */
package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 474. Ones and Zeroes
 *
 * You are given an array of binary strings strs and two integers m and n.
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 * A set x is a subset of a set y if all elements of x are also elements of y.
 *
 * Example 1:
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
 * Output: 4
 * Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
 * Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
 * {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
 *
 * Example 2:
 * Input: strs = ["10","0","1"], m = 1, n = 1
 * Output: 2
 * Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 *
 * Constraints:
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] consists only of digits '0' and '1'.
 * 1 <= m, n <= 100
 */
public class _0474_OnesAndZeroes {

    /**
     * 改进 - 使用二维数组, 更快时间更少空间
     */
    public int findMaxForm_3(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        int[] counts;

        for (int i = 0; i < len; i++) {
            counts = count(strs[i]);
            for (int j = m; j >= counts[0]; j--) {
                for (int k = n; k >= counts[1]; k--) {
                    if (j >= counts[0] && k >= counts[1]) {
                        dp[j][k] = Math.max(
                                dp[j][k],
                                dp[j - counts[0]][k - counts[1]] + 1
                        );
                    }
                }
            }
        }

        return dp[m][n];
    }


    /**
     * dp[i][j][k] 表示通过前 i 个字符串, 包含 j 0's 与 k 1's, 能获得最大的 subset 大小
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len][m + 1][n + 1];
        int[] counts = count(strs[0]);

        // 注意! 不止是 dp[i][counts[0]][counts[1]] 需要赋值为1, 其右的座标点都需要赋值. 考虑整合起来一起写
        if (counts[0] <= m && counts[1] <= n) {
            for (int j = counts[0]; j <= m; j++) {
                for (int k = counts[1]; k <= n; k++) {
                    dp[0][j][k] = 1;
                }
            }
        }

        for (int i = 1; i < len; i++) {
            counts = count(strs[i]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= counts[0] && k >= counts[1]) {
                        dp[i][j][k] = Math.max(
                                dp[i - 1][j][k],
                                dp[i - 1][j - counts[0]][k - counts[1]] + 1
                        );
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }

//        for (int i = 0; i < len; i++) {
//            counts = count(strs[i]);
//            for (int j = 0; j <= m; j++) {
//                for (int k = 0; k <= n; k++) {
//                    if (j >= counts[0] && k >= counts[1]) {
//                        if (i == 0) {
//                            dp[i][j][k] = 1;
//                        } else {
//                            dp[i][j][k] = Math.max(
//                                    dp[i - 1][j][k],
//                                    dp[i - 1][j - counts[0]][k - counts[1]] + 1
//                            );
//                        }
//                    } else {
//                        if (i != 0) {
//                            dp[i][j][k] = dp[i - 1][j][k];
//                        }
//                    }
//                }
//            }
//        }

        return dp[len - 1][m][n];
    }

    /**
     * 另一种写法 也是用 3维数组. 空间消耗稍微大一点, 时间消耗稍微小一点
     */
    public int findMaxForm_2(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        int[] counts = new int[] {0, 0};

        for (int i = 0; i <= len; i++) {
            if (i > 0) {
                counts = count(strs[i - 1]);
            }
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (i == 0) {
                        dp[i][j][k] = 0;
                    } else if (j >= counts[0] && k >= counts[1]) {
                        dp[i][j][k] = Math.max(
                                dp[i - 1][j][k],
                                dp[i - 1][j - counts[0]][k - counts[1]] + 1
                        );
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }

        return dp[len][m][n];
    }

    // 作用是读取字符串 s 中, 0 与 1 的个数, 存入一个二维数组中
    public int[] count(String s) {
        int[] res = new int[2];
        for (char c : s.toCharArray()) {
            if (c == '0') res[0]++;
            if (c == '1') res[1]++;
        }
        return res;
    }

    @Test
    public void test1() {
        String[] strs = {"10","0001","111001","1","0"};
        int m = 3, n = 4, expected = 3;
        int actual = findMaxForm(strs, m, n);
        assertEquals(expected, actual);
    }
}
