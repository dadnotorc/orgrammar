/*
Medium
#Stack, #Tree
 */
package leetcode;


import lintcode._0066_BinaryTreePreorderTraversal;

import java.util.*;

/**
 * 145. Binary Tree Postorder Traversal
 *
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 *
 * Example 1:
 * 1
 *  \
 *   2
 *  /
 * 3
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 * Example 3:
 * Input: root = [1]
 * Output: [1]
 *
 * Example 4:
 *   1
 *  /
 * 2
 * Input: root = [1,2]
 * Output: [2,1]
 *
 * Example 5:
 * 1
 *  \
 *   2
 * Input: root = [1,null,2]
 * Output: [2,1]
 *
 * Constraints:
 * * The number of the nodes in the tree is in the range [0, 100].
 * * -100 <= Node.val <= 100
 *
 * Follow up:
 * Recursive solution is trivial, could you do it iteratively?
 */
public class _0145_BinaryTreePostorderTraversal {

    /**
     * Recursion 递归
     */
    public List<Integer> postorderTraversal_Recursion(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(root, ans);
        return ans;
    }

    private void helper(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }

        helper(node.left, ans);
        helper(node.right, ans);

        ans.add(node.val);
    }


    /**
     * stack解法， 与preorder顺序相反
     */
    public List<Integer> postorderTraversal_iterative(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                ans.add(0, cur.val); // 注意！这里将当前节点值加入首位，即从后往前加入ans队列
                stack.push(cur);
                cur = cur.right; // 与preorder相反，先加入右子节点
            }

            TreeNode node = stack.pop();
            cur = node.left; // 此处也与preorder相反
        }

        return ans;
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
