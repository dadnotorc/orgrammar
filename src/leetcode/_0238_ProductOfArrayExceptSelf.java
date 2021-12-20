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
 *
 * 相同 lintcode 50 Product of Array Exclude Itself
 */
public class _0238_ProductOfArrayExceptSelf {

    /**
     * 1 | _ x 2 x 3 x 4
     * 2 | 1 x _ x 3 x 4
     * 3 | 1 x 2 x _ x 4
     * 4 | 1 x 2 x 3 x _
     *
     * 将空格处当做1. 先从上向下做阶乘, 再从下向上.
     *
     * 注意, 从下向上时, 使用tmp记录之前一层的积
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        ans[0] = 1;

        // 先做左下三角部分， 后做右上三角部分

        for (int i = 1; i < nums.length; i++) { // 从第二位开始到末尾
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        int tmp = 1; // 注意, 从下向上时, 使用tmp记录 (右上部分每层的乘积)

        for (int j = nums.length - 2; j >= 0; j--) { // 从倒数第二层开始, 而不是倒数第一层， 因为最后一层已经算好了
            // 这里tmp部分记录右端的乘积， ans[j]记录左端的乘积, 两者相乘得到当前的值
            tmp *= nums[j + 1];
            ans[j] *= tmp;
        }

        return ans;
    }
}
