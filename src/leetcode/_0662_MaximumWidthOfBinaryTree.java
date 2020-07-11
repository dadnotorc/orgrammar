/*
Medium
#Tree
Amazon
 */
package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 662. Maximum Width of Binary Tree
 *
 * Given a binary tree, write a function to get the maximum width of the given tree.
 * The width of a tree is the maximum width among all levels.
 * The binary tree has the same structure as a full binary tree, but some nodes are null.
 *
 * The width of one level is defined as the length between the end-nodes
 * (the leftmost and right most non-null nodes in the level,
 * where the null nodes between the end-nodes are also counted into the length calculation.
 *
 * Example 1:
 * Input:
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 *
 * Example 2:
 * Input:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 *
 * Example 3:
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 *
 * Example 4:
 *
 * Input:
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 *
 * Note: Answer will in the range of 32-bit signed integer.
 *
 * 此题与 lintcode 1101. Maximum Width of Binary Tree 相同
 */
public class _0662_MaximumWidthOfBinaryTree {

    /*
     * 记住, binary tree的属性, 假设父节点的index是 n, 其左节点为 2 * n, 右节点为 2 * n + 1
     */


    /**
     * DFS
     * List中保存每层最左node的index
     * 对每个node, 将其index与同层最左node的index比较, 计算当前宽度
     */
    int res = 0;

    public int widthOfBinaryTree_DFS(TreeNode root) {
        dfs(root, new ArrayList<Integer>(), 0, 1);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> leftMostNodes, int depth, int curIndex) {
        if (node == null) { return; }

        if (depth == leftMostNodes.size()) { leftMostNodes.add(curIndex); } // 记录每层最左node的index

        res = Math.max(res, curIndex - leftMostNodes.get(depth) + 1); // 计算到当前node的宽度

        dfs(node.left, leftMostNodes, depth + 1, curIndex * 2);
        dfs(node.right, leftMostNodes, depth + 1, curIndex * 2 + 1);
    }

    /**
     * BFS
     * 使用两个Queue, q1记录上一层的nodes, q2记录q1中每个nodes的index
     * 1. 在每一层中, 获得最左节点的index 与 最右节点的index, 两者相减+1为当前层的宽度
     * 2. 遍历每层中节点时, 将其(非空)的子节点加入q1, 并将其index加入q2
     */
    public int widthOfBinaryTree_BFS(TreeNode root) {
        int res = 0;
        if (root == null) { return res; }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> indexQueue = new LinkedList<>();

        nodeQueue.offer(root);
        indexQueue.offer(1); // root index 为 1

        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            int start = 0, end = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                int index = indexQueue.poll();

                if (i == 0) { start = index; } // 当层首节点, 记录其index为start
                if (i == size - 1) { end = index; } // 当层尾节点, 记录其index为end

                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    indexQueue.offer(index * 2);
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    indexQueue.offer(index * 2 + 1);
                }
            }
            res = Math.max(res, end - start + 1);
        }

        return res;
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
