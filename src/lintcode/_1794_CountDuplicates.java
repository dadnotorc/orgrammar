/*
Easy

Amazon, Saleforce
 */
package lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 1794 · Count Duplicates
 *
 * Given an array of integers, your task is to find the duplicate numbers in the array.
 * Your program should return all duplicate numbers and order them in order of where they started repeating.
 * For example, the array [5,1,2,2,1,1] has two duplicate digits,1 and 2.
 * The number 1 repeats at index 4, and the number 2 repeats at index 3, so your program should return [2, 1],
 * because the 2 repeats before the 1 repeats.
 *
 * Example 1:
 * Input: nums = [1, 2, 2, 3, 3, 3]
 * Output: [2, 3]
 *
 * Example 2:
 * Input: nums = [1, 2, 3]
 * Output: []
 */
public class _1794_CountDuplicates {

    /**
     * 使用 HashMap 记录 <出现的数字, 该数字的出现次数>
     * 只有当出现次数等于2的时候, 将该数字加入答案中. 小于或者大于的时候, 都忽略
     *
     * 时间 O(n)
     * 空间 O(n)
     */
    public List<Integer> countduplicates(List<Integer> nums) {
        List<Integer> ans = new ArrayList<>();

        if (nums == null || nums.size() < 2) {
            return ans;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            if (map.get(i) == 2) { // 只考虑第一次重复的情况.   如果写成 >= 2, 就会把之后的重复也错误的写进答案中
                ans.add(i);
            }
        }

        return ans;
    }
}
