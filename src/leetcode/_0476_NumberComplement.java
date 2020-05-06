/*
Easy
#Bit Manipulation
 */
package leetcode;

import org.junit.Test;

/**
 * 476. Number Complement
 *
 * Given a positive integer, output its complement number.
 * The complement strategy is to flip the bits of its binary representation.
 *
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101 (no leading zero bits),
 * and its complement is 010. So you need to output 2.
 *
 *
 * Example 2:
 * Input: 1
 * Output: 0
 * Explanation: The binary representation of 1 is 1 (no leading zero bits),
 * and its complement is 0. So you need to output 0.
 *
 * Note:
 * The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 * You could assume no leading zero bit in the integer’s binary representation.
 * This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
 */
public class _0476_NumberComplement {

    /**
     * complement number = num XOR array of 1's
     * 找出num二进制长度, 将每一位都换成1, 最后将其与num做XOR运算
     *
     * 易错点
     * 1. 括号隔开位运算
     */
    public int findComplement(int num) {
        int allOnes = 1;

        while (num > allOnes) {
            // 下面等同 allOnes = (allOnes << 1) + 1
            allOnes = (allOnes << 1) | 1; // 注意! 这里两部运算必须用括号隔开
        }

        return num ^ allOnes;
    }


    /**
     * 另一种写法, 找出num二进制长度, 将每一位都换成0, 并在最左加上1. 完成后将此数减一, 即所有位皆为1
     * 例如 input=5 即 101, 找到1000 即 8, 8-1=7 即 0111.
     *
     * 但是这种写法的会TLE, 例如 number = 2147483647
     */
    public int findComplement_2_TLE(int num) {
        int allOnes = 1;

        while (num >= allOnes) {
            allOnes = allOnes << 1;
        }

        allOnes -= 1;

        return num ^ allOnes;
    }


    @Test
    public void test1() {
        org.junit.Assert.assertEquals(2, findComplement(5));
    }
}
