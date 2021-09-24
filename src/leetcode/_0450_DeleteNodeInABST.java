/*
Medium
#Tree, #BFS, #Binary Tree
 */
package leetcode;

/**
 * 450. Delete Node in a BST
 *
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 * 1. Search for a node to remove.
 * 2. If the node is found, delete the node.
 *
 * Follow up: Can you solve it with time complexity O(height of tree)?
 *
 * Example 1:
 *     5             5
 *    / \           / \
 *   3   6    ->   4   6
 *  / \   \       /    \
 * 2  4    7     2      7
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 * Explanation: The tree does not contain a node with value = 0.
 *
 * Example 3:
 * Input: root = [], key = 0
 * Output: []
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 10^4].
 * - -10^5 <= Node.val <= 10^5
 * - Each node has a unique value.
 * - root is a valid binary search tree.
 * - -10^5 <= key <= 10^5
 */
public class _0450_DeleteNodeInABST {

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode node = root;
        TreeNode parentNode = null;
        while (node != null && node.val != key) {
            parentNode = node;
            if (node.val < key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        if (node == null) {
            return root;
        }

        if (node.left != null && node.right != null) {
            TreeNode p = node.right;
            TreeNode pp = node;
            while (p.left != null) {
                pp = p;
                p = p.left;
            }
            node.val = p.val;
            node = p;
            parentNode = pp;
        }

        TreeNode childNode = node.left != null ? node.left : node.right;

        if (parentNode == null) {
            root = childNode;
        } else if (parentNode.left == node) { // 将node删去, 直接将parentNode与childNode相连
            parentNode.left = childNode;
        } else {
            parentNode.right = childNode;
        }
        return root;
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
