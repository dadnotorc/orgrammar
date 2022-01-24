package lintcode;

import util.TreeNode;

import java.util.*;

/**
 * 651. Binary Tree Vertical Order Traversal - 较简单, 同位置的节点, 按先后顺序即可
 * Medium
 * #Hash Table, #BFS
 * Facebook, Google
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
 *  9  20
 *     /\
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
 *  此题与 LeetCode 987 区别在, 对相同(x,y)的节点, 此题要求按从左到右的顺序, LeetCode要求按数值大小
 *  此题比较简单, 无需两层 TreeMap. 内层用 ArrayList 即可
 */
public class _0651_Binary_Tree_Vertical_Order_Traversal_ArrayList {

    /**
     * BFS - 比 leetcode 987 简单, 无需保证 same row same column 按大小排列. 使用 ArrayList 即可.
     * 否则, 需要两层 TreeMap. 内层 TreeMap 的 value 为 ProrityQueue<Integer>
     */
    class ResultType{
        TreeNode node;
        int column;
        public ResultType(TreeNode _node, int _column) {
            this.node = _node;
            this.column = _column;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        // <column, node_val_in_the_same_column>
        Map<Integer, ArrayList<Integer>> map = new TreeMap<>();

        Queue<ResultType> q = new LinkedList<>();
        q.offer(new ResultType(root, 0));

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                ResultType rt = q.poll();
                TreeNode node = rt.node;
                int column = rt.column;

                if (!map.containsKey(column)) {
                    map.put(column, new ArrayList<>());
                }

                map.get(column).add(node.val);

                if (node.left != null) {
                    q.offer(new ResultType(node.left, column - 1));
                }
                if (node.right != null) {
                    q.offer(new ResultType(node.right, column + 1));
                }
            }
        }

        for (ArrayList<Integer> list : map.values()) {
            ans.add(new ArrayList<>(list));
        }

        return ans;
    }




    /**
     * DFS 有个 bug - input {3,9,8,4,0,1,7,#,#,#,2,5}, exp =[[4],[9,5],[3,0,1],[8,2],[7]]
     *        3
     *      /   \
     *    9       8
     *   / \     / \
     * 4     0 1    7
     *       / \
     *     5      2
     * DFS 的结果是 [[4],[9,5],[3,0,1],[2,8],[7]]    2 与 8 反了
     */
    public List<List<Integer>> verticalOrder_DFS(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null)
            return ans;

        // 使用TreeMap的原因是: 相对HashMap来说, TreeMap是sorted, natural ordering
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        dfs(root, 0, 0, map);

        for (ArrayList<Integer> value : map.values()) { // column from left to right
            ans.add(new ArrayList<>(value));
        }

        return ans;
    }

    private void dfs(TreeNode root, int col, int row, TreeMap<Integer, ArrayList<Integer>> map) {
        if (root == null)
            return;

        if (!map.containsKey(col))
            map.put(col, new ArrayList<>());

        map.get(col).add(root.val);

        // y值从0开始递增, 方便TreeMap找寻
        dfs(root.left, col - 1, row + 1, map);
        dfs(root.right, col + 1, row + 1, map);
    }
}
