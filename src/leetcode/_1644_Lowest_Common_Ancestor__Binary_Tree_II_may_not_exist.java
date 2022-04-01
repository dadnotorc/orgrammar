package leetcode;

/**
 * 1644. Lowest Common Ancestor of a Binary Tree II
 * Medium
 * #Prime
 * LinkedIn, Microsoft
 *
 * Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q.
 * If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.
 *
 * Example 1:
 *        3
 *      /  \
 *    5     1
 *   / \   / \
 *  6  2   0  8
 *    / \
 *   7  4
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 *        3
 *      /  \
 *    5     1
 *   / \   / \
 *  6  2   0  8
 *    / \
 *   7  4
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.
 *
 * Example 3:
 *        3
 *      /  \
 *    5     1
 *   / \   / \
 *  6  2   0  8
 *    / \
 *   7  4
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
 * Output: null
 * Explanation: Node 10 does not exist in the tree, so return null.
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 *
 *
 * Follow up: Can you find the LCA traversing the tree, without checking nodes existence?
 */
public class _1644_Lowest_Common_Ancestor__Binary_Tree_II_may_not_exist {

    /**
     * 如果 p q 都存在, 则使用 leetcode 236 解法
     * 否则, 需要检查是否遇到过 p 或者 q
     */

    // labuladong的模板
    // 这里可以用 int 取代两个 boolean, 记录遇到 p q 的个数, 如果只遇到 1个 或更少, 返回 null
    boolean isFound_p = false, isFound_q = false;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = helper(root, p.val, q.val);
        if (!isFound_p || !isFound_q) { // p q 有任何一个没找到, 即返回null
            return null;
        }
        return ans;
    }

    private TreeNode helper(TreeNode root, int val_p, int val_q) {
        if (root == null) { return null; }

        // 这里跟 236 不一样, 不能在前序位置返回了, 因为要对二叉树进行完全搜索, 遍历每一个节点
        // 所有要挪动后序位置去
        // if (root.val == val_p || root.val == val_q) { return root; }

        TreeNode left = helper(root.left, val_p, val_q);
        TreeNode right = helper(root.right, val_p, val_q);

        // 后序位置，判断当前节点是不是 LCA 节点
        if (left != null && right != null) { return root; }

        // 这里是从前序那里挪来的, 要检查当前 root 是否是 p 或者 q
        if (root.val == val_p || root.val == val_q) {
            if (root.val == val_p) { isFound_p = true; }
            if (root.val == val_q) { isFound_q = true; }
            return root;
        }

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
