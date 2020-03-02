/*
Medium
#Array, #Two Pointers
Amazon
 */
package lintcode;

/**
 * 383. Container With Most Water
 *
 * Given n non-negative integers a1, a2, ..., an, where each represents a point
 * at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
 * of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis
 * forms a container, such that the container contains the most water.
 *
 * Notice
 * - You may not slant the container.
 *
 * Example
 * Example 1:
 *
 * Input: [1, 3, 2]
 * Output: 2
 * Explanation:
 * Selecting a1, a2, capacity is 1 * 1 = 1
 * Selecting a1, a3, capacity is 1 * 2 = 2
 * Selecting a2, a3, capacity is 2 * 1 = 2
 * Example 2:
 *
 * Input: [1, 3, 2, 2]
 * Output: 4
 * Explanation:
 * Selecting a1, a2, capacity is 1 * 1 = 1
 * Selecting a1, a3, capacity is 1 * 2 = 2
 * Selecting a1, a4, capacity is 1 * 3 = 3
 * Selecting a2, a3, capacity is 2 * 1 = 2
 * Selecting a2, a4, capacity is 2 * 2 = 4
 * Selecting a3, a4, capacity is 2 * 1 = 2
 */
public class _0383_ContainerWithMostWater {


    /**
     * 双指针, 左右指针分居数组两头, 纷纷向中间移动, 每次移动较矮的一头
     * 保留较高的一头不动的原因是, 我们希望找到左右指针之间较大的面积 = 较低一头的高度 * 距离
     * 如果移动较高的一头, 得到的面积只会更少, 因为较低一头的高度只会不变或更低, 距离只会减少
     *
     * time:  O(n)
     * space: O(1)
     */
    public int maxArea_2(int[] heights) {
        if (heights == null || heights.length < 2)
            return 0;

        int ans = 0;
        int l = 0, r = heights.length - 1;

        while (l < r) {
            int curVol = Math.min(heights[l], heights[r]) * (r - l);
            ans = Math.max(ans, curVol);

            if (heights[l] < heights[r]) {
                l++;
            } else {
                r--;
            }
        }

        return ans;
    }



    /**
     * 双指针, 两层循环, 右指针每移动一次, 左指针从头移动到右指针之前
     *
     * time:  O((1 + (n-1)) * (n-1) / 2) = O(n^2)
     * space: O(1)
     */
    public int maxArea_1(int[] heights) {
        if (heights == null || heights.length < 2)
            return 0;

        int ans = 0;

        for (int r = 1; r < heights.length; r++) {
            for (int l = 0; l < r; l++) {
                int curVol = Math.min(heights[l], heights[r]) * (r - l);
                ans = Math.max(ans, curVol);
            }
        }

        return ans;
    }
}
