/*
Naive
#Enumerate, #Array
NetEase
 */
package lintcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 366 · Fibonacci
 *
 * Find the Nth number in Fibonacci sequence.
 * A Fibonacci sequence is defined as follow:
 * - The first two numbers are 0 and 1.
 * - The i th number is the sum of i-1 th number and i-2 th number.
 * - The first ten numbers in Fibonacci sequence is:
 *   0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...
 *
 * The Nth fibonacci number won't exceed the max value of signed 32-bit integer in the test cases.
 *
 * Example 1:
 * 	Input:  1
 * 	Output: 0
 * Explanation: return the first number in  Fibonacci sequence .
 *
 * Example 2:
 * 	Input:  2
 * 	Output: 1
 * 	Explanation: return the second number in  Fibonacci sequence .
 */
public class _0366_Fibonacci {


    /**
     * 减少 dp 数组的空间到 3 位
     */
    public int fibonacci(int n) {
        if (n <= 2) { return n - 1; }

        int[] dp = new int[3];
        // dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < n; i++) {
            dp[i % 3] = dp[(i - 1) % 3] + dp[(i - 2) % 3];
        }

        return dp[(n - 1) % 3];
    }

    /**
     * 递归 + 记忆
     */
    public int fibonacci_dp(int n) {
        if(n == 1) { return 0; } // 别忘了特判, 不然如果 n = 1, 下面array的初始化会out of boundary
        if(n == 2) { return 1; }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        return helper(n, dp);
    }

    public int helper(int n, int[] array) {
        if (array[n] != -1) {
            return array[n];
        }

        array[n] = helper(n - 2, array) + helper(n - 1, array);
        return array[n];
    }

    /**
     * 正确, 但是会 TLE
     */
    public int fibonacci_TLE(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }

        return fibonacci_TLE(n - 2) + fibonacci_TLE(n - 1);
    }

    @Test
    public void test0() {
        org.junit.Assert.assertEquals(0, fibonacci(1));
        org.junit.Assert.assertEquals(1, fibonacci(2));
    }
}
