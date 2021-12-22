/*
Easy
#DFS, #Binary Tree, #Divide and Conquer
 */
package lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 68 · Binary Tree Postorder Traversal - 后序 - 左 右 上
 *
 * Given a binary tree, return the postorder traversal of its nodes’ values.
 * - The first data is the root node, followed by the value of the left and right son nodes,
 *   and "#" indicates that there is no child node.
 * - The number of nodes does not exceed 20.
 *
 * Example 1:
 * Input: binary tree = {1,2,3}
 * Output: [2,3,1]
 *    1
 *  /  \
 * 2     3
 * It will be serialized to {1,2,3} followed by post-order traversal
 *
 * Example 2:
 * Input: binary tree = {1,#,2,3}
 * Output: [1,3,2]
 * 1
 * \
 *  2
 * /
 * 3
 * It will be serialized to {1,#,2,3} followed by post-order traversal
 *
 * Challenge
 * Can you do it without recursion?
 *
 input =  {1,2,3,4,5,6,7}
 *      1
 *     / \
 *    2   3
 *   / \ / \
 *  4  5 6 7
 * output = {4,5,2,6,7,3,1}
 */
public class _0068_BinaryTreePostorderTraversal {

    /**
     * DFS
     */
    public List<Integer> postorderTraversal_dfs(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }

    public void dfs(TreeNode node, List<Integer> list) {
        if (node == null) return;

        dfs(node.left, list);
        dfs(node.right, list);
        list.add(node.val);
    }

    /**
     * 也使用 stack, 加入 prev 和 cur 指针
     * 使用栈进行二叉树后序遍历，首先对左子树进行遍历压入栈中，直至左子树为空，
     * 然后访问右子树。故每个节点会被访问两次，当节点被第二次访问时，该节点出栈。
     */
    public List<Integer> postorderTraversal_stack_2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) { return ans; }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;  // previously traversed node
        TreeNode cur = root;

        stack.push(root);
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (prev == null || prev.left == cur || prev.right == cur) { // traverse down the tree
                if (cur.left != null) {
                    stack.push(cur.left);
                } else if (cur.right != null) { // 注意是 if, else if
                    stack.push(cur.right);
                }
            } else if (cur.left == prev) { // traverse up the tree from the left
                if (cur.right != null) {
                    stack.push(cur.right);
                }
            } else { // traverse up the tree from the right
                ans.add(cur.val);
                stack.pop();
            }
            prev = cur;
        }

        return ans;
    }


    /**
     * 使用 stack
     * 不能反向存节点, 必须先存左, 再存右
     */
    public List<Integer> postorderTraversal_stack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) { return ans; }

        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(0, node.val);
            if (node.left != null) stack.push(node.left); // 先压左, 再压右. 因为读取时, 是往队列头里加 (先进后出)
            if (node.right != null) stack.push(node.right);
        }

        return ans;
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
