/*
Easy
#Bit Manipulation
Amazon,
 */
package lintcode;

import org.junit.Test;

/**
 * 1046. Prime Number of Set Bits in Binary Representation
 *
 * Given two integers L and R, find the count of numbers in the range [L, R] (inclusive)
 * having a prime number of set bits in their binary representation.
 *
 * (Recall that the number of set bits an integer has is the number of 1s present when written in binary.
 * For example, 21 written in binary is 10101 which has 3 set bits. Also, 1 is not a prime.)
 *
 * Notice
 * 1. L, R will be integers L <= R in the range [1, 10^6].
 * 2. R - L will be at most 10000.
 *
 * Example 1:
 * Input: L = 6, R = 10
 * Output: 4
 * Explanation:
 * 6 -> 110 (2 set bits, 2 is prime)
 * 7 -> 111 (3 set bits, 3 is prime)
 * 9 -> 1001 (2 set bits , 2 is prime)
 * 10->1010 (2 set bits , 2 is prime)
 *
 * Example 2:
 * Input: L = 10, R = 15
 * Output: 5
 * Explanation:
 * 10 -> 1010 (2 set bits, 2 is prime)
 * 11 -> 1011 (3 set bits, 3 is prime)
 * 12 -> 1100 (2 set bits, 2 is prime)
 * 13 -> 1101 (3 set bits, 3 is prime)
 * 14 -> 1110 (3 set bits, 3 is prime)
 * 15 -> 1111 (4 set bits, 4 is not prime)
 */
public class _1046_PrimeNumberOfSetBitsInBinaryRepresentation {

    public int countPrimeSetBits(int L, int R) {
        int ans = 0;

        for (int i = L; i <= R; i++) {
            if (isPrime(getSetBits(i))) {
                ans++;
            }
        }

        return ans;
    }

    private int getSetBits(int i) {
        int setBits = 0;
        while (i != 0) {
            i = i & (i -1);
            setBits++;
        }
        return setBits;
    }

    // n最大值为10^6, 转成二进制, 长度为20 (2^19 < 10^6 < 2^20)
    // 所以需要找出20以内的所有质数
    private boolean isPrime(int n) {
        if (n == 2 || n == 3 || n == 5 || n == 7 || n == 11 || n == 13 || n == 17 || n == 19)
            return true;
        return false;
    }


    /**
     * 第二套解法
     */

    private int getSetBits_2(int i) {
        int setBits = 0;
        while (i != 0) {
            setBits += i & 1;
            i >>= 1;
        }
        return setBits;
    }


    private boolean isPrime_2(int n) {
        if (n < 2)
            return false;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void test1() {
        org.junit.Assert.assertEquals(4, countPrimeSetBits(6, 10));
    }

}
