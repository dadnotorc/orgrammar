/*
Easy
#Bit Manipulation
 */
package leetcode;

/**
 * 461. Hamming Distance
 *
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Given two integers x and y, calculate the Hamming distance.
 *
 * Note:
 * 0 ≤ x, y < 231.
 *
 * Example:
 *
 * Input: x = 1, y = 4
 *
 * Output: 2
 *
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * The above arrows point to positions where the corresponding bits are different.
 */
public class _0461_HammingDistance {

    /**
     * 1. 先做异或xor运算, x ^ y. 如果某一位等于1, 说明x与y中的对应位置, bit值不相同
     * 2. 统计xor的值中1的个数
     */
    public int hammingDistance(int x, int y) {
        int val = x ^ y;

        int res = 0;
        while (val != 0) {
            res += val & 1;
            val >>= 1;
        }

        return res;
    }


    /**
     * 类似做法, 统计1的写法略有不同
     */
    public int hammingDistance_2(int x, int y) {
        int val = x ^ y;

        int res = 0;
        while (val != 0) {
            res++;
            val = val & (val - 1);
        }

        return res;
    }
}
