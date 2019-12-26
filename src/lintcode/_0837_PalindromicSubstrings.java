/*
Easy
String,
Facebook, LinkedIn
 */
package lintcode;

import org.junit.Test;

/**
 * 837. Palindromic Substrings
 *
 * Given a string, your task is to count how many palindromic substrings in
 * this string. The substrings with different start indexes or end indexes
 * are counted as different substrings even they consist of same characters.
 *
 * Notice
 * - The input string length won't exceed 1000
 *
 * Example1
 * Input: "abc"
 * Output: 3
 * Explanation:
 * 3 palindromic strings: "a", "b", "c".
 *
 * Example2
 * Input: "aba"
 * Output: 4
 * Explanation:
 * 4 palindromic strings: "a", "b", "a", "aba".
 */
public class _0837_PalindromicSubstrings {

    /**
     * 解法1
     * 遍历每个字符, 并以该字符为中心, 向左右扩展寻找回文串
     * time:  O(n ^ 2)
     * space: O(1)
     */
    public int countPalindromicSubstrings(String str) {
        if (str == null || str.length() == 0)
            return 0;

        int ans = 0;

        for (int i = 0; i < str.length(); i++) {
            ans += countPalindrome(str, i, i); // 奇数长度
            ans += countPalindrome(str, i, i+1); // 偶数长度
        }

        return ans;
    }

    // 找出从l和r开始的palindrome substring数量
    private int countPalindrome(String s, int l, int r) {
        int count = 0;

        while (l >= 0 && r < s.length() && (s.charAt(l--) == s.charAt(r++)))
            count++;

        return count;
    }


    /**
     * 解法2 - 九章参考 https://www.jiuzhang.com/solution/palindromic-substrings/#tag-highlight
     * 考虑如果substring(j,i)如果是回文串，那么str[i]和str[j]一定相同，并且一定满足以下两个条件之一
     * 1.substring(j+1,i-1)也是回文串
     * 2.i-j<=2，即substring(j,i)长度<=2
     * 那么我们就只需要顺着这个思路dp就行了
     *
     * DP的用途是避免重复判断已知的回文串
     * time:  O(n ^ 2)
     * space: O(n ^ 2)
     */
    public int countPalindromicSubstrings_jiuzhang(String str) {
        if (str == null || str.length() == 0)
            return 0;

        int ans = 0, n = str.length();

        // dp[j][i] 表示从j到i的子字符串是否为回文串
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) { // j <= i 保证子字符串有效
                if (str.charAt(j) == str.charAt(i)
                        && (i - j <= 2 || dp[j + 1][i - 1] == 1)) {
                    dp[j][i] = 1;
                } else {
                    dp[j][i] = 0;
                }

                ans += dp[j][i];
            }
        }

        return ans;
    }

    @Test
    public void test1() {
        org.junit.Assert.assertEquals(3, countPalindromicSubstrings("abc"));
        org.junit.Assert.assertEquals(3, countPalindromicSubstrings_jiuzhang("abc"));
    }

    @Test
    public void test2() {
        org.junit.Assert.assertEquals(4, countPalindromicSubstrings("aba"));
        org.junit.Assert.assertEquals(4, countPalindromicSubstrings_jiuzhang("aba"));
    }
}
