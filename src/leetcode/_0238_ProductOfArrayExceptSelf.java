/*
Medium
#Array
 */
package leetcode;

/**
 * 238. Product of Array Except Self
 *
 * Given an array nums of n integers where n > 1,  return an array output such that
 * output[i] is equal to the product of all the elements of nums except nums[i].
 *
 * Example:
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Constraint: It's guaranteed that the product of the elements of any prefix or suffix
 * of the array (including the whole array) fits in a 32 bit integer.
 *
 * Note: Please solve it without division and in O(n).
 *
 * Follow up:
 * Could you solve it with constant space complexity?
 * (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class _0238_ProductOfArrayExceptSelf {

    /**
     * 1 |     2 x 3 x 4
     * 2 | 1 x     3 x 4
     * 3 | 1 x 2 x     4
     * 4 | 1 x 2 x 3
     *
     * 将空格处当做1. 先从上向下做阶乘, 再从下向上.
     *
     * 注意, 从下向上时, 使用tmp记录之前一层的积
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        ans[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        int tmp = 1; // 注意, 从下向上时, 使用tmp记录 (右上部分每层的乘积)

        for (int j = nums.length - 2; j >= 0; j--) { // 从倒数第二层开始, 而不是第一层
            tmp *= nums[j + 1];
            ans[j] *= tmp;
        }

        return ans;
    }
}
