/*
Medium
#Tree, #Recursion, #BST
Amazon
 */
package leetcode;

import util.TreeNode;

import java.util.TreeSet;

/**
 * 783. Minimum Distance Between BST Nodes
 *
 * Given a Binary Search Tree (BST) with the root node, return the minimum
 * difference between the values of any two different nodes in the tree.
 *
 * Example :
 * Input: root = [4,2,6,1,3,null,null]
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
 *
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 *
 * while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
 *
 * Note:
 * The size of the BST will be between 2 and 100.
 * The BST is always valid, each node's value is an integer, and each node's value is different.
 *
 * lintcode 1746 · Minimum Distance Between BST Nodes
 */
public class _0783_MinimumDistanceBetweenBSTNodes {

    /**
     * 使用 inorder traverse 遍历 - 左中右
     * 因为是 BST 树, inorder traverse 保证 values are sorted 递增序列
     *
     * time:  O(n)
     * space: O(h)  h - max depth of the tree, worst case h = n (extremely unbalanced)
     */
    Integer min = Integer.MAX_VALUE;
    TreeNode pre = null;

    public int minDiffInBST(TreeNode root) {
        dfs(root);
        return min;
    }

    public void dfs(TreeNode node) {
        if (node.left != null) {
            dfs(node.left);
        }

        if (pre != null) {
            min = Math.min(min, node.val - pre.val);
        }

        pre = node;

        if (node.right != null) {
            dfs(node.right);
        }
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


    // test data
    // [90,69,null,49,89,null,52,null,null,null,null]   expect = 1
}
