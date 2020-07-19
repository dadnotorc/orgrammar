/*
Medium
#DP
Amazon
 */
package leetcode;

/**
 * 213. House Robber II
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain
 * amount of money stashed. All houses at this place are arranged in a circle. That means the first
 * house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 *              because they are adjacent houses.
 * Example 2:
 *
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 */
public class _0213_HouseRobber2 {

    /**
     * iterative + memo - bottom up
     *
     * 一个圈, 0到n-1. 对0号屋, 我们可选择:
     * 1. 偷 ->　如此，１号与ｎ-1号不能偷, 所以递归看 2 ~ n-2 之间能偷多少
     * 2. 不偷 -> 那就可以递归看 1 ~ n-1 之间能偷多少
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) { return 0; }
        if (nums.length == 1) { return nums[0]; }

        return Math.max(
                nums[0] + helper(nums, 2, nums.length - 2), // 偷0号, 所以1号与n-1号不能偷, 递归进2号到n-2号
                helper(nums, 1, nums.length - 1) // 不偷0号, 递归进1号到n-1号
        );

        // 另一种写法
//        return Math.max(
//                helper(nums, 0, nums.length - 2),
//                helper(nums, 1, nums.length - 1));
    }

    // 这段类似 198. House Robber 的 iterative + memo - bottom up解法
    // 与198不同的地方是, 198里假设nums[0]一定被偷, 循环是从第二家开始, 所以prev1=nums[0].
    // 这里我们从start开始, 所以prev2和prev1都等于0
    private int helper(int[] nums, int start, int end) {
        int prev2 = 0, prev1 = 0;
        for (int i = start; i <= end; i++) {
            int cur = Math.max(prev2 + nums[i], prev1);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }
}
