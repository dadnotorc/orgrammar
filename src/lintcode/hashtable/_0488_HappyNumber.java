/*
Easy
Hash Table, Mathematics
Airbnb, Amazon, Uber, Twitter
 */
package lintcode.hashtable;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 488. Happy Number
 *
 * Write an algorithm to determine if a number is happy.
 *
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the sum of the
 * squares of its digits, and repeat the process until the number equals 1
 * (where it will stay), or it loops endlessly in a cycle which does not
 * include 1. Those numbers for which this process ends in 1 are happy numbers.
 *
 * Example 1:
 *
 * Input:19
 * Output:true
 * Explanation:
 * 19 is a happy number
 *     1^2 + 9^2 = 82
 *     8^2 + 2^2 = 68
 *     6^2 + 8^2 = 100
 *     1^2 + 0^2 + 0^2 = 1
 *
 * Example 2:
 * Input:5
 * Output:false
 * Explanation:
 * 5 is not a happy number
 * 25->29->85->89->145->42->20->4->16->37->58->89
 * 89 appears again.
 */
public class _0488_HappyNumber {

    // n is positive integer, n > 0
    // 递归
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        return helper(set, n);
    }

    private boolean helper(HashSet<Integer> set, int n) {
        int sum = getSum(n); // 先计算, 后判断

        if (sum == 1)
            return true;
        if (set.contains(sum)) //出现loop
            return false;

        set.add(sum);
        return helper(set, sum);
    }

    private int getSum(int num) {
        int sum = 0, remainder = 0;
        while (num > 0) {
            remainder = num % 10;
            sum += remainder * remainder;
            num = num / 10;
        }
        return sum;
    }

    /* 解法2 九章参考 */
    // 循环
    public boolean isHappy_2(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n != 1) {
            if (set.contains(n))
                return false;
            set.add(n);
            n = getNextNum(n);
        }
        return true;
    }

    private int getNextNum(int n) {
        int toReturn = 0, remainder = 0;
        while (n != 0) {
            remainder = n % 10;
            toReturn += remainder * remainder;
            n /= 10;
        }
        return toReturn;
    }

    @Test
    public void test1() {
        org.junit.Assert.assertTrue(isHappy(19));
        org.junit.Assert.assertTrue(isHappy_2(19));
    }

    @Test
    public void test2() {
        org.junit.Assert.assertFalse(isHappy(5));
        org.junit.Assert.assertFalse(isHappy_2(5));
    }
}
