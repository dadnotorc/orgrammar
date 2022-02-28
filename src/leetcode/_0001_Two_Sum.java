package leetcode;

import org.junit.Test;

import java.util.HashMap;

/**
 * 1. Two Sum
 * Easy
 * #Array, #Hash Table
 *
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class _0001_Two_Sum {

    /*
    面试时要确认
    - 答案是否一定存在
    - 答案是否不止一组

    暴力解法 - 先排序, 然后两层循环, 针对每一个数, 看其之后是否存在另一个值, 两者之和等于 target
            - 增强: 因为数组已经排序, 如果当前值 >  target, 则无需继续
            - O(n ^ 2)

    优化解 - HashMap <target - i, index_of_i>
          - 将当前值与 target 的差值记录, 并记录当前值的座标. 之后遇到时, 说明找到了一对
          - O (n)
     */

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) { return new int[0]; }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(nums[i])) {
                return new int[] {map.get(nums[i]), i};
            }

            map.put(target - nums[i], i);
        }

        return new int[0];
    }



    @Test
    public void test1() {
        int[] nums = {2,7,11,15};
        int[] exp = {0,1};

        org.junit.Assert.assertArrayEquals(exp, twoSum(nums, 9));
    }
}
