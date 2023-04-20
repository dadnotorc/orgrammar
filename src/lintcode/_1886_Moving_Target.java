package lintcode;

/**
 * 1886 · Moving Target
 * Easy
 * #Two Pointers, #Same Direction Two Pointers
 *
 * Given an array nums and an integer target ,
 * you need to move the element which equal to target to the front of the array,
 * and the order of the remaining elements can not be changed.
 * All your move operations should be performed on the original array.
 *
 * - The length of array is within range: [1, 100000]
 * - If the target does not appear in the array, there is no need to modify the original array.
 *
 * Example 1:
 * Input: nums = [5, 1, 6, 1], target=1
 * Output: [1, 1, 5, 6]
 * Explanation: 1 is target, so you should keep them in the front of array
 *
 * Example 2:
 * Input: nums = [-1, 2, 3, 5, 2, 2], target = 2
 * Output: [2, 2, 2, -1, 3, 5]
 * Explanation: 2 is target, so you should keep them in front of the array
 *
 * Example 3:
 * Input: nums = [2, 3, 4, 6], target = 1
 * Output: [2, 3, 4, 6]
 * Explanation: there is not target
 */
public class _1886_Moving_Target {

    /**
     * 也是同向指针, 从后往前 - 比从前往后快
     *
     * 时间 O(n) - n为数组长度，需要遍历一遍数组。
     * 空间 O(1) - 原地操作不需要要额外的空间。
     */
    public void moveTarget_2(int[] nums, int target) {
        if (nums == null || nums.length == 0) { return; }

        int n = nums.length;
        int count = 0; // 记录 target 的数量
        int l = n - 1, r = n - 1;

        while (l >= 0) {
            if (nums[l] != target) {
                if (l != r) {
                    nums[r] = nums[l];
                }
                r--;
                l--;
            } else {
                count++;
                l--;
            }
        }

        for (int i = 0; i < count; i++) {
            nums[i] = target;
        }
    }



    /**
     * 同向指针 - 从前往后
     */
    public void moveTarget(int[] nums, int target) {
        if (nums == null || nums.length == 0) { return; }

        int n = nums.length;
        int l = 0, r = 1;

        while (r < n) {
            if (nums[r] == target) {
                int mid = r;
                while (mid > l) {
                    nums[mid] = nums[mid - 1];
                    mid--;
                }
                nums[l] = target;
                l++;
            }

            r++;

            // 上面是简化出来的
//            if (nums[r] != target) {
//                r++; // 这里不能移动 l 指针哦
//            } else {
//                int mid = r;
//                while (mid > l) {
//                    nums[mid] = nums[mid - 1];
//                    mid--;
//                }
//                nums[l] = target;
//                l++;
//                r++;
//            }
        }
    }
}
