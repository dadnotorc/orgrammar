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
 * Given a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
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
 *
 * 等同 leetcode 101. Symmetric Tree
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
     *
     * 此解法重点:
     * 1. 分析清楚3种应当return的情况, 加上1种应当继续递归的情况
     * 2. 递归时, 比较node1.left vs node2.right && node1.right vs node2.left
     */
    public boolean isSymmetric_recursion(TreeNode root) {
        if (root == null)
            return true; // 注意! root为空时, 也是对称的

        return isCurLevelSymmetric(root.left, root.right);
    }

    private boolean isCurLevelSymmetric(TreeNode n1, TreeNode n2) {
        if (n1 == null || n2 == null) {
            return n1 == n2; // 有一者为null时, 另一者必须也为null, 否则返回false
        }

        return (n1.val == n2.val) && isCurLevelSymmetric(n1.left, n2.right) && isCurLevelSymmetric(n1.right, n2.left);

//        if (n1 == null && n2 == null)
//            return true;
//
//        if (n1 == null || n2 == null || n1.val != n2.val)
//            return false;
//
//        return helper(n1.left, n2.right) && helper(n1.right, n2.left);
    }


    /**
     * BFS + Stack / Queue (LinkedList)
     *
     * 注意Queue本身是不支持 null element的. 但是LinkedList可以
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode n1 = queue.poll();
            TreeNode n2 = queue.poll();

            if (n1 == null && n2 == null) {
                continue; // 注意! 这里不能 return null, 因为还有别的node没比较呢
            }

            if (n1 == null || n2 == null || n1.val != n2.val) {
                return false;
            }

            queue.offer(n1.left);
            queue.offer(n2.right);
            queue.offer(n1.right);
            queue.offer(n2.left);
        }

        return true; // 别忘了最后这个return
    }



    /**
     * 解法太复杂了...
     *
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
    public boolean isSymmetric_2(TreeNode root) {
        if (root == null)
            return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Deque<Optional<TreeNode>> deque = new ArrayDeque<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                deque.offerLast(Optional.ofNullable(node));
                if (node != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }

            if (!isCurLevelSymmetric(deque)) // 当前层不对称 -> 返回false； 对称 -> 继续下一层的判断
                return false;

        }
        return true;
    }

    private boolean isCurLevelSymmetric(Deque<Optional<TreeNode>> deque) {
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
