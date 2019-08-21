package interviews;

import org.junit.Test;

/**
 * A palindrome is a sequence of characters that reads the same backwards
 * and forwards. Given a string, s, find the longest palindromic substring in s.
 *
 * Example:
 * Input: "banana"
 * Output: "anana"
 *
 * Input: "million"
 * Output: "illi"
 *
 * Twitter
 */
public class FindLongestPalindrome {

    // todo 读 https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/

    /**
     * time:  O(n ^ 2)
     * space: O(1)
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) { return ""; }

        int sLen = 0;
        int sIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            int tmp = getPalindromeLen(s, i);
            if (tmp > sLen) {
                sLen = tmp;
                sIndex = i;
            }
        }
        return getPalindromeSubstring(s, sIndex, sLen);
    }

    private int getPalindromeLen(String s, int index) {
        int l = index, r = index + 1;
        int len = 0;
        while (l >= 0 && r < s.length()) {
            if (s.charAt(l) == s.charAt(r)) {
                len += 2;
                l--;
                r++;
            } else if (l > 0 && s.charAt(l - 1) == s.charAt(r)) {
                len += 3;
                l -= 2;
                r++;
            } else {
                return len;
            }
        }
        return len;
    }

    private String getPalindromeSubstring(String s, int index, int len) {
        if ((len & 1) == 0) { // even length
            return s.substring(index - len / 2 + 1, index + len / 2 + 1);
        }

        // odd length
        return s.substring(index - len / 2, index + len / 2 + 1);
    }

    @Test
    public void test1() {
        String s = "banana";
        System.out.println("act=" + longestPalindrome(s));
        org.junit.Assert.assertTrue("anana".equals(longestPalindrome(s)));
    }

    @Test
    public void test2() {
        String s = "million";
        System.out.println("act=" + longestPalindrome(s));
        org.junit.Assert.assertTrue("illi".equals(longestPalindrome(s)));
    }

    @Test
    public void test3() {
        String s = "tracecars";
        System.out.println("act=" + longestPalindrome(s));
        org.junit.Assert.assertTrue("racecar".equals(longestPalindrome(s)));
    }

    @Test
    public void test4() {
        String s = "aabc";
        System.out.println("act=" + longestPalindrome(s));
        org.junit.Assert.assertTrue("aa".equals(longestPalindrome(s)));
    }

}
