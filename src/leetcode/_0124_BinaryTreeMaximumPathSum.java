/*
Hard
#Tree, #DFS, #postorder
 */
package leetcode;

import util.TreeNode;

/**
 * 124. Binary Tree Maximum Path Sum
 *
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node
 * to any node in the tree along the parent-child connections. The path must contain
 * at least one node and does not need to go through the root.
 *
 * Example 1:
 * Input: [1,2,3]
 *        1
 *       / \
 *      2   3
 * Output: 6
 *
 * Example 2:
 * Input: [-10,9,20,null,null,15,7]
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: 42
 */
public class _0124_BinaryTreeMaximumPathSum {

    int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        oneSideMax(root);
        return maxSum;
    }

    /**
     * helper - 后序遍历
     * 1. 考虑以当前节点为root, subtree的最大sum, 即左子树最大值 + 右子树最大值 + 当前节点值
     *    注意, 如果左右子树最大值为负值, 可选择不取此子树 (将其最大值定为0)
     * 2. 比较当前最大值与全局最大值
     * 3. helper返回值为, 以当前节点为父节点, 单一子树方向的最大值 (parent-child), 只考虑左右子树中较大者, 将其与当前节点值相加
     *
     * 易错点:
     * 1. 在helper返回值里要注意, 类似计算curMax, 左右子树可能为负, 此时考虑不取 (将其最大值定为0)
     *
     * 观察#1和#3后, 发现如果左右子树为负, 两者都会将其值定为0, 所以获得子树最大和之时即可比较, 之后即无需多次比较
     */
    private int oneSideMax(TreeNode node) {
        if (node == null)
            return 0;
        int left = Math.max(oneSideMax(node.left), 0); // 获得左子树中的最大值, 若小于0, 则取0
        int right = Math.max(oneSideMax(node.right), 0); // 获得右子树中的最大值, 若小于0, 则取0
        int curMax = left + right + node.val; // 以当前node为root, curMax为其下最大的path sum

        if (curMax > maxSum)
            maxSum = curMax;

        return Math.max(left, right) + node.val; // 只取左右子树中的较大者与当前节点组合


        // 将左右子树值与0的比较提出来, 无需多次比较

//        int left = helper(node.left);
//        int right = helper(node.right);
//        int curMax = Math.max(left, 0) + Math.max(right, 0) + node.val;
//
//        if (curMax > maxSum)
//            maxSum = curMax;
//
//        // 注意! 如果左子树或者右子树为负数, 我们可不取, 只取当前节点值 (0 + node.val)
//        return Math.max(Math.max(left, right),0) + node.val;
    }
}
