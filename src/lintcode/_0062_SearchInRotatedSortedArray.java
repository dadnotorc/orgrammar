/*
Medium
#Sorted Array, #Array, #Binary Search
Facebook, LinkedIn, Microsoft, Uber
 */
package lintcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 62. Search in Rotated Sorted Array
 * Medium
 * LinkedIn, Facebook, Uber, Microsoft
 *
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * You are given a target value to search. If found in the array return its
 *  index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Example 1:
 * Input: [4, 5, 1, 2, 3] and target=1,
 * Output: 2.
 *
 * Example 2:
 * Input: [4, 5, 1, 2, 3] and target=0,
 * Output: -1.
 *
 * Challenge
 * - O(logN) time
 *
 * 假设, 数组为上升数组
 */
public class _0062_SearchInRotatedSortedArray {

    public int search(int[] a, int target) {
        if (a == null || a.length == 0) {
            return -1;
        }

        int l = 0, r = a.length - 1;
        int mid;

        while (l + 1 < r) {
            mid = l + (r - l) / 2;
            if (a[mid] == target) {
                return mid;
            }
            if (a[l] < a[mid]) { // l与mid同侧
                if (a[l] <= target && target <= a[mid]) { // target在l与mid之间
                    r = mid;
                } else { // target在mid与r之间
                    l = mid;
                }
            } else { //l与mid异侧
                if (a[mid] <= target && target <= a[r]) { // target在mid与r之间
                    l = mid;
                } else { // // target在l与mid之间
                    r = mid;
                }
            }
        }

        if (a[l] == target) {return l;}
        if (a[r] == target) {return r;}

        return -1;
    }

    @Test
    public void test1() {
        int[] a = {4,5,6,7,8,1,2,3};
        int t = 1;
        int expected = 5;
        int actual = search(a, t);
        assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        int[] a = {4,5,6,7,8,1,2,3};
        int t = 0;
        int expected = -1;
        int actual = search(a, t);
        assertEquals(expected, actual);
    }

    @Test
    public void test3() {
        int[] a = {4};
        int t = 4;
        int expected = 0;
        int actual = search(a, t);
        assertEquals(expected, actual);
    }

    @Test
    public void test4() {
        int[] a = {5,4};
        int t = 5;
        int expected = 0;
        int actual = search(a, t);
        assertEquals(expected, actual);
    }

    @Test
    public void test5() {
        int[] a = {5,4};
        int t = 4;
        int expected = 1;
        int actual = search(a, t);
        assertEquals(expected, actual);
    }

    @Test
    public void test6() {
        int[] a = {2,5};
        int t = 2;
        int expected = 0;
        int actual = search(a, t);
        assertEquals(expected, actual);
    }

    public void test7() {
        int[] a = {2,5};
        int t = 5;
        int expected = 1;
        int actual = search(a, t);
        assertEquals(expected, actual);
    }

//    @Test
//    public void test8() {
//        int[] a = {3,2,1,8,7,6,5,4};
//        int t = 0;
//        int expected = -1;
//        int actual = search(a, t);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void test9() {
//        int[] a = {3,2,1,8,7,6,5,4};
//        int t = 1;
//        int expected = 2;
//        int actual = search(a, t);
//        assertEquals(expected, actual);
//    }

    @Test
    public void test10() {
        int[] a = {6,8,9,1,3,5};
        int t = 5;
        int expected = 5;
        int actual = search(a, t);
        assertEquals(expected, actual);
    }
}
