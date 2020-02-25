/*
Medium
#Hash Table
Facebook, Google
 */
package lintcode;

import util.TreeNode;

import java.util.*;

/**
 * 651. Binary Tree Vertical Order Traversal
 *
 * Given a binary tree, return the vertical order traversal of its nodes' values.
 * (ie, from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Example1
 * Inpurt:  {3,9,20,#,#,15,7}
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 *    3
 *   /\
 *  /  \
 *  9  20
 *     /\
 *    /  \
 *   15   7
 *
 * Example2
 * Input: {3,9,8,4,1,0,7}
 * Output: [[4],[9],[3,1,0],[8],[7]]
 * Explanation:
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  10   7
 *
 *  此题与 LeetCode 987 区别在, 对相同(x,y)的节点, 此题要求按从左到右的顺序, LeetCode要求按数值大小
 */
public class _0651_BinaryTreeVerticalOrderTraversal {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null)
            return ans;

        // 使用TreeMap的原因是: 相对HashMap来说, TreeMap是sorted, natural ordering
        // 遍历整个数, 用TreeMap来记录在 (x,y) 点对应 nodes 的值
        // x 相同的节点放在一起 (y 不同)
        // x,y 皆相同时, 按左右顺序排列 (Queue即可) 如果按数值大小, 选择PriorityQueue
        TreeMap<Integer, TreeMap<Integer, Queue<Integer>>> map = new TreeMap<>();
        dfs(root, 0, 0, map);

        for (TreeMap<Integer, Queue<Integer>> ys : map.values()) { // from left to right
            ans.add(new ArrayList<>());

            for (Queue<Integer> queue : ys.values()) {
                while (!queue.isEmpty()) {
                    ans.get(ans.size() - 1).add(queue.poll());
                }
            }
        }

        return ans;
    }

    private void dfs(TreeNode root, int x, int y, TreeMap<Integer, TreeMap<Integer, Queue<Integer>>> map) {
        if (root == null)
            return;

        if (!map.containsKey(x))
            map.put(x, new TreeMap<>());

        if (!map.get(x).containsKey(y))
            map.get(x).put(y, new LinkedList<>());

        map.get(x).get(y).offer(root.val);

        // y值从0开始递增, 方便TreeMap找寻
        dfs(root.left, x-1, y+1, map);
        dfs(root.right, x+1, y+1, map);
    }
}
