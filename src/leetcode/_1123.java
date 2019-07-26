package leetcode;

import util.TreeNode;

/**
 * 1123. Lowest Common Ancestor of Deepest Leaves
 * Medium
 *
 * Given a rooted binary tree, return the lowest common ancestor of its
 *  deepest leaves.
 *
 * Recall that:
 * - The node of a binary tree is a leaf if and only if it has no children
 * - The depth of the root of the tree is 0, and if the depth of a node is d,
 *    the depth of each of its children is d+1.
 * - The lowest common ancestor of a set S of nodes is the node A with the
 *    largest depth such that every node in S is in the subtree with root A.
 *
 *
 * Example 1:
 * Input: root = [1,2,3]
 * Output: [1,2,3]
 * Explanation:
 * The deepest leaves are the nodes with values 2 and 3.
 * The lowest common ancestor of these leaves is the node with value 1.
 * The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".
 *
 * Example 2:
 * Input: root = [1,2,3,4]
 * Output: [4]
 *
 * Example 3:
 * Input: root = [1,2,3,4,5]
 * Output: [2,4,5]
 *
 *
 * Constraints:
 * - The given tree will have between 1 and 1000 nodes.
 * - Each node of the tree will have a distinct value between 1 and 1000.
 */
public class _1123 {

    /**
     * LC上的参考解答
     * similar to 236. Lowest Common Ancestor of a Binary Tree
     */
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        int d = depth(root);
        return helper(root, d);
    }

    private int depth(TreeNode n) { // compute depth of the tree.
        if (n == null)
            return 0;
        return 1 + Math.max(depth(n.left), depth(n.right));
    }

    private TreeNode helper(TreeNode n, int d) {
        // base case 1: not deepest leaf.
        if (n == null && d > 0) return null;

        // base case 2: found a deepest leaf.
        if (n != null && d == 1) return n;

        // general case: recurse to children.
        TreeNode L = helper(n.left, d - 1);
        TreeNode R = helper(n.right, d - 1);

        // both children have deepest leaves.
        if (L != null && R != null) return n;

        // at most 1 child has deepest leaf.
        return L == null ? R : L;
    }
}
