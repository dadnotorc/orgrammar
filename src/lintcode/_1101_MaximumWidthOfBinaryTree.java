/*
Medium
#BFS, #DFS, #Binary Tree
Amazon
 */
package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1101. Maximum Width of Binary Tree
 *
 * Given a binary tree, write a function to get the maximum width of the given tree.
 * The width of a tree is the maximum width among all levels. The binary tree has the
 * same structure as a full binary tree, but some nodes are null.
 *
 * The width of one level is defined as the length between the end-nodes
 * (the leftmost and right most non-null nodes in the level, where the null nodes between
 * the end-nodes are also counted into the length calculation.
 *
 * Example 1:
 * Input:
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,#,9).
 *
 * Example 2:
 * Input:
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 *
 * Example 3:
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 *
 * Example 4:
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,#,#,#,#,#,#,7).
 *
 * Notice
 * - The answer will be in the range of 32-bit signed integer.
 *
 * 此题与 leetcode 662. Maximum Width of Binary Tree 相同
 */
public class _1101_MaximumWidthOfBinaryTree {

    int res = 0;
    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, new ArrayList<>(), 0, 1);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> leftMostNodes, int depth, int curIndex) {
        if (node == null) { return; }

        if (depth == leftMostNodes.size()) { leftMostNodes.add(curIndex); }

        res = Math.max(res, curIndex - leftMostNodes.get(depth) + 1);

        dfs(node.left, leftMostNodes, depth + 1, curIndex * 2);
        dfs(node.right, leftMostNodes, depth + 1, curIndex * 2 + 1);
    }

    class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
