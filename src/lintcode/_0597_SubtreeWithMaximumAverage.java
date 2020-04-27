/*
Easy
#Binary Tree, #DFS, #Divide and Conquer
Amazon
Ladder
 */
package lintcode;

import util.TreeNode;

/**
 * 597. Subtree with Maximum Average
 *
 * Given a binary tree, find the subtree with maximum average. Return the root of the subtree.
 *
 * Notice
 * - LintCode will print the subtree which root is your return node.
 * - It's guaranteed that there is only one subtree with maximum average.
 *
 * Example 1
 * Input: {1,-5,11,1,2,4,-2}
 * Output: 11
 * Explanation:
 *      1
 *    /   \
 *  -5     11
 *  / \   /  \
 * 1   2 4    -2
 * The average of subtree of 11 is 4.3333, is the maximum.
 *
 * Example 2
 * Input: {1,-5,11}
 * Output: 11
 * Explanation:
 *      1
 *    /   \
 *  -5     11
 * The average of subtree of 1,-5,11 is 2.333,-5,11.
 * So the subtree of 11 is the maximum.
 */
public class _0597_SubtreeWithMaximumAverage {

    /**
     * 用ResultType记录以当前节点为root, 其下所有节点的sum, 以及节点数量 (包括root本身)
     * 用此代表平均值 (避免计算)
     */

    class ResultType {
        int sum, size;
        public ResultType (int sum, int size) {
            this.sum = sum;
            this.size = size;
        }
    }

    // 使用member variables记录最大平均值 及 对应的node
    private TreeNode maxAverageNode = null;
    private ResultType maxAverageRT = new ResultType(0, 0);

    public TreeNode findSubtree2(TreeNode root) {
        helper(root);
        return maxAverageNode;
    }

    /**
     * 分治法 - 此函数作用有两点:
     * 1. 分别找出左右子节点的平均值(既ResultType), 用此二者加上当前节点 算出当前节点平均值
     * 2. 比较并更新最大平均值及其对应节点
     *
     * 易错点:
     * 1. 比较平均值之前, 记得判断maxAverageNode是否为空, 如果是, 直接赋值
     */
    private ResultType helper(TreeNode node) {
        if (node == null)
            return new ResultType(0, 0);

        ResultType leftRT = helper(node.left);
        ResultType rightRT = helper(node.right);

        ResultType curRT = new ResultType(
                leftRT.sum + rightRT.sum + node.val,
                leftRT.size + rightRT.size + 1);

        // 如果最大平均值未找到 或者 当前node平均值 >= 已知最大平均值
        if (maxAverageNode == null
                || curRT.sum * maxAverageRT.size >= maxAverageRT.sum * curRT.size) {

            // 用乘法避免产生float number
            // (cur.sum / cur.size) >= (max.sum / max.size) 既是 (cur.sum * max.size) >= (max.sum * cur.size)

            maxAverageNode = node;
            maxAverageRT = curRT;
        }

        return curRT;
    }
}
