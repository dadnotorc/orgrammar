package leetcode;

/**
 * 238. Product of Array Except Self
 * Medium
 * #Array, #Prefix Sum
 *
 * Given an integer array nums, return an array answer such that
 * answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 * Constraints:
 * 2 <= nums.length <= 10^5
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity?
 * (The output array does not count as extra space for space complexity analysis.)
 *
 * 相同 lintcode 50 Product of Array Exclude Itself
 */
public class _0238_Product_Of_Array_Except_Self {

    /**
     * a | 1 * b * c * d
     * b | a * 1 * c * d
     * c | a * b * 1 * d
     * d | a * b * c * 1
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
