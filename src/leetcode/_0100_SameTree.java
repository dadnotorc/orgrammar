/*
Easy
#Tree, #DFS
 */
package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 100. Same Tree
 *
 * Given two binary trees, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 *
 * Example 1:
 * Input:     1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 * Output: true
 *
 * Example 2:
 * Input:     1         1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 * Output: false
 *
 * Example 3:
 * Input:     1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 * Output: false
 */
public class _0100_SameTree {


    /**
     * Iterative
     * 使用一个stack/queue, 同时记录相同位置的节点
     *
     * 如果使用stack的话, 注意push节点的顺序 和 pop节点的顺序 是相反.
     */
    public boolean isSameTree_iterative_1queue(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);

        while (queue.size() > 1) {
            TreeNode a = queue.poll();
            TreeNode b = queue.poll();
            if (a == null && b == null) { continue; }
            if (a == null || b == null) { return false; }
            if (a.val != b.val) { return false; }

            // 这里注意, 两棵树上同一位置节点要成对的添加
            queue.offer(a.left);
            queue.offer(b.left);
            queue.offer(a.right);
            queue.offer(b.right);
        }

        return queue.size() == 0;
    }



    /**
     * Iterative
     * 使用两个stack/queue, 分别记录两个树上的节点
     */
    public boolean isSameTree_iterative_2stacks(TreeNode p, TreeNode q) {
        Stack<TreeNode> stackP = new Stack<>();
        Stack<TreeNode> stackQ = new Stack<>();
        stackP.push(p);
        stackQ.push(q);

        while (!stackP.isEmpty() && !stackQ.isEmpty()) {
            TreeNode a = stackP.pop();
            TreeNode b = stackQ.pop();

            if (a == null && b == null) { continue; }
            if (a == null || b == null) { return false; }
            if (a.val != b.val) { return false; }
            stackP.push(a.right);
            stackP.push(a.left);
            stackQ.push(b.right);
            stackQ.push(b.left);
        }

        // 循环结束, 别忘了看看两个stack是否皆为空. 如果有一者不为空 -> false
        return stackP.isEmpty() && stackQ.isEmpty();
    }



    /**
     * Recursion
     * 判断当前对应位置的两个节点:
     * 1. 两者皆为空 -> true
     * 2. 只有一个为空 -> false
     * 3. 两者皆不为空
     *    a. 两者值不相同 -> false
     *    b. 两者值相同 -> 对左右子节点进行递归
     */
    public boolean isSameTree_recursion(TreeNode p, TreeNode q) {
        if (p == null && q == null) { return true; }
        if (p == null || q == null) { return false; }
        if (p.val != q.val) { return false; }
        return isSameTree_recursion(p.left, q.left)  && isSameTree_recursion(p.right, q.right);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
