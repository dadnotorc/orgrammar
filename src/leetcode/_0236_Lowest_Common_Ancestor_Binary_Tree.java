package leetcode;

import java.util.*;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * Medium
 * #Tree, #DFS, #Binary Tree
 * Facebook
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined
 * between two nodes p and q as the lowest node in T that has both p and q as descendants
 * (where we allow a node to be a descendant of itself).”
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
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 *
 * Example 3:
 *   1
 *  /
 * 2
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 * Constraints:
 * The number of nodes in the tree is in the range [2, 10^5].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 */
public class _0236_Lowest_Common_Ancestor_Binary_Tree {

    /**
     * todo 再读读
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) { // p, q 共有同一个 parent node
            return root;
        }

        return left != null ? left : right;
    }


    /**
     * 效率不如 前一种
     *
     * 1. traverse tree, 直到遇到 p 或者 q. 遍历的同时:
     *    - 记录 parent-child mapping
     *    - 记录 遍历的顺序 (stack ? queue)
     * 2. 从 p 开始, 通过 parent Map, 往上记录其所有的 ancestor nodes, 直到顶
     *    - 这些 ancestors 里, 肯定会有 q 的 ancestor
     * 3. 所以在 ancestor nodes 里, 找 q 的 ancestor
     *
     * 时间 O(n) + O(2 x logn) -> 遍历树 + 找 p 的 path + 找 q 的 path
     * 空间 O(2 x n) + O(logn) -> map + stack/queue + set
     */
    public TreeNode lowestCommonAncestor_iterative(TreeNode root, TreeNode p, TreeNode q) {
        // <child node, parent node>
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        // 记录 tree traverse 路过的 nodes
        Stack<TreeNode> stack = new Stack<>();

        parentMap.put(root, null);
        stack.push(root);

        while (!parentMap.containsKey(p) || !parentMap.containsKey(q)) {
            TreeNode node = stack.pop();

            if (node.left != null) {
                parentMap.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parentMap.put(node.right, node);
                stack.push(node.right);
            }
        }

        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parentMap.get(p);
        }
        while (!ancestors.contains(q)) {
            q = parentMap.get(q);
        }

        return q;
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
