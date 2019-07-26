package lintcode;

import java.util.Arrays;

/**
 * 533. Two Sum - Closest to target
 *
 * Given an array nums of n integers, find two integers in nums such that the sum is closest to a given number, target.
 * Return the absolute value of difference between the sum of the two integers and the target.
 *
 * Challenge:
 * Do it in O(n log n) time complexity.
 *
 * time complexity:  O(n log n)
 * space complexity: O(1)
 */
public class _0533 {
	
	/**
     * @param nums an integer array
     * @param target an integer
     * @return the difference between the sum and the target
     */
    public int twoSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return -1;
        }

        // don't forget to sort first
        Arrays.sort(nums);
        
        int left = 0, right = nums.length - 1;
        int diff = Integer.MAX_VALUE;
        
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                diff = Math.min(diff, target - nums[left] - nums[right]);
                left++;
            } else {
                diff = Math.min(diff, nums[left] + nums[right] - target);
                right--;
            }
        }
        
        return diff;
    }
}
