package leetcode;

import org.junit.Test;

/**
 * 70. Climbing Stairs
 * Easy
 * #DP
 *
 * You are climbing a staircase. It takes n steps to reach to the top. Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 *
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step (方法1 - 每次走 1 步, 共走两 2 次)
 * 2. 2 steps         (方法2 - 每次走 2 步, 只走 1 次)
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

    /*
    这里要搞清楚题意, 问的是到达每格有多少种不同的走法, 而不是最少用多少步

    0 层 -> 1种方法, 就是不走
    1 层 -> 1种方法, 走一步
    2 层 -> 2种办法:
           - 从第 0 层, 走两格
           - 从第 1 层, 走一格
           所以 dp[2] = dp[0] + dp[1]
    3 层 -> dp[1] + dp[2] = 1 + 2 = 3

    n 层 -> dp[n - 2] + dp[n - 1]

    因为是从 0 层 到 n 层, 所以 dp array 长度 为 n + 1
     */


    /**
     * 简化 - 因为 dp[n] = dp[n - 2] + dp[n - 1]
     * 我们只需要记录 3 个临时函数, t2, t1 和 t0 对应上面的 3 个值
     * 时间 O(n), 空间 O(1)
     */
    public int climbStairs(int n) {
        /* 可以省略
        if (n < 2) {
            return 1;
        }*/

        int t0 = 1;
        int t1 = 1;
        int t2 = 0; // 这里不能不赋值, 否则会报错, t2 未被初始化

        for (int i = 2; i <= n; i++) { // 注意 这里是 <= n, 而不是 < 0, 因为是 从 0 到 n , 共 n + 1 层
            t2 = t0 + t1;
            t0 = t1;
            t1 = t2;
        }

        return t1; // 如果省略了开头的特判, 这里就不能返回 t2, 而必须返回 t1
    }


    /**
     * 使用 array - 每格表示到达当前格, 共有多少种办法 , 长度为 n + 1,
     *
     * 时间 O(n)
     * 空间 O(n)
     */
    public int climbStairs_array(int n) {
        if (n < 2) {
            return 1;
        }

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
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
