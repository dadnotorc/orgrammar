/*
Naive
#Enumerate, #Array

 */
package lintcode;

import java.util.List;

/**
 * 297 · Find the maximum
 *
 * Find the maximum of n numbers.
 * - We promise that all numbers in the list are in the range of int.
 *
 * Example 1:
 * Input：[1, 2, 3, 4, 5]
 * Output：5
 */
public class _0297_Findmaximum {

    public int maxNum(List<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        int ans = Integer.MIN_VALUE;
        for (int num : nums) {
            ans = Math.max(ans, num);
        }

        return ans;
    }
}
