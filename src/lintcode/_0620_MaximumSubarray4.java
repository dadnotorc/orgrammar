/*
Medium
#Subarray, #Array, #Two Pointers
Amazon, Facebook
 */
package lintcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * 620. Maximum Subarray IV
 *
 * Given an integer arrays, find a contiguous subarray which has the
 * largest sum and length should be greater or equal to given length k.
 *
 * Return the largest sum, return 0 if there are fewer than k elements
 * in the array.
 *
 * 1. Ensure that the result is an integer type.
 * 2. k > 0
 *
 * Example 1:
 * Input: [-2,2,-3,4,-1,2,1,-5,3], 5
 * Output: 5
 * Explanation: [2,-3,4,-1,2,1], sum=5
 *
 * Example 2:
 * Input: [5,-10,4], 2
 * Output: -1
 */
public class _0620_MaximumSubarray4 {

    // TODO 看看有没有更快的解法

    /**
     * 双指针解法.
     * 右指针指在i位, 左指针指去i-k+1位, 左右指针之间subarray长度为k
     * minSum记录index 1到i-k+1之间, 最小的前缀和.
     * 当前前缀和 - k位之前的最小前缀和 = 当前subarray之和. 纪录最大值并返回
     * time: O(n)
     * space: O(1)
     */
    public int maxSubarray4(int[] nums, int k) {
        int ans = Integer.MIN_VALUE;
        if (nums == null || nums.length < k || k <= 0) { return 0; }

        int l = 0, r = 0;
        // 注意, 这里不能用 minSum = Integer.MAX_VALUE;
        // 因为当右指针指去k-1时, 当前subarray和 = nums[r] - 0;
        // 之后才开始寻找更小的前缀和
        int minSum = 0;

        while (r < nums.length) {
            if (r > 0) { // r要从0开始, 不然会错误计算array sz=1, k=1的情况. 看test3
                nums[r] += nums[r-1];
            }

            if (r - k + 1 >= l) {
                // 注意, 要先算ans, 再更新minSum
                ans = Integer.max(ans, nums[r] - minSum);
                minSum = Integer.min(minSum, nums[l]);
                l++;
            }

            r++;
        }

        return ans;
    }

    @Test
    public void test1() {
        int act = maxSubarray4(new int[]{-2,2,-3,4,-1,2,1,-5,3}, 5);
        assertEquals(5, act);
    }

    @Test
    public void test2() {
        int act = maxSubarray4(new int[]{5,-10,4}, 2);
        assertEquals(-1, act);
    }

    @Test
    public void test3() {
        int act = maxSubarray4(new int[]{5}, 1);
        assertEquals(5, act);
    }
}
