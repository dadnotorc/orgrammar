/*
Medium
#String
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
 * - O(n^2) time is acceptable. Can you do it in O(n) time.
 */
public class _0200_LongestPalindromicSubstring {

    // todo Manancher's Algorithm
    // https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/
    // https://www.jiuzhang.com/solution/longest-palindromic-substring/

    /**
     * 中心枚举法
     * time:  O(n^2)
     * space: O(1)
     *
     * 易错点:
     * 1. 遍历字符串时, 针对每个字符, 需要做两次findPalindrome计算, 因为字符串可能为偶数或者奇数长度
     * 2. findPalindrome中, 别忘了判断, 如果l==r, 则当前长度+1, 否则+2
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1)
            return s;

        int ansStartIndex = 0, curLen = 0, maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
             curLen = findPalindrome(s, i, i); // 奇数长度
             if (curLen > maxLen) {
                 maxLen = curLen;
                 ansStartIndex = i - maxLen / 2;
             }

             curLen = findPalindrome(s, i, i + 1); // 偶数长度
             if (curLen > maxLen) {
                 maxLen = curLen;
                 ansStartIndex = i - maxLen / 2 + 1;
             }
        }

        return s.substring(ansStartIndex, ansStartIndex + maxLen);
    }

    // 给出当前index, 找出可向左右延伸最长距离
    public int findPalindrome(String s, int l, int r) {
        int len = 0;

        while (l >= 0 && r < s.length()) {
            if (s.charAt(l) != s.charAt(r)) {
                break;
            }

            len += l == r ? 1 : 2; // 注意判断当前左右指针是否重合
            l--;
            r++;
        }

        return len;
    }


    /**
     * DP 解法
     * 如果从i到j的字符串是palindrome, 需要满足首尾字符相同 s.charAt(i) == s.charAt(j), 以及以下两个条件其中之一
     * - 字符串长度最长为3
     * - 字符串长度大于3, 且各自前一位字符也相同 dp[i+1][j-1] == true
     */
    public String longestPalindrome_DP(String s) {
        if (s == null || s.length() == 0)
            return s;

        int strLen = s.length();
        int ansStartIndex = 0, maxLen = 0;

        // 记录从i开始到j结束的substring是否是palindrome
        boolean[][] dp = new boolean[strLen][strLen];

        for (int i = strLen - 1; i >= 0; i--) {
            for (int j = i; j < strLen; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)
                        &&
                        (j - i < 3 //从i到j的substring长度<=3, 这时只要首尾相等即可
                                || dp[i+1][j-1]) //substring长度>3, 这时需要较短的substring也是palindrome
                        );

                //寻找更长的substring
                if (dp[i][j] && (j - i + 1) > maxLen) {
                    ansStartIndex = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(ansStartIndex, ansStartIndex + maxLen);
    }


    @Test
    public void test0() {
        String s = "a";
        org.junit.Assert.assertEquals("a", longestPalindrome(s));
        org.junit.Assert.assertEquals("a", longestPalindrome_DP(s));
    }

    @Test
    public void test1() {
        String s = "banana";
        System.out.println("act=" + longestPalindrome(s));
        org.junit.Assert.assertTrue("anana".equals(longestPalindrome(s)));
        org.junit.Assert.assertTrue("anana".equals(longestPalindrome_DP(s)));
    }

    @Test
    public void test2() {
        String s = "million";
        System.out.println("act=" + longestPalindrome(s));
        org.junit.Assert.assertTrue("illi".equals(longestPalindrome(s)));
        org.junit.Assert.assertTrue("illi".equals(longestPalindrome_DP(s)));
    }

    @Test
    public void test3() {
        String s = "tracecars";
        System.out.println("act=" + longestPalindrome(s));
        org.junit.Assert.assertTrue("racecar".equals(longestPalindrome(s)));
        org.junit.Assert.assertTrue("racecar".equals(longestPalindrome_DP(s)));
    }

    @Test
    public void test4() {
        String s = "aabc";
        System.out.println("act=" + longestPalindrome(s));
        org.junit.Assert.assertTrue("aa".equals(longestPalindrome(s)));
        org.junit.Assert.assertTrue("aa".equals(longestPalindrome_DP(s)));
    }

    @Test
    public void test5() {
        String s = "abcdzdcab";
        String exp = "cdzdc";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
        org.junit.Assert.assertEquals(exp, longestPalindrome_DP(s));
    }

    @Test
    public void test6() {
        String s = "aa";
        String exp = "aa";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
        org.junit.Assert.assertEquals(exp, longestPalindrome_DP(s));
    }

    @Test
    public void test7() {
        String s = "abcdzdcab";
        String exp = "cdzdc";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
        org.junit.Assert.assertEquals(exp, longestPalindrome_DP(s));
    }

    @Test
    public void test8() {
        String s = "aba";
        String exp = "aba";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
        org.junit.Assert.assertEquals(exp, longestPalindrome_DP(s));
    }

    @Test
    public void test9() {
        String s = "ab";
        String exp = "a";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
        //org.junit.Assert.assertEquals(exp, longestPalindrome_DP(s));
    }

    @Test
    public void test10() {
        String s = "abbbba";
        String exp = "abbbba";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
        org.junit.Assert.assertEquals(exp, longestPalindrome_DP(s));
    }

    @Test
    public void test11() {
        String s = "abbbb";
        String exp = "bbbb";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
        org.junit.Assert.assertEquals(exp, longestPalindrome_DP(s));
    }

    @Test
    public void test12() {
        String s = "abbbbc";
        String exp = "bbbb";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
        org.junit.Assert.assertEquals(exp, longestPalindrome_DP(s));
    }

    @Test
    public void test13() {
        String s = "ccc";
        String exp = "ccc";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
        org.junit.Assert.assertEquals(exp, longestPalindrome_DP(s));
    }

    @Test
    public void test14() {
        String s = "abbcde";
        String exp = "bb";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
        org.junit.Assert.assertEquals(exp, longestPalindrome_DP(s));
    }

    @Test
    public void test15() {
        String s = "aaa";
        String exp = "aaa";
        org.junit.Assert.assertEquals(exp, longestPalindrome(s));
        org.junit.Assert.assertEquals(exp, longestPalindrome_DP(s));
    }
}
