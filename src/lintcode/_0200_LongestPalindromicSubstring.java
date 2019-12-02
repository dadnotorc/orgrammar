/*
Medium
String
Amazon, Microsoft, Uber
 */
package lintcode;

import org.junit.Test;

/**
 * 200. Longest Palindromic Substring
 *
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000, and there
 * exists one unique longest palindromic substring.
 *
 * Example 1:
 * Input:"abcdzdcab"
 * Output:"cdzdc"
 *
 * Example 2:
 * Input:"aba"
 * Output:"aba"
 *
 * Challenge
 * - O(n2) time is acceptable. Can you do it in O(n) time.
 */
public class _0200_LongestPalindromicSubstring {

    // 中心枚举法
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1)
            return s;

        String ans = "";

        for (int i = 0; i < s.length(); i++) {
            String sub = findPalindrome(s, i);
            if (sub.length() > ans.length()) {
                ans = sub;
            }
        }

        return ans;
    }

    // todo  有 bug
    public String findPalindrome(String s, int index) {
        StringBuilder sb = new StringBuilder();

        sb.append(s.charAt(index));
        int l = index - 1, r = index + 1;
        if (r < s.length() && s.charAt(index) == s.charAt(r)) // 注意 要判断 r 是否越界
            sb.append(s.charAt(r++));

        while (l >= 0 && r < s.length()) {
            if (s.charAt(l) != s.charAt(r))
                break;

            sb.insert(0, s.charAt(l--));
            sb.append(s.charAt(r++));
        }
        return sb.toString();
    }

    @Test
    public void test0() {
        String s = "a";
        String exp = "a";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
    }

    @Test
    public void test1() {
        String s = "aa";
        String exp = "aa";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
    }

    @Test
    public void test2() {
        String s = "abcdzdcab";
        String exp = "cdzdc";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
    }

    @Test
    public void test3() {
        String s = "aba";
        String exp = "aba";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
    }

    @Test
    public void test4() {
        String s = "ab";
        String exp = "a";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
    }

    @Test
    public void test5() {
        String s = "abbbba";
        String exp = "abbbba";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
    }

    @Test
    public void test6() {
        String s = "abbbb";
        String exp = "bbbb";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
    }

    @Test
    public void test7() {
        String s = "abbbbc";
        String exp = "bbbb";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
    }

    @Test
    public void test8() {
        String s = "ccc";
        String exp = "ccc";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
    }

    @Test
    public void test9() {
        String s = "abbcde";
        String exp = "bb";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
    }
}
