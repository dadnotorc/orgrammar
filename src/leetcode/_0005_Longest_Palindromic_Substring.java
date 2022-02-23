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

    private int l, r;

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) { return s; }

        int n = s.length();

        for (int i = 0; i < n - 1; i++) { // 到 n - 2 停止, 因为第二个 helper 会检查 i + 1
            helper(s, i, i);
            helper(s, i , i + 1);
        }

        return s.substring(l, r + 1);
    }

    private void helper(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        if (r - l < j - i) {
            l = i + 1; // i, j 字符不相同, 但是 i + 1 和 j - 1 字符相同
            r = j - 1;
        }
    }




    @Test
    public void test1() {
        longestPalindrome("cbbd");
    }
}
