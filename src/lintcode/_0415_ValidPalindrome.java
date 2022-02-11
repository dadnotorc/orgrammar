package lintcode;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 415. Valid Palindrome
 * Medium
 * #String, #Two Pointers
 * LinkedIn, Facebook, Microsoft, Uber
 * FAQ+
 *
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 *
 * Example 1:
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama"
 *
 * Example 2:
 * Input: "race a car"
 * Output: false
 * Explanation: "raceacar"
 *
 * Challenge
 * O(n) time without extra memory.
 *
 * 类似 leetcode 125
 */
public class _0415_ValidPalindrome {
    /*
    String可否为空 empty? 面试时可跟面试官确认.
    我们这里假设 empty string is a valid palindrome
     */

    /**
     * 先使用regex移除标点/空格, 变成小写, 之后用双指针
     */
    public boolean isPalindrome_regex(String s) {

        if (s == null) { return true; }

        String str = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    /* ~~~~~ */

    /**
     * 只考虑 alphanumeric characters, 不考虑大小写/标点/空格
     * 左右指针分别从头尾两个方向相向移动,判断是否相同
     * time: O(n)
     * space: O(1)
     */
    public boolean isPalindrome(String s) {

        if (s == null) { return true; }

        int l = 0;
        int r = s.length() - 1;
        String str = s.toLowerCase();
        char lc, rc;
        while (l < r) {
            lc = str.charAt(l);
            rc = str.charAt(r);
            if (!isAlphanumeric(lc)) {
                l++;
            } else if (!isAlphanumeric(rc)) {
                r--;
            } else {
                if (lc != rc) {
                    return false;
                }
                l++; // 別忘了移动指针, 两者要反向, 一个+, 一个-
                r--;
            }
        }

        return true;
    }

    private boolean isAlphanumeric(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') || // 本题中不需要考虑大写
                (c >= '0' && c <= '9');

        // 下列也可用
        // return Character.isLetter(c) || Character.isDigit(c);
    }

    @Test
    public void test0() {
        String s = null;
        assertTrue(isPalindrome(s));
        assertTrue(isPalindrome_regex(s));
    }

    @Test
    public void test1() {
        String s = "race a car";
        assertFalse(isPalindrome(s));
        assertFalse(isPalindrome_regex(s));
    }

    @Test
    public void test2() {
        String s = "A man, a plan, a canal: Panama";
        assertTrue(isPalindrome(s));
        assertTrue(isPalindrome_regex(s));
    }

    @Test
    public void test3() {
        String s = "";
        assertTrue(isPalindrome(s));
        assertTrue(isPalindrome_regex(s));
    }

    @Test
    public void test4() {
        String s = "A";
        assertTrue(isPalindrome(s));
        assertTrue(isPalindrome_regex(s));
    }

    @Test
    public void test5() {
        String s = "A a";
        assertTrue(isPalindrome(s));
        assertTrue(isPalindrome_regex(s));
    }

    @Test
    public void test6() {
        String s = "Aba";
        assertTrue(isPalindrome(s));
        assertTrue(isPalindrome_regex(s));
    }

    @Test
    public void test7() {
        String s = "A b,B A  ";
        assertTrue(isPalindrome(s));
        assertTrue(isPalindrome_regex(s));
    }
}
