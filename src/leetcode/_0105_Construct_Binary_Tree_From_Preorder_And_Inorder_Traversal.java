package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Medium
 * #Array, #Tree, #DFS, #preorder
 * 
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 *
 * Example 1:
 *   3
 *  / \
 * 9   20
 *     / \
 *   15   7
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 
 * Example 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * 
 * Constraints:
 * 1 <= preorder.length = inorder.length <= 3000
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of *unique* values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 */
public class _0105_Construct_Binary_Tree_From_Preorder_And_Inorder_Traversal {

    // 面试时, 需要确认是否有 duplicate, 假设没有

    /*
    preorder - 上 左 右; inorder - 左 上 右
     
    1. 先遍历 inorder, 获得 hashmap<节点值, 下标>
    2. 前续递归
       - 对当前 preorder 中的节点, 通过 hashmap 找到其在 inorder 对应的值
         preorder[0] 为当前节点, 假设在 inorder 中与之对应的是 inorder[4] (preorder[0] = inorder[4])
         则inorder[0]~inorder[3] 属于当前节点的左子树, inorder[5]~inorder[last] 属于根节点的右子树
    
       - 出口: preorder 或者 inorder 已经读完
     */

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = getInorderIndex(inorder);
        return constructTree(
                preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1,
                map);
    }

    // 通过遍历 inorder, 获得 mapping - <节点值, 其对应的 inorder 下标>
    private Map<Integer, Integer> getInorderIndex(int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return map;
    }

    // 递归 构造树
    private TreeNode constructTree(int[] preorder, int preStart, int preEnd,
                                   int[] inorder, int inStart, int inEnd,
                                   Map<Integer, Integer> map) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = map.get(root.val);

        // 左子树
        // preorder 中 preStart + 1 为 root 的左子节点;
        //          从 preStart + 1 到 preStart + leftSize 为左子树
        // inorder 中从 inStart 到 inIndex - 1 为 root 左子树

        // 左子树的大小 leftSize = inIndex - inStart

        // 右数字
        // preorder 中 preStart + leftSize + 1 即跳过左子树, 找到第一个右子节点
        //          从 preStart + leftSize + 1 到 preEnd 为右子树
        // inorder 中从 inIndex + 1 到 inEnd 为 root 右子树

        int leftSize = inIndex - inStart;

        root.left = constructTree(
                preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, inIndex - 1,
                map);

        root.right = constructTree(
                preorder, preStart + leftSize + 1, preEnd,
                inorder, inIndex + 1, inEnd,
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
