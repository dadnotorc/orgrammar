package leetcode;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * Easy
 * #DFS, #Binary Search Tree, #Binary Tree
 *
 *
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 *
 * Example 1:
 *        6
 *      /  \
 *    2     8
 *   / \   / \
 *  0  4   7  9
 *    / \
 *   3  5
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 *
 * Example 2:
 *        6
 *      /  \
 *    2     8
 *   / \   / \
 *  0  4   7  9
 *    / \
 *   3  5
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 *
 * Example 3:
 *    2
 *   /
 *  1
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2

 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 10^5].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 * p and q will exist in the BST.
 */
public class _0235_Lowest_Common_Ancestor_Binary_Search_Tree {


    /**
     * 利用 BST 左小右大的特性, 假设 val1 < val2, 如果下列情况
     * - val1 <= node.val <= val2, 说明当前 node 即 LCA
     * - node.val < val1, 说明 node 值太小, 要去 右子树 继续查找
     * - node.val > val2, 说明 node 值太大, 要去 左子树 继续查找
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return helper(root, Math.min(p.val, q.val), Math.max(p.val, q.val));
    }

    private TreeNode helper(TreeNode node, int val1, int val2) {
        if (node == null) { return null; }

        // 这步没错, 但是不需要了, 会降低效率
        // if (node.val == val1 || node.val == val2) { return node; }

        if (node.val < val1) {
            return helper(node.right, val1, val2);
        }

        if (node.val > val2) {
            return helper(node.left, val1, val2);
        }

        // val1 <= node.val <= val2
        return node;
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
