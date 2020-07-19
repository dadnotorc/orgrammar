/*
Easy
#DP
 */
package leetcode;

import java.util.Arrays;

/**
 * 198. House Robber
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain
 * amount of money stashed, the only constraint stopping you from robbing each of them is that
 * adjacent houses have security system connected and it will automatically contact the police
 * if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 *
 * Example 2:
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 *              Total amount you can rob = 2 + 9 + 1 = 12.
 *
 * Constraints:
 * - 0 <= nums.length <= 100
 * - 0 <= nums[i] <= 400
 */
public class _0198_HouseRobber {

    /**
     * iterative + memo - bottom up
     * 不再使用array, 而是只用两个数字记录 dp[i - 2] 和 dp[i - 1]
     */
    public int rob_DP_bottom_up_2(int[] nums) {
        if (nums.length < 1) { return 0; }
        int prev2 = 0;
        int prev1 = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            int cur = Math.max(prev2 + nums[i - 1], prev1);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }


    /**
     * iterative + memo - bottom up
     * base: dp[0] = 0 表示没偷之前, 共有 $0; dp[1] = nums[0] 表示偷了第1家后, 共有 $nums[0]. 之后进入循环
     * 第2家: 偷 = dp[0] + nums[1]; 不偷 = dp[1]
     * 第3家: 偷 = dp[1] + nums[2]; 不偷 = dp[2]
     * ...
     * 第i家: 偷 = dp[i - 2] + nums[i - 1]; 不偷 = dp[i - 1]
     */
    public int rob_DP_bottom_up(int[] nums) {
        if (nums.length < 1) { return 0; }
        int[] dp = new int[nums.length + 1]; // 注意要+1
        dp[0] = 0; // 开始偷之前
        dp[1] = nums[0]; // 偷了第一家
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[nums.length];
    }



    /**
     * Recursion解法会重复计算helper(i)很多此, 所以使用array做memo (Recursion + memo - top down)
     * time: O(n); space:O(n)
     */
    int[] dp;

    public int rob_DP_top_down(int[] nums) {
        dp = new int[nums.length];
        Arrays.fill(dp, -1); // 初始为-1, 因为nums里的值为 non-negative, 但仍有可能为0, 所以访问过某间房可能什么都没偷到
        return helper_1(nums, nums.length - 1);
    }

    private int helper_1(int[] nums, int index) {
        if (index < 0) { return 0; }
        if (dp[index] >= 0) { return dp[index]; } // index房已访问过, 直接返回其值

        int curAmount = Math.max(helper_1(nums, index - 2) + nums[index], helper_1(nums, index - 1));
        dp[index] = curAmount;
        return curAmount;
    }


    /**
     * Recursion - top down - 会TLE
     * 对于i房子, 盗贼有两个选项:
     * 1. 偷, 但是就不能偷i-1. 所以只能偷i-2
     * 2. 不偷. 这样到i房子时, 到手的钱与到i-1房子时一样多
     * 所以 helper(i) = Math.max(helper(i-2) + nums[i], helper(i-1))
     */
    public int rob_Recursion_TLE(int[] nums) {
        return helper_TLE(nums, nums.length - 1);
    }

    private int helper_TLE(int[] nums, int index) {
        if (index < 0) {
            return 0;
        }
        return Math.max(helper_TLE(nums, index - 2) + nums[index], helper_TLE(nums, index - 1));
    }
}
