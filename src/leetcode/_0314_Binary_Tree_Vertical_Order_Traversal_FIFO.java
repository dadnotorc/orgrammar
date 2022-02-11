package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 314. Binary Tree Vertical Order Traversal
 * Medium
 * #Prime
 * Adobe, Amazon, Facebook Meta, Google, Microsoft, Oracle
 *
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Example1
 * Inpurt:  {3,9,20,null,null,15,7}
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 *    3
 *   / \
 * 9    20
 *     / \
 *   15   7
 *
 * Example2
 * Input: {3,9,8,4,1,0,7}
 * Output: [[4],[9],[3,1,0],[8],[7]]
 * Explanation:
 *        3
 *      /   \
 *    9       8
 *   / \     / \
 * 4    1  0    7
 *
 *
 * Examples 3:
 * Input:  [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
 * Output: [[4], [9,5], [3,0,1], [8,2], [7]]
 *         3
 *     /     \
 *    9       8
 *   /  \   /  \
 *  4    0 1    7
 *       /  \
 *    5       2
 *
 * 与 lintcode 651 完全相同
 *
 * 比 leetcode 987 简单. 对相同(x,y)的节点, 此题要求按从左到右的顺序, 987 要求按数值大小
 */
public class _0314_Binary_Tree_Vertical_Order_Traversal_FIFO {

    /*
    当前解法中, 遇到 相同 column 相同 row 的值时, 按照从左到右的先后顺序排列即可, 先进来者先加入队列

    follow up, 如果这些值需要按大小排列 - 则将 最内层的 ArrayList 换成 PriorityQueue
     */

    /**
     * 用 DFS 来遍历
     * 使用 TreeMap<Integer, TreeMap<Integer, ArrayList<Integer>>> 来记录 <column, <row, val_list_in_cur_row>>
     *
     * 到下一层的时候, 左子树 column - 1, row + 1; 右子树 column + 1, row + 1
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) { return ans; }

        // <column, <row, val_list>>
        TreeMap<Integer, TreeMap<Integer, ArrayList<Integer>>> map = new TreeMap<>();

        dfs(root, 0, 0, map);

        for (TreeMap<Integer, ArrayList<Integer>> column_val : map.values()) {
            ArrayList<Integer> list = new ArrayList<>();

            for (ArrayList<Integer> row_val : column_val.values()) {
                list.addAll(row_val);
                /*
                for (int val : row_val) {
                    list.add(val);
                }
                 */
            }

            ans.add(list);
        }

        return ans;
    }


    public void dfs(TreeNode node, int column, int row, TreeMap<Integer, TreeMap<Integer, ArrayList<Integer>>> map) {
        if (node == null) { return; }

        if (!map.containsKey(column)) {
            map.put(column, new TreeMap<Integer, ArrayList<Integer>>());
        }

        if (!map.get(column).containsKey(row)) {
            map.get(column).put(row, new ArrayList<Integer>());
        }

        map.get(column).get(row).add(node.val);

        dfs(node.left, column - 1, row + 1, map);
        dfs(node.right, column + 1, row + 1, map);
    }



    class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
