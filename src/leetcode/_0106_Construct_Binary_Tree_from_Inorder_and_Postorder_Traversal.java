package leetcode;

import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Medium
 * #Array, #Hash Table, #Divde and Conquer, #Tree, #Binary Tree
 *
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree
 * and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 *
 * Example 1:
 *   3
 *  / \
 * 9   20
 *     / \
 *   15   7
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 *
 * Example 2:
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 *
 *
 * Constraints:
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder and postorder consist of unique values.
 * Each value of postorder also appears in inorder.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * postorder is guaranteed to be the postorder traversal of the tree.
 */
public class _0106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {

    // 面试时, 需要确认是否有 duplicate, 假设没有

    /*
    inorder - 左 上 右; postorder - 左 右 上
     */

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = getInorderIndex(inorder);
        return constructTree(
                inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1,
                map);
    }

    private Map<Integer, Integer> getInorderIndex(int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return map;
    }

    private TreeNode constructTree(int[] inorder, int inStart, int inEnd,
                                   int[] postorder, int postStart, int postEnd,
                                   Map<Integer, Integer> map) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd]);
        int inIndex = map.get(root.val);

        // 左子树
        // inorder 中从 inStart 到 inIndex - 1 为 root 左子树
        // postorder 中 postEnd - rightSize -1 为 root 的左子节点;
        //          从 postStart 到 postEnd - rightSize -1 为左子树

        // 右子树的大小 rightSize = inEnd - inIndex

        // 右子树
        // inorder 中从 inIndex + 1 到 inEnd 为 root 右子树
        // postorder 中 postEnd - 1 为 root 右子节点
        //           从 postEnd - rightSize 到 postEnd - 1 为右子树

        int rightSize = inEnd - inIndex;

        root.left = constructTree(
                inorder, inStart, inIndex - 1,
                postorder, postStart, postEnd - rightSize - 1,
                map);

        root.right = constructTree(
                inorder, inIndex + 1, inEnd,
                postorder, postEnd - rightSize, postEnd - 1,
                map);

        return root;
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
