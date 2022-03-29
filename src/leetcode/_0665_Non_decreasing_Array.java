package leetcode;

/**
 * 665. Non-decreasing Array
 * Medium
 * #Array
 * Google, Microsoft
 *
 * Given an array nums with n integers, check if it could become non-decreasing by modifying at most one element.
 *
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).
 *
 * Example 1:
 * Input: nums = [4,2,3]
 * Output: true
 * Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 *
 * Example 2:
 * Input: nums = [4,2,1]
 * Output: false
 * Explanation: You can't get a non-decreasing array by modify at most one element.
 *
 * Challenge: Can you find a solution in O(n) time?
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 10^4
 * -10^5 <= nums[i] <= 10^5
 *
 * lintcode 1099
 */
public class _0665_Non_decreasing_Array {

    /**
     * 参考 https://leetcode.com/problems/non-decreasing-array/discuss/1190763/JS-Python-Java-C++-or-Simple-Solution-w-Visual-Explanation
     *
     * 当遇到 4, 2 时
     * - 如果前一位是 5, 或者 下一位时 1, error count = 2 -> false, 例如
     *    - 5, 4, 2 或者 4, 2, 1
     *
     * - 当前一位, 或者后一位, 至少有一个是在 2至4 的区间之外的, 可以通过降低4 或者 升高2 来达到 true, 例如
     *    - 1, 4, 2, 5 (都在区间外)
     *    - 1, 4, 2, 3 (前者在区间外, 后者在区间内)
     *    - 3, 4, 2, 5 (前者在区间内, 后者在区间外)
     *
     * - 当前一位 与 后一位均在区间内, 必须修改两个值 -> false, 例如
     *    - 3, 4, 2, 3
     *
     * 归纳一下, 当 nums[i - 1] > nums[i] 时, 即 i-1 指向 4, i 指向 2
     * - 如果 error count > 1 -> false
     * - 如果 nums[i - 2] > nums[i] && nums[i - 1] > nums[i + 1] -> false
     *    - 注意检查边界
     *
     * 易错点:
     * - 遍历从 1 开始到 n - 1, 而不是从 0 开始到 n - 2, 例如 [3,4,2,3], exp = false, output = true
     */
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                count++;

                if (count > 1) {
                    return false;
                }

                if (i > 1 && i < nums.length - 1 &&
                        nums[i - 2] > nums[i] && nums[i - 1] > nums[i + 1]) {
                    return false;
                }
            }
        }

        return true;
    }




    /**
     * bug - [3,4,2,3], exp = false, output = true
     * 只记录有多少次 nums[i] > nums[i + 1], 没考虑特殊情况
     */
    public boolean checkPossibility_bug(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                count++;
            }
        }
        return count <= 1;
    }
}
