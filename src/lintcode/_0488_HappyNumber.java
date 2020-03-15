/*
Easy
#Hash Table, #Mathematics
Airbnb, Amazon, Uber, Twitter
 */
package lintcode;

import org.junit.Test;

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

    /**
     * 递归
     *
     * 易错点:
     * 1. 注意set.add, set.contains, 还有getNext的顺序
     *    先判断set.contains, 再做set.add, 最后计算getNext. next的值会在下层递归里被加入set
     */
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        // 注意这里不要将n加入set, 不然会使helper提前退出
        return helper(set, n);
    }

    private boolean helper(HashSet<Integer> set, int n) {
        if (n == 1)
            return true;
        if (set.contains(n)) //出现loop
            return false;

        set.add(n);
        return helper(set, getNext(n));
    }

    private int getNext(int num) {
        int sum = 0, remainder = 0;
        while (num > 0) {
            remainder = num % 10;
            sum += remainder * remainder;
            num = num / 10;
        }
        return sum;
    }



    /**
     * 循环
     *
     * 易错点:
     * 1. 同上, 先判断set.contains, 再做set.add, 最后计算getNext. next的值会在下层循环里被加入set
     */
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
