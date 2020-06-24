/*
Medium
#Binary Search, #Tree
 */
package leetcode;

import interviews.TreeTraverse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 222. Count Complete Tree Nodes
 *
 * Given a complete binary tree, count the number of nodes.
 *
 * Note:
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Example:
 *
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * Output: 6
 */
public class _0222_CountCompleteTreeNodes {


    /**
     * 找出最左子节点的高度 left 和 最右子节点的高度 right
     * - 如果两者相等, 说明此树不缺少任何节点, 其包含的节点数为2的left次方-1
     * - 如果两者不等, 说明缺少节点, 则分别对左右节点进行下一层递归
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = getLeftHeight(root);
        int right = getRightHeight(root);

        if (left == right) {
            return (1 << left) - 1; // 等同于2的n次方-1
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    // get the height of the leftmost leaf node
    // 如果root本身为leaf node, 返回 1.
    private int getLeftHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            height++;
            root = root.left;
        }
        return height;
    }

    // get the height of the rightmost leaf node
    private int getRightHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            height++;
            root = root.right;
        }
        return height;
    }



    /**
     * BFS 一个一个数 速度慢
     */
    public int countNodes_BFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            res++;
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }
        return res;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
