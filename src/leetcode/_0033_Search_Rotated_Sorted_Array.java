package leetcode;

/**
 * 33. Search in Rotated Sorted Array
 * Medium
 * #Array, #Binary Search
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
public class _0033_Search_Rotated_Sorted_Array {

    /*
    暴力解法 - 遍历 O(n)

    二分法 - 针对 nums[mid] < target 或者 > target, 每个又都有两种可能
    - nums[mid] < target
       - target 在左上
          - mid 也在左上 -> l = mid + 1;   <- 这两者一样
          - mid 在右下 -> r = mid - 1;
       - target 在右下
          - mid 也在右下 -> l = mid + 1;   <- 这两者一样

     - nums[mid] > target
       - target 在左上
          - mid 也在左上 -> r = mid - 1;   <- 这两者一样
       - target 在右下
          - mid 在左上 -> l = mid + 1;
          - mid 也在右下 -> r = mid - 1;   <- 这两者一样

     所以写 if 的时候, 将单独的一种列出来, 另两种合在一起
     */

    public int search(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return -1;
        }

        int l = 0, r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // if 中, 如果要写 target >= nums[l] && nums[mid] <= nums[r]
            // 必须加上 nums[l] > nums[r], 保证是 rotated 过的

            // 同理, else 中要写, t

            if (nums[mid] < target) {
                // 如果要写 target >= nums[l] && nums[mid] <= nums[r]
                // 必须加上 nums[l] > nums[r], 保证是 rotated 过的
                if (target > nums[r] && nums[mid] < nums[l]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // 同理, 这里可以写 nums[l] > nums[r] && target <= nums[r] && nums[mid] >= nums[l]
                if (target < nums[l] && nums[mid] > nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return nums[l] == target ? l : -1;
    }


    /**
     * l + 1 < r
     */
    public int search_1(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int l = 0, r = nums.length - 1;

        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                if (target >= nums[l] && nums[mid] < nums[l]) { // target在左上, nums[mid]在右下. 注意 = 的写法
                    r = mid - 1;
                } else { // nums[mid]和target都在左上, 或者都在右下
                    l = mid + 1;
                }
            } else { // nums[mid] > target
                if (nums[mid] >= nums[l] && target < nums[l]) { // nums[mid]在左上, target在右下
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


    /**
     * l <= r
     */
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
