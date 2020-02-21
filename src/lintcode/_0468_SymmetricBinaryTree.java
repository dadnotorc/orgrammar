/*
Easy
#Binary Tree
Amazon
 */
package lintcode;

import util.TreeNode;

import java.util.*;

/**
 * 468. Symmetric Binary Tree
 *
 * Given a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 *
 * Example 1:
 * Input: {1,2,2,3,4,4,3}
 * Output: true
 * Explanation:
 *          1
 *         / \
 *        2   2
 *       / \ / \
 *       3 4 4 3
 * is a symmetric binary tree
 *
 * Example 2:
 * Input: {1,2,2,#,3,#,3}
 * Output: false
 * Explanation:
 *          1
 *         / \
 *         2  2
 *         \   \
 *          3   3
 * is not a symmetric binary tree
 *
 * Challenge
 * - Can you solve it both recursively and iteratively?
 */
public class _0468_SymmetricBinaryTree {

    /**
     * 递归的定义: 对比当前左右节点
     *
     * 递归的出口:
     * a) 如两者皆为null -> 返回true
     * b) 如两者之一为null -> 返回false
     * c) 如两者皆不为null, 且val不同 -> 返回false
     *
     * 递归的拆解: 如两者皆不为null, 且val相同 -> 继续判断下一层递归
     */
    public boolean isSymmetric_recursion(TreeNode root) {
        if (root == null)
            return true;

        return check(root.left, root.right);
    }

    private boolean check(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null)
            return true;

        if (node1 == null || node2 == null)
            return false;

        if (node1.val != node2.val)
            return false;

        return check(node1.left, node2.right) && check(node1.right, node2.left);
    }

    /**
     * BFS - 将每一层节点取出, 判断当前层是否对称
     *
     * ArrayDeque 本身不支持null elements
     * workaround: ArrayDeque<Optional<T>> (java.util.Optional)
     *
     * // Add non-null value
     * queue.add(Optional.of(value))
     *
     * // Add nullable value
     * queue.add(Optional.ofNullable(value))
     *
     * // Add null
     * queue.add(Optional.empty())
     *
     * // Unbox
     * last = queue.pollLast().orElse(null)
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Deque<Optional<TreeNode>> deque = new ArrayDeque<Optional<TreeNode>>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                deque.offerLast(Optional.ofNullable(node));
                if (node != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }

            if (!check(deque)) // 当前层不对称 -> 返回false； 对称 -> 继续下一层的判断
                return false;

        }
        return true;
    }

    private boolean check(Deque<Optional<TreeNode>> deque) {
        while (deque.size() >= 2) {
            TreeNode firstNode = deque.pollFirst().orElse(null);
            TreeNode lastNode = deque.pollLast().orElse(null);

            if (firstNode == null && lastNode == null) {
                continue;
            } else if (firstNode == null || lastNode == null) {
                return false;
            } else if (firstNode.val != lastNode.val) {
                return false;
            }
        }

        return true;
    }
}
