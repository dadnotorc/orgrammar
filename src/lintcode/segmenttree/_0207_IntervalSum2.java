/*
Hard
#Segment Tree, #Binary Search

 */
package lintcode.segmenttree;

/**
 * 207. Interval Sum II
 * Given an integer array in the construct method, implement two methods
 * query(start, end) and modify(index, value):
 *
 * For query(start, end), return the sum from index start to index end
 * in the given array.
 * For modify(index, value), modify the number in the given index to value
 *
 * Example 1
 * Input:
 * [1,2,7,8,5]
 * [query(0,2),modify(0,4),query(0,1),modify(2,1),query(2,4)]
 * Output: [10,6,14]
 * Explanation:
 * Given array A = [1,2,7,8,5].
 * After query(0, 2), 1 + 2 + 7 = 10,
 * After modify(0, 4), change A[0] from 1 to 4, A = [4,2,7,8,5].
 * After query(0, 1), 4 + 2 = 6.
 * After modify(2, 1), change A[2] from 7 to 1，A = [4,2,1,8,5].
 * After query(2, 4), 1 + 8 + 5 = 14.
 *
 * Example 2
 * Input:
 * [1,2,3,4,5]
 * [query(0,0),query(1,2),query(3,4)]
 * Output: [1,5,9]
 * Explantion:
 * 1 = 1
 * 2 + 3 = 5
 * 4 + 5 = 9
 *
 * Challenge: O(logN) time for query and modify.
 */
public class _0207_IntervalSum2 {

    // 使用 member variable
    private SegmentTree tree;

    public _0207_IntervalSum2(int[] A) {
        if (A == null || A.length == 0)
            return;

        tree = new SegmentTree(A);
    }

    public long query(int start, int end) {
        return tree.query(start, end);
    }

    public void modify(int index, int value) {
        tree.modify(index, value);
    }
}

class SegmentTreeNode {
    long sum;
    int start, end;
    SegmentTreeNode left, right;
    public SegmentTreeNode (int start, int end) {
        this.start = start;
        this.end = end;
        sum = 0;
        left = right = null;
    }
}

class SegmentTree {
    private int size;
    private SegmentTreeNode root;
    public SegmentTree (int[] A) {
        size = A.length;
        root = buildTreeNode(0, size - 1, A);
    }

    public long query(int queryStart, int queryEnd) {
        return query(root, queryStart, queryEnd);
    }

    public void modify(int index, int value) {
        modify(root, index, value);
    }

    private SegmentTreeNode buildTreeNode(int start, int end, int[] A) {

        SegmentTreeNode node = new SegmentTreeNode(start, end);

        if (start == end) {
            node.sum = A[start];
            return node;
        }

        int mid = (start + end) / 2;
        node.left = buildTreeNode(start, mid, A);
        node.right = buildTreeNode(mid + 1, end, A);
        node.sum = node.left.sum + node.right.sum;

        return node;
    }

    private long query(SegmentTreeNode node, int queryStart, int queryEnd) {
        if (node.start == queryStart && node.end == queryEnd) {
            return node.sum;
        }

        int mid = (node.start + node.end) / 2;
        long leftSum = 0, rightSum = 0;

        if (queryStart <= mid) {
            leftSum = query(node.left, queryStart, Math.min(mid, queryEnd));
        }
        if (queryEnd >= mid + 1) {
            rightSum = query(node.right, Math.max(queryStart, mid + 1), queryEnd);
        }

        return leftSum + rightSum;
    }

    private void modify(SegmentTreeNode node, int index, int value) {
        // 到达 leaf node, 且 leaf node 就是 index 所指的节点
        // 无需判断 node.start 是否等于 index
        if (node.start == node.end) {
            node.sum = value;
            return;
        }

        // 无需计算 mid, 减少一步运算

        if (index <= node.left.end) { // 所需修改的节点在左子树一边
            modify(node.left, index, value);
        } else {
            modify(node.right, index, value);
        }

        // 别忘了更新当前节点的 sum
        node.sum = node.left.sum + node.right.sum;
    }
}
