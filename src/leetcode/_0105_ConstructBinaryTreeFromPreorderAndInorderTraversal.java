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
public class _0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    // 面试时, 需要确认是否有 duplicate

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
        return helper(preorder, 0, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode helper(int[] preorder, int preIndex,
                            int[] inorder, int inStart, int inEnd, Map<Integer, Integer> map) {
        if (preIndex > preorder.length - 1 || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preIndex]);
        int inIndex = map.get(root.val);
        // 从 inStart 开始到 inIndex - 1 都为 root 的左子树
        // 从 inIndex + 1 开始到 inEnd 都为 root 的右子树

        // 左子节点 - preorder 中 preIndex + 1 即为当前节点的左子节点; inorder 中从 inStart 到 inIndex - 1
        // 为左子树中的节点们

        // 右子节点 - (inIndex-inStart) = 左子树的大小, 所以 preorder 中 preIndex + (inIndex -
        // inStart) + 1 即跳过左子树, 找到第一个右子节点
        // inorder 中 inIndex + 1 到 inEnd 为右子树中的节点们

        root.left = helper(preorder, preIndex + 1, inorder, inStart, inIndex - 1, map);
        root.right = helper(preorder, preIndex + (inIndex - inStart) + 1, inorder, inIndex + 1, inEnd, map);

        return root;
    }

    // 获得inorder中节点值与其下标对应的mapping
    private Map<Integer, Integer> getInorderIndex(int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return map;
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
