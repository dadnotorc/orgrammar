/*
Medium
#Binary Search Tree (BST)
Google, Uber
FAQ++
 */
package lintcode;

import util.TreeNode;

import java.util.Stack;

/**
 * 902. Kth Smallest Element in a BST
 *
 * Given a binary search tree, write a function to find the kth smallest element in it.
 *
 * Notice
 * - You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
 * Example 1:
 * Input：{1,#,2},2
 * Output：2
 * Explanation：
 * 	1
 * 	 \
 * 	  2
 * The second smallest element is 2.
 *
 * Example 2:
 * Input：{2,1,3},1
 * Output：1
 * Explanation：
 *   2
 *  / \
 * 1   3
 * The first smallest element is 1.
 *
 * Challenge
 * - What if the BST is modified (insert/delete operations) often and you need to find
 *   the kth smallest frequently? How would you optimize the kthSmallest routine?
 */
public class _0902_KthSmallestElementInBST {

    /**
     * 先走到左下角 (最小值), 然后沿着树走k步
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        for (int i = 1; i < k; i++) { // 从第1步开始到k-1, 因为到达第k步时需要停止循环并跳出
            // 注意! 这里不能pop(), 需要保留在stack中,
            // 因为在之后的else里(往上走时), 需要判断上层node是否时当前node的父节点
            TreeNode node = stack.peek();

            if (node.right != null) { // 往右下走, 然后往左下走
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            } else { // 需要往上走
                node = stack.pop();
                while (!stack.isEmpty() && stack.peek().right == node) {
                    node = stack.pop();
                }
            }
        }

        return stack.peek().val;
    }
}
