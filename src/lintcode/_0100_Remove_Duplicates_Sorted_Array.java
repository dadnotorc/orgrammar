package lintcode;

import java.util.Arrays;

/**
 * 100 · Remove Duplicates from Sorted Array
 * Easy
 * #Array
 *
 * Given a sorted array, 'remove' the duplicates in place such that
 * each element appear only once and return the 'new' length.
 *
 * Do not allocate extra space for another array, you must do this in place
 * with constant memory. We will determine correctness by the length k
 * of the returned array, intercepting the first k elements of the array.
 *
 * Example 1:
 * Input: nums = []
 * Output: []
 *
 * Example 2:
 * Input: nums = [1,1,2]
 * Output: [1,2]
 */
public class _0100_Remove_Duplicates_Sorted_Array {

    /*
    - 当 l 与 r 的值相同时, r 后移, l 不变
    - 当 l 与 r 的值不同时:
       - 如果 l 与 r 相邻, 双双后移
       - 如果不相邻, 说明之前有重复的值, 可以覆盖
         - l 为重复值的第一位, 所以需要先后移, 即+1
           l 与 r 值交换 / 覆盖
           别忘了后移 r
     */


    /**
     * 简化
     */
    public int removeDuplicates_2(int[] nums) {
        if (nums == null || nums.length == 0) { return 0; }

        int l = 0, r = 1;
        while (r < nums.length) {
            if (nums[l] != nums[r]) {
                // 如果相邻 -> 均进位
                // 不相邻 -> 之间有 duplicate, 需要改写

                l++;
                if (l < r) {
                    nums[l] = nums[r];
                }
            }

            r++;
        }

        return l + 1;
    }

    /**
     * 题目给定已经 sorted
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) { return 0; }

        int l = 0, r = 1;
        while (r < nums.length) {
            if (nums[l] != nums[r]) {
                // 如果相邻 -> 均进位
                // 不相邻 -> 之间有 duplicate, 需要 swap

                if (l + 1 == r) {
                    l++;
                    r++;
                } else {
                    l++;
                     // swap(nums, l, r); 直接覆盖即可, 无需swap
                    nums[l] = nums[r];
                    r++;
                }

            } else {
                r++;
            }
        }

        return l + 1;
    }

}
