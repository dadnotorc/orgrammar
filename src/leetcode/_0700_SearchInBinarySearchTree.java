/*
Easy
#Tree, #BST
 */
package leetcode;

/**
 * 700. Search in a Binary Search Tree
 *
 * Given the root node of a BST and a value. You need to find the node in the BST that
 * the node's value equals the given value. Return the subtree rooted with that node.
 * If such node doesn't exist, you should return NULL.
 *
 * For example,
 * Given the tree:
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 * And the value to search: 2
 * You should return this subtree:
 *       2
 *      / \
 *     1   3
 * In the example above, if we want to search the value 5, since there is no node with value 5, we should return NULL.
 *
 * Note that an empty tree is represented by NULL, therefore you would see the expected output
 * (serialized tree format) as [], not null.
 */
public class _0700_SearchInBinarySearchTree {

    /**
     * 循环做法
     */
    public TreeNode searchBST_iterative(TreeNode root, int val) {
        while (root != null && root.val != val) {
            root = root.val < val ? root.right : root.left;
        }
        return root;
    }

    /**
     * 也是递归, 但是更简洁
     */
    public TreeNode searchBST_recursion(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root; // if root is null, return root is the same as return null
        }

        return root.val < val ? searchBST_recursion(root.right, val) : searchBST_recursion(root.left, val);
    }


    /**
     * 递归做法
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }

        TreeNode node;
        if (root.val < val) {
            node = searchBST(root.right, val);
        } else {
            node = searchBST(root.left, val);
        }
        return node;
    }




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
