/*
Medium
#Binary Tree Traversal, #Queue, #Binary Tree, #BFS
Apple, LinkedIn, Amazon, Facebook, Microsoft, Uber
FAQ
 */
package lintcode;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 69. Binary Tree Level Order Traversal
 *
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 *
 * Notice:
 * - The first data is the root node, followed by the value of the left and
 *   right son nodes, and "#" indicates that there is no child node.
 * - The number of nodes does not exceed 20.
 *
 * Example 1
 * Input：{1,2,3}
 * Output：[[1],[2,3]]
 * Explanation：
 *   1
 *  / \
 * 2   3
 * it will be serialized {1,2,3} level order traversal
 *
 * Example 2:
 * Input：{1,#,2,3}
 * Output：[[1],[2],[3]]
 * Explanation：
 * 1
 *  \
 *   2
 *  /
 * 3
 * it will be serialized {1,#,2,3} level order traversal
 *
 * Challenge
 * 1: Using only 1 queue to implement it.
 * 2: Use BFS algorithm to do it.
 */
public class _0069_BinaryTreeLevelOrderTraversal {

    /**
     * BFS - 每轮取出当前层的所有节点, 并将这些点的子节点加入队列, 以供下一轮
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) { return ans; }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> curLvl = new ArrayList<>();
            int curSz = queue.size(); //当前level中nodes的数量

            for (int i = 0; i < curSz; i++) {
                TreeNode node = queue.poll();
                curLvl.add(node.val);

                if (node.left != null) { queue.offer(node.left); }
                if (node.right != null) { queue.offer(node.right); }
            }

            ans.add(curLvl);
        }

        return ans;
    }

    /**
     * DFS - 每次DFS都从root开始向下寻找目标层, 当把目标层所有节点加完后, 目标层向下移, 重新开始DFS
     * 出口: 如果current node为空或者当前层超过目标层, 则返回; 如果当前层即为目标层, 将current node的值加入队列
     *       遍历完整棵树后, 将队列加入答案中. 然后重置DFS, 从root开始, 目标层下移
     * 拆解: 分别向current node的左子树和右子树递归
     */
}
