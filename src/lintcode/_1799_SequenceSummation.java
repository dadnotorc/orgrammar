/*
Easy

Salesforce
 */
package lintcode;

import org.junit.Test;

/**
 * 1799. Sequence summation
 *
 * 小明在上数学课，课上老师说需要求等差为1的等差数列的和
 * (sum of the arithmetic series with the difference of one)，这个很简单。
 * 但是小明想到，如果在某一刻，等差变为了-1，再进行求和怎么解？ 你能帮帮他嘛？
 *
 * 例子：首项为5，在第5项也就是9的时候等差变为了-1，末项为6，那么这个数列的和是5+6+7+8+9+8+7+6=56
 * 输入：首项的值i，在第j项的时候等差变为了-1，末项的值k
 * 输出：数列的和
 *
 * Example 1：
 * input：i = 5, j = 9, k = 6
 * output：56
 * 5+6+7+8+9+8+7+6 = 56
 *
 * Example 2：
 * input：i = 5, j = 5, k = 5
 * output：5
 */
public class _1799_SequenceSummation {

    // 解法 1
    // time: O(n)
    // space: O(1)
    public long equlSum(long i, long j, long k) {
        return getSum(i, j - 1)+ getSum(k, j - 1) + j;
    }

    private long getSum(long start, long end) {
        if (start > end)
            return 0;
        return (start + end) * (end - start + 1) / 2;
    }

    public long equlSum_two(long i, long j, long k) {
        if (i < k)
            return j + getSum(k, j - 1) * 2 + getSum(i, k - 1);
        else if (i > k)
            return j + getSum(i, j - 1) * 2 + getSum(k, i - 1);
        else
            return j + getSum(i, j - 1) * 2;
    }

    @Test
    public void test1() {
        org.junit.Assert.assertEquals(56, equlSum(5, 9, 6));
        org.junit.Assert.assertEquals(56, equlSum_two(5, 9, 6));
    }

    @Test
    public void test2() {
        org.junit.Assert.assertEquals(5, equlSum(5, 5, 5));
        org.junit.Assert.assertEquals(5, equlSum_two(5, 5, 5));
    }
}
