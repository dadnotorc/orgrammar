/*
Easy
Tree, Recursion
 */
package leetcode.recursion;

import util.TreeNode;

import java.util.Stack;

/**
 * 938. Range Sum of BST
 *
 * Given the root node of a binary search tree, return the sum of values of
 * all nodes with value between L and R (inclusive).
 *
 * The binary search tree is guaranteed to have unique values.
 *
 * Example 1:
 * Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
 * Output: 32
 *
 * Example 2:
 * Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * Output: 23
 *
 * Note:
 * The number of nodes in the tree is at most 10000.
 * The final answer is guaranteed to be less than 2^31.
 */
public class _0938_RangeSumofBST {

    /**
     * 使用递归
     * time:  O(n) DFS 遍历所有nodes (worst case)
     * space: O(h) h = max depth of the tree, worst case (extremely unbalanced) -> O(n)
     */
    public int rangeSumBST_recursion(TreeNode root, int L, int R) {
        if (root == null)
            return 0;

        int ans = 0;

        if (root.val >= L && root.val <= R) // 当前值符合条件, 加入和
            ans += root.val;

        if (root.val > L) // 检查左子树
            ans += rangeSumBST_recursion(root.left, L, R);

        if (root.val < R) // 检查右子树
            ans += rangeSumBST_recursion(root.right, L, R);

        // 如果不判断左右子树, 会不会stack overflow?

        return ans;
    }

    /**
     * 使用循环 - 时间耗费比递归高, 因为有额外的push/pop动作
     * time:  O(n)
     * space: O(n)
     */
    public int rangeSumBST_iterative(TreeNode root, int L, int R) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int ans = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null)
                continue;

            if (node.val >= L && node.val <= R)
                ans += node.val;

            if (node.val > L)
                stack.push(node.left);

            if (node.val < R)
                stack.push(node.right);
        }

        return ans;
    }
}
