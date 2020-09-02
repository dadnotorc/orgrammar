package explore;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,2,3]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTree_PreorderTraversal {

    /**
     * Iteratively - 使用Stack
     * 先push右子节点, 后push左子节点. 如此, 读取时, 先读左子节点, 后读右子节点
     */
    public List<Integer> preorderTraversal_iteratively(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) { return ans; }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                ans.add(node.val);
                stack.push(node.right);
                stack.push(node.left);
            }
        }

        return ans;
    }


    /**
     * Recursively
     * 先递归进左子树, 后进右子树
     */
    public List<Integer> preorderTraversal_recursive(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        traverse(root, ans);
        return ans;
    }

    private void traverse(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }

        ans.add(node.val); // 先序遍历 preorder, 在递归之前

        traverse(node.left, ans);

        traverse(node.right, ans);
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