/*
Easy
#Binary Search
 */
package lintcode;

import org.junit.Test;

/**
 * 457 · Classical Binary Search
 *
 * Find any position of a target number in a sorted array. Return -1 if target does not exist.
 *
 * Example 1:
 * Input: nums = [1,2,2,4,5,5], target = 2
 * Output: 1 or 2
 *
 * Example 2:
 * Input: nums = [1,2,2,4,5,5], target = 6
 * Output: -1
 *
 * Challenge
 * - O(logn) time
 */
public class _0457_ClassicalBinarySearch {

    /**
     *
     */
    public int findPosition_first_occurance(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return - 1;
        }

        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return nums[l] == target ? l : -1;
    }

    /**
     *
     */
    public int findPosition_lowest_index(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return - 1;
        }

        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                r = mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return nums[l] == target ? l : -1;
    }

    @Test
    public void test1() {
        int[] nums = {1,2,2,4,5,5};
        org.junit.Assert.assertEquals(2, findPosition_first_occurance(nums, 2));
        org.junit.Assert.assertEquals(1, findPosition_lowest_index(nums, 2));
    }
}
