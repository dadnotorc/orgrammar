package leetcode;

import util.MountainArray;

/**
 * 1095. Find in Mountain Array
 * Hard
 *
 * (This problem is an interactive problem.)
 *
 * You may recall that an array A is a mountain array if and only if:
 * - A.length >= 3
 * - There exists some i with 0 < i < A.length - 1 such that:
 *   - A[0] < A[1] < ... A[i-1] < A[i]
 *   - A[i] > A[i+1] > ... > A[A.length - 1]
 *
 * Given a mountain array mountainArr, return the minimum index such that
 *  mountainArr.get(index) == target.  If such an index doesn't exist, return -1.
 *
 * You can't access the mountain array directly.
 * You may only access the array using a MountainArray interface:
 * - MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * - MountainArray.length() returns the length of the array.
 *
 * This is MountainArray's API interface.
 * You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 *
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.
 * Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * Constraints:
 * 1. 3 <= mountain_arr.length() <= 10000
 * 2. 0 <= target <= 10^9
 * 3. 0 <= mountain_arr.get(index) <= 10^9
 *
 * 先用一次二分法找到山峰, 峰值大于左右.
 * 之后继续用二分法, 先在山峰左侧(递增区间)查找, 如找到则返回答案.
 * 如未找到, 则继续在山峰右侧(递减区间)查找.
 *
 * 时间复杂度: O(log n). 二分法需要 O(log n), 三次二分法需要3倍, 因为是常数, 所以仍然是O(log n)
 * 空间复杂度: O(1).
 */
public class _1095 {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peak = findPeak(mountainArr);

        // 先在山峰左侧找 (always return the minimum index if exist)
        int l = 0, r = peak;

        while (l <= r) {
            int m = (l + r) / 2;
            if (mountainArr.get(m) < target) {
                l = m + 1;
            } else if (mountainArr.get(m) > target) {
                r = m - 1;
            } else {
                return m;
            }
        }

        // 后在山峰后侧找
        l = peak;
        r = mountainArr.length() - 1;

        while (l <= r) {
            int m = (l + r) / 2;
            if (mountainArr.get(m) > target) {
                l = m + 1;
            } else if (mountainArr.get(m) < target) {
                r = m - 1;
            } else {
                return m;
            }
        }

        return -1;
    }

    private int findPeak(MountainArray mnt) {
        int l = 1, r = mnt.length() - 2; // 因为答案在之间, 而且这样保证不会出界
        while (l + 1 < r) {
            int m = (l + r) / 2;
            if (mnt.get(m) < mnt.get(m + 1)) { // uphill
                l = m + 1;
            } else {
                r = m;
            }
        }
        if (mnt.get(l) < mnt.get(r)) {
            return r;
        } else {
            return l;
        }
    }
}
