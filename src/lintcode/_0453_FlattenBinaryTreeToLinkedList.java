/*
Easy
#DFS, #Binary Tree, #Divide and Conquer
Microsoft
 */
package lintcode;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 453 · Flatten Binary Tree to Linked List
 *
 * Flatten a binary tree to a fake "linked list" in pre-order traversal.
 *
 * Here we use the right pointer in TreeNode as the next pointer in ListNode.
 *
 * Don't forget to mark the left child of each node to null. Or you will get Time Limit Exceeded or Memory Limit Exceeded.
 *
 * Example 1:
 * Input:{1,2,5,3,4,#,6}
 * Output：{1,#,2,#,3,#,4,#,5,#,6}
 * Explanation：
 *      1
 *     / \
 *    2   5
 *   / \   \
 *  3   4   6
 *
 * 1
 * \
 *  2
 *   \
 *    3
 *     \
 *      4
 *       \
 *        5
 *         \
 *          6
 *
 * Example 2:
 * Input:{1}
 * Output:{1}
 * Explanation：
 *          1
 *          1
 *
 * Challenge
 * Do it in-place without any extra memory.
 */
public class _0453_FlattenBinaryTreeToLinkedList {

    /**
     * 循环 - 用 stack, 也是 extra memory
     */
    public void flatten_iterative(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node.right != null) { // 先推 右子树
                stack.push(node.right);
            }

            if (node.left != null) { // 后推 左子树
                stack.push(node.left);
            }

            // connect
            node.left = null;

            if (stack.isEmpty()) {
                node.right = null;
            } else {
                node.right = stack.peek(); // 不能 pop 走, 因为下一循环就是要处理它
            }
        }
    }

    /**
     * 递归 - pre-order traversal
     */
    private TreeNode parentNode = null;

    public void flatten_recursive(TreeNode root) {
        if (root == null) {
            return;
        }

        if (parentNode != null) {
            parentNode.left = null;
            parentNode.right = root;
        }

        parentNode = root;
        TreeNode right = root.right; // 这里必须保存 当前 right 的指针, 否则 recursion回来后, root.right 会变 (参见 parentNode.right = root;)
        flatten_recursive(root.left);
        flatten_recursive(right);
    }


    /**
     * Divide and Conquer
     */
    public void flatten_recursive_2(TreeNode root) {
        helper(root);
    }

    private TreeNode helper(TreeNode root) {
        if (root == null) {
            return null;
        }

        // divde
        TreeNode leftLast = helper(root.left);
        TreeNode rightLast = helper(root.right);

        // connect leftLast to root.right
        if (leftLast != null) {
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        if (rightLast != null) {
            return rightLast;
        }

        if (leftLast != null) {
            return leftLast;
        }

        return root;
    }


    /**
     * 使用 extra memory 的话, 先用 DFS 遍历 tree, 写到 list 上, 然后遍历 list, 写回 tree
     */
    public void flatten_extra_memory(TreeNode root) {
        if (root == null) { return; }

        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        TreeNode cur = root;
        for (int i = 1; i < list.size(); i++) {
            cur.left = null; // 别忘了把 left node 设为 null
            cur.right = new TreeNode(list.get(i));
            cur = cur.right;
        }
    }

    // pre-order traversal, 将 node 的值写入 list
    public void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        list.add(node.val);
        dfs(node.left, list);
        dfs(node.right, list);
    }



    class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
