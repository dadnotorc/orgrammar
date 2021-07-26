/*
Easy
#Tree, #DFS

 */
package leetcode;

/**
 * 112. Path Sum
 *
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Given the below binary tree and sum = 22,
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 *
 * 注意：
 * 1. 是整条 path，从 root 到 leaf
 * 2. 题目没有限定是否会有负值，所以 curSum 超过 sum 时，仍应继续
 */
public class _0112_PathSum {

    /**
     * 前序遍历 - top-down - 先计算父节点时的 pathSum，然后传给子节点
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        sum -= root.val;

        if (root.left == null && root.right == null && sum == 0) {
            return true; // 这里不能 return sum == 0。因为其他还有其他 path 仍未访问
        }

        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }


    /**
     * 不能通过 AC 的原因是，如果 root 本身为空，严格上讲，这就不存在一条从根到叶子节点的 path
     */
    public boolean hasPathSum_fail_to_AC(TreeNode root, int sum) {
        if (root == null) {
            return sum == 0;
        }

        sum -= root.val;

        return hasPathSum_fail_to_AC(root.left, sum) || hasPathSum_fail_to_AC(root.right, sum);
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
