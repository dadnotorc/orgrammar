package lintcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 402. Continuous Subarray Sum
 * Medium
 * Amazon, Facebook
 *
 * Given an integer array, find a continuous subarray where the sum of numbers
 *  is the biggest. Your code should return the index of the first number and
 *  the index of the last number. (If their are duplicate answer, return the
 *  minimum one in lexicographical order)
 *
 * Example 1:
 * Input: [-3, 1, 3, -3, 4]
 * Output: [1, 4]
 *
 * Example 2:
 * Input: [0, 1, 0, 1]
 * Output: [0, 3]
 * Explanation: The minimum one in lexicographical order.
 */
public class _0402_ContinuousSubarraySum {

    /**
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
            /**
             * 两个判断
             * 1. 到(i-1)为止,前缀数组和是否为负数.
             *    - 如果是, 则从i位重新开始. 同时挪动左右指针
             *    - 如果不是, 则添加上i位数值, 并只挪动右指针
             *
             * 2. 到i位为止, 前缀数组和是否最大
             *    - 如果是, 更新相关值
             */
            if (curSum < 0) { // 前一位数(i-1)为负数, 且比之前所有数之和都小
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


        // TODO 怎么用prefix sum来解
    public List<Integer> continuousSubarraySum_prefixsum(int[] a) {

        if (a == null || a.length == 0) {
            return new ArrayList<>();
        }

        if (a.length == 1) {
            return new ArrayList<>(Arrays.asList(a[0], a[0]));
        }

        List<Integer> ans = new ArrayList<>();

        /**
         * 先计算前缀和数组 prefix sum.
         * 记录见过的最大值 max ,以及 max 之前的第一位最小值 min,
         *  注意必须是第一位的, 这样保证 minIndex 最小
         */
        int maxIndex = 0;
        int minIndex = 0;
        int diff = 0;
        for (int i = 1; i < a.length; i++) {
//            a[i] += a[i-1];
//
//            if (a[maxIndex] < a[i]) {
//                maxIndex = i;
//            }

            if (a[i] - a[i-1] >= diff) {
                maxIndex = i;
            }
            a[i] += a[i-1];
        }

        for (int j = 0; j <= maxIndex; j++) {
            if (a[j] < a[minIndex]) {
                minIndex = j;
            }
        }

        /**
         * 最小值如为负数, 取其后一位, 因为后一位为正数才能增加)
         *
         * 最小值为0或者正数时, 说明它开始, 之后都为正数
         */
        ans.add(a[minIndex] < 0 ? minIndex + 1 : minIndex);
        ans.add(maxIndex);

        return ans;
    }

    @Test
    void test0() {
        int[] a = {0};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0, 0));
        List<Integer> act = new _0402_ContinuousSubarraySum().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test1() {
        int[] a = {-3, 1, 3, -3, 4};
        List<Integer> exp = new ArrayList<>(Arrays.asList(1, 4));
        List<Integer> act = new _0402_ContinuousSubarraySum().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test2() {
        int[] a = {0, 1, 0, 1};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0, 3));
        List<Integer> act = new _0402_ContinuousSubarraySum().continuousSubarraySum(a);

        System.out.println(act.size());
        System.out.println(act.get(0));
        System.out.println(act.get(1));

        //assertTrue(exp.equals(act));
    }

    @Test
    void test3() {
        int[] a = {-4,7,-8,6,-5,-3,4};
        List<Integer> exp = new ArrayList<>(Arrays.asList(1, 1));
        List<Integer> act = new _0402_ContinuousSubarraySum().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test4() {
        int[] a = {-3,1,3,-4,7};
        List<Integer> exp = new ArrayList<>(Arrays.asList(1, 4));
        List<Integer> act = new _0402_ContinuousSubarraySum().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test5() {
        int[] a = {1,0,0,1};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0, 3));
        List<Integer> act = new _0402_ContinuousSubarraySum().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test6() {
        int[] a = {-1,0,0,1};
        List<Integer> exp = new ArrayList<>(Arrays.asList(1, 3));
        List<Integer> act = new _0402_ContinuousSubarraySum().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test7() {
        int[] a = {0,0,0,1};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0, 3));
        List<Integer> act = new _0402_ContinuousSubarraySum().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    /**
     * 注意 这个case
     */
    @Test
    void test8() {
        int[] a = {-1,-2,-3,-100,1,2,3,100};
        List<Integer> exp = new ArrayList<>(Arrays.asList(4,7));
        List<Integer> act = new _0402_ContinuousSubarraySum().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test9() {
        int[] a = {-101,-33,-44,-55,-67,-78,-101,-33,-44,-55,-67,-78,-100,-200,-1000,-22,-100,-200,-1000,-22};
        List<Integer> exp = new ArrayList<>(Arrays.asList(15,15));
        List<Integer> act = new _0402_ContinuousSubarraySum().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test10() {
        int[] a = {1,0,0,0};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0,0));
        List<Integer> act = new _0402_ContinuousSubarraySum().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test11() {
        int[] a = {};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0,0));
        List<Integer> act = new _0402_ContinuousSubarraySum().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }
}
