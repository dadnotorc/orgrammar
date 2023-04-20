package lintcode;

/**
 * 1332. Number of 1 Bits
 * Easy
 * #Bit Manipulation
 * Apple, Microsoft
 *
 * Write a function that takes an unsigned integer and returns the number of ’1' bits
 * the corresponding binary number has (also known as the Hamming weight).
 *
 * Example 1
 * Input：n = 11
 * Output：3
 * Explanation：11(10) = 1011(2), so return 3
 *
 * Example 2
 * Input：n = 7
 * Output：3
 * Explanation：7(10) = 111(2), so return 3
 */
public class _1332_NumberOf1Bits {

    /**
     * 较快
     */
    public int hammingWeight(int n) {
        int ans = 0;

        while (n != 0) {
            ans += n & 1;
            n >>= 1;
        }

        return ans;
    }

    /**
     * 较慢
     */
    public int hammingWeight_2(int n) {
        int ans = 0;

        while (n != 0) {
            n = n & (n -1);
            ans++;
        }

        return ans;
    }
}
