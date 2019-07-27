package lintcode.twosum;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**High Five
 * 533. Two Sum - Closest to target
 *
 * Given an array nums of n integers, find two integers in nums such that the sum is closest to a given number, target.
 * Return the absolute value of difference between the sum of the two integers and the target.
 *
 * Challenge:
 * Do it in O(n log n) time complexity.
 */
public class _0533_TwoSumClosest {
	
	/**
     * time complexity:  O(n log n)
     * space complexity: O(1)
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

    @Test
    void test1() {
        int[] nums = new int[] {-1, 2, 1, -4};
        int target = 4;
        int result = (new _0533_TwoSumClosest().twoSumClosest(nums, target));
        // The minimum difference is 1. (4 - (2 + 1) = 1).
        assertTrue(result == 1);
    }

    @Test
    void test2() {
        int[] nums = new int[] {-1, -1, -1, -4};
        int target = 4;
        int result = (new _0533_TwoSumClosest().twoSumClosest(nums, target));
        // The minimum difference is 6. (4 - (- 1 - 1) = 6).
        assertTrue(result == 6);
    }
}
