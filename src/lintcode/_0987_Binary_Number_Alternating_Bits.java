package lintcode;

/**
 * 987 · Binary Number with Alternating Bits
 * Easy
 * #Enumerate, #Binary
 * Yahoo
 *
 * To give a positive integer, please check whether its binary representation has alternate bits.
 * That is, two adjacent bits always have different values.
 *
 * Example 1:
 * Input: 5
 * Output: True
 * Explanation: The binary representation of 5 is: 101
 *
 * Example 2:
 * Input: 7
 * Output: False
 * Explanation: The binary representation of 7 is: 111.
 *
 * Example 3:
 * Input: 11
 * Output: False
 * Explanation: The binary representation of 11 is: 1011.
 *
 * Example 4:
 * Input: 10
 * Output: True
 * Explanation: The binary representation of 10 is: 1010.
 */
public class _0987_Binary_Number_Alternating_Bits {

    /**
     * 1. 先将 n 与 (n>>1) 进行 XOR. 如果 n 是 0,1 交错的, 此举得到的值将只有 1, 没有
     * 2. 1111 + 0001 = 10000
     *    1111 & 10000 = 0
     */

    public boolean hasAlternatingBits(int n) {
        int temp = n ^ (n >> 1);

        return (temp & (temp + 1)) == 0;
    }




    /**
     * 有 bug
     * n = 20 => 10100 & 1010 == 0, 但是错误了
     */
    public boolean hasAlternatingBits_bug(int n) {
        return (n & (n >> 1)) == 0;
    }
}
