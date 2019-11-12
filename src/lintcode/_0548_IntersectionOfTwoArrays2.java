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

    // 解法1 - 使用 HashMap
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0
                || nums2 == null || nums2.length == 0)
            return new int[0];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            if (map.containsKey(i))
                map.put(i, map.get(i) + 1);
            else
                map.put(i, 1);
        }

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
