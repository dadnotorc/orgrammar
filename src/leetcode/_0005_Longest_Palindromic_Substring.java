package leetcode;

import org.junit.Test;

/**
 * 5. Longest Palindromic Substring
 * Medium
 * #String, #DP
 *
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */
public class _0005_Longest_Palindromic_Substring {


    /**
     * 从每个字符出发, 双指针向左右移动, 检查是否存在回文
     * 回文长度可能为奇数 / 偶数, 所以要检查
     * - 以 i 为中心
     * - 以 i 与 i + 1 为中心
     *
     * 时间 - O(n ^ 2), helper循环 1 + 2 + ... + n / 2 + ... + 2 + 1. 有两个 helper, 合起来就是 (n^2 + 2xn) / 2
     * 空间 - O(1)
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) { return s; }

        int n = s.length();

        int[] boundary = new int[2]; // [0] = 左边界, [1] = 右边界

        for (int i = 0; i < n - 1; i++) { // 到 n - 2 停止, 因为第二个 helper 会检查 i + 1
            helper(s, i, i, boundary);
            helper(s, i , i + 1, boundary);
        }

        return s.substring(boundary[0], boundary[1] + 1);
    }

    private void helper(String s, int i, int j, int[] boundary) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        if (boundary[1] - boundary[0] < j - i - 1) { // 注意 这里一定要 - 1, 因为当前 i 与 j 是不相同的字符
            boundary[0] = i + 1; // i, j 字符不相同, 但是 i + 1 和 j - 1 字符相同
            boundary[1] = j - 1;
        }
    }


    /**
     * 太慢了
     *
     * 二维 DP 数组, dp[i][j] 表示从 i 到 j 的子字符串是否为 palindrome
     * dp[i][j] = true, if
     *    char[i] == char[j] && dp[i + 1][j - 1] == true
     */
    public String longestPalindrome_DP(String s) {
        if (s == null || s.length() < 2) { return s; }

        int n = s.length();

        int l = 0, r = 0;

        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }


        for (int i = n - 1; i >= 0; i--) { // 这样写, 保证 i 在左, j 在右  (i <= j)
            for (int j = i; j < n; j++) {
                if (i == j) {
                    continue;
                }

                if (s.charAt(i) == s.charAt(j) && (
                        j == i + 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;

                    if (r - l < j - i + 1) { // 注意 这里是 +1, 不是 -1, 因为 此时 i j 字符相同
                        r = j;
                        l = i;
                    }
                }
            }
        }

        return s.substring(l, r + 1);
    }

}
