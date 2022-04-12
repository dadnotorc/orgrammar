package lintcode;

/**
 * 397 · Longest Continuous Increasing Subsequence - left to right OR right to left
 * Easy
 * #Array
 * Facebook Meta
 *
 * Give an integer array，find the longest increasing continuous subsequence in this array.
 *
 * An increasing continuous subsequence:
 * - Can be from right to left or from left to right.
 * - Indices of the integers in the subsequence should be continuous.
 *
 * Example 1:
 * Input: [5, 4, 2, 1, 3]
 * Output: 4
 * Explanation:
 * For [5, 4, 2, 1, 3], the LICS  is [5, 4, 2, 1], return 4.
 *
 * Example 2:
 * Input: [5, 1, 2, 3, 4]
 * Output: 4
 * Explanation:
 * For [5, 1, 2, 3, 4], the LICS  is [1, 2, 3, 4], return 4.
 *
 * Challenge
 * O(n) time and O(1) extra space.
 */
public class _0397_Longest_Continuous_Increasing_Subsequence_both_directions {

    /**
     * 一次遍历, 两个检查, 看递增 还是 递减
     * - 递增 -> 递增长度 +1, 递减长度重置为 1
     * - 递减 -> 递增长度重置为 1, 递减长度 +1
     * 每一步更新最长长度
     */
    public int longestIncreasingContinuousSubsequence_2(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int longest = 1, inc = 1, dec = 1;

        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                inc++;
                dec = 1;
            } else if (A[i] < A[i - 1]) {
                inc = 1;
                dec++;
            } else { // 注意这里, i 与 i - 1 不变是, inc 与 dec 都要重置
                inc = 1;
                dec = 1;
            }

            longest = Math.max(longest, Math.max(inc, dec));
        }

        return longest;
    }




    /**
     * 不如另一个快
     *
     * 检查连续 3 个数字, 看上升/下降趋势是否有变化
     * - 无变化 -> 递增 curMax
     * - 有 -> 更新 ans, 并重置 curMax
     *
     * curMax 从 2 开始
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null) { return 0; }
        if (A.length < 3) { return A.length; }

        int ans = 0, curMax = 2;
        for (int i = 2; i < A.length; i++) {
            if (sameTrend(A[i - 2], A[i - 1], A[i])) {
                curMax++;
            } else {
                ans = Math.max(ans, curMax);
                curMax = 2;
            }
        }

        return Math.max(ans, curMax);
    }

    private boolean sameTrend(int a, int b, int c) {
        return (a > b && b > c) || (a < b && b < c);
    }
}
