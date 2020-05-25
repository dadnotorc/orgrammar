/*
Medium
#Tree, #BST
 */
package leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * 1008. Construct Binary Search Tree (BST) from Preorder Traversal
 *
 * Return the root node of a binary search tree that matches the given preorder traversal.
 *
 * (Recall that a binary search tree is a binary tree where for every node,
 * any descendant of node.left has a value < node.val,
 * and any descendant of node.right has a value > node.val.
 *
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
 *
 * Example 2:
 * Input: [8,5,1,7,11,10,12]
 * Output: [8,5,11,1,7,10,12]
 *
 *          8
 *       /    \
 *      5     11
 *    /  \   /  \
 *  1    7  10   12
 */
public class _1008_ConstructBinarySearchTreeFromPreorderTraversal {

    /**
     * Iterative with Stack
     *
     * 易错点
     * 1. peek() stack的时候, 别忘了判断是stack是否为空
     * 2. 创建left 或者 right child node之后, 别忘了将其加入push进stack里
     */
    public TreeNode bstFromPreorder_iterative(int[] preorder) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);

        for (int i = 1; i < preorder.length; i++) {
            int childVal = preorder[i];
            TreeNode parentNode = stack.peek(); // 注意 这里是peek, 不能pop

            if (childVal < parentNode.val) {
                parentNode.left = new TreeNode(childVal);
                stack.push(parentNode.left);
            } else {
                parentNode = stack.pop();
                while (!stack.isEmpty() && childVal > stack.peek().val) { // 别忘了判断stack是否为空
                    parentNode = stack.pop();
                }
                parentNode.right = new TreeNode(childVal);
                stack.push(parentNode.right);
            }
        }

        return root;
    }

    /**
     * DFS
     *
     * time: O(n)
     */

    int index = 0; // 使用member variable记录array index

    public TreeNode bstFromPreorder_recursive(int[] preorder) {
        return helper(preorder, Integer.MAX_VALUE);
    }

    private TreeNode helper(int[] preorder, int upperBound) {
        if (index == preorder.length || preorder[index] > upperBound ) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[index]);
        index++;

        /*
        当创建node=1时, 传入的uppeBound = 5 (通过node.left传入)
        preorder中下一个值为7, 7 > node.val(1) 而且 7 >　upperBound(5)
        所以1的left与right都为null
         */
        node.left = helper(preorder, node.val);
        node.right = helper(preorder, upperBound);

        return node;
    }





    class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }



    @Test
    public void test1() {
        TreeNode root = bstFromPreorder_recursive(new int[] {8,5,1,7,10,12});
    }
}