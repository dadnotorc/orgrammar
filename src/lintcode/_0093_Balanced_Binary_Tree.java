package lintcode;

import explore.BinaryTree_InorderTraversal;

/**
 * 93 · Balanced Binary Tree
 * Easy
 * #DFS, #Binary Tree, #Divide and Conquer
 *
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which
 * the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example 1:
 * Input: tree = {1,2,3}
 * Output: true
 * Explanation:
 * This is a balanced binary tree.
 *           1
 *          / \
 *         2   3
 *
 * Example 2:
 * Input: tree = {1,#,2,3,4}
 * Output: false
 * Explanation:
 * This is not a balanced tree.
 * The height of node 1's right sub-tree is 2 but left sub-tree is 0.
 *            1
 *             \
 *             2
 *            /  \
 *           3   4
 */
public class _0093_Balanced_Binary_Tree {

    /**
     * bottom-up
     *
     * time: O(n)
     */
    public boolean isBalanced_2(TreeNode root) {
        if (root == null) { return true; }

        return helper(root) != -1;
    }

    private int helper(TreeNode node) {
        if (node == null) { return 0; }

        int left = helper(node.left);
        int right = helper(node.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }

        return 1 + Math.max(left, right);
    }





    /**
     * top-down
     * get the heights of 2 sub trees, and check their difference
     *
     * time: O(n^2) - it gets the height of the (sub tree) for each node
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) { return true; }

        int left = getHeight(root.left);
        int right = getHeight(root.right);

        return Math.max(left, right) - Math.min(left, right) <= 1 &&
                isBalanced(root.left) && // 后面的两个条件 不能省略
                isBalanced(root.right);
    }

    private int getHeight(TreeNode node) {
        if (node == null) { return 0; }

        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }





    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
