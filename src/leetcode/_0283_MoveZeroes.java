/*
Easy
#Array, #Two Pointers
 */
package leetcode;

/**
 * 283. Move Zeroes
 *
 * Given an array nums, write a function to move all 0's to the end of it
 * while maintaining the relative order of the non-zero elements.
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 *
 * Note:
 * - You must do this in-place without making a copy of the array.
 * - Minimize the total number of operations.
 */
public class _0283_MoveZeroes {
    /**
     * 双指针 - l指向将被替换的下标, r寻找非0的数值, 找到后, 将其存入l指针处, l和r指针分别向后移动
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2)
            return;

        int l = 0, r = 0;

        // 将r指针处的非0值存入l指针
        while (r < nums.length) {
            if (nums[r] != 0) {
                nums[l++] = nums[r];
            }
            r++;
        }

        // 所有非0值已被找到, 将后续下标均填上0
        while (l < nums.length) {
            nums[l++] = 0;
        }
    }
}
