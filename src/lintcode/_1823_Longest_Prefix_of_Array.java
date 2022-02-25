package lintcode;

/**
 * 1823 · Longest Prefix of Array
 *
 * Given two positive integers X and Y, and an array nums of positive integers.
 * We need to find the longest prefix index which contains an equal number of X and Y，
 * The number of X in the longest prefix must appear at least one time, Return to the index.
 * Return the maximum index of largest prefix if exist, otherwise return -1.
 *
 *
 * The length of nums within range: [0, 1000000]
 * nums[i], X and Y within range: [1, 1 000000]
 *
 * Example 1:
 * Input: X = 2, Y = 4, nums: [1, 2, 3, 4, 4, 3]
 * Output: 3
 * Explanation: The longest prefix with same number of occurrences of 2 and 4 is: {1, 2, 3, 4}, so you should return 3
 *
 * Example 2:
 * Input: X = 7, Y = 42, nums = [7, 42, 5, 6, 42, 8, 7, 5, 3, 6, 7]
 * Output : 9
 * Explanation：The longest prefix with same number of occurrences of 7 and 42 is: {7, 42, 5, 6, 42, 8, 7, 5, 3, 6}, so you should return 9
 *
 * Example 3:
 * Input: X = 1, Y = 10, nums: [2, 3, 1]
 * Output: -1
 * Explanation: There are no prefix makes both 1 and 10 appear and  same number of occurrences
 */
public class _1823_Longest_Prefix_of_Array {

    /**
     * 从左往右遍历数组。分别记录 X 和 Y 出现的次数。并且判断两者出现次数是否相等, 且不等于 0
     *
     * 复杂度分析
     * 时间复杂度：O（n）
     * n为数组长度。
     *
     * 空间复杂度：O(1)
     */
    public int LongestPrefix(int X, int Y, int[] nums) {
        if (nums == null || nums.length < 2) { return -1; }

        int ans = -1;
        int x_ct = 0, y_ct = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == X) { x_ct++; }
            if (nums[i] == Y) { y_ct++; }

            if (x_ct == y_ct && x_ct != 0) {
                ans = i;
            }
        }

        return ans;
    }


    /**
     * 有 bug, 无法应对 x = 1, y = 2, nums = [1,1,1,1,1,2]
     */
    public int LongestPrefix_bug(int X, int Y, int[] nums) {
        if (nums == null || nums.length < 2) { return -1; }

        int ans = -1;
        int x_ct = 0, y_ct = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == X) { x_ct++; }
            if (nums[i] == Y) { y_ct++; }

            if (x_ct == y_ct) {
                ans = i;
            }
        }

        return x_ct == 0 || y_ct == 0 ? -1 : ans;
    }
}
