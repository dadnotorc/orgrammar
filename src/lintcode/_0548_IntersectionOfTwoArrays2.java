/*
Medium
Sort, Two Pointers, Hash Table, Binary Search
Facebook, Salesforce
 */
package lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 548. Intersection of Two Arrays II
 *
 * Given two arrays, write a function to compute their intersection.
 *
 * Notice
 * - Each element in the result should appear as many times as it shows in both arrays.
 * - The result can be in any order.
 *
 * Example1
 * Input:
 * nums1 = [1, 2, 2, 1], nums2 = [2, 2]
 * Output:
 * [2, 2]
 *
 * Example2
 * Input:
 * nums1 = [1, 1, 2], nums2 = [1]
 * Output:
 * [1]
 *
 * Challenge
 * - What if the given array is already sorted? How would you optimize your algorithm?
 * - What if nums1's size is small compared to num2's size? Which algorithm is better?
 * - What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
public class _0548_IntersectionOfTwoArrays2 {

    /**
     * 使用 HashMap
     * 1. 将 nums1 中的数字 即出现次数 加入 hashmap
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0
                || nums2 == null || nums2.length == 0)
            return new int[0];

        // 读取 num1 中所有数字及其出现次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            if (map.containsKey(i))
                map.put(i, map.get(i) + 1);
            else
                map.put(i, 1);
            // 可缩写成 map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // 读取 nums2 中的数字, 如果在 hashmap 中存在, 将其在 hashmap 中的出现次数 - 1, 直到为 0
        ArrayList<Integer> list = new ArrayList<>();
        for (int j : nums2) {
            if (map.containsKey(j) && map.get(j) > 0) {
                list.add(j);
                map.put(j, map.get(j) - 1);
            }
        }

        int[] ans = new int[list.size()];
        for (int i =  0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    @Test
    public void test1() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] exp = {2, 2};
        org.junit.Assert.assertArrayEquals(exp, intersection(nums1, nums2));
    }

    @Test
    public void test2() {
        int[] nums1 = {1, 1, 2};
        int[] nums2 = {1};
        int[] exp = {1};
        org.junit.Assert.assertArrayEquals(exp, intersection(nums1, nums2));
    }
}
