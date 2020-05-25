/*
Easy
#Tree, #BST, #DFS
 */
package leetcode;

/**
 * 108. Convert Sorted Array to Binary Search Tree (BST)
 *
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which
 * the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 * Given the sorted array: [-10,-3,0,5,9],
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class _0108_ConvertSortedArrayToBinarySearchTree {

    /**
     * recursion
     *
     * 易错点
     * 1. 计算左右子树时, upper bound 和 lower bound 要使用 mid - 1 和 mid + 1
     */
    public TreeNode sortedArrayToBST_recursive(int[] nums) {
        TreeNode root = helper(nums, 0, nums.length - 1);
        return root;
    }

    private TreeNode helper(int[] nums, int l, int r) {
        if (l > r)
            return null;

        int mid = l + (r - l) / 2;
        TreeNode node = new TreeNode(nums[mid]);

        node.left = helper(nums, l, mid - 1); // 注意! 要 mid -1
        node.right = helper(nums, mid + 1, r); // 注意! 要 mid -1

        return node;
    }


    // todo 想想如何使用 iterative


    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
