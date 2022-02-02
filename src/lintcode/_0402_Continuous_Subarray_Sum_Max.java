package lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * 402. Continuous Subarray Sum - Maximum Subarray
 * Medium
 * #Array, #Prefix Sum
 * Amazon, Facebook
 *
 * Given an integer array, find a continuous subarray where the sum of numbers is the biggest.
 * Your code should return the index of the first number and the index of the last number.
 * (If their are duplicate answer, return the minimum one in lexicographical order)
 *
 * Example 1:
 * Input: [-3, 1, 3, -3, 4]
 * Output: [1, 4]
 *
 * Example 2:
 * Input: [0, 1, 0, 1]
 * Output: [0, 3]
 * Explanation: The minimum one in lexicographical order.
 *
 * 类似 leetcode 523. 但是 leetcode 是找是否存在 sum 为 k 的倍数的 subarray
 * 此题是, 找拥有最大 sum 的一组 subarray
 *
 * 这题实际更类似 leetcode 53. Maximum Subarray
 * leetcode 要求返回的是 sum 值
 */
public class _0402_Continuous_Subarray_Sum_Max {

    /**
     * 遍历数组的同时, 同时做以下判断
     * 1. 到 i 之前, 前缀数组和 是否 为负数 (注意, 这里仍未加上 i 的值)
     *    - 如果是, 则从 i 位重新开始 - 前缀和为 i 值, 同时挪动 左右指针 到 i
     *    - 如果不是, 则前缀和加上 i 值, 并 只挪动 右指针
     *
     * 2. 处理完 i 值后, 比较 前缀数组 vs 最大数组和
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public List<Integer> continuousSubarraySum(int[] a) {

        int[] ans = new int[2];

        if (a == null || a.length < 1) {
            return Arrays.asList(ans[0], ans[1]);
        }

        int l = 0, r = 0;
        long curSum = a[0], maxSum = a[0];

        for (int i = 1; i < a.length; i++) {

            if (curSum < 0) { // 加上 (i-1) 后, 前缀和为负数, 且比之前所有数之和都小. 这里不能用 <=
                curSum = a[i]; // 取前缀和变成负数后的第一位
                l = r = i;
            } else {
                curSum += a[i];
                r = i;
            }

            if (curSum > maxSum) {
                maxSum = curSum;
                ans[0] = l;
                ans[1] = r;
            }

        }

        return Arrays.asList(ans[0], ans[1]);
    }






    /**
     * 有 bug
     * [1,2,-2,-100,1,2,-2], exp = [0,1], actual = [1,1]
     * [0,1,2], exp = [0,2], actual = [1,2]
     */
    public List<Integer> continuousSubarraySum_bug(int[] a) {

        if (a == null || a.length == 0) {
            return new ArrayList<>();
        }

        if (a.length == 1) {
            return new ArrayList<>(Arrays.asList(a[0], a[0]));
        }

        List<Integer> ans = new ArrayList<>();

        int maxIndex = 0, minIndex = 0, preMinIndex = -1;
        int cumSum = a[0], maxSum = a[0], minSum = a[0];

        for (int i = 1; i < a.length; i++) {

            cumSum += a[i];

            if (cumSum > maxSum) {
                maxSum = cumSum;
                maxIndex = i;
            }

            if (cumSum < minSum) {
                minSum = cumSum;
                preMinIndex = minIndex <= maxIndex ? minIndex : preMinIndex;
                minIndex = i;
            }
        }

        if (minIndex <= maxIndex) {
            ans.add(minIndex == maxIndex ? minIndex : minIndex + 1);
        } else if (preMinIndex <= maxIndex) {
            ans.add(preMinIndex == maxIndex ? preMinIndex : preMinIndex + 1);
        }

        ans.add(maxIndex);

        return ans;
    }






    @Test
    public void test0() {
        int[] a = {0};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0, 0));
        List<Integer> act = continuousSubarraySum(a);
        assertTrue(exp.equals(act));

        act = continuousSubarraySum_bug(a);
        assertTrue(exp.equals(act));
    }

    @Test
    public void test1() {
        int[] a = {-3, 1, 3, -3, 4};
        List<Integer> exp = new ArrayList<>(Arrays.asList(1, 4));
        List<Integer> act = continuousSubarraySum(a);
        assertTrue(exp.equals(act));

        act = continuousSubarraySum_bug(a);
        assertTrue(exp.equals(act));
    }

    @Test
    public void test2() {
        int[] a = {0, 1, 0, 1};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0, 3));
        List<Integer> act = continuousSubarraySum(a);
        assertTrue(exp.equals(act));

        act = continuousSubarraySum_bug(a);
        assertTrue(exp.equals(act));
    }

    @Test
    public void test3() {
        int[] a = {-4,7,-8,6,-5,-3,4};
        List<Integer> exp = new ArrayList<>(Arrays.asList(1, 1));
        List<Integer> act = continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    public void test4() {
        int[] a = {-3,1,3,-4,7};
        List<Integer> exp = new ArrayList<>(Arrays.asList(1, 4));
        List<Integer> act = continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    public void test5() {
        int[] a = {1,0,0,1};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0, 3));
        List<Integer> act = continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    public void test6() {
        int[] a = {-1,0,0,1};
        List<Integer> exp = new ArrayList<>(Arrays.asList(1, 3));
        List<Integer> act = continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    public void test7() {
        int[] a = {0,0,0,1};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0, 3));
        List<Integer> act = continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    /**
     * 注意 这个case
     */
    @Test
    public void test8() {
        int[] a = {-1,-2,-3,-100,1,2,3,100};
        List<Integer> exp = new ArrayList<>(Arrays.asList(4,7));
        List<Integer> act = continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    public void test9() {
        int[] a = {-101,-33,-44,-55,-67,-78,-101,-33,-44,-55,-67,-78,-100,-200,-1000,-22,-100,-200,-1000,-22};
        List<Integer> exp = new ArrayList<>(Arrays.asList(15,15));
        List<Integer> act = continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    public void test10() {
        int[] a = {1,0,0,0};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0,0));
        List<Integer> act = continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    public void test11() {
        int[] a = {};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0,0));
        List<Integer> act = continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }
}
