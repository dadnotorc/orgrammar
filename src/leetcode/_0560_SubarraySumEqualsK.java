/*
Medium
#Array, #Hash Table
 */
package leetcode;

import org.junit.Test;

import java.util.HashMap;

/**
 * 560. Subarray Sum Equals K
 *
 * Given an array of integers and an integer k, you need to find
 * the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 *
 * Note:
 * - The length of the array is in range [1, 20,000].
 * - The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class _0560_SubarraySumEqualsK {

    /**
     * 易错点:
     * 1. 循环从index 0开始, 而不要从1开始. 否则会错过num只有1位数字的可能, 例如test1()
     */
    public int subarraySum(int[] nums, int k) {

        // key = prefixSum, val = 该prefixSum出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 循环开始之前, 已知前缀和为0出现的次数为1, 即什么都不取

        int ans = 0;
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            if (map.containsKey(prefixSum - k)) {
                ans += map.get(prefixSum - k);
            }

            if (map.containsKey(prefixSum)) {
                map.put(prefixSum, map.get(prefixSum) + 1);
            } else {
                map.put(prefixSum, 1);
            }
        }

        return ans;
    }

    @Test
    public void test1() {
        int[] num = {1};
        int k = 0;
        org.junit.Assert.assertEquals(1, subarraySum(num, k));
    }
}
