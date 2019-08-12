/*
Medium
Segment Tree, Binary Tree
 */
package lintcode.segmenttree;

import util.SegmentTreeNode;

/**
 * 203. Segment Tree Modify
 * For a Maximum Segment Tree, which each node has an extra value max to store
 * the maximum value in this node's interval.
 *
 * Implement a modify function with three parameter root, index and value to
 * change the node's value with [start, end] = [index, index] to the new given
 * value. Make sure after this change, every node in segment tree still has the
 * max attribute with the correct value.
 *
 * Example 1:
 * Input："[1,4,max=3][1,2,max=2][3,4,max=3][1,1,max=2][2,2,max=1][3,3,max=0][4,4,max=3]",2,4
 * Output："[1,4,max=4][1,2,max=4][3,4,max=3][1,1,max=2][2,2,max=4][3,3,max=0][4,4,max=3]"
 * Explanation：
 * For segment tree:
 * 	                      [1, 4, max=3]
 * 	                    /                \
 * 	        [1, 2, max=2]                [3, 4, max=3]
 * 	       /              \             /             \
 * 	[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=3]
 *
 * if call modify(root, 2, 4), we can get:
 *
 * 	                      [1, 4, max=4]
 * 	                    /                \
 * 	        [1, 2, max=4]                [3, 4, max=3]
 * 	       /              \             /             \
 * 	[1, 1, max=2], [2, 2, max=4], [3, 3, max=0], [4, 4, max=3]
 *
 * Example 2:
 * Input："[1,4,max=3][1,2,max=2][3,4,max=3][1,1,max=2][2,2,max=1][3,3,max=0][4,4,max=3]",4,0
 * Output："[1,4,max=4][1,2,max=4][3,4,max=0][1,1,max=2][2,2,max=4][3,3,max=0][4,4,max=0]"
 * Explanation：
 * For segment tree:
 * 	                      [1, 4, max=3]
 * 	                    /                \
 * 	        [1, 2, max=2]                [3, 4, max=3]
 * 	       /              \             /             \
 * 	[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=3]
 * if call modify(root, 4, 0), we can get:
 *
 * 	                      [1, 4, max=2]
 * 	                    /                \
 * 	        [1, 2, max=2]                [3, 4, max=0]
 * 	       /              \             /             \
 * 	[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=0]
 *
 * Challenge: Do it in O(h) time, h is the height of the segment tree.
 */
public class _0203_SegmentTreeModify {

    /**
     * 先按两分法自上而下查询区间,找到目标子字节并修改值后,自下而上更改各节点值
     */
    public void modify(SegmentTreeNode root, int index, int value) {

        if (index < root.start || index > root.end) { return; }

        if (index == root.start && index == root.end) {
            root.max = value;
            return; // 找到目标子节点后反弹, 别忘了中止递归
        }

        int mid = root.start + (root.end - root.start) / 2;
        if (index <= mid) {
            modify(root.left, index, value);
        } else {
            modify(root.right, index, value);
        }

        root.max = Math.max(root.left.max, root.right.max); // 反弹开始自下而上更新
    }
}
