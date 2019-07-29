package lintcode.binarytree;
/*
Medium
Binary Tree Traversal, Queue, Binary Tree, BFS
Apple, LinkedIn, Amazon, Facebook, Microsoft, Uber
FAQ
 */

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
 * The first data is the root node, followed by the value of the left and
 * right son nodes, and "#" indicates that there is no child node.
 * The number of nodes does not exceed 20.
 *
 * Example 1
 * Input：{1,2,3}
 * Output：[[1],[2,3]]
 * Explanation：
 *   1
 *  / \
 * 2   3
 * it will be serialized {1,2,3}
 * level order traversal
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
 * it will be serialized {1,#,2,3}
 * level order traversal
 *
 * Challenge
 * 1: Using only 1 queue to implement it.
 * 2: Use BFS algorithm to do it.
 */
public class _0069_BinaryTreeLevelOrderTraversal {

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
}
