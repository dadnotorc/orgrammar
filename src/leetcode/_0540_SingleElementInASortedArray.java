/*
Medium
#Binary Search
 */
package leetcode;

/**
 * 540. Single Element in a Sorted Array
 *
 * You are given a sorted array consisting of only integers where every element appears exactly twice,
 * except for one element which appears exactly once. Find this single element that appears only once.
 *
 * Example 1:
 * Input: [1,1,2,3,3,4,4,8,8]
 * Output: 2
 *
 * Example 2:
 * Input: [3,3,7,7,10,11,11]
 * Output: 10
 *
 * Note: Your solution should run in O(log n) time and O(1) space.
 */
public class _0540_SingleElementInASortedArray {

    /**
     * 在第一种写法上简化得来
     */
    public int singleNonDuplicate_2(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l < r) {
            int m = l + (r - l) / 2;
            // if m is even, tmp = m + 1
            // if m is odd, tmp = m - 1
            // tmp = (m & 1) == 0 ? m + 1 : m - 1;
            int tmp = m ^ 1;

            if (nums[m] == nums[tmp]) {
                // if m is even, nums[m] = nums[m+1] -> 说明答案在m右边, ans >= m+2
                // if m is odd, nums[m] = nums[m-1] -> 说明答案也在m右边, ans >= m+1
                // 所以取+1
                l = m + 1;
            } else {
                // 注意, 这里 ans <= m, 所以不能-1
                r = m;
            }
        }

        return nums[l];
    }

    /**
     * 二分法
     *
     * 如果全部数字皆重复, 则下标成对出现:
     * 01 23 45 67 89 -> 偶数下标与其后一位相对, 基数下标与其前一位相等
     *
     * 如果偶数下标与其前一位相等 -> 答案在左边, 且 ans <= m-2
     * 如果偶数下标与其后一位相等 -> 答案在右边, 且 ans >= m+2
     *
     * 如果基数下标与其前一位相等 -> 答案在右边, 且 ans >= m+1
     * 如果基数下标与其后一位相等 -> 答案在左边, 且 ans <= m-1
     */
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l < r) {
            int m = l + (r - l) / 2;

            if (nums[m] != nums[m - 1] && nums[m] != nums[m + 1]) {
                return nums[m];
            } else if ((m & 1) == 0) { // even
                if (nums[m] == nums[m - 1]) {
                    r = m - 2;
                } else {
                    l = m + 2;
                }
            } else { // odd
                if (nums[m] == nums[m - 1]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }

        return nums[l];
    }
}
