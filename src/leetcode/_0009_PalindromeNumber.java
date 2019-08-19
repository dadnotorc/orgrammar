/*
Easy
Math
 */
package leetcode;

/**
 * 9. Palindrome Number
 * Determine whether an integer is a palindrome.
 * An integer is a palindrome when it reads the same backward as forward.
 *
 * Example 1:
 * Input: 121
 * Output: true
 *
 * Example 2:
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 *
 * Example 3:
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 *
 * Follow up: Could you solve it without converting the integer to a string?
 */
public class _0009_PalindromeNumber {

    // 只revert一半数字， 则不需要考虑overflow的问题
    public boolean isPalindrome(int x) {
        if (x == 0) { return true; }

        if (x < 0 || x % 10 == 0) { return false; }

        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        return x == rev || x == rev / 10; // 后者是考虑当输入数字为奇数个的情况
    }

    // 因为需要翻转所有数字， 所以需要考虑是否会oerflow
    public boolean isPalindrome_RevertAll(int x) {
        if (x < 0) { return false; }

        int rev = 0, val = x;
        while (val != 0) {
            int pop = val % 10;
            val /= 10;

            if (rev > Integer.MAX_VALUE / 10 ||
                    (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return false;
            }

            rev = rev * 10 + pop;
        }

        return rev == x;
    }
}
