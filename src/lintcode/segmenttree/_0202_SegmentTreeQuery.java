/*
Medium
Segment Tree, Binary Tree
 */
package lintcode.segmenttree;

import util.SegmentTreeNode;

/**
 * 202. Segment Tree Query
 * For an integer array (index from 0 to n-1, where n is the size of this
 * array), in the corresponding SegmentTree, each node stores an extra
 * attribute max to denote the maximum number in the interval of the array
 * (index from start to end).
 *
 * Design a query method with three parameters root, start and end, find the
 * maximum number in the interval [start, end] by the given root of segment tree.
 *
 * Example 1:
 * Input："[0,3,max=4][0,1,max=4][2,3,max=3][0,0,max=1][1,1,max=4][2,2,max=2][3,3,max=3]",1,2
 * Output：4
 * Explanation：
 * For array [1, 4, 2, 3], the corresponding Segment Tree is :
 * 	                  [0, 3, max=4]
 * 	                 /             \
 * 	          [0,1,max=4]        [2,3,max=3]
 * 	          /         \        /         \
 * 	   [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]
 * The maximum value of [1,2] interval is 4
 *
 * Example 2:
 * Input："[0,3,max=4][0,1,max=4][2,3,max=3][0,0,max=1][1,1,max=4][2,2,max=2][3,3,max=3]",2,3
 * Output：3
 * Explanation：
 * For array [1, 4, 2, 3], the corresponding Segment Tree is :
 * 	                  [0, 3, max=4]
 * 	                 /             \
 * 	          [0,1,max=4]        [2,3,max=3]
 * 	          /         \        /         \
 * 	   [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]
 * The maximum value of [2,3] interval is 3
 */
public class _0202_SegmentTreeQuery {
    /**
     * 每次分别对当前区间的左右子树搜索区间最大值。
     * 如果查询起点位于区间左侧，终点位于右侧，左子树查询区间为(start,mid)。
     * 如果终点位于左侧，左子树查询区间为(start,end)。
     * 如果查询终点位于区间右侧，起点位于左侧，右子树查询区间为(mid+1,end)。
     * 如果起点位于右侧，右子树查询区间为(start,end)。
     */
    public int query(SegmentTreeNode root, int start, int end) {
        if (start == root.start && end == root.end) {
            return root.max;
        }

        int mid = root.start + (root.end - root.start) / 2;
        int leftMax = Integer.MIN_VALUE, rightMax = Integer.MIN_VALUE;

        if (start <= mid) { //一定要用 <=. 只用 < 会出错. 因为左子树区间是[start, mid]
            if (end <= mid) { // start, end 都在左边
                leftMax = query(root.left, start, end);
            } else {
                leftMax = query(root.left, start, mid);
            }
        }
        if (mid < end) {
            if (mid < start) { // start, end 都在右边
                rightMax = query(root.right, start, end);
            } else {
                rightMax = query(root.right, mid + 1, end);
            }
        }

        return Math.max(leftMax, rightMax);
    }

    public int query_1(SegmentTreeNode root, int start, int end) {
        if (start == root.start && end == root.end) {
            return root.max;
        }

        int mid = root.start + (root.end - root.start) / 2;

        if (start <= mid && end <= mid) { //一定要用 <=. 只用 < 会出错
            return query(root.left, start, end);
        }

        if (mid < end && mid < start) {
            return query(root.right, start, end);
        }

        int leftMax = query(root.left, start, mid);
        int rightMax = query(root.right, mid + 1, end);
        return Math.max(leftMax, rightMax);
    }
}
