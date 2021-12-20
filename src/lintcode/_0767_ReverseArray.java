/*
Easy
#Two Pointers, #Array

 */
package lintcode;

/**
 * 767 · Reverse Array
 *
 * Reverse the given array nums inplace.
 * - Inplace means you can't use extra space.
 *
 * Example 1:
 * Input: nums = [1,2,5]
 * Output: [5,2,1]
 */
public class _0767_ReverseArray {

    public void reverseArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int n = nums.length;

        for (int i = 0, j = n -1; i < j; i++, j--) {
            int xor = nums[i] ^ nums[j];
            nums[i] ^= xor;
            nums[j] ^= xor;
        }
    }
}
