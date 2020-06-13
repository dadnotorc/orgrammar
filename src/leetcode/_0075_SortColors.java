/*
Medium
#Array, #Two Pointers, #Sort
 */
package leetcode;

import org.junit.Test;

/**
 * 75. Sort Colors
 *
 * Given an array with n objects colored red, white or blue, sort them in-place so that
 * objects of the same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Follow up:
 * - A rather straight forward solution is a two-pass algorithm using counting sort.
 *   First, iterate the array counting number of 0's, 1's, and 2's,
 *   then overwrite array with total number of 0's, then 1's and followed by 2's.
 * - Could you come up with a one-pass algorithm using only constant space?
 */
public class _0075_SortColors {

    /**
     * 三根指针, 指向首/尾/中间, 首指针用于接受0, 尾指针用于接受2, 中指针遍历, 找寻0或者2, 提供给首尾指针
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int low = 0, high = nums.length - 1, i = 0;
        while (i <= high) { // 注意 这里需要用 <=
            if (nums[i] == 0) { // 将low指针与i指针的值互换
                nums[i] = nums[low];
                nums[low] = 0;
                low++;
            } else if (nums[i] == 2) { // 将high指针与i指针的值互换
                nums[i] = nums[high];
                nums[high] = 2;
                high--;
                i--; // 这里需要i--, 抵消之后的++, 因为i指针的新值可能为0或者2, 下一层循环的时候, 我们需要再检查一次
            }

            i++;
        }
    }
}
