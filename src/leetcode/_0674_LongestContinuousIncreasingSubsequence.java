/*
Easy
Array
 */
package leetcode;

import org.junit.Test;

/**
 * 674. Longest Continuous Increasing Subsequence
 *
 * Given an unsorted array of integers, find the length of longest continuous
 * increasing subsequence (subarray).
 *
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
 * Even though [1,3,5,7] is also an increasing subsequence,
 * it's not a continuous one where 5 and 7 are separated by 4.
 *
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 1
 * Explanation: The longest continuous increasing subsequence is [2], its length is 1.
 *
 * Note: Length of the array will not exceed 10,000.
 */
public class _0674_LongestContinuousIncreasingSubsequence {

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int ans = 1, curMax = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                curMax++;
            }
            else {
                ans = Math.max(ans, curMax);
                curMax = 1;
            }
        }

        return Math.max(ans, curMax); // 注意, 如果直接return ans就会出错. 因为ans在循环中可能从未更新过, 比如 {1,3,5,7}
    }

    // 另一种写法
    public int findLengthOfLCIS_v2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int ans = 1, curMax = 1;

        for (int i = 1; i < nums.length; i++) {
            curMax = nums[i] > nums[i - 1] ? curMax + 1 : 1;
            ans = Math.max(ans, curMax);
        }

        return ans;
    }

    @Test
    public void test1() {
        int[] nums = {1,3,5,4,7};
        org.junit.Assert.assertEquals(3, findLengthOfLCIS(nums));
        org.junit.Assert.assertEquals(3, findLengthOfLCIS_v2(nums));
    }

    @Test
    public void test2() {
        int[] nums = {2,2,2,2,2};
        //org.junit.Assert.assertEquals(1, findLengthOfLCIS(nums));
        org.junit.Assert.assertEquals(1, findLengthOfLCIS_v2(nums));
    }

    @Test
    public void test3() {
        int[] nums = {1,3,5,7};
        //org.junit.Assert.assertEquals(4, findLengthOfLCIS(nums));
        org.junit.Assert.assertEquals(4, findLengthOfLCIS_v2(nums));
    }
}
