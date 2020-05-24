/*
Medium
#Two Pointers, #Interval
 */
package leetcode;

import util.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * 986. Interval List Intersections
 *
 * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 * The intersection of two closed intervals is a set of real numbers that is either empty,
 * or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
 *
 * Example 1:
 * Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 *
 * Note:
 * 0 <= A.length < 1000
 * 0 <= B.length < 1000
 * 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 * NOTE: input types have been changed on April 15, 2019.
 * Please reset to default code definition to get new method signature.
 */
public class _0986_IntervalListIntersections {

    /**
     * 双指针 - 两组值较大的开始值 如果小于等于 较小的结束值, 则说明两者有交集
     * 比较结束后, 移动结束值较小者
     */
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0)
            return new int[0][0];

        List<int[]> res = new ArrayList<>();

        int i = 0, j = 0;
        int startMax = 0, endMin = 0;

        while (i < A.length && j < B.length) {
            startMax = Math.max(A[i][0], B[j][0]);
            endMin = Math.min(A[i][1], B[j][1]);

            if (startMax <= endMin) { // 注意 例子中 [24,25] 和 [25,26] 的交集是 [25, 25]
                res.add(new int[] {startMax, endMin});
            }

            if (A[i][1] == endMin) {
                i++;
            } else {
                j++;
            }
        }


        return res.toArray(new int[0][2]);
        // return res.toArray(new int[res.size()][2]);
        // 两者都可以, 但是前者较快 https://shipilev.net/blog/2016/arrays-wisdom-ancients/#_conclusion
    }




    /**
     * 使用Interval类
     */
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0)
            return new Interval[0];

        List<Interval> res = new ArrayList<>();

        int i = 0, j = 0;
        int startMax, endMin;

        while (i < A.length && j < B.length) {
            Interval intervalA = A[i];
            Interval intervalB = B[j];

            startMax = Math.max(intervalA.start, intervalB.start);
            endMin = Math.min(intervalA.end, intervalB.end);

            if (startMax <= endMin) {
                res.add(new Interval(startMax, endMin));
            }

            if (intervalA.end == endMin) {
                i++;
            } else {
                j++;
            }
        }

        return res.toArray(new Interval[0]);
        // return res.toArray(new Interval[res.size()]);
        // 两者都可以, 但是前者较快 https://shipilev.net/blog/2016/arrays-wisdom-ancients/#_conclusion
    }
}
