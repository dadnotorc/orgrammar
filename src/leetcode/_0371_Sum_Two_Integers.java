package leetcode;

/**
 * 371. Sum of Two Integers
 * Medium
 * #Math, #Bit Manipulation
 *
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 *
 * Example 1:
 * Input: a = 1, b = 2
 * Output: 3
 *
 * Example 2:
 * Input: a = 2, b = 3
 * Output: 5
 *
 *
 * Constraints:
 * -1000 <= a, b <= 1000
 */
public class _0371_Sum_Two_Integers {

    /**
     * 两个数字相加, 可以分拆成两步, "additional" and "carry"
     * 1. add numbers, but ignore the carry
     * 2. do the carry only, and ignore the addition of the each digit
     * 3. add the result from first 2 steps
     *
     * example: 759 + 674
     * 1. 759 + 674 ->  323 (ignore carry)
     * 2. 759 + 674 -> 1110 (only carry)
     * 3. 1110 + 323 = 1433
     *
     * recursion 直到 没有 carry, 既 b = 0
     */
    public int getSum(int a, int b) {
        return b==0 ? a : getSum(a ^ b, (a & b) << 1);
    }
}
