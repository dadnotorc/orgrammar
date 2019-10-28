/*
Medium
Bit Manipulation, DP
Salesforce
 */
package lintcode.dp;

import org.junit.Test;

/**
 * 664. Counting Bits
 *
 * Given a non negative integer number num. For every numbers i in the range
 * 0 ≤ i ≤ num calculate the number of 1's in their binary representation and
 * return them as an array.
 *
 * Example1
 * Input: 5
 * Output: [0,1,1,2,1,2]
 * Explanation:
 * The binary representation of 0~5 is:
 * 000
 * 001
 * 010
 * 011
 * 100
 * 101
 * the count of "1" in each number is: 0,1,1,2,1,2
 *
 * Example2
 * Input: 3
 * Output: [0,1,1,2]
 *
 * Challenge
 * 1. It is very easy to come up with a solution with run time O(n*sizeof(integer)).
 * But can you do it in linear time O(n) /possibly in a single pass?
 * 2. Space complexity should be O(n).
 * 3. Can you do it like a boss? Do it without using any builtin function like
 * __builtin_popcount in c++ or in any other language.
 */
public class _0664_CountingBits {

    /**
     * time:  O(n)
     * space: O(n)
     */
    public int[] countBits(int num) {
        int[] ans = new int[num + 1]; // num >= 0
        int pre = 0;

        for (int i = 1; i <= num; i++) {
            if (isPowerOfTwo(i)) {
                ans[i] = 1;
                pre = i;
            } else {
                ans[i] = ans[pre] + ans [i - pre];
            }
        }

        return ans;
    }

    private boolean isPowerOfTwo(int n) {
        if (n <= 0)
            return false;
        // 如果 n = 2 ^ x, n 的二进制值为 10000, n-1 为 01111
        return  ((n & (n-1)) == 0);
    }

    /* ~~~ */

    // 九章 参考
    public int[] countBits_jz(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }
        return ans;
    }

    @Test
    public void test0() {
        int[] exp = {0,};
        org.junit.Assert.assertArrayEquals(exp, countBits(0));
        org.junit.Assert.assertArrayEquals(exp, countBits_jz(0));
    }

    @Test
    public void test1() {
        int[] exp = {0,1,1,2,1,2};
        org.junit.Assert.assertArrayEquals(exp, countBits(5));
        org.junit.Assert.assertArrayEquals(exp, countBits_jz(5));
    }

    @Test
    public void test2() {
        int[] exp = {0,1,1,2};
        org.junit.Assert.assertArrayEquals(exp, countBits(3));
        org.junit.Assert.assertArrayEquals(exp, countBits_jz(3));
    }

    @Test
    public void test3() {
        int[] exp = {0,1,1,2,1,2,2,3,1};
        org.junit.Assert.assertArrayEquals(exp, countBits(8));
        org.junit.Assert.assertArrayEquals(exp, countBits_jz(8));
    }
}
