package lintcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1126 · Merge Two Binary Trees
 * Easy
 * #DFS, #Binary Tree, #Divide and Conquer
 * Amzon
 *
 * Given two binary trees and imagine that when you put one of them to cover the other,
 * some nodes of the two trees are overlapped while the others are not.
 *
 * You need to merge them into a new binary tree. The merge rule is that if two nodes overlap,
 * then sum node values up as the new value of the merged node.
 * Otherwise, the NOT null node will be used as the node of new tree.
 *
 * The merging process must start from the root nodes of both trees.
 *
 * Example1
 * Input:
 * {1,3,2,5}
 * {2,1,3,#,4,#,7}
 * Output:
 * {3,4,5,5,4,#,7}
 * Explanation:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 *
 * Merged tree:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 *
 * Example2
 * Input:
 * {1}
 * {1,2}
 * Output:
 * {2,2}
 */
public class _1126_Merge_Two_Binary_Trees {

    /**
     * 基础的二叉树遍历。
     * 类似于树的先序遍历，合并的时候先合并根节点，之后递归的合并左右子树。
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) { return t2; }
        if (t2 == null) { return t1; }

        TreeNode t3 = new TreeNode(t1.val + t2.val);
        t3.left = mergeTrees(t1.left, t2.left);
        t3.right = mergeTrees(t1.right, t2.right);
        return t3;
    }




    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
