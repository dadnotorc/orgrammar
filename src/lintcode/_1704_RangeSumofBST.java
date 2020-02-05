/*
Medium
Recursion, Tree
Amazon, Facebook, Microsoft
 */
package lintcode;

import util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1704. Range Sum of BST
 *
 * Given the root node of a binary search tree, return the sum of values
 * of all nodes with value between L and R (inclusive).
 *
 * The binary search tree is guaranteed to have unique values.
 *
 * Notice
 * - The number of nodes in the tree is at most 10000.
 * - The final answer is guaranteed to be less than 2^31.
 *
 * Example 1:
 *
 * Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
 * Output: 32
 *
 * Example 2:
 * Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * Output: 23
 */
public class _1704_RangeSumofBST {

    /**
     * 使用 DFS.
     * L < node 时, 左子树仍有可能大于 L, 所有继续搜索左子树
     * node < R 时, 右子树仍有可能小于 R, 所有继续搜索右子树
     * 这样的判断保证了:
     * L < node < R 时, 搜索左右子树
     * node <= L < R 时, 左子树只会更小, 但右子树可能符合条件, 所以只搜索右子树
     * L < R <= node 时, 右子树只会更大, 但左子树可能符合条件, 所以只搜索左子树
     */
    public int rangeSumBST_recursion(TreeNode root, int L, int R) {
        int sum = 0;

        if (root == null)
            return sum;

        if (root.val >= L && root.val <= R)
            sum += root.val;

        if (root.val > L)
            sum += rangeSumBST_recursion(root.left, L, R);

        if (root.val < R)
            sum += rangeSumBST_recursion(root.right, L, R);

        return sum;
    }

    public int rangeSumBST_iterative(TreeNode root, int L, int R) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int ans = 0;

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node != null) {
                if (node.val >= L && node.val <= R)
                    ans += node.val;

                if (node.val > L)
                    q.offer(node.left);

                if (node.val < R)
                    q.offer(node.right);
            }
        }

        return ans;
    }
}