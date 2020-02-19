/*
Easy
#Binary Tree
Facebook, Google
 */
package lintcode;

import util.TreeNode;

/**
 * 595. Binary Tree Longest Consecutive Sequence
 *
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node
 * in the tree along the parent-child connections. The longest cconsecutiveonsecutive path
 * need to be from parent to child (cannot be the reverse).
 *
 * Example 1:
 * Input:
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 * Output:3
 * Explanation:
 * Longest consecutive sequence path is 3-4-5, so return 3.
 * Example 2:
 *
 * Input:
 *    2
 *     \
 *      3
 *     /
 *    2
 *   /
 *  1
 * Output:2
 * Explanation:
 * Longest consecutive sequence path is 2-3,not 3-2-1, so return 2.
 *
 * 题目要求寻找 1.连续 2.递增 的最长序列长度
 */
public class _0595_BinaryTreeLongestConsecutiveSequence {

    /**
     * DFS
     * 递归定义: 返回从当前节点开始, 能够组成的最长的连续递增序列的长度
     * 递归出口: 如当前node为空, 返回长度为0 (因为从当前节点开始, 无法组成连续递增序列)
     * 递归拆解:
     * 1. 如果当前节点的值==父节点值(如不空)+1, 则递增最长序列长度; 否则, 重置长度为1
     * 2. Divide - 分别递归进入左右子树, 获得以左右节点为root开始的最长长度
     * 3. Conquer - 比较 a)以左节点为根的最大长度, b)以右节点为根的最大长度, c)当前节点的最长长度(步骤1中获得的值) - 取最大值返回
     */
    public int longestConsecutive(TreeNode root) {
        return dfs(root, null, 0);
    }

    private int dfs(TreeNode node, TreeNode parent, int preMaxLen) {
        if (node == null)
            return  0;

        int curMaxLen = (parent != null && (node.val == parent.val + 1))
                ? preMaxLen + 1
                : 1;

        int left = dfs(node.left, node, curMaxLen);
        int right = dfs(node.right, node, curMaxLen);
        return Math.max(curMaxLen, Math.max(left, right));
    }

    /**
     * 解法2 - 也是DFS, 使用ResultType class
     * ResultType包含两个值:
     * a) 从当前节点出发, 能够组成的最长的连续递增序列的长度
     * b) 以当前节点为root, 其子树中最大/最长的长度
     *
     * 递归定义: 返回当前节点的ResultType
     * 递归出口: 如当前node为空, 返回空的ResultType (因为从当前节点开始, 无法组成连续递增序列)
     * 递归拆解:
     * 1. Divide - 分别递归进入左右子树, 获得各自的ResultType
     * 2. Conquer - 对比当前节点及左右子节点, 如果当前节点的值+1==子节点值(如不空)+1,
     *    取3者中ResultType a)最大值放入当前节点, 并更新当前节点ResultType b)
     */
    public int longestConsecutive_2(TreeNode root) {
        return dfs_2(root).maxInSubtree;
    }

    private ResultType dfs_2(TreeNode node) {
        if (node == null)
            return new ResultType(0, 0);

        ResultType left = dfs_2(node.left);
        ResultType right = dfs_2(node.right);

        // maxFromCurNode = 1 是指当前节点本身
        ResultType curResult = new ResultType(0, 1);

        if (node.left != null && (node.val + 1 == node.left.val))
            curResult.maxFromCurNode = Math.max(curResult.maxFromCurNode, left.maxFromCurNode + 1);

        if (node.right != null && (node.val + 1 == node.right.val))
            curResult.maxFromCurNode = Math.max(curResult.maxFromCurNode, right.maxFromCurNode + 1);

        curResult.maxInSubtree = Math.max(
                curResult.maxFromCurNode,
                Math.max(left.maxInSubtree, right.maxInSubtree));

        return curResult;
    }

    private class ResultType {
        int maxInSubtree;
        int maxFromCurNode;
        public ResultType(int maxInSubtree, int maxFromCurNode) {
            this.maxInSubtree = maxInSubtree;
            this.maxFromCurNode = maxFromCurNode;
        }
    }
}
