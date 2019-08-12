/*
Medium
Segment Tree, Binary Search
 */
package lintcode.segmenttree;

import util.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * 206. Interval Sum
 * Given an integer array (index from 0 to n-1, where n is the size of this
 * array), and an query list. Each query has two integers [start, end].
 * For each query, calculate the sum number between index start and end in
 * the given array, return the result list.
 *
 * Example 1:
 * Input: array = [1,2,7,8,5],  queries = [(0,4),(1,2),(2,4)]
 * Output: [23,9,20]
 *
 * Example 2:
 * Input: array : [4,3,1,2],  queries : [(1,2),(0,2)]
 * Output: [4,8]
 *
 * Challenge: O(logN) time for each query
 */
public class _0206_IntervalSum {

    public List<Long> intervalSum_SegmentTree(int[] A, List<Interval> queries) {
        List<Long> ans = new ArrayList<>(queries.size());

        return ans;
    }

    /* ~~~~~ */

    // 使用前缀和数组
    public List<Long> intervalSum_PrefixSum(int[] A, List<Interval> queries) {
        List<Long> ans = new ArrayList<>(queries.size());

        if (A == null || A.length == 0) { return ans; }

        long[] prefixSums = getPrefixSums(A);

        for (Interval interval : queries) {
            if(isValid(prefixSums.length, interval)) {
                ans.add(query(prefixSums, interval.start, interval.end));
            }
        }

        return ans;
    }

    /**
     * time = O(n)
     */
    private long[] getPrefixSums(int[] A) {
        long[] sums = new long[A.length];
        sums[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            sums[i] = sums[i - 1] + A[i];
        }
        return sums;
    }

    /**
     * 查询[0, end] -> 直接返回前缀和数组中 end 指针处的前缀和
     * 查询[i, j] -> 返回 j 处前缀和减去 i-1 处前缀和
     * time = O(1)
     */
    private long query(long[] sums, int start, int end) {
        if (start == 0) { // 从 0 到 j
            return sums[end];
        } else { // 从 i 到 j, i 不为0
            return sums[end] - sums[start - 1];
        }
    }

    private boolean isValid(int length, Interval interval) {
        return interval.start >= 0
                && interval.end < length
                && interval.start <= interval.end;
    }
}
