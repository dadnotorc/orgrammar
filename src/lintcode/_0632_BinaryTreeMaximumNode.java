/*
Naive
Binary Tree
AirBnB
 */
package lintcode;

import org.junit.Test;
import util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 632. Binary Tree Maximum Node
 *
 * Find the maximum node in a binary tree, return the node.
 *
 * Example 1:
 * Input:
 * {1,-5,3,1,2,-4,-5}
 * Output: 3
 * Explanation:
 * The tree look like this:
 *      1
 *    /   \
 *  -5     3
 *  / \   /  \
 * 1   2 -4  -5
 *
 * Example 2
 * Input:
 * {10,-5,2,0,3,-4,-5}
 * Output: 10
 * Explanation:
 * The tree look like this:
 *      10
 *    /   \
 *  -5     2
 *  / \   /  \
 * 0   3 -4  -5
 */
public class _0632_BinaryTreeMaximumNode {

    /* Method 1 - BFS */
    public TreeNode maxNode_BFS(TreeNode root) {
        if (root == null)
            return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode ans = root;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (ans.val < node.val)
                    ans = node;

                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }

        return ans;
    }

    /* Method 2 - DFS */
    public TreeNode maxNode_DFS(TreeNode root) {
        if (root == null)
            return null;

        // 不用使用本地变量, 因为当findMax返回时, 本地变量值会被初始值覆盖
        // int max = Integer.MIN_VALUE;
        dfs(root);

        return max;
    }

    private TreeNode max;

    private void dfs(TreeNode node) {
        if (node == null)
            return;

        if (max == null || max.val < node.val)
            max = node;

        dfs(node.left);
        dfs(node.right);
    }

    /* Method 3 - 九章算法 Recursion */
    public TreeNode maxNode_Recursion(TreeNode root) {
        if (root == null)
            return root;

        TreeNode maxOnLeft = maxNode_Recursion(root.left);
        TreeNode maxOnRight = maxNode_Recursion(root.right);
        return findMax(root, findMax(maxOnLeft, maxOnRight));
    }

    private TreeNode findMax(TreeNode n1, TreeNode n2) {
        if (n1 == null)
            return n2;
        if (n2 == null)
            return n1;
        if (n1.val < n2.val)
            return n2;
        else
            return n1;
    }

    @Test
    public void test1() {
        TreeNode n0 = new TreeNode(1);
        TreeNode n1 = new TreeNode(-5);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(2);
        TreeNode n5 = new TreeNode(-4);
        TreeNode n6 = new TreeNode(-5);

        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;

        TreeNode act_bfs = maxNode_BFS(n0);
        TreeNode act_dfs = maxNode_DFS(n0);
        TreeNode act_recursion = maxNode_Recursion(n0);
        org.junit.Assert.assertEquals(n2.val, act_bfs.val);
        org.junit.Assert.assertEquals(n2.val, act_dfs.val);
        org.junit.Assert.assertEquals(n2.val, act_recursion.val);
    }
}
