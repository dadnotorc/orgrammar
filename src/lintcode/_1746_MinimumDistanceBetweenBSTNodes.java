/*
Easy
#BST, #Binary Tree
Amazon
 */
package lintcode;

import java.util.TreeSet;

/**
 * 1746 · Minimum Distance Between BST Nodes
 *
 * Given a Binary Search Tree (BST) with the root node （root）, return the minimum difference between the values of any two different nodes in the tree.
 *
 * Wechat reply the 【1746】 get the latest frequent Interview questions . (wechat id : jiuzhang15)
 *
 * The size of the BST will be between 2 and 100.
 * The BST is always valid, each node's value is an integer, and each node's value is different.
 * Example
 * Example 1:
 *
 * Input: root = {2,1}
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 *
 * The given tree {2,1} is represented by the following diagram:
 *
 *       2
 *      /
 *     1
 *
 * while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
 * Example 2:
 *
 * Input: root = {4,2,6,1,3}
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 *
 * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
 *
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 *
 * while the minimum difference in this tree is 1.
 *
 * leetcode 783. Minimum Distance Between BST Nodes
 */
public class _1746_MinimumDistanceBetweenBSTNodes {

    /**
     * 使用 inorder traverse 遍历 - 左中右
     * 因为是 BST 树, inorder traverse 保证 values are sorted 递增序列
     *
     * time:  O(n)
     * space: O(h)  h - max depth of the tree, worst case h = n (extremely unbalanced)
     */
    Integer min = Integer.MAX_VALUE, prev = null;

    public int minDiffInBST(util.TreeNode root) {
        if (root.left != null)
            minDiffInBST(root.left);

        if (prev != null)
            min = Math.min(min, root.val - prev);

        prev = root.val;

        if (root.right != null)
            minDiffInBST(root.right);

        return min;
    }

    /**
     * follow up: 如果不是 BST, 则使用 TreeSet 记录访问过的 nodes 值.
     *            使用 pre order traverse
     *
     * time:  O(nlogn)
     * space: O(n)
     */

    Integer minimum = Integer.MAX_VALUE;
    TreeSet<Integer> ts = new TreeSet<>();

    public int minDiffInTree(TreeNode root) {
        if (root == null) {
            return minimum;
        }

        if (!ts.isEmpty()) {
            if (ts.floor(root.val) != null)
                minimum = Math.min(minimum, root.val - ts.floor(root.val));
            if (ts.ceiling(root.val) != null)
                minimum = Math.min(minimum, ts.ceiling(root.val) - root.val);
        }

        ts.add(root.val);

        if (root.left != null)
            minDiffInTree(root.left);
        if (root.right != null)
            minDiffInTree(root.right);

        return minimum;
    }


    /**
     * 有bug - [90,69,null,49,89,null,52]
     *         90
     *       /
     *     69
     *   /   \
     * 49    89
     *   \
     *   52
     * 该答案结果是 3, 但实际应该是 90 - 89 = 1
     */
    int min1 = Integer.MAX_VALUE;

    public int minDiffInBST_bug(TreeNode root) {
        dfs(root);
        return min1;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            min1 = Math.min(min, node.val - node.left.val);
            dfs(node.left);
        }

        if (node.right != null) {
            min1 = Math.min(min, node.right.val - node.val);
            dfs(node.right);
        }
    }










    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
