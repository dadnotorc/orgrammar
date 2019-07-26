package lintcode;

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
public class _0062 {

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
            if (a[l] < a[mid]) {
                if (a[l] <= target && target <= a[mid]) {
                    r = mid;
                } else {
                    l = mid;
                }
            } else {
                if (a[mid] <= target && target <= a[r]) {
                    l = mid;
                } else {
                    r = mid;
                }
            }
        }

        if (a[l] == target) {return l;}
        if (a[r] == target) {return r;}

        return -1;
    }
}
