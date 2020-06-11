/*
Easy
#Math, #Bit Manipulation
 */
package leetcode;

/**
 * 231. Power of Two
 *
 * Given an integer, write a function to determine if it is a power of two.
 *
 * Example 1:
 * Input: 1
 * Output: true
 * Explanation: 2 ^ 0 = 1
 *
 * Example 2:
 * Input: 16
 * Output: true
 * Explanation: 2 ^ 4 = 16
 *
 * Example 3:
 * Input: 218
 * Output: false
 */
public class _0231_PowerOfTwo {

    /**
     * 假设n为2的幂, 即最左位为1, 其他位皆为0; 则n-1, 除首位为0, 其他位皆为1
     * n ^ (n - 1) = 0
     *
     * time: O(1)
     *
     * 易错点:
     * 1. bitwise and "&" 运算符左右需要用括号包起来
     */
    public boolean isPowerOfTwo_2(int n) {
//        if (n < 1) {
//            return false;
//        }
//        return (n & (n-1)) == 0;

        return n > 0 && (n & (n-1)) == 0;
    }


    /**
     * power of 2的值, 其二进制只有在首位为1, 其他位皆为0
     * 只有当n=1时, 最右一位才为1; 其他情况, 最右一位皆为0
     *
     * 此方法等同每次除以2, 直到余数不为0停止. 判断剩余数字是否为1
     *
     * time: O(logN)
     */
    public boolean isPowerOfTwo(int n) {
        if (n < 1) {
            return false;
        }

        while (n != 1) {
            if ((n & 1) != 0) {
                return false;
            }
            n >>= 1;
        }

        return true;

//        while (n % 2 == 0) {
//            n /= 2;
//        }
//
//        return n == 1;
    }
}
