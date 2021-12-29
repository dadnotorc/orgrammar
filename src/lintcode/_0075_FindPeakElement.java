/*
Medium
#Binary Search
 */
package lintcode;

/**
 * 75 · Find Peak Element
 *
 * There is an integer array which has the following features:
 * - The numbers in adjacent positions are different.
 * - A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
 *
 * We define a position P is a peak if:
 * - A[P] > A[P-1] && A[P] > A[P+1]
 * Find a peak element in this array. Return the index of the peak.
 * - It's guaranteed the array has at least one peak.
 * - The array may contain multiple peeks, find any of them.
 * - The array has at least 3 numbers in it.
 *
 * Example 1:
 * Input: A = [1, 2, 1, 3, 4, 5, 7, 6]
 * Output: 1
 * Explanation: Returns the index of any peak element. 6 is also correct.
 *
 * Example 2:
 * Input: A = [1,2,3,4,1]
 * Output: 3
 *
 * Challenge
 * - Time complexity O(logN) - 二分
 *
 * leetcode 162. Find Peak Element
 */
public class _0075_FindPeakElement {

    public int findPeak(int[] A) {
        int l = 0, r = A.length - 1;

        // 到 l 与 r 相邻时, 结束 while 循环
        while (l  + 1 < r) { // 必须 l + 1 < r, 不能 l < r. 例如 A = [1,2,1,2,3,1]
            int mid = l + (r - l) / 2;

            // 与 A[P] > A[P-1] && A[P] > A[P+1] 相反
            if (A[mid] < A[mid - 1]) { // 在下坡, 需要往左, 即 r 向左移动
                r = mid - 1; // 也可以不 - 1
            } else if (A[mid] < A[mid + 1]) { // 在上坡, 需要向右, 即 l 向右移动
                l = mid + 1; // 也可以不 + 1
            } else {
                return mid;
            }
        }

        return A[l] > A[r] ? l : r;
    }
}
