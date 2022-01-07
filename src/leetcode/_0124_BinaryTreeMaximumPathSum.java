/*
Hard
#Binary Tree, #DFS, #postorder, #DP
FB Meta
 */
package leetcode;

import util.TreeNode;

/**
 * 124. Binary Tree Maximum Path Sum
 *
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence
 * has an edge connecting them. A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 * Example 1:
 * Input: [1,2,3]
 *        1
 *       / \
 *      2   3
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 *
 * Example 2:
 * Input: [-10,9,20,null,null,15,7]
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 3 * 10^4].
 * -1000 <= Node.val <= 1000
 *
 * lintcode 94. Binary Tree Maximum Path Sum
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


        // 以下解法也对, 但是需要将左右子树值与 0 重复比较, 不如上面的解法, 将比较提出来, 减少比较次数

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
