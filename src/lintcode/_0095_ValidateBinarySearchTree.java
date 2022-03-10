package lintcode;

import util.TreeNode;

import java.util.Stack;

/**
 * 95. Validate Binary Search Tree (BST)
 * Medium
 * #Divide and Conquer, #BST, #Binary Tree, #Recursion
 * Amazon, Facebook, Microsoft
 * 
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees.
 * - A single node tree is a BST
 *
 * Example 1:
 * Input:  {-1}
 * Output：true
 * Explanation：
 * For the following binary tree（only one node）:
 * 	      -1
 * This is a binary search tree.
 *
 * Example 2:
 * Input:  {2,1,4,#,#,3,5}
 * Output: true
 * For the following binary tree:
 * 	  2
 * 	 / \
 * 	1   4
 * 	   / \
 * 	  3   5
 * This is a binary search tree.
 *
 * ~~~
 * 左subtree所有节点的值 < 父节点的值
 * 右subtree所有节点的值 > 父节点的值
 * Input: {3,1,5,#,#,2,6}
 * 	  3
 * 	 / \
 * 	1   5
 * 	   / \
 * 	  2   6
 * Output: false
 * 
 * leetcode 98
 */
public class _0095_ValidateBinarySearchTree {

    /*
    暴力 - In-order traversal 左-上-右, 将所有节点值存入 list. 然后遍历 list, 比较前后两个值 - O (2n)
     */

    /**
     * 解法 1 - 分制法 - O(n)
     * 
     * 每次用当前值 比较 当前最小值 与 当前最大值.
     * 
     * 注意要用 Long, 针对 {Integer.MAX_VALUE} 这个case, input = [2147483647]
     */
    public boolean isValidBST(TreeNode root) {
        return divideConquer(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean divideConquer(TreeNode node, long min, long max) {
        if (node == null)
            return true;

        if (node.val <= min || node.val >= max)
            return false;

        return divideConquer(node.left, min, node.val)
                && divideConquer(node.right, node.val, max);
    }


    /**
     * 解法 2 - 循环 - O(n)
     */
    public boolean isValidBST_2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        // 一路找左子节点, 即最小值
        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        TreeNode lastNode = null; // lastNode时已访问过的节点, 且一直在当前节点左边

        while (!stack.isEmpty()) {

            // compare to last node
            TreeNode node = stack.peek();
            if (lastNode != null && lastNode.val >= node.val)
                return false;
            lastNode = node;

            // move to next node
            if (node.right == null) {
                node = stack.pop();
                while (!stack.isEmpty() && stack.peek().right == node) {
                    node = stack.pop();
                }
            } else {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }

        return true;
    }
}
