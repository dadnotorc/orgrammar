package leetcode;

import util.TreeNode;

import java.util.*;

/**
 * 94. Binary Tree Inorder Traversal
 * Medium
 *
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * 定义
 * Algorithm Inorder(tree)
 *    1. Traverse the left subtree, i.e., call Inorder(left-subtree)
 *    2. Visit the root.
 *    3. Traverse the right subtree, i.e., call Inorder(right-subtree)
 *
 *     1
 *    / \
 *   2   3      -> inorder (left, root, right): 4 2 5 1 3
 *  / \
 * 4   5
 *
 *     1
 *    / \
 *   2   3      -> inorder (left, root, right): [4,2,6,5,7,1,3]
 *  / \
 * 4   5
 *    / \
 *   6   7
 */
public class _0094_BinaryTreeInorderTraversal {

    /**
     * Recursion 递归
     */
    public List<Integer> inorderTraversal_Recursion(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }

    public void helper(TreeNode n, List<Integer> l) {
        if (n == null) {
            return;
        }

        helper(n.left, l);
        l.add(n.val);
        helper(n.right, l);
    }

    /**
     * Stack 堆
     * 这个解法多加一个指针, 比下面的inorderTraversal_Stack更简单易读
     */
    public List<Integer> inorderTraversal_Stack_s(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode n = stack.pop();
            list.add(n.val);
            cur = n.right;
        }

        return list;
    }

    /**
     * Stack 堆
     */
    public List<Integer> inorderTraversal_Stack(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        /**
         * Can't use Deque as it throw NP exception when adding null.
         * Use ArrayList or LinkedList instead
         */
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode n = stack.peek();
            if (n != null) {
                stack.push(n.left);
            } else {
                stack.pop(); // pop the null stack
                if (!stack.isEmpty()) {
                    n = stack.pop(); // pop the parent node
                    list.add(n.val);
                    stack.push(n.right);
                }
            }
        }

        return list;
    }
}
