package lintcode;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 71. Binary Tree Zigzag Level Order Traversal
 * Medium
 * LinkedIn, Microsoft
 *
 * Given a binary tree, return the zigzag level order traversal of
 *  its nodes' values. (ie, from left to right, then right to left
 *  for the next level and alternate between).
 *
 * Example 1:
 * Input:{1,2,3}
 * Output:[[1],[3,2]]
 * Explanation:
 *     1
 *    / \
 *   2   3
 * it will be serialized {1,2,3}
 *
 * Example 2:
 * Input:{3,9,20,#,#,15,7}
 * Output:[[3],[20,9],[15,7]]
 * Explanation:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * it will be serialized {3,9,20,#,#,15,7}
 */
public class _0071_BinaryTreeZigzagLevelOrderTraversal {

    /**
     * BFS
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        boolean isOddLevel = true;

        while (!q.isEmpty()) {

            ArrayList<Integer> curLevel = new ArrayList<>();

            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode n;

                if (isOddLevel) {
                    n = q.pollFirst(); // 奇数层, queue中从左往右读
                } else {
                    n = q.pollLast(); // 偶数层, queue中从右往左读
                }

                curLevel.add(n.val);

                if (isOddLevel) {
                    /**
                     * 当前是奇数层, 下一层偶数层, 将会从右往左读
                     *   所以添加时, 应加入queue末端
                     * 而且, 先加 left child, 后加 right child
                     *   目的是让 left child 在 right  child 左边
                     */
                    if (n.left != null) {
                        q.offer(n.left);
                    }
                    if (n.right != null) {
                        q.offer(n.right);
                    }
                } else {
                    /**
                     * 当前是偶数层, 下一层奇数层, 将会从左往右读
                     *   所以添加时, 应加入queue首段
                     * 而且, 先加 right child, 后加 left child
                     *   目的同样是让 left child 在 right  child 左边
                     */
                    if (n.right != null) {
                        q.push(n.right);
                    }
                    if (n.left != null) {
                        q.push(n.left);
                    }
                }
            }

            isOddLevel = !isOddLevel; // 每层改变
            ans.add(new ArrayList<>(curLevel));
        }

        return ans;
    }
}
