/*
Easy
#DFS, #Binary Tree
Amazon
Ladder
 */
package lintcode;

import util.TreeNode;

/**
 * 628. Maximum Subtree
 *
 * Given a binary tree, find the subtree with maximum sum. Return the root of the subtree.
 *
 * Example 1:
 * Input: {1,-5,2,0,3,-4,-5}
 * Output:3
 * Explanation:
 *      1
 *    /   \
 *  -5     2
 *  / \   /  \
 * 0   3 -4  -5
 * The sum of subtree 3 (only one node) is the maximum. So we return 3.
 *
 * Example 2:
 * Input: {1}
 * Output:1
 * Explanation:
 * There is one and only one subtree in the tree. So we return 1.
 *
 * Notice
 * - LintCode will print the subtree which root is your return node.
 * - It's guaranteed that there is only one subtree with maximum sum and the given binary tree is not an empty tree.
 */
public class _0628_MaximumSubtree {

    int maxSum = Integer.MIN_VALUE; // 注意 初始值去MIN_VALUE, 而不是0
    TreeNode maxSumNode = null;

    public TreeNode findSubtree(TreeNode root) {
        getSum(root);
        return maxSumNode;
    }

    /*
    此函数返回, 以当前节点为root的subtree的sum
    计算过程中, 如果找到大于maxSum的情况, 更新maxSum以及maxSumNode
     */
    private int getSum(TreeNode node) {
        if (node == null)
            return 0;

        int left = getSum(node.left);
        int right = getSum(node.right);

        int sum = node.val + left + right;

        if (sum > maxSum) {
            maxSum = sum;
            maxSumNode = node;
        }

        return sum;
    }
}
