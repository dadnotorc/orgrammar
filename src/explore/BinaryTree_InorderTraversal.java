package explore;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTree_InorderTraversal {

    /**
     * 使用指针
     */
    public List<Integer> inorderTraversal_iteratively_stack_pointer(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        // 这里也无需特判, 因为循环中会有

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        // 循环开始前不需要在stack中加入root
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) { // 从当前(非空)节点开始, 一路添加左子节点
                stack.push(cur);
                cur = cur.left;
            }

            // 左子节点为空, 添加父节点值(在stack中), 指针指去右子节点
            TreeNode node = stack.pop();
            ans.add(node.val);
            cur = node.right;
        }

        return ans;
    }


    /**
     * Iteratively - 使用stack
     *
     * 易错点:
     * 1. pop掉null节点后, 必须判断stack是否为空
     */
    public List<Integer> inorderTraversal_iteratively_stack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek(); // 还不能pop

            if (node != null) { // 父节点不为空, 则继续添加其左子节点
                stack.push(node.left);
            } else { // 当前节点为空, 则需返回其父节点, 并添加父节点值
                stack.pop(); // // 移除空节点
                if (!stack.isEmpty()) { // 注意! 这里必须判断是否为空
                    node = stack.pop(); // 取出父节点
                    ans.add(node.val);
                    stack.push(node.right);
                }
            }
        }

        return ans;
    }




    /**
     * Iteratively - 使用ArrayList或者LinkedList
     * 不能使用deque, 因为deque中加入null时会出现 NP exception
     *
     * 易错点:
     * 1. 在list中add或者remove节点时, 都需指定 index 0 (其实可以用stack代替)
     * 2. remove掉null节点后, 必须判断list是否为空
     */
    public List<Integer> inorderTraversal_iteratively_list(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()) {
            TreeNode node = list.get(0);
            if (node != null) { // 父节点不为空, 则继续添加其左子节点
                list.add(0, node.left);
            } else { // 当前节点为空, 则需返回其父节点, 并添加父节点值
                list.remove(0); // 移除空节点
                if (!list.isEmpty()) { // 注意! 这里必须判断是否为空
                    node = list.remove(0); // 取出父节点
                    ans.add(node.val);
                    list.add(0, node.right);
                }
            }
        }

        return ans;
    }


    /**
     * Recursively
     */
    public List<Integer> inorderTraversal_recursive(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        traverse(root, ans);
        return ans;
    }

    private void traverse(TreeNode node, List<Integer> ans) {
        if (node == null) { return; }

        traverse(node.left, ans);

        ans.add(node.val); // 中序遍历 inorder, 在左右子树递归之间

        traverse(node.right, ans);

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
