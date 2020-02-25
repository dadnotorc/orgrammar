/*
Medium
Amazon
 */
package leetcode.binarytree;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 987. Vertical Order Traversal of a Binary Tree
 *
 * Given a binary tree, return the vertical order traversal of its nodes values.
 *
 * For each node at position (X, Y), its left and right children respectively
 *  will be at positions (X-1, Y-1) and (X+1, Y-1).
 *
 * Running a vertical line from X = -infinity to X = +infinity, whenever the
 *  vertical line touches some nodes, we report the values of the nodes in
 *  order from top to bottom (decreasing Y coordinates).
 *
 * If two nodes have the same position, then the value of the node that is
 *  reported first is the value that is smaller.
 *
 * Return an list of non-empty reports in order of X coordinate.
 * Every report will have a list of values of nodes.
 *
 *
 *
 * Example 1:
 * 	   3
 * 	  /  \
 * 	9    20
 * 	   /   \
 * 	  15    7
 * Input: [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Without loss of generality, we can assume the root node is at position (0, 0):
 *  Then, the node with value 9 occurs at position (-1, -1);
 *  The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
 *  The node with value 20 occurs at position (1, -1);
 *  The node with value 7 occurs at position (2, -2).
 *
 *
 * Example 2:
 * 	    1
 * 	  /   \
 * 	 2     3
 * 	/  \  / \
 * 4   5 6  7
 * Input: [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * The node with value 5 and the node with value 6 have the same position
 *  according to the given scheme.
 * However, in the report "[1,5,6]", the node value of 5 comes first since
 *  5 is smaller than 6.
 *
 *
 * Note:
 * 1. The tree will have between 1 and 1000 nodes.
 * 2. Each node's value will be between 0 and 1000.
 */
public class _0987_BinaryTreeVerticalOrderTraversal {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        /**
         * (multiple) nodes at location (x, y) -> map.get(x).get(y).poll()
         *
         * 使用TreeMap的原因是
         * 相对HashMap来说, TreeMap是sorted, natural ordering
         *
         * 使用PriorityQueue的原因是
         *  "If two nodes have the same position, then the value of the node
         *    that is reported first is the value that is smaller."
         *  所以同在(x,y)的两个nodes需要按大小排序.
         *  但是x相同但y不同的nodes(不同层)不需要排序 注意!
         */
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

        // 遍历整个数, 用TreeMap来记录在 (x,y) 点对应 nodes 的值
        // x 相同的节点放在一起 (y 不同)
        // x,y 皆相同时, 按数值大小排列 (所以使用PriorityQueue)
        dfs(root, 0, 0, map);

        List<List<Integer>> list = new ArrayList<>();

        // x 值从小到大, y 值也从小到大
        for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) { // 注意 用 .values()
            list.add(new ArrayList<>());
            for (PriorityQueue<Integer> nodes : ys.values()) {
                while (!nodes.isEmpty()) {
                    /**
                     * 在当前 list 最后一位的arraylist中添加node值
                     */
                    list.get(list.size() - 1).add(nodes.poll());
                }
            }
        }

        return list;
    }

    public void dfs(TreeNode root, int x, int y,
                    TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {
        if (root == null) {
            return;
        }

        if (!map.containsKey(x)) {
            map.put(x, new TreeMap<>());
        }

        if (!map.get(x).containsKey(y)) {
            /**
             * 同在(x,y)的多个nodes, 按node.val大小排序
             */
            map.get(x).put(y, new PriorityQueue<>());
        }

        map.get(x).get(y).offer(root.val);
        /**
         * 注意,递归child nodes时, y的值 +1, 而不是 -1
         */
        dfs(root.left, x-1, y+1, map);
        dfs(root.right, x+1, y+1, map);
    }


}
