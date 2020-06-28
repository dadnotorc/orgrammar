/*
Medium
#Tree, #DFS
 */
package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 129. Sum Root to Leaf Numbers
 *
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Input: [1,2,3]
 *     1
 *    / \
 *   2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 *
 * Example 2:
 * Input: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 */
public class _0129_SumRootToLeafNumbers {


    /**
     * 使用循环
     */
    class ResultType {
        TreeNode node;
        Integer val;
        ResultType(TreeNode node, Integer val) {
            this.node = node;
            this.val = val;
        }
    }

    public int sumNumbers(TreeNode root) {
        int res = 0;
        Queue<ResultType> q = new LinkedList<>();
        q.offer(new ResultType(root, 0));
        while (!q.isEmpty()) {
            ResultType rt = q.poll();
            if (rt.node != null) {
                int cur = rt.val * 10 + rt.node.val;
                if (rt.node.left == null && rt.node.right == null) {
                    res += cur;
                    continue;
                }
                q.offer(new ResultType(rt.node.left, cur));
                q.offer(new ResultType(rt.node.right, cur));
            }
        }
        return res;
    }


    /**
     * 递归, 使用 member variable
     */
    int res = 0;

    public int sumNumbers_recursion_1(TreeNode root) {
        helper_1(root, 0);
        return res;
    }

    private void helper_1(TreeNode node, int cur) {
        if (node == null) {
            return;
        }

        cur = cur * 10 + node.val;

        if (node.left == null && node.right == null) {
            res += cur;
            return;
        }

        helper_1(node.left, cur);
        helper_1(node.right, cur);
    }


    /**
     * 递归, 不使用member variable
     */
    public int sumNumbers__recursion_2(TreeNode root) {
        return helper(root, 0);
    }

    private int helper(TreeNode node, int cur) {
        if (node == null) {
            return 0;
        }

        cur = cur * 10 + node.val;

        if (node.left == null && node.right == null) {
            return cur;
        }

        return helper(node.left, cur) + helper(node.right, cur);
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
