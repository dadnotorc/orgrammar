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
     * 考虑如果substring(l,r)如果是回文串，那么str[l]和str[r]一定相同，并且一定满足以下两个条件之一
     * 1. r-l<=2，即substring(l,r)长度<=3 (例如 a, aa, aba)
     * 2. substring(l+1,r-1)也是回文串
     * 那么我们就只需要顺着这个思路dp就行了. 注意: 1,2的顺序不能乱, 不然当r=0时, 会out of index
     *
     * DP的用途是避免重复判断已知的回文串
     * time:  O(n ^ 2)
     * space: O(n ^ 2)
     */
    public int countPalindromicSubstrings_jiuzhang(String str) {
        if (str == null || str.length() == 0)
            return 0;

        int ans = 0, n = str.length();

        // dp[l][r] 表示从l到r的子字符串是否为回文串
        int[][] dp = new int[n][n];

        for (int r = 0; r < n; r++) {
            for (int l = 0; l <= r; l++) {  // l <= r 保证子字符串有效
                if (str.charAt(l) == str.charAt(r)
                        && (r - l <= 2 || dp[l + 1][r - 1] == 1)) { // 必须先判断l,r之间长度, 再看dp
                    dp[l][r] = 1;
                } else {
                    dp[l][r] = 0;
                }

                ans += dp[l][r];
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
