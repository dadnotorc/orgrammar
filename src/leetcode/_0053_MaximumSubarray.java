package leetcode;

/**
 * 53. Maximum Subarray
 * Easy
 * #Array, #Divide and Conquer, #DP
 *
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution using
 * the divide and conquer approach, which is more subtle.
 *
 * 类似 lintcode 402. Continuous Subarray Sum
 */
public class _0053_MaximumSubarray {

    /**
     * 每遇到一个数字, 考虑到当前数字为止 (必须包括当前值), 最大的subarry之和,
     * 如果curMax + i < i, 则 curMax = i
     * 同时更新maxSum
     */
    public int maxSubArray_3(int[] nums) {
        int curMax = 0, maxSum = Integer.MIN_VALUE;

        for (int i : nums) {
            curMax = Math.max(curMax + i, i);
            maxSum = Math.max(maxSum, curMax);
        }

        return maxSum;
    }


    /**
     * min记录前缀和中最小的那一位
     * 1. prefixSum - min = 到当前位为止的最大区间和, 将其与maxSum比较取较大值
     * 2. 如果当前位的prefixSum < min, 则更新min
     */
    public int maxSubArray_2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int prefixSum = 0, maxSum = nums[0], min = 0;

        for (int i : nums) {
            prefixSum += i;

            if (prefixSum - min > maxSum) {
                maxSum = prefixSum - min;
            }

            if (prefixSum < min) {
                min = prefixSum;
            }
        }

        return maxSum;
    }


    /**
     * 求当前值的curSum有两种情况:
     * 1. 当前值 = curSum: 如果前一个值对应的curSum < 0, 此时加上任何值只会与当前值相等或者更小
     * 2. 当前值 + curSum
     * 每读一个值, 更新maxSum
     *
     * time: O(n)
     */
    public int maxSubArray_iterative(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int curSum = nums[0], maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
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
}
