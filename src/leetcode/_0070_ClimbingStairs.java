/*
Easy
#DP
 */
package leetcode;

import org.junit.Test;

/**
 * 70. Climbing Stairs
 *
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways
 * can you climb to the top?
 *
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2:
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class _0070_ClimbingStairs {

    /**
     * 0层 -> 1种方法
     * 1层 -> 1种
     * 2层 -> 到达0层的方法 + 到到1层的方法 = 1 + 1 = 2
     * 3层 -> 到达1层的方法 + 到达2层的方法 = 1 + 2 = 3
     * 4层 -> 到达2层的方法 + 到达3层的方法 = 2 + 3 = 5
     */
    public int climbStairs(int n) {
        if (n < 2) {
            return 1;
        }

        int t0 = 1;
        int t1 = 1;
        int t2 = 0;

        for (int i = 2; i <= n; i++) {
            t2 = t0 + t1;
            t0 = t1;
            t1 = t2;
        }

        return t2;
    }

    public int climbStairs_array(int n) {
        if (n < 2) {
            return 1;
        }

        int[] seq = new int[n+1];
        seq[0] = 1;
        seq[1] = 1;

        for (int i = 2; i <= n; i++) {
            seq[i] = seq[i-1] + seq[i-2];
        }

        return seq[n];
    }

        @Test
    public void test0() {
        org.junit.Assert.assertEquals(1, climbStairs(0));
        org.junit.Assert.assertEquals(1, climbStairs(1));
        org.junit.Assert.assertEquals(2, climbStairs(2));
        org.junit.Assert.assertEquals(3, climbStairs(3));
        org.junit.Assert.assertEquals(5, climbStairs(4));
        org.junit.Assert.assertEquals(1, climbStairs_array(0));
        org.junit.Assert.assertEquals(1, climbStairs_array(1));
        org.junit.Assert.assertEquals(2, climbStairs_array(2));
        org.junit.Assert.assertEquals(3, climbStairs_array(3));
        org.junit.Assert.assertEquals(5, climbStairs_array(4));
    }

    @Test
    public void test1() {
        org.junit.Assert.assertEquals(14930352, climbStairs(35));
        org.junit.Assert.assertEquals(14930352, climbStairs_array(35));
    }
}
