/*
Easy
Array, Hash Table
 */
package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

/**
 * 1. Two Sum
 * Given an array of integers, return indices of the two numbers such that
 * they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 *
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class _0001_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) { return new int[0]; }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        return new int[0];
    }

    @Test
    public void test1() {
        int[] nums = {2,7,11,15};
        int[] exp = {0,1};
        int[] act = twoSum(nums, 9);

        assertTrue(Arrays.equals(exp, act));
    }
}
