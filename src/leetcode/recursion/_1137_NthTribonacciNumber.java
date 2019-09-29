/*
Easy
Recursion
 */
package leetcode.recursion;

import org.junit.Test;

/**
 * 1137. N-th Tribonacci Number
 *
 * The Tribonacci sequence Tn is defined as follows:
 *  T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 * Given n, return the value of Tn.
 *
 * Example 1:
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 *
 * Example 2:
 * Input: n = 25
 * Output: 1389537
 *
 * Constraints:
 *  0 <= n <= 37
 * The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 */
public class _1137_NthTribonacciNumber {

    /**
     * test3 会超时
     * time: O(n)
     * space: O(n)
     */
    public int tribonacci_recursion(int n) {
        if (n < 3) {
            return n == 0 ? 0 : 1;
        }
        return tribonacci_recursion(n-3)
                + tribonacci_recursion(n-2)
                + tribonacci_recursion(n-1);
    }

    /**
     * time: O(n)
     * space: O(n)
     */
    public int tribonacci_array(int n) {

        // 不做特殊情况判断的话, test0 会fail
        if (n < 3) {
            return n == 0 ? 0 : 1;
        }

        int[] seq = new int[n+1]; //长度为 n+1, 因为 0 <= n <= 37
        seq[0] = 0;
        seq[1] = 1;
        seq[2] = 1;

        for (int i = 3; i <= n; i++) {
            seq[i] = seq[i-3] + seq[i-2] + seq[i-1];
        }

        return seq[n];
    }

    /**
     * 先计算 t3 = t0 + t1 + t2
     * 再更新 t0 = t1, t1 = t2, t2 = t3
     * time: O(n)
     * space: O(1)
     */
    public int tribonacci(int n) {
        if (n < 3) {
            return n == 0 ? 0 : 1;
        }

        int t0 = 0;
        int t1 = 1;
        int t2 = 1;
        int t3 = -1;

        for (int i = 3; i <= n; i++) {
            t3 = t0 + t1 + t2;
            t0 = t1;
            t1 = t2;
            t2 = t3;
        }
        return t3;
    }

        @Test
    public void test0() {
        org.junit.Assert.assertEquals(0, tribonacci_array(0));
        org.junit.Assert.assertEquals(1, tribonacci_array(1));
        org.junit.Assert.assertEquals(1, tribonacci_array(1));
        org.junit.Assert.assertEquals(0, tribonacci(0));
        org.junit.Assert.assertEquals(1, tribonacci(1));
        org.junit.Assert.assertEquals(1, tribonacci(1));
    }

    @Test
    public void test1() {
        org.junit.Assert.assertEquals(4, tribonacci_array(4));
        org.junit.Assert.assertEquals(4, tribonacci(4));
    }

    @Test
    public void test2() {
        org.junit.Assert.assertEquals(1389537, tribonacci_array(25));
        org.junit.Assert.assertEquals(1389537, tribonacci(25));
    }

    @Test
    public void test3() {
        org.junit.Assert.assertEquals(615693474, tribonacci_array(35));
        org.junit.Assert.assertEquals(615693474, tribonacci(35));
    }
}


