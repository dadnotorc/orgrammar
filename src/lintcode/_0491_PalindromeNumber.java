/*
Easy
 */
package lintcode;

/**
 * 491 · Palindrome Number
 *
 * Check a positive number is a palindrome or not.
 *
 * A palindrome number is that if you reverse the whole number you will get exactly the same number.
 *
 * It's guaranteed the input number is a 32-bit integer,
 * but after reversion, the number may exceed the 32-bit integer.
 *
 * Example 1:
 * Input: 11
 * Output: true
 *
 * Example 2:
 * Input: 1232
 * Output: false
 * Explanation: 1232!=2321
 */
public class _0491_PalindromeNumber {

    /**
     * 把 num 转成数组
     */
    public boolean isPalindrome_array(int num) {
        if (num < 10) {
            return true;
        }

        int[] array = new int[32];
        int index = 0;
        while (num != 0) {
            array[index++] = num % 10;
            num /= 10;
        }

        for (int i = 0; i < index / 2; i++) {
            if (array[i] != array[index - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    //

    /**
     * 反转数字, 对比 原数字 vs 反转数字
     * 要小心, 反转后可能超过 32-bit integer.
     */
    public boolean isPalindrome_rev(int num) {
        if (num < 10) {
            return true;
        }

        int rev = reverse(num);
        return num == rev;
    }

    // 123 -> 321
    public int reverse (int num) {
        int res = 0;
        while (num != 0) {
            res = res * 10 + num % 10;
            num /= 10;
        }
        return res;
    }
}
