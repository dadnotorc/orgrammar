package leetcode;

import java.util.HashSet;

/**
 * 1676. Lowest Common Ancestor of a Binary Tree IV
 * Medium
 * #Prime
 * Amazon
 *
 * Given the root of a binary tree and an array of TreeNode objects nodes,
 * return the lowest common ancestor (LCA) of all the nodes in nodes.
 * All the nodes will exist in the tree, and all values of the tree's nodes are unique.
 *
 * Example 1:
 *        3
 *      /  \
 *    5     1
 *   / \   / \
 *  6  2   0  8
 *    / \
 *   7  4
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [4,7]
 * Output: 2
 * Explanation: The lowest common ancestor of nodes 4 and 7 is node 2.
 *
 * Example 2:
 *        3
 *      /  \
 *    5     1
 *   / \   / \
 *  6  2   0  8
 *    / \
 *   7  4
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [1]
 * Output: 1
 * Explanation: The lowest common ancestor of a single node is the node itself.
 *
 * Example 3:
 *        3
 *      /  \
 *    5     1
 *   / \   / \
 *  6  2   0  8
 *    / \
 *   7  4
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [7,6,2,4]
 * Output: 5
 * Explanation: The lowest common ancestor of the nodes 7, 6, 2, and 4 is node 5.
 *
 * Example 4:
 *        3
 *      /  \
 *    5     1
 *   / \   / \
 *  6  2   0  8
 *    / \
 *   7  4
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [0,1,2,3,4,5,6,7,8]
 * Output: 3
 * Explanation: The lowest common ancestor of all the nodes is the root node.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * All nodes[i] will exist in the tree.
 * All nodes[i] are distinct.
 */
public class _1676_Lowest_Common_Ancestor_Binary_Tree_IV_array {

    /**
     * 解法类似 leetcode 236
     * 把 nodes 数组中所有节点的值存入 hashset, 用于比较 当前节点的值 是否是给出的所有 nodes 中的其中一位
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        HashSet<Integer> set = new HashSet<>();
        for(TreeNode node : nodes) {
            set.add(node.val);
        }
        return helper(root, set);
    }

    private TreeNode helper(TreeNode root, HashSet<Integer> set) {
        if (root == null) { return null; }

        // 前序位置
        if (set.contains(root.val)) { return root; }

        TreeNode left = helper(root.left, set);
        TreeNode right = helper(root.right, set);

        // 后序位置，判断当前节点是不是 LCA 节点
        if (left != null && right != null) { return root; }

        return left != null ? left : right;
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
