/*
Medium
Two Pointers, Binary Search
Amazon, Google
 */
package lintcode;

import org.junit.Test;

/**
 * 460. Find K Closest Elements
 *
 * Given target, a non-negative integer k and an integer array A sorted in
 * ascending order, find the k closest numbers to target in A, sorted in
 * ascending order by the difference between the number and target.
 * Otherwise, sorted in ascending order by number if the difference is same.
 *
 * Notice
 * - The value k is a non-negative integer and will always be smaller than
 *    the length of the sorted array.
 * - Length of the given array is positive and will not exceed 10^4
 * - Absolute value of elements in the array will not exceed 10^4
 *
 * Example 1:
 * Input: A = [1, 2, 3], target = 2, k = 3
 * Output: [2, 1, 3]
 *
 * Example 2:
 * Input: A = [1, 4, 6, 8], target = 3, k = 3
 * Output: [4, 1, 6]
 *
 * Challenge
 * - O(logn + k) time
 */
public class _0460_FindKClosestElements {

    /**
     * 先二分选最接近的数值, 如有多个同样接近的, 选最小的
     * 再在周围找 k 个最接近的值
     */
    public int[] kClosestNumbers(int[] nums, int target, int k) {
        if (k == 0) // 注意 k 只是 non negative
            return new int[0];

        int[] ans = new int[k];

        int closestIndex = findClosest(nums, target);
        ans[0] = nums[closestIndex];

        int l = closestIndex - 1;
        int r = closestIndex + 1;
        for (int i = 1; i < k; i++) {
            if (l < 0)
                ans[i] = nums[r++];
            else if (r >= nums.length)
                ans[i] = nums[l--];
            else
                ans[i] = (target - nums[l]) <= (nums[r] - target) ? nums[l--] : nums[r++];
        }

        return ans;
    }

    // return the index of the closest number to target - binary search
    private int findClosest(int[] nums, int target) {
        int toReturn = nums.length;
        int min = Integer.MAX_VALUE;
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                if ((target - nums[mid]) < min) {
                    min = target - nums[mid];
                    toReturn = mid;
                } else if (target - nums[mid] == min) {
                    toReturn = Math.min(toReturn, mid);
                }
                l = mid + 1;
            } else { // A[mid] > target
                if ((nums[mid] - target) < min) {
                    min = nums[mid] - target;
                    toReturn = mid;
                } else if ((nums[mid] - target) == min) {
                    toReturn = Math.min(toReturn, mid);
                }
                r = mid - 1;
            }
        }

        return toReturn;
    }

    @Test
    public void test0() {
        int[] A = {1, 2, 3};
        int[] exp = {};
        org.junit.Assert.assertArrayEquals(exp, kClosestNumbers(A, 2, 0));
    }

    @Test
    public void test1() {
        int[] A = {1, 2, 3};
        int[] exp = {2, 1, 3};
        org.junit.Assert.assertArrayEquals(exp, kClosestNumbers(A, 2, 3));
    }

    @Test
    public void test2() {
        int[] A = {1, 4, 6, 8};
        int[] exp = {4, 1, 6};
        org.junit.Assert.assertArrayEquals(exp, kClosestNumbers(A, 3, 3));
    }

    @Test
    public void test3() {
        int[] A = {1,4,6,10,20};
        int[] exp = {20,10,6,4};
        org.junit.Assert.assertArrayEquals(exp, kClosestNumbers(A, 21, 4));
    }

    @Test
    public void test4() {
        int[] A = {1,4,8,12,16,28,38};
        int[] exp = {12,8,16,4};
        org.junit.Assert.assertArrayEquals(exp, kClosestNumbers(A, 12, 4));
    }
}
