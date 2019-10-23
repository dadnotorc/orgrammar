/*
Easy
Tree, Recursion
 */
package leetcode.recursion;

import org.junit.Test;
import util.TreeNode;

import java.util.spi.LocaleNameProvider;

/**
 * 687. Longest Univalue Path
 *
 * Given a binary tree, find the length of the longest path where each node in
 * the path has the same value. This path may or may not pass through the root.
 *
 * The length of path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 * Input:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * Output: 2
 *
 * Example 2:
 * Input:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * Output: 2
 *
 * Note: The given binary tree has not more than 10000 nodes.
 * The height of the tree is not more than 1000.
 */
public class _0687_LongestUnivaluePath {

    Integer longestPath = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null)
            return longestPath;
        helper(root, root.val);
        return longestPath;
    }

    /**
     * 注意 helper 返回值的意义，并不是经过某个结点的最长路径的长度，
     * 最长路径长度保存在了结果 longestPath中，不是返回值，
     * 返回的是以该结点为终点的最长路径长度，这样回溯的时候，我们还可以继续连上其父结点, 判断是否可以组path
     *
     * 递归时, 分别对左右子节点调用递归函数, 得到以左右子节点为终点的最长路径.
     * 当左子节点与当前节点值相同时, 左侧最长路径数 +1, 否则归 0
     * 右子节点同理
     * 最后左右子节点最长路径相加, 更新 longestPath
     * 调用当前节点值的函数, 其返回值只能取左右路径值较大的一方, 因为如果还要与其父节点链接组成 path,
     * 只能在左右子节点中取一, 所以取较大值
     *
     * post order traversal, bottom up
     */
    private int helper(TreeNode node, int preNodeVal) {
        if (node == null)
            return 0;

        int leftPath = helper(node.left, node.val);
        int rightPath = helper(node.right, node.val);

        longestPath = Math.max(longestPath, leftPath + rightPath); // 保存最长路径

        // 获得函数返回值
        if (node.val == preNodeVal) // 若当前节点与其父节点值相等, 为了组path, 取左右子节点中较大者, 并给返回值 +1
            return Math.max(leftPath, rightPath) + 1;
        else
            return 0;
    }

    @Test
    public void test0() {
        TreeNode n0 = new TreeNode(1);
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n0.left = n1;
        n0.right = n2;

        n1.left = n3;
        n1.right = n4;

        n2.right = n5;

        org.junit.Assert.assertEquals(2, longestUnivaluePath(n0));
    }

    @Test
    public void test1() {
        TreeNode n0 = new TreeNode(5);
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(5);

        n0.left = n1;
        n0.right = n2;

        n1.left = n3;
        n1.right = n4;

        n2.left = n5;

        org.junit.Assert.assertEquals(2, longestUnivaluePath(n0));
    }
}
