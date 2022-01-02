/*
Easy
#Two Pointers, #Hash Table
Facebook, Uber
 */
package lintcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import static org.junit.Assert.assertArrayEquals;

/**
 * 547. Intersection of Two Arrays
 *
 * Given two arrays, write a function to compute their intersection.
 *
 * - Each element in the result must be unique.
 * - The order of the results needs to be ascending
 *
 * Example 1:
 * Input: nums1 = [1, 2, 2, 1], nums2 = [2, 2],
 * Output: [2].
 *
 * Example 2:
 * Input: nums1 = [1, 2], nums2 = [2],
 * Output: [2].
 *
 * Challenge
 * - Can you implement it in three different algorithms?
 */
public class _0547_IntersectionOfTwoArrays {

    /**
     * 不能简单地把两个数组中的数字都丢进 HashSet 中, 因为这样没有检查是否有交集
     *
     * 不过可以用两个 HashSet, 参照 intersection_3
     */


    /**
     * 1. sort nums1 - 为了完成 binary search
     * 2. binary search - 确定 nums2 中的数字是否存在于 nums1 中
     *
     * 时间: O(nlogn) + O(n) + O(n), sorting + 1st loop + 2nd loop
     * 空间: O(n), n = Math.min(nums1.length, nums2.length)
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 ||
                nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);

        HashSet<Integer> set = new HashSet<>(); // set 中的数字 就是两个数组的交集

        for (int b : nums2) {
            if (!set.contains(b)) {
                if (binarySearch(nums1, b)) {
                    set.add(b);
                }
            }
        }

        int[] ans = new int[set.size()];
        int index = 0;
        for (int num : set) {
            ans[index++] = num;
        }

        return ans;
    }

    // check if b exists in arr[]
    public boolean binarySearch(int[] arr, int b) {
        int l = 0, r = arr.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == b) {
                return true;
            } else if (arr[mid] < b) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return arr[l] == b;
    }


    /**
     * sort both arrays, then merge numbers that exist in both arrays
     */
    public int[] intersection_1(int[] nums1, int[] nums2) {
        // write your code here
        if (nums1 == null || nums1.length == 0 ||
                nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        HashSet<Integer> set = new HashSet<>(); // avoid duplicate
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                set.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        Iterator<Integer> it = set.iterator();
        int[] ans = new int[set.size()];
        int index = 0;
        while (it.hasNext()) {
            ans[index++] = it.next();
        }

        return ans;
    }


    /**
     * sort and merge - 2
     */
    public int[] intersection_2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 ||
                nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        int[] tmp = new int[Math.min(nums1.length, nums2.length)];
        int tmpIndex = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                if (tmpIndex == 0 || tmp[tmpIndex - 1] != nums1[i]) {
                    tmp[tmpIndex++] = nums1[i];
                }
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] ans = new int[tmpIndex];
        for (int index = 0; index < tmpIndex; index++) {
            ans[index] = tmp[index];
        }

        return ans;
    }


    /**
     * hash map
     */
    public int[] intersection_3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 ||
                nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        HashSet<Integer> set1 = new HashSet<>();
        for (int a : nums1) {
            set1.add(a);
        }

        HashSet<Integer> set2 = new HashSet<>();
        for (int b : nums2) {
            if (set1.contains(b)) { // nums2 中的数字也存在于 nums1
                set2.add(b);
            }
        }

        int[] ans = new int[set2.size()];
        int index = 0;
        for (Integer i : set2) {
            ans[index++] = i;
        }

        return ans;
    }

    @Test
    public void test1() {
        int[] a = {1,2,2,1};
        int[] b = {2,2};
        int[] expected = {2};
        int[] actual = new _0547_IntersectionOfTwoArrays().intersection(a, b);
        int[] actual1 = new _0547_IntersectionOfTwoArrays().intersection_1(a, b);
        int[] actual2 = new _0547_IntersectionOfTwoArrays().intersection_2(a, b);
        int[] actual3 = new _0547_IntersectionOfTwoArrays().intersection_3(a, b);
        assertArrayEquals(expected, actual);
        assertArrayEquals(expected, actual1);
        assertArrayEquals(expected, actual2);
        assertArrayEquals(expected, actual3);
    }

    @Test
    public void test2() {
        int[] a = {1,2};
        int[] b = {2};
        int[] expected = {2};
        int[] actual = new _0547_IntersectionOfTwoArrays().intersection(a, b);
        int[] actual1 = new _0547_IntersectionOfTwoArrays().intersection_1(a, b);
        int[] actual2 = new _0547_IntersectionOfTwoArrays().intersection_2(a, b);
        int[] actual3 = new _0547_IntersectionOfTwoArrays().intersection_3(a, b);
        assertArrayEquals(expected, actual);
        assertArrayEquals(expected, actual1);
        assertArrayEquals(expected, actual2);
        assertArrayEquals(expected, actual3);
    }

    @Test
    public void test3() {
        int[] a = {7,3,5,9,7,3};
        int[] b = {5,3,5,8};
        int[] expected = {3,5};
        int[] actual = new _0547_IntersectionOfTwoArrays().intersection(a, b);
        int[] actual1 = new _0547_IntersectionOfTwoArrays().intersection_1(a, b);
        int[] actual2 = new _0547_IntersectionOfTwoArrays().intersection_2(a, b);
        int[] actual3 = new _0547_IntersectionOfTwoArrays().intersection_3(a, b);
        assertArrayEquals(expected, actual);
        assertArrayEquals(expected, actual1);
        assertArrayEquals(expected, actual2);
        assertArrayEquals(expected, actual3);
    }
}
