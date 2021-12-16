/*
Easy
#Two Pointers, #Array
Facebook
 */
package lintcode;

import javax.swing.plaf.InsetsUIResource;
import java.nio.IntBuffer;

/**
 * 539 · Move Zeroes
 *
 * Given an array nums, write a function to move all 0's to the end of it
 * while maintaining the relative order of the non-zero elements.
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 *
 * Example 1:
 * Input: nums = [0, 1, 0, 3, 12],
 * Output: [1, 3, 12, 0, 0].
 *
 * Example 2:
 * Input: nums = [0, 0, 0, 3, 1],
 * Output: [3, 1, 0, 0, 0].
 */
public class _0539_MoveZeroes {

    /**
     *
     */
    public void moveZeroes_2(int[] nums) {
        if (nums == null || nums.length < 2)
            return;

        int l = 0, r = 0;
        while (r < nums.length) {
//            if (nums[r] != 0 && l != r) {   不能这么写
            if (nums[r] != 0) {
                // 遇到非0数赋值给新数组指针指向的位置
                if (l != r) {
                    nums[l] = nums[r];
                }
                l++;
            }
            r++;
        }

        while (l < nums.length) { // 若新数组指针还未指向尾部，将剩余数组赋值为0
            nums[l] = 0;
            l++;
        }
    }

    /**
     *
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2)
            return;

        int l = 0;
        while (l < nums.length - 1) {
            if (nums[l] == 0) {
                int r = l + 1;
                while (r < nums.length) {
                    if (nums[r] != 0) {
                        swap(nums, l, r);
                        break;
                    }
                    r++;
                }
                if (r == nums.length) { // l 及其右边全为 0
                    break;
                }
            }
            l++;
        }
    }


    /**
     *
     */
    public void moveZeroes_3(int[] nums) {
        if (nums == null || nums.length < 2)
            return;

        int l = 0, r = 1;
        while (l < nums.length - 1) {
            if (nums[l] == 0) {
                r = Math.max(r, l + 1);
                while (r < nums.length) {
                    if (nums[r] != 0) {
                        swap(nums, l, r);
                        break;
                    }
                    r++;
                }
                if (r == nums.length) { // l 及其右边全为 0
                    break;
                }
            }
            l++;
        }
    }

    public void swap(int[] nums, int l, int r) {
        int xor = nums[l] ^ nums[r];
        nums[l] ^= xor;
        nums[r] ^= xor;
    }

}
