/*
Easy
Binary Tree Traversal, Recursion
Amazon, Facebook, Google
 */
package lintcode;

import util.TreeNode;

/**
 * 1181. Diameter of Binary Tree
 *
 * Given a binary tree, you need to compute the length of the diameter of the
 * tree. The diameter of a binary tree is the length of the longest path between
 * any two nodes in a tree. This path may or may not pass through the root.
 *
 * Notice
 * - The length of path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Example 2:
 * Input:[2,3,#,1]
 * Output:2
 * Explanation:
 *       2
 *     /
 *    3
 *  /
 * 1
 *
 * 类似 leetcode 437 PathSum3
 */
public class _1181_DiameterOfBinaryTree {

    int maxLen = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        findMaxDepthOfCurrentNode(root);
        return maxLen;
    }

    // 此函数作用有两点:
    // 1. 更新最长路径
    // 2. 找出当前节点两棵子树中最长的那一支
    private int findMaxDepthOfCurrentNode(TreeNode root) {
        if (root == null)
            return 0;

        // 分别找出左右子树中, 各自最长的那支
        int left = findMaxDepthOfCurrentNode(root.left);
        int right = findMaxDepthOfCurrentNode(root.right);

        // 找出从左子树,经过当前节点,到右子树的最远记录
        maxLen = Math.max(maxLen, left + right);

        // 返回值为当前节点的左右子树中那个最长的那一支, +1为子节点到当前点的距离
        return Math.max(left, right) + 1;
    }
}
