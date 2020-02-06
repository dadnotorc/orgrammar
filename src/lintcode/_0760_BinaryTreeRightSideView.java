/*
Medium
Binary Tree, BFS, DFS
Amazon, Facebook
 */
package lintcode;

import util.TreeNode;

import java.util.*;

/**
 * 760. Binary Tree Right Side View
 *
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom
 *
 * Example 1
 * Input: {1,2,3,#,5,#,4}
 * Output: [1,3,4]
 * Explanation:
 *    1
 *  /   \
 * 2     3
 *  \     \
 *   5     4

 * Example 2
 * Input: {1,2,3}
 * Output: [1,3]
 * Explanation:
 *    1
 *  /   \
 * 2     3
 *
 * 其他例子 (打印出最右侧的所有nodes)
 * Input: {1,2,3,#,5}
 * Output: [1,3,5]
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 */
public class _0760_BinaryTreeRightSideView {

    /**
     * 解法1 - BFS
     * 遍历每一层, 把当前层的最右节点的值加入答案队列中. 忽略其他节点
     * time:  O(n)
     * space: O(1)
     */
    public List<Integer> rightSideView_BFS(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if (root == null)
            return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            TreeNode node = null;
            for (int i = 0; i < size; i++) {
                 node = queue.poll();

                if (node.left != null)
                    queue.offer(node.left);

                if (node.right != null)
                    queue.offer(node.right);
            }

            ans.add(node.val); // 只把最后/最右的node值加入答案中
        }

        return ans;
    }

    /**
     * 解法2 - DFS
     * 此解的重点是
     * 1. 遍历树的时候, 先访问右子树, 再访问左子树
     * 2. 遍历时, 使用 HashMap 记录当前层数 + 当前层最右节点的值 (当前层第一个碰到的节点)
     * time:  O(n)
     * space: O(m) m = depth of the tree
     */
    public List<Integer> rightSideView_DFS(TreeNode root) {

        // map的key是当前的层数, value是当前层最右node的值
        Map<Integer, Integer> map = new HashMap<>();
        helper(map, root, 1);

        // 遍历完整个树后, map中记录每层最右node的值
        List<Integer> ans = new ArrayList<>();
        int depth = 1;
        while (map.containsKey(depth)) {
            // 把每层最右的值存入答案队列中
            ans.add(map.get(depth++));
        }

        return ans;
    }

    /**
     * helper的作用是遍历整个树, 先访问右子树, 后访问左子树.
     * 在同一层的节点中, 先访问到的必然是最右的节点, 将其值存入map中. 忽略其他节点
     */
    private void helper(Map<Integer, Integer> map, TreeNode node, int depth) {
        if (node == null)
            return;

        if (!map.containsKey(depth))
            map.put(depth, node.val);

        helper(map, node.right, depth + 1);
        helper(map, node.left, depth + 1);
    }
}
