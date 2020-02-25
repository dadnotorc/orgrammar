/*
Medium
#Two Pointers, #Array, #Forward-Backward Traversal
Airbnb, Amazon, Apple, Google, Twitter
FAQ+
 */
package lintcode;

/**
 * 363. Trapping Rain Water
 *
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 *
 * Example 1:
 * Input: [0,1,0]
 * Output: 0
 *
 * Example 2:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * Challenge
 * - O(n) time and O(1) memory
 * - O(n) time and O(n) memory is also acceptable.
 */
public class _0363_TrappingRainWater {

    /**
     * 初始: 左右指针 l 和 r 分别指向数组首尾, leftMax和rightMax分别记录从左右向中间移动时, 各自的最大值(最高点)
     * 循环: l 不能超过 r
     * a) 比较指针所指当前值 height[l] 与 heights[r]
     *    * height[l] < heights[r]: 左边地势较低, 可能有积水, 水量为 leftMax-heights[l].
     *                              移动左指针, 并更新leftMax
     *    * 否则: 右边地势较低, 可能有积水, 水量为 rightMax-heights[r]. 移动右指针, 并更新rightMax
     */
    /* 另一种写法, 速度较快 */
    public int trapRainWater_2(int[] heights) {
        if (heights == null || heights.length < 3)
            return 0;

        int l = 1, r = heights.length - 2, ans = 0; // 左右指针从两头的第二位开始
        int leftMax = heights[l - 1], rightMax = heights[r + 1]; // leftMax, rightMax 从两头开始

        while (l <= r) { // 用 <= : {1,0,1}, len=3, 初始时 l = 1 = r
            if (leftMax < rightMax) {
                if (leftMax <= heights[l]) {
                    leftMax = heights[l];
                } else {
                    ans += leftMax - heights[l];
                }
                l++;

            } else {
                if (rightMax <= heights[r]) {
                    rightMax = heights[r];
                } else {
                    ans += rightMax - heights[r];
                }
                r--;

            }
        }

        return ans;
    }



    public int trapRainWater(int[] heights) {
        if (heights == null || heights.length < 3)
            return 0;

        int l = 0, r = heights.length - 1, ans = 0;
        int leftMax = heights[l], rightMax = heights[r];

        while (l < r) {
            if (heights[l] < heights[r]) {
                ans += leftMax - heights[l];
                l++;
                leftMax = Math.max(leftMax, heights[l]);
            } else {
                ans += rightMax - heights[r];
                r--;
                rightMax = Math.max(rightMax, heights[r]);
            }
        }

        return ans;
    }




}
