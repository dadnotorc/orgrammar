/*
Easy
#Tree, #BFS
 */
package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 993. Cousins in Binary Tree
 *
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 * We are given the root of a binary tree with unique values, and the values x and y of two different
 * nodes in the tree. Return true if and only if the nodes corresponding to the values x and y are cousins.
 *
 * Example 1:
 *       1
 *      / \
 *     2  3
 *    /
 *   4
 * Input: root = [1,2,3,4], x = 4, y = 3
 * Output: false
 *
 * Example 2:
 *       1
 *      / \
 *     2  3
 *     \   \
 *     4   5
 * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 * Output: true
 *
 * Example 3:
 *       1
 *      / \
 *     2  3
 *     \
 *     4
 * Input: root = [1,2,3,null,4], x = 2, y = 3
 * Output: false
 *
 * Note:
 * - The number of nodes in the tree will be between 2 and 100.
 * - Each node has a unique integer value from 1 to 100.
 *
 * 假设:
 * x,y一定存在, 且不相同
 */
public class _0993_CousinsInBinaryTree {

    /**
     * BFS
     *
     * 易错点:
     * 别忘了判断当前节点, 以及子节点是否为空
     */
    public boolean isCousins_BFS(TreeNode root, int x, int y) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        boolean foundX = false, foundY = false;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (node == null) { continue; }

                // 由当前层决定值是否相同, 由前一层决定parent node是否是同一个
                // 也可以理解成, 确保当前节点的左右子节点是否为x,y

                if (node.val == x) {foundX = true; }
                if (node.val == y) { foundY = true; }

                // 别忘了判断子节点是否为空
                if (node.left != null && node.right != null &&
                        ((node.left.val == x && node.right.val == y) || (node.left.val == y && node.right.val == x))) {
                    return false;
                }

                // 别忘了把子节点放入队列
                q.offer(node.left);
                q.offer(node.right);
            }

            if (foundX && foundY) { return true; } // 在当前层同时找到了x,y

            if (foundX || foundY) { return false; } // 在当前层只找到了x 或者 y 其中一个
        }

        return false;
    }



    /**
     * DFS
     */
    class ResultType {
        int depth;
        TreeNode parent;
        ResultType(int depth, TreeNode parent) {
            this.depth = depth;
            this.parent = parent;
        }
    }

    public boolean isCousins_DFS(TreeNode root, int x, int y) {
        ResultType resultTypeX = findNode(null, root, x, 0);
        ResultType resultTypeY = findNode(null, root, y, 0);

        return resultTypeX.depth == resultTypeY.depth && resultTypeX.parent != resultTypeY.parent;
    }

    private ResultType findNode(TreeNode parent, TreeNode child, int target, int depth) {
        if (child == null) {
            return null;
        }
        if (child.val == target) {
            return new ResultType(depth, parent);
        }

        ResultType left = findNode(child, child.left, target, depth + 1);
        ResultType right = findNode(child, child.right, target, depth + 1);

        return left != null ? left : right;
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
