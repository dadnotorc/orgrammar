/*
Easy
#Binary Search
 */
package lintcode;

/**
 * 458 · Last Position of Target
 *
 * Find the last position of a target number in a sorted array. Return -1 if target does not exist.
 *
 * Example 1:
 * Input: nums = [1,2,2,4,5,5], target = 2
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,2,2,4,5,5], target = 6
 * Output: -1
 */
public class _0458_LastPositionOfTarget {

    public int lastPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) { return -1; }

        if (target < nums[0] || target > nums[nums.length - 1]) { return -1; }

        int l = 0, r = nums.length - 1;
        while (l + 1 < r) { // 相邻时结束
            int mid = l + (r - l) / 2;
            // 下面两种写法都对
//            if (nums[mid] == target) {
//                l = mid;
//            } else if (nums[mid] < target) {
//                l = mid + 1;
//            } else {
//                r = mid - 1;
//            }
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        if (nums[r] == target) { return r; }
        if (nums[l] == target) { return l; }
        return -1;
    }

    /**
     * TLE .....
     */
    public int lastPosition_tle(int[] nums, int target) {
        if (nums == null || nums.length == 0) { return -1; }

        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                l = mid; // 这里就是出问题的地方
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return nums[l] == target ? l : -1;
    }
}
