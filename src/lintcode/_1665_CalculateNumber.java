/*
Easy
Binary
Salesforce
 */
package lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 1665. Calculate number
 *
 * Given a decimal number n, now you need to convert it to a binary number
 * and return the sum of 1 and the every position of 1. n<=10^9
 *
 * Example 1:
 * Input: 10,
 * Output: [2,1,3]
 * Explanation: 10转成2进制为1010，总共有2个1，所以第一个是2，1的位置是第1个和第3个，所以后续两个数为1，3.
 *
 * Example 2:
 * Input: 7,
 * Output: [3,1,2,3]
 * Explanation: 7转成2进制为111，总共有3个1，所以第一个是3，1的位置是第1个、第2个和第3个，所以后续三个数为1，2，3.
 */
public class _1665_CalculateNumber {

    public int[] calculateNumber(int num) {
        int sum = 0;
        int[] binaryArray = new int[32]; // 因为 n <= 10 ^ 9
        int index = 0;

        // 对num进行对2取模, 获得的数字为当前2进制位的值. 然后将num对2整除, 再取模获得下一位的值
        while (num > 0) {
            if (num % 2 == 1)
                sum++;
            binaryArray[index++] = num % 2;
            num /= 2;
        }
        // 循环结束时, index值为该2进制数组实际长度.
        // 例如, num = 10 -> 1010. 循环结束时, index = 4

        int[] ans = new int[sum + 1];
        ans[0] = sum;
        int ansIndex = 1;
        for (int i = 31; i >= 0; i--) {
            if (binaryArray[i] == 1)
                ans[ansIndex++] = index - i;
        }

        return ans;
    }

    @Test
    public void test1() {
        int[] expect = {2,1,3};
        org.junit.Assert.assertArrayEquals(expect, calculateNumber(10));
    }

    @Test
    public void test2() {
        int[] expect = {3,1,2,3};
        org.junit.Assert.assertArrayEquals(expect, calculateNumber(7));
    }

    @Test
    public void test3() {
        int[] expect = {0};
        org.junit.Assert.assertArrayEquals(expect, calculateNumber(0));
    }
}
