/*
Medium
#Array, #DP
 */
package leetcode;

import java.util.Arrays;

/**
 * 1035. Uncrossed Lines
 *
 * We write the integers of A and B (in the order they are given) on two separate horizontal lines.
 *
 * Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:
 * - A[i] == B[j];
 * - The line we draw does not intersect any other connecting (non-horizontal) line.
 *
 * Note that a connecting lines cannot intersect even at the endpoints:
 * each number can only belong to one connecting line.
 *
 * Return the maximum number of connecting lines we can draw in this way.
 *
 * Example 1:
 * Input: A = [1,4,2], B = [1,2,4]
 * 1 4 2
 * |  \
 * 1 2 4
 * Output: 2
 * Explanation: We can draw 2 uncrossed lines as in the diagram.
 * We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4
 * will intersect the line from A[2]=2 to B[1]=2.
 *
 * Example 2:
 * Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
 * Output: 3
 *
 * Example 3:
 * Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
 * Output: 2
 *
 * Note:
 * 1 <= A.length <= 500
 * 1 <= B.length <= 500
 * 1 <= A[i], B[i] <= 2000
 *
 * 此题与Longest Common Subsequence (LCS)相同
 */
public class _1035_UncrossedLines {

    /**
     * Further optimization using 2 rows of array
     */



    /**
     * DP解法 - bottom up 2D DP
     * dp[i][j] denote the longest common subsequence between the first i elements of A and the first j elements of B
     * time: O(m * n); space: O(m * n)
     *
     * 例2中, A = [2,5,1,2,5], B = [10,5,2,1,5,2]. 则matrix dp[][] 为
     *      ~ 10 5 2 1 5 2
     *      - -- - - - - -
     *  ~ | 0  0 0 0 0 0 0
     *  2 | 0  0 0 1 1 1 1
     *  5 | 0  0 1 1 1 2 2
     *  1 | 0  0 1 1 2 2 2
     *  2 | 0  0 1 2 2 2 3
     *  5 | 0  0 1 2 2 3 3
     */
    public int maxUncrossedLines_DP_bottom_up(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i- 1] == B[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }


    /**
     * DP解法 - top down with memorization
     *
     * time: O(m * n); space: O(m * n)
     */
    public int maxUncrossedLines_DP_top_down(int[] A, int[] B) {
        int[][] dp = new int[A.length][B.length];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return lcs(A, B, 0, 0, dp);
    }

    // 从后往前比较
    private int lcs(int[] A, int[] B, int i, int j, int[][] dp) {
        if (i == A.length || j == B.length) {
            return 0;
        }

        if (dp[i][j] != -1) { // dp[i][j]已被赋值
            return dp[i][j];
        }

        if (A[i] == B[j])
            dp[i][j] = 1 + lcs(A, B, i + 1, j + 1, dp);
        else // 最后一位不同 A="...1", B="...2". 将A最后一位取走和B继续递归, 另外将B最后一位取走和A继续递归, 最后两者比较取较大者
            dp[i][j] = Math.max(lcs(A, B, i + 1, j, dp), lcs(A, B, i, j + 1, dp));

        return dp[i][j];
    }





    /**
     * top down - 会TLE
     */
    public int maxUncrossedLines_TLE(int[] A, int[] B) {
        return lcs(A, B, 0, 0);
    }

    // 从后往前比较
    private int lcs(int[] A, int[] B, int i, int j) {
        if (i == A.length || j == B.length) {
            return 0;
        }

        int count = 0;

        if (A[i] == B[j]) // 最后一位相同 A="...1", B="...1". 将1拿走, 长度加1
            count = 1 + lcs(A, B, i + 1, j + 1);
        else // 最后一位不同 A="...1", B="...2". 将A最后一位取走和B继续递归, 另外将B最后一位取走和A继续递归, 最后两者比较取较大者
            count = Math.max(lcs(A, B, i + 1, j), lcs(A, B, i, j + 1));

        return count;
    }
}
