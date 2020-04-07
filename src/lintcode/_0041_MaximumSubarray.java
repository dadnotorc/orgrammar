/*
Easy
#Greedy, #Array, #Subarray, #Enumeration
LinkedIn, Microsoft
FAQ++
 */
package lintcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 41. Maximum Subarray
 * Given an array of integers, find a contiguous subarray which has the
 * largest sum. The subarray should contain at least one number.
 *
 * Example1:
 * Input: [−2,2,−3,4,−1,2,1,−5,3]
 * Output: 6
 * Explanation: the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 *
 * Example2:
 * Input: [1,2,3,4]
 * Output: 10
 * Explanation: the contiguous subarray [1,2,3,4] has the largest sum = 10.
 *
 * Challenge: Can you do it in time complexity O(n)?
 */
public class _0041_MaximumSubarray {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }

        int curSum = nums[0], maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            /**
             * 两个判断
             * 1. 到(i-1)为止,前缀数组和是否为负数.
             *    - 如果是, 则从i位重新开始
             *    - 如果不是, 则添加上i位数值
             *
             * 2. 到i位为止, 前缀数组和是否最大
             *    - 如果是, 更新相关值
             */
            if (curSum < 0) {
                curSum = nums[i];
            } else {
                curSum += nums[i];
            }

            if (curSum > maxSum) {
                maxSum = curSum;
            }
        }

        return maxSum;
    }

    @Test
    public void test1() {
        int[] nums = {-2,2,-3,4,-1,2,1,-5,3};
        int act = maxSubArray(nums);
        assertEquals(6, act);
    }

    @Test
    public void test2() {
        int[] nums = {1,2,3,4};
        int act = maxSubArray(nums);
        assertEquals(10, act);
    }

    @Test
    public void test3() {
        int[] nums = {-4};
        int act = maxSubArray(nums);
        assertEquals(-4, act);
    }

    @Test
    public void test4() {
        int[] nums = {-1,-2,-3,-100,-1,-50};
        int act = maxSubArray(nums);
        assertEquals(-1, act);
    }
}
