/*
Easy

Amazon
Ladder
 */
package lintcode;

import org.junit.Test;

/**
 * 604. Window Sum
 *
 * Given an array of n integers, and a moving window(size k), move the window at each iteration
 * from the start of the array, find the sum of the element inside the window at each moving.
 *
 * Example 1
 * Input：array = [1,2,7,8,5], k = 3
 * Output：[10,17,20]
 * Explanation：
 * 1 + 2 + 7 = 10
 * 2 + 7 + 8 = 17
 * 7 + 8 + 5 = 20
 */
public class _0604_WindowSum {

    public int[] winSum(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return new int[0];

        int l = nums.length;
        int[] ans = new int[Math.max(1, l - k + 1)]; // 当 l<=k 时, 窗口涵盖整个数组, sum只会有一位

        for (int i = 0; i < Math.min(l, k); i++) {
            ans[0] += nums[i];
        }

        int index = 1;
        for (int j = k; j < l; j++) {
            ans[index] = ans[index - 1] + nums[j] - nums[j - k];
            index++;
        }

        return ans;
    }

    @Test
    public void test1() {
        int[] nums = {1,2,7,7,2};
        int[] exp = {10,16,16};
        org.junit.Assert.assertArrayEquals(exp, winSum(nums, 3));
    }
}
