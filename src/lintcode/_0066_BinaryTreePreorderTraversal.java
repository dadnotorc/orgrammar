/*
Easy
#Binary Tree, #Traversal, #Recursion, #Non recursion, #DFS, #Divide and Conquer
 */
package lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 66. Binary Tree Preorder Traversal - 前序 - 上 左 右
 *
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * Notice
 * - The first data is the root node, followed by the value of the left and right son nodes,
 *   and "#" indicates that there is no child node.
 * - The number of nodes does not exceed 20.
 *
 * Example 1:
 * Input：{1,2,3}
 * Output：[1,2,3]
 * Explanation:
 *    1
 *   / \
 *  2   3
 * it will be serialized {1,2,3} Preorder traversal
 *
 * Example 2:
 * Input：{1,#,2,3}
 * Output：[1,2,3]
 * Explanation:
 * 1
 *  \
 *   2
 *  /
 * 3
 * it will be serialized {1,#,2,3} Preorder traversal
 *
 * Challenge
 * - Can you do it without recursion?
 *
 *
 * input =  {1,2,3,4,5,6,7}
 *      1
 *     / \
 *    2   3
 *   / \ / \
 *  4  5 6 7
 * output = {1,2,4,5,3,6,7}
 *
 * 等同 leetcode 144. Binary Tree Preorder Traversal
 */
public class _0066_BinaryTreePreorderTraversal {

    /**
     * Divide and conquer 最慢
     */
    public List<Integer> preorderTraversal_divide_conquer(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null)
            return res;

        // divide
        List<Integer> left = preorderTraversal_divide_conquer(root.left);
        List<Integer> right = preorderTraversal_divide_conquer(root.right);

        // conquer
        res.add(root.val);
        res.addAll(left);
        res.addAll(right);

        return res;
    }

    /**
     * DFS - 先递归进左节点, 后进右节点 - 较快
     */
    public List<Integer> preorderTraversal_recursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs (TreeNode node, List<Integer> res) {
        if (node == null)
            return;

        res.add(node.val);
        dfs(node.left, res);
        dfs(node.right, res);
    }



    /**
     * 也是stack解法， 加入cur node指针
     */
    public List<Integer> preorderTraversal_iterative_2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                ans.add(cur.val); // 访问子节点之前, 先将父节点加入ans队列
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop(); // 此node已被加入ans队列中
            cur = cur.right;
        }

        return ans;
    }





    /**
     * 使用stack反向保存节点 - 最快
     * 先存右节点, 后存左节点
     *
     * 易错点:
     * 1. 用stack, 而不是用queue
     */
    public List<Integer> preorderTraversal_iterative(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) { return ans; }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) { // stack里的所有节点都不为空
            TreeNode node = stack.pop();
            ans.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return ans;
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
