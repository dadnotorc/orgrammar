/*
Easy
#DFS, #Binary Tree, #Divide and Conquer
 */
package lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 67 · Binary Tree Inorder Traversal - 中序 - 左 上 右
 *
 * Given a binary tree, return the inorder traversal of its nodes‘ values.
 *
 * Example 1:
 * Input: binary tree = {1,2,3}
 * Output: [2,1,3]
 *    1
 *  /  \
 * 2    3
 * It will be serialized as {1,2,3} inorder traversal
 *
 * Example 2:
 * Input: binary tree = {1,#,2,3}
 * Output: [1,3,2]
 * 1
 *  \
 *  2
 * /
 * 3
 * It will be serialized as {1,#,2,3} inorder traversal
 *
 * Challenge
 * Can you do it without recursion?
 *
 * input =  {1,2,3,4,5,6,7}
 *      1
 *     / \
 *    2   3
 *   / \ / \
 *  4  5 6 7
 * output = {4,2,5,1,6,3,7}
 */
public class _0067_BinaryTreeInorderTraversal {

    /**
     * DFS - 先递归去左节点, 然后加当前节点进队列, 最后递归去右节点
     */
    public List<Integer> inorderTraversal_dfs(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }

    public void dfs(TreeNode node, List<Integer> list) {
        if (node == null) return;

        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }

    /**
     * 也使用 stack, 加入 cur 指针
     */
    public List<Integer> inorderTraversal_stack_2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            ans.add(cur.val);
            cur = cur.right;
        }

        return ans;
    }


    /**
     * 使用stack
     */
    public List<Integer> inorderTraversal_stack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) { return ans; }

        Stack<TreeNode> stack = new Stack<>();

        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            ans.add(node.val);

            if (node.right == null) {
                node = stack.pop();
                while (!stack.isEmpty() && stack.peek().right == node) { // 直接 pop 掉已经访问过的 parent node (上节点)
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
