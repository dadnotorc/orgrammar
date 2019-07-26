package leetcode.binarysearch;

/**
 * 162. Find Peak Element
 * Medium
 *
 * A peak element is an element that is greater than its neighbors.
 *
 * Given an input array nums, where nums[i] ≠ nums[i+1],
 *  find a peak element and return its index.
 *
 * The array may contain multiple peaks, in that case return the
 *  index to any one of the peaks is fine.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return
 *  the index number 2.
 *
 * Example 2:
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where
 *  the peak element is 2, or index number 5 where the peak element is 6.
 *
 * Note:
 * - Your solution should be in logarithmic complexity.
 *
 * 对数 -> O(log n) -> 二分法 binary search
 */
public class _0162_FindPeak {

    public int findAnyPeakElement(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }

        int l = 0, r = nums.length - 1;
        int mid;
        while (l + 1 < r) {
            mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) {
                return mid;
            }

            if (nums[mid] > nums[mid-1]) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return nums[l] > nums[r] ? l : r;
    }
}
