/*
Medium
#Tree
 */
package leetcode;

import util.TreeNode;

import java.util.Stack;

/**
 * 108. Construct Binary Search Tree from Preorder Traversal
 *
 * Return the root node of a binary search tree that matches the given preorder traversal.
 * (Recall that a binary search tree is a binary tree where for every node,
 * any descendant of node.left has a value < node.val,
 * and any descendant of node.right has a value > node.val.
 * Also recall that a preorder traversal displays the value of the node first,
 * then traverses node.left, then traverses node.right.)
 *
 * Note:
 * - 1 <= preorder.length <= 100
 * - The values of preorder are distinct.
 *
 * Example 1:
 * Input: [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 *
 *          8
 *       /    \
 *      5     10
 *    /  \      \
 *  1    7       12
 */
public class _0108_ConstructBinarySearchTreeFromPreorderTraversal {

    /**
     * 易错点
     * 1. peek() stack的时候, 别忘了判断是stack是否为空
     * 2. 创建left 或者 right child node之后, 别忘了将其加入push进stack里
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);

        for (int i = 1; i < preorder.length; i++) {
            int val = preorder[i];
            TreeNode node = stack.peek();

            if (val < node.val) {
                node.left = new TreeNode(val);
                stack.push(node.left);
            } else {
                node = stack.pop();
                while (!stack.isEmpty() && val > stack.peek().val) { // 别忘了判断stack是否为空
                    node = stack.pop();
                }
                node.right = new TreeNode(val);
                stack.push(node.right);
            }
        }

        return root;
    }
}