package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 480 · Binary Tree Paths
 * Easy
 * #BFS, #DFS, #Binary Tree, #Divide and Conquer
 *
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Example 1:
 * Input：{1,2,3,#,5}
 * Output：["1->2->5","1->3"]
 * Explanation：
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * Example 2:
 * Input：{1,2}
 * Output：["1->2"]
 * Explanation：
 *    1
 *  /
 * 2
 */
public class _0480_Binary_Tree_Paths {

    /**
     * DFS + StringBuilder
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) { return ans; }

        StringBuilder sb = new StringBuilder();
        sb.append(root.val);

        helper(root, ans, sb);
        return ans;
    }

    private void helper(TreeNode node, List<String> ans, StringBuilder sb) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) { // leaf node
            ans.add(sb.toString());
            return;
        }

        int preLength = sb.length();

        if (node.left != null) {
            sb.append("->").append(node.left.val);
            helper(node.left, ans, sb);
            sb.delete(preLength, sb.length());
        }

        if (node.right != null) {
            sb.append("->").append(node.right.val);
            helper(node.right, ans, sb);
            sb.delete(preLength, sb.length());
        }
    }



    /**
     * 也是 DFS, 但是不用 StringBuilder
     */
    public List<String> binaryTreePaths_2(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) { return ans; }

        helper_2(root, ans, String.valueOf(root.val));
        return ans;
    }

    private void helper_2(TreeNode node, List<String> ans, String path) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            ans.add(path);
            return;
        }

        if (node.left != null) {
            helper_2(node.left, ans, path + "->" + node.left.val);
        }

        if (node.right != null) {
            helper_2(node.right, ans, path + "->" + node.right.val);
        }
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
