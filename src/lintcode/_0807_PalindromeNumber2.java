/*
Easy
#Binary
Amazon
Ladder
 */
package lintcode;

/**
 * 807. Palindrome Number II
 *
 * Determines whether a binary representation of a non-negative integer n is a palindrome
 *
 * Example1
 * Input: n = 0
 * Output: True
 * Explanation:
 * The binary representation of 0 is: 0
 *
 * Example2
 * Input: n = 3
 * Output: True
 * Explanation:
 * The binary representation of 3 is: 11
 *
 * Example3
 * Input: n = 4
 * Output: False
 * Explanation:
 * The binary representation of 4 is: 100
 *
 * Example4
 * Input: n = 6
 * Output: False
 * Explanation:
 * The binary representation of 6 is: 110
 *
 * Notice
 * 0 <= n <= 2^32 - 1
 */
public class _0807_PalindromeNumber2 {

    /**
     * 先将数字的二进制转成String, 然后对String使用双指针比较char
     *
     * 易错点:
     * 1. 别忘了移动指针
     */
    public boolean isPalindrome(int n) {
        if (n == 0)
            return true;
        if ((n & 1) == 0) // 偶数, 最后一位为0
            return false;

        StringBuilder sb = new StringBuilder();

        while (n != 0) {
            sb.append(n & 1);
            n >>= 1;
        }

        return isPalindrome(sb.toString());
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;

        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) { // 别忘了移动指针
                return false;
            }
        }

        return true;
    }
}
