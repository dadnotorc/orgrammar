package lintcode;

import org.junit.Test;

/**
 * 406 · Minimum Size Subarray Sum
 * Medium
 * #Two Pointers, #Prefix Sum, #Array
 * Facebook
 *
 * Given an array of n positive integers and a positive integer s,
 * find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return -1 instead.
 *
 * Example 1:
 * Input: [2,3,1,2,4,3], s = 7
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 * Example 2:
 * Input: [1, 2, 3, 4, 5], s = 100
 * Output: -1
 *
 * Challenge
 * - If you have figured out the O(nlog n) solution, try coding another solution of which the time complexity is O(n).
 *
 * 等同 leetcode 209
 */
public class _0406_Minimum_Size_Subarray_Sum {

    /**
     * 九章解法 - 类似前缀额解法, 但是不维护前缀和数组 - sliding windows, 保持其长度在 s 附近
     *
     * 时间 O(2 x n) - 每个元素最多被访问 2 次, l 与 r 指针各 1 次
     * 空间 O(1)
     */
    public int minimumSize_9z(int[] nums, int s) {
        if (nums == null || nums.length == 0) { return -1; }

        int prefixSum = 0;
        int ans = Integer.MAX_VALUE;

        for (int l = 0, r = 0; r < nums.length; r++) {
            prefixSum += nums[r];

            while (prefixSum >= s) {
                ans = Math.min(ans, r - l + 1);
                prefixSum -= nums[l];
                l++;
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    /**
     * 求 前缀和 - subarray [l, r] 的 sum = prefixSum[r + 1] - prefixSum[l] -  prefixSum 的长度为 n + 1
     *
     * 例如, input = [1,2,3,4,5]; prefixSum = [0,1,3,6,10,15].
     * nums[2] ~ nums[4] 之和 = 3 + 4 + 5 = 12 = prefixSum[4 + 1] - prefixSum[2] = 15 - 3
     *
     * 注意! 前缀和数组长度 必须为 n + 1, 否则缺少计算 subarray [0, r] 的可能 (即从0 到 r)
     * input = [1,2,3,4,5]; prefixSum = [1,3,6,10,15].
     * nums[0] ~ nums[4] 之和 = 1 + 2 + 3 + 4 + 5 = 15. 此答案通过前缀和相减无法获得
     *
     * 时间 O(3 x n) - 每个元素最多被访问 3 次. 求前缀和 1 次, 之后 l 与 r 指针各 1 次
     * 空间 O(n)
     */
    public int minimumSize(int[] nums, int s) {
        if (nums == null || nums.length == 0) { return -1; }

        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        int[] prefixSum = new int[n + 1]; // 长度必须为 n + 1, 否则无法计算 subarray [0, r] 之和. 参见之后的 bug

        // 求前缀和
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] += prefixSum[i] + nums[i];
        }

        // 这里的 l 和 r 是 prefixSum 上的下标, 对应 nums 上的 l 与 r - 1
        int l = 0;
        for (int r = 1; r <= n; r++) { // 注意这里 r 从 1 开始, 而且要 <= n, 因为 prefixSum 数组长一位
            while (l < r && prefixSum[r] - prefixSum[l] >= s) {
                ans = Math.min(ans, r - l);
                l++;
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }






    /**
     * 有 bug
     */
    public int minimumSize_bug(int[] nums, int s) {
        if (nums == null || nums.length == 0) { return -1; }

        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        int[] prefixSum = new int[n]; // 这个长度 缺少计算 subarray [0, r] 的能力
        prefixSum[0] = nums[0];
        if (prefixSum[0] >= s) {
            return 1;
        }

        // 求前缀和
        for (int i = 1; i < n; i++) {
            prefixSum[i] += prefixSum[i - 1] + nums[i];
        }

        int l = 1, r = 1;
        while (r < n) {
            if (prefixSum[r] - prefixSum[l - 1] >= s) {
                ans = Math.min(ans, r - l + 1);
                l++;
            } else {
                r++;
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    @Test
    public void test1() {
        int[] nums = {9,1,8,2,7,3,6,4,5,10}; // 前缀和为 [9, 10, 18, 20, 27, 30, 36, 40, 45, 55]
        int s = 55;

//        org.junit.Assert.assertEquals(10, minimumSize_bug(nums, s));
        org.junit.Assert.assertEquals(10, minimumSize(nums, s));
    }
}
