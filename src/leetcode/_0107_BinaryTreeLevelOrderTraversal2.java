/*
Easy
#Tree, #BFS
 */
package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107. Binary Tree Level Order Traversal II
 *
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class _0107_BinaryTreeLevelOrderTraversal2 {

    /**
     * DFS - recursion
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res, 0);
        return res;
    }

    private void helper(TreeNode node, List<List<Integer>> res, int level) {
        if (node == null) {
            return;
        }

        if (level >= res.size()) {
            res.add(0, new ArrayList<>());
        }

        helper(node.left, res, level + 1);
        helper(node.right, res, level + 1);

        res.get(res.size() - 1 - level).add(node.val); // 反向加val, leaf node的值加在第0位的list中, root值加在末位的list中
    }

    /**
     * BFS with Queue
     */
    public List<List<Integer>> levelOrderBottom_BFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> curRow = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                curRow.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            res.add(0, curRow);
        }

        return res;
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
