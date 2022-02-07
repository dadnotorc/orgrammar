package leetcode;

/**
 * 153. Find Minimum in Rotated Sorted Array
 * Medium
 * #Array, #Binary Search
 *
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,2,4,5,6,7] might become:
 *    - [4,5,6,7,0,1,2] if it was rotated 4 times.
 *    - [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array
 * [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 *
 * Example 3:
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 *
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * All the integers of nums are unique.
 * nums is sorted and rotated between 1 and n times.
 */
public class _0153_Find_Min_Rotated_Sorted_Array {

    /*
    暴力解法 - 遍历, 寻找最小 - O(n)

    改进 - 二分法 -  O(logn)

    小心特殊情况
    1. rotate 到原位, 单调递增
    2. 如果数组只有两位, 不能找到 nums[i - 1] > nums[i] && nums[i] < nums[i + 1])
     */


    /**
     * 二分法
     */
    public int findMin_1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        int l = 0, r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;

            // 只比较 两位, 为了避免数组只有 2 位的情况
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }

            // if (nums[mid] >= nums[l] && nums[mid] > nums[r]) {
            if (nums[l] > nums[r] && nums[mid] >= nums[l]) {
                l  = mid + 1; // 这必须 + 1, 不然会 TLE
            } else {
                r = mid;
            }
        }

        return nums[l];
    }

    /**
     *
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        int l = 0, r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return nums[l];
    }
}
