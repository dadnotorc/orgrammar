/*
Medium
#Array, #Binary Search
 */
package leetcode;

/**
 * 33. Search in Rotated Sorted Array
 *
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the rotation and an integer target,
 * return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Example 3:
 * Input: nums = [1], target = 0
 * Output: -1
 *
 * Constraints:
 * - 1 <= nums.length <= 5000
 * - -10^4 <= nums[i] <= 10^4
 * - All values of nums are unique.
 * - nums is guaranteed to be rotated at some pivot.
 * - -10^4 <= target <= 10^4
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

    // 第二种写法, l <= r
    public int search_2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                if (nums[l] <= target && nums[l] > nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[l] <= nums[mid] && nums[l] > target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return -1;
    }
}
