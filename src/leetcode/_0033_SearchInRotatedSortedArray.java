/*
Medium
#Array, #Binary Search
 */
package leetcode;

/**
 * 33. Search in Rotated Sorted Array
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class _0033_SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int l = 0, r = nums.length - 1;

        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                if (nums[l] <= target && nums[l] > nums[mid]) { // target在左上, nums[mid]在右下
                    r = mid - 1;
                } else { // nums[mid]和target都在左上, 或者都在右下
                    l = mid + 1;
                }
            } else { // nums[mid] > target
                if (nums[l] <= nums[mid] && nums[l] > target) { // nums[mid]在左上, target在右下
                    l = mid + 1;
                } else { // target和nums[mid]都在左上, 或者都在右下
                    r = mid - 1;
                }
            }
        }

        if (nums[l] == target)
            return l;
        if (nums[r] == target)
            return r;
        return -1;
    }
}
