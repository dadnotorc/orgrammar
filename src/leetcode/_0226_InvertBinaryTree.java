package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 226. Invert Binary Tree
 * Easy
 * #Tree, #DFS, #BFS
 * 
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Example 1
 * 4 4
 * / \ / \
 * 2 7 => 7 2
 * / \ / \ / \ / \
 * 1 3 6 9 9 6 3 1
 * Input: [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 * 
 * Example 2
 * Input: [2,1,3]
 * Output: [2,3,1]
 * 
 * Example 3
 * Input: []
 * Output: []
 *
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 * 
 * Trivia:
 * This problem was inspired by this original tweet by Max Howell:
 *
 * Google: 90% of our engineers use the software you wrote (Homebrew),
 * but you can’t invert a binary tree on a whiteboard so f*** off.
 */
public class _0226_InvertBinaryTree {
    /*
    1. 从 root 开始, 将每个基点依次加入 queue / stack
    2. 每次从 queue / stack 取出当前 node 时, 将左右子节点互换. 然后将子节点压入 queue / stack
     */

    public TreeNode invertTree_BFS(TreeNode root) {
        if (root == null) { return null; }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }

        return root;
    }

    /**
     * 使用stack
     */
    public TreeNode invertTree_stack(TreeNode root) {
        if (root == null) { return null; }

        Stack<TreeNode> stack = new Stack<>();
        // Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            // 注意, 左右子树已反转, 所以先将左子树(原右子树)压入stack, 后将右子树(原左子树)压入stack
            // 左子树(原右子树)在下, 右子树(原左子树)在上
            // 之后处理时, 会先处理右子树, 后处理左子树
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return root;
    }



    /**
     * DFS - 当tree深度很大时, 会stack overflow. 解决方法是使用stack
     */
    public TreeNode invertTree_stack_overflow(TreeNode root) {
        if (root == null) { return null; }

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = invertTree_stack_overflow(right);
        root.right = invertTree_stack_overflow(left);

        return root;
    }




    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
