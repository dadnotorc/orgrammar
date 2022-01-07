/*
Medium
#Recursion, #Divide and Conquer, #DP
Amazon, FB Meta, Microsoft
FAQ
 */
package lintcode;

import util.TreeNode;

/**
 * 94. Binary Tree Maximum Path Sum
 *
 * Given a binary tree, find the maximum path sum.
 * The path may start and end at any node in the tree.
 *
 * Example 1:
 * Input:  For the following binary tree（only one node）:
 * 2
 * Output：2
 *
 * Example 2:
 * Input:  For the following binary tree:
 *
 *      1
 *     / \
 *    2   3
 * Output: 6
 *
 * Example from LeetCode
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 *
 * leetcode 124. Binary Tree Maximum Path Sum
 */
public class _0094_BinaryTreeMaximumPathSum {

    /**
     * 解法1 - 使用 member variable
     *
     * 易错点:
     * 1. 计算左右single path max的时候, 注意pathSum可能为负值, 此时则取0 (即不取此子树)
     */
    int ans;

    public int maxPathSum(TreeNode root) {
        // 注意:
        // 1. ans必须是global variable, 如果在这里init, 传入dfs, 传入的是值, 而不是指针
        // 2. 初始值必须是Integer.MIN_VALUE, 而不能是0. 例如 input: {-1}; output应该也是-1, 而不是0
        ans = Integer.MIN_VALUE;
        getSinglePathMaxDFS(root);
        return ans;
    }

    // 返回从当前节点出发向下(单一子树方向), 能够组成的最大pathSum
    private int getSinglePathMaxDFS(TreeNode node) {
        if (node == null)
            return 0;

        // 注意: 必须取0或者dfs返回值, 因为后者可能返回负值.
        // 这样的话, 可以选择不取子树, 而只取0
        int maxLeft = Math.max(0, getSinglePathMaxDFS(node.left));
        int maxRight = Math.max(0, getSinglePathMaxDFS(node.right));

        // 在每个节点, 用当前节点值加上左右子树各自最大的pathSum
        // 得到以当前节点为拐点, 最大的pathSum
        ans = Math.max(ans, node.val + maxLeft + maxRight);
        return node.val + Math.max(maxLeft, maxRight);
    }



    /**
     * 解法2 - 使用 post order traversal (左右上) + ResultType
     *
     * 易错点:
     * 1. 递归出口, 当node为null时, 返回ResultType(0, MIN). singlePath=0表示不取该点, maxPath=MIN_VALUE因为其他path可能有负数出现
     * 2. 递归拆解, 计算singlePath时, 注意可能返回值为负数, 此时取0
     */
    public class ResultType{
        int singlePath, maxPath;
        public ResultType (int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    public int maxPathSum_2(TreeNode root) {
        ResultType resultType = helper(root);
        return resultType.maxPath;
    }

    public ResultType helper(TreeNode node) {
        if (node == null)
            return new ResultType(0, Integer.MIN_VALUE); // 后者取MIN, 因为可能有负值node存在

        ResultType left = helper(node.left);
        ResultType right = helper(node.right);

        int curSinglePath = node.val + Math.max(left.singlePath, right.singlePath);
        curSinglePath = Math.max(0, curSinglePath); // 如果取此path得到负值, 不然不取 -> 取0

        int curMaxPath = node.val + left.singlePath + right.singlePath; // 当前点加上左右子树的最大path
        int preMaxPath = Math.max(left.maxPath, right.maxPath); // 上一层的最大值
        curMaxPath = Math.max(curMaxPath, preMaxPath);

        return new ResultType(curSinglePath, curMaxPath);
    }
}
