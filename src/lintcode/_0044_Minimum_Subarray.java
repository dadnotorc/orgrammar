package lintcode;

import java.util.List;

/**
 * 44 · Minimum Subarray
 * Easy
 * #DP, #Prefix Sum
 *
 * Given an array of integers, find the continuous subarray with the smallest sum.
 *
 * Return the sum of the subarray.
 *
 * The subarray should contain one integer at least.
 *
 * Example 1:
 * Input: array = [1,-1,-2,1]
 * Output: -3
 * Explanation:
 * The sum of the sub-arrays [-1,-2] is the minimum value -3.
 *
 * Example 2:
 * Input: array = [1,-1,-2,1,-4]
 * Output: -6
 * Explanation:
 * The sum of the sub-arrays [-1,-2,1,-4] is the minimum value -6.
 */
public class _0044_Minimum_Subarray {

    /*
    遍历每个元素的时候, 比较 前缀和 VS 当前元素值, 取较小者
    意思是, 如果当前元素比前缀和小, 则放弃之前的元素, 从当前元素重新开始

    例如 nums = [1,-1,-2,1,-4]
    index 0: minSumIncludeCurrent = 1;                     minSumGlobal = 1
    index 1: minSumIncludeCurrent = min(-1, -1 + 1) = -1 , minSumGlobal = min(1, -1) = -1
    index 2: minSumIncludeCurrent = min(-2, -2 + -1) = -3, minSumGlobal = min(-1, -3) = -3
    index 3: minSumIncludeCurrent = min(1, 1 + -3) = -2,   minSumGlobal = min(-3, -2) = -3
    index 4: minSumIncludeCurrent = min(-4, -4 + -2) = -6, minSumGlobal = min(-3, -6) = -6

    时间 O(n), 空间 O(1)
     */
    public int minSubArray(List<Integer> nums) {
        if (nums == null || nums.size() == 0) { return 0; }

        int minSumIncludeCurrent = nums.get(0); // min sum subarray 必须包含当前元素
        int minSumGlobal = minSumIncludeCurrent; // min sum subarray 可以不包含当前元素

        for (int i = 1; i < nums.size(); i++) {
            minSumIncludeCurrent = Math.min(nums.get(i), nums.get(i) + minSumIncludeCurrent);
            minSumGlobal = Math.min(minSumGlobal, minSumIncludeCurrent);
        }

        return minSumGlobal;
    }
}
