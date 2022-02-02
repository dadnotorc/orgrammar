package leetcode;

/**
 * 523. Continuous Subarray Sum
 * medium
 * #Array, #Hash Table, #Prefix Sum
 * Facebook
 *
 * Given an integer array nums and an integer k, return true if nums has a continuous subarray
 * of size at least two whose elements sum up to a multiple of k, or false otherwise.
 *
 * An integer x is a multiple of k if there exists an integer n such that x = n * k.
 * 0 is always a multiple of k.
 *
 * Example 1:
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 *
 * Example 2:
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 *
 * Example 3:
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= sum(nums[i]) <= 2^31 - 1
 * 1 <= k <= 2^31 - 1
 *
 * 类似 lintcode 402, 但是 lintcode 的题是找拥有最大 sum 的一组 subarray
 * 此题是, 找是否存在 sum 为 k 的倍数的 subarray
 *
 *
 */
public class _0523_Continuous_Subarray_Sum {

    /*
    题目限制了 nums[i] 为 非负整数, 所以前缀和只会越来越多
     */

    public boolean checkSubarraySum(int[] nums, int k) {

    }
}
