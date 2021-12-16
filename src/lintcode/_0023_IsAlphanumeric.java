/*
Naive
 */
package lintcode;

/**
 * 23 · Is alphanumeric
 *
 * Given a character c,return ture if it is a letter or a number,otherwise return false.
 *
 * Example 1:
 * Input: c = '1'
 * Output: true
 * Explanation: '1'belongs to the number.
 */
public class _0023_IsAlphanumeric {

    public boolean isAlphanumeric(char c) {
        int i = c - '0';
        int j = c - 'a';
        int k = c - 'A';
        return ((i >= 0 && i <= 9) ||
                (j >= 0 && j <= 26) ||
                (k >= 0 && k <= 26));
    }

    public boolean isAlphanumeric_2(char c) {
        return ((c >= '0' && c <= '9') ||
                (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z'));
    }

    public boolean isAlphanumeric_3(char c) {
        return Character.isLetterOrDigit(c);
    }
}
