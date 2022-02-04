package leetcode;

/**
 * 152. Maximum Product Subarray
 * Medium
 *
 *
 * Given an integer array nums, find a contiguous non-empty subarray within the array
 * that has the largest product, and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 * Example 1:
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 *
 * Example 2:
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * DP 解法 很类似 leetcode 1567. Maximum Length of Subarray With Positive Product
 */
public class _0152_Maximum_Product_Subarray {

    /**
     * DP - 简化, 另一种写法
     */
    public int maxProduct_2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ans = nums[0];
        int max = ans, min = ans;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                // swap max with min
                int temp = max;
                max = min;
                min = temp;
            }

            max = Math.max(max * nums[i], nums[i]);
            min = Math.min(min * nums[i], nums[i]);

            ans = Math.max(max, ans);
        }

        return ans;
    }


    /**
     * DP 简化, 减少空间消耗
     */
    public int maxProduct_1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ans = nums[0];
        int max = Math.max(nums[0], 0);
        int min = Math.min(nums[0], 0);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                max = Math.max(nums[i] * max, nums[i]);
                min = nums[i] * min;
            } else {
                int temp = max; // 这步要小心, 错了好几次了!
                max = nums[i] * min;
                min = Math.min(nums[i] * temp, nums[i]);
            }

            ans = Math.max(max, ans);
        }

        return ans;
    }




    /**
     * DP - 使用两个数组, 分别记录到达 i 位置时, 最大的 positive product, 以及 最小的 negative product
     * 时间 - O(n), 空间 O(2 x n)
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] pos = new int[nums.length];
        int[] neg = new int[nums.length];

        if (nums[0] > 0 ) {
            pos[0] = nums[0];
        } else {
            neg[0] = nums[0];
        }

        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                pos[i] = Math.max(nums[i] * pos[i - 1], nums[i]);
                neg[i] = nums[i] * neg[i - 1];
            } else {
                pos[i] = nums[i] * neg[i - 1];
                neg[i] = Math.min(nums[i] * pos[i - 1], nums[i]);
            }

            ans = Math.max(Math.max(pos[i], neg[i]), ans);
        }

        return ans;
    }
}
