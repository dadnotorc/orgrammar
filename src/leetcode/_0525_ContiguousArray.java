/*
Medium
#Hash Table
Facebook
 */
package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. Contiguous Array
 *
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 *
 * Note:
 * - The length of the given binary array will not exceed 50,000.
 *
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0,1] is the longest contiguous subarray with equal number of 0 and 1.
 *
 * Example 2:
 *
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0,1] (or [1,0]) is a longest contiguous subarray with equal number of 0 and 1.
 *
 * 注意! 题目只要求subarray中, 0和1的数量相同, 但是没有要求顺序 (不一定要0101 或者 1010)
 *
 * 此题与 lintcode 994. Contiguous Array 相同
 */
public class _0525_ContiguousArray {

    // todo 需要再做一遍来理解
    /**
     * 此题要点:
     * 1. 将0考虑成-1, 求prefixSum. 遇到相同prefixSum时, 表示当前范围内的数字互相抵消 (不加不减)
     * 2. 计算长度时, 长度 = 当前下标 - 当前范围的前一位. 例如0101, 长度 = 3 - (-1)
     * 例如    [ 0,1, 0, 0, 1, 0, 1, 0, 1]
     * 前缀和: [-1,0,-1,-2,-1,-2,-1,-2,-1]
     * map中先放入(0,-1) ->　表明第一次出现前缀和为０的前一位是-1
     */
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;

        int max = 0, prefixSum = 0;
        Map<Integer, Integer> map = new HashMap<>(); // key为prefixSum, val=出现位置的下标
        map.put(0, -1); // 从-1开始 -> 从当前范围的前一位开始

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i] == 0 ? -1 : 1; // 将0变成-1

            if (map.containsKey(prefixSum)) {
                max = Math.max(max, i - map.get(prefixSum));
            } else {
                map.put(prefixSum, i);
            }
        }

        return max;
    }


    @Test
    public void test1() {
        int[] nums = {0,1,0,0,1,0,1,0,1};
        int output = findMaxLength(nums);
        org.junit.Assert.assertEquals(8, output);
    }
}
