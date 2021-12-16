/*
Medium
#Array, #DP, #0-1背包

 */
package leetcode;

/**
 * 416. Partition Equal Subset Sum
 *
 * Given a non-empty array nums containing only positive integers, find if the array can be
 * partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Example 1:
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 * Constraints:
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class _0416_PartitionEqualSubsetSum {

    /**
     * 改进 - 使用一维数组
     */
    public boolean canPartition_1D(int[] nums) {
        int n = nums.length;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) { // 非偶数
            return false;
        }

        int half = sum / 2;

        // 寻找 一个 subset, 它的和 等于 half

        boolean[] dp = new boolean[half + 1];
        // 用一维数组代替之前的二维数组

        dp[0] = true; // subset 不包含 nums[0]
        if (nums[0] < half) { // 可能存在 nums = [100] 的情况
            dp[nums[0]] = true; // subset 包含 nums[0]
        }

        for (int i = 1; i < n; i++) {
            // 注意 这里需要从大到小, 否则会错误赋值.
            // 例如 nums = [1,2,5], exp = false, but output = true
            // 当处理数字 1 时, dp = [T, T, F, F, F]
            // 当处理数字 2 时, dp 本应该 = [T, T, T, T, F]. 但是如果 j 从小到大遍历, 读到 j = 4 时, 会读取 j = 2时的新赋值, 导致重复计算
            for (int j = half - nums[i]; j >= 0; j--) {
                if (dp[j]) {
                    dp[j + nums[i]] = true;
                }
            }
        }

        return dp[half];
    }

    /**
     * 另一种写法
     */
    public boolean canPartition_2(int[] nums) {
        int n = nums.length;

        int sum = 0;
        for (int num : nums) { sum += num; }
        if ((sum & 1) == 1) { return false; }

        int half = sum / 2;

        boolean[][] dp = new boolean[n][half + 1];
        // dp[i][j]中, i 表示 nums 数组中数字的下标; j 为 [0, half]中的一个数字.
        // 如 dp[i][j] == true, 说明当 subset 中包含 nums[i] 时, subset 之和等于 j

        dp[0][0] = true; // subset 不包含 nums[0]
        if (nums[0] < half) { // 可能存在 nums = [100] 的情况
            dp[0][nums[0]] = true; // subset 包含 nums[0]
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= half; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n - 1][half];
    }

    /**
     * 另一种写法
     */
    public boolean canPartition_3(int[] nums) {
        int n = nums.length;

        int sum = 0;
        for (int num : nums) { sum += num; }
        if ((sum & 1) == 1) { return false; }

        int half = sum / 2;

        boolean[][] dp = new boolean[n + 1][half + 1];

        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= half; j++) {
                if (j == 0) {
                    dp[i][j] = true;
                } else if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][half];
    }


    /**
     * dp[i][j]中, i 表示 nums 数组中数字的下标; j 为 [0, half]中的一个数字.
     * 如 dp[i][j] == true, 说明当 subset 中包含 nums[i] 时, subset 之和等于 j
     *
     * 最后我们判断, dp[n - 1][half] 是否为 true
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) { // 非偶数
            return false;
        }

        int half = sum / 2;
        // 寻找 一个 subset, 它的和等于 half

        boolean[][] dp = new boolean[n][half + 1];

        dp[0][0] = true; // subset 不包含 nums[0]
        if (nums[0] < half) { // 可能存在 nums = [100] 的情况
            dp[0][nums[0]] = true; // subset 包含 nums[0]
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= half; j++) { // 不把第i个数字放入subset
                if (dp[i - 1][j]) {
                    dp[i][j] = true;
                }
            }
            for (int j = 0; j + nums[i] <= half; j++) { // 第i个数字放入subset
                if (dp[i - 1][j]) {
                    dp[i][j + nums[i]] = true;
                }
            }
        }

        return dp[n - 1][half];
    }



    /**
     * 回溯 backtrack - 会TLE - O(2^n)
     */
    public boolean canPartition_backtrack(int[] nums) {
        int n = nums.length;
        int sum = 0;

        for(int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1) {
            return false;
        }

        sum = sum / 2;

        return helper(nums, 0, 0, sum);
    }

    public boolean helper(int[] nums, int index, int curSum, int sum) {
        if (curSum > sum || index >= nums.length) { // subset 之和已超, 或者没有更多可选数
            return false;
        }

        if (curSum == sum) {
            return true;
        }

        // 两个选项: 两者都需要 index + 1
        // 1. 加入当前数字, curSum增加
        // 2. 不加当前数字, curSum不变
        return helper(nums, index + 1, curSum + nums[index], sum) || helper(nums, index + 1, curSum, sum);
    }

}
