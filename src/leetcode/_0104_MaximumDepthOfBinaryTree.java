/*
Easy
#Tree, #BFS, #DFS, #Binary Tree
 */
package leetcode;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 104. Maximum Depth of Binary Tree
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 *
 * Example 1:
 *   3
 *  / \
 * 9  20
 *    / \
 *   15  7
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 *
 * Example 2:
 * Input: root = [1,null,2]
 * Output: 2
 *
 * Example 3:
 * Input: root = []
 * Output: 0
 *
 * Example 4:
 * Input: root = [0]
 * Output: 1
 *
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 10^4].
 * - -100 <= Node.val <= 100
 */
public class _0104_MaximumDepthOfBinaryTree {

    // 递归
    public int maxDepth_recursion(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMax = maxDepth_recursion(root.left);
        int rightMax = maxDepth_recursion(root.right);

        return Math.max(leftMax, rightMax) + 1;
    }

    // BFS
    public int maxDepth_BFS(TreeNode root) {
        int res = 0;
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res++;
        }
        return res;
    }

    // DFS
    public int maxDepth_DFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ans = 0;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> heights = new Stack<>();
        stack.push(root);
        heights.push(1);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int curHeight = heights.pop();
            ans = Math.max(ans, curHeight);

            if (node.right != null) {
                stack.push(node.right);
                heights.push(curHeight + 1);
            }
            if (node.left != null) {
                stack.push(node.left);
                heights.push(curHeight + 1);
            }
        }

        return ans;
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
