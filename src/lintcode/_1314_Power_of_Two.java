package lintcode;

/**
 * 1314 · Power of Two
 * Easy
 * #Binary
 * Google
 *
 * Given an integer, write a function to determine if it is a power of two.
 *
 * Example
 * Input: n = 3
 * Output: false
 */
public class _1314_Power_of_Two {

    /**
     * 优化 - 利用 bitwise operation
     * 2 的倍数, 例如 16 的二进制为 10000, 15 的二进制为 01111, bitwise & 等于 0
     */
    public boolean isPowerOfTwo_3(int n) {
        if (n < 1) { return false; }

        return (n & (n - 1)) == 0;
    }


    /**
     * 优化 1 - n != 1 且 不能被 2 整除的, 肯定不是
     */
    public boolean isPowerOfTwo_2(int n) {
        if (n < 1) { return false; }

        if (n == 1) { return true; } // 优化
        if (n % 2 != 0) { return false; }  // 优化

        for (int i = 2; i <= n; i = i * 2) {
            if (i == n) { return true; }
        }

        return false;
    }

    /**
     * 暴力 - O(logn)
     */
    public boolean isPowerOfTwo(int n) {
        if (n < 1) { return false; }

        for (int i = 1; i <= n; i = i * 2) {
            if (i == n) { return true; }
        }

        return false;
    }
}
