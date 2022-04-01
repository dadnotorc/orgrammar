package leetcode;

import java.util.HashSet;

/**
 * 1650. Lowest Common Ancestor of a Binary Tree III
 * Medium
 * #Prime
 * Facebook meta, LinkedIn, Microsoft
 *
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 *
 * Each node will have a reference to its parent node. The definition for Node is below:
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
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
 * Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
 *
 * Example 3:
 *    1
 *   /
 *  2
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 10^5].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 * p and q exist in the tree.
 */
public class _1650_Lowest_Common_Ancestor_Binary_Tree_III_parent_node {

    /*
     * 由于节点中包含父节点的指针，所以二叉树的根节点就没必要输入了。
     *
     * 这道题其实不是公共祖先的问题，而是单链表相交的问题，你把 parent 指针想象成单链表的 next 指针，题目就变成了：
     * 给你输入两个单链表的头结点 p 和 q，这两个单链表必然会相交，请你返回相交点。
     */

    /**
     * 双指针, i j 分别从 p q 开始走, 每次一步, 直到走到 i == j
     * 如果 i 走到 根节点, 转到 q 节点从头走
     * 如果 j 走到 根节点, 转到 p 节点从头走
     */
    public TreeNode lowestCommonAncestor_1(TreeNode p, TreeNode q) {
        TreeNode i = p, j = q;

        while (i != j) {
            if (i == null) {
                i = q;
            } else {
                i = i.parent;
            }

            if (j == null) {
                j = p;
            } else {
                j = j.parent;
            }
        }

        return i;
    }


    /**
     * 解法 2 - 使用 hashset 记录 从 p 到根节点 路径中的所有节点
     * 再从 q 节点开始往根节点走, 直到遇到 p 路径中已有的节点
     */

    public TreeNode lowestCommonAncestor_2(TreeNode p, TreeNode q) {
        HashSet<TreeNode> set = new HashSet<>();
        TreeNode cur = p;

        while (cur != null) {
            set.add(cur);
            cur = cur.parent;
        }

        cur = q;
        while (!set.contains(cur)) {
            cur = cur.parent;
        }

        return cur;
    }



    class TreeNode {
        int val;
        TreeNode left, right, parent;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = this.parent = null;
        }
    }
}
