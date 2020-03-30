/*
Medium
#Segment Tree, #Binary Search

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

    /**
     * 解法2 - 使用线状树
     * 因为此题不需要修改, 使用前缀和数组更合适 (每次查询的时间法度是 O(logn) vs O(1))
     * 但是需要修改时, 线状树更合适 (每次修改的时间法度是 O(logn) vs O(n))
     *
     * time: O(mlogn)  m=queries.size(), n=A.length
     */
    public List<Long> intervalSum_SegmentTree(int[] A, List<Interval> queries) {
        List<Long> ans = new ArrayList<>();

        if (A == null || A.length == 0)
            return ans;

        SegmentTree tree = new SegmentTree(A);
        for (Interval interval : queries) {
            ans.add(tree.query(interval.start, interval.end));
        }

        return ans;
    }

    class SegmentTreeNode {
        long sum;
        int start, end;
        SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            sum = 0;
            left = right = null;
        }
    }

    class SegmentTree {
        private int size;
        private SegmentTreeNode root;
        public SegmentTree(int[] A) {
            size = A.length;
            root = buildTreeNode(0, size - 1, A);
        }

        public long query(int queryStart, int queryEnd) {
            return query(root, queryStart, queryEnd);
        }

        private SegmentTreeNode buildTreeNode(int start, int end, int[] A) {
            // 先创建当前node, 给定 start 和 end
            SegmentTreeNode node = new SegmentTreeNode(start, end);

            if (start == end) { // 到达 leaf node
                node.sum = A[start];
                return node;
            }

            // 创建左右子树, 并根据左右子树更新当前节点 sum 值
            int mid = (start + end) / 2;
            node.left = buildTreeNode(start, mid, A);
            node.right = buildTreeNode(mid + 1, end, A);
            node.sum = node.left.sum + node.right.sum;

            return node;
        }

        // 在当前节点中, 找到能够表示 [queryStart, queryEnd] 的节点, 返回所有节点的 sum
        private long query(SegmentTreeNode node, int queryStart, int queryEnd) {
            if (queryStart == node.start && queryEnd == node.end) {
                return node.sum;
            }

            int mid = (node.start + node.end) / 2;
            long leftSum = 0;
            long rightSum = 0;

            if (queryStart <= mid) { // query的左边界在左子树内
                // query 右边界取当前 queryEnd 和 mid 的较小值
                leftSum = query(node.left, queryStart, Math.min(mid, queryEnd));
            }

            if (queryEnd >= mid + 1) { // query的右边界在右子树内
                // query 右边界取当前 queryEnd 和 mid 的较小值
                rightSum = query(node.right, Math.max(queryStart, mid + 1), queryEnd);
            }

            return leftSum + rightSum;
        }
    }



    /**
     * 解法1 - 使用前缀和数组
     *
     * time: O(n + m)   m=queries.size(), n=A.length
     */
    public List<Long> intervalSum_PrefixSum(int[] A, List<Interval> queries) {
        List<Long> ans = new ArrayList<>();

        if (A == null || A.length == 0) { return ans; }

        long[] prefixSums = getPrefixSums(A);

        for (Interval interval : queries) {
            if(isValid(prefixSums.length, interval)) {
                ans.add(query(prefixSums, interval.start, interval.end));
            }
        }

        return ans;
    }

    // time: O(n)
    private long[] getPrefixSums(int[] A) {
        long[] sums = new long[A.length];
        sums[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            sums[i] = sums[i - 1] + A[i];
        }
        return sums;
    }

    // 查询[0, end] -> 直接返回前缀和数组中 end 指针处的前缀和
    // 查询[i, j] -> 返回 j 处前缀和减去 i-1 处前缀和
    // time: O(1)
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
