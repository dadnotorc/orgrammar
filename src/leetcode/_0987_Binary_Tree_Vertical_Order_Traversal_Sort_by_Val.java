package leetcode;

import util.TreeNode;

import java.util.*;

/**
 * 987. Vertical Order Traversal of a Binary Tree - 较复杂, 同位置的节点, 按其 val 从小到大排列
 * Hard
 * #Hash Table, #Tree, #DFS, #BFS, #Binary Tree
 * Amazon
 *
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 *
 * For each node at position (row, col), its left and right children will be at positions
 * (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 *
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column
 * index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes
 * in the same row and same column. In such a case, sort these nodes by their values.
 *
 * Return the vertical order traversal of the binary tree.
 *
 *  Example 1:
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
 *
 * 此题难点
 * - same column, same column 的两个节点, 输出时按 val 从小到大排列 - 用 Priority Queue
 * - same column, but different row, 按从上到下的先后顺序 (不考虑前一项时 ArrayList 即可, 不然就需要多一层 TreeMap)
 */
public class _0987_Binary_Tree_Vertical_Order_Traversal_Sort_by_Val {

    /**
     * BFS + TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>
     *
     * 使用 2 个 Queue, 和 1 个 map
     * 1. Queue #1 记录通过 BFS 访问的节点
     * 2. Queue #2 记录当前节点对应的 column (root 的 column 为 0, left 为 -1, right 为 1)
     * 3. TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>
     *    - 外层的 integer - column 坐标;  内层的 integer - row 座标
     *
     * 要注意的点
     * 1. 使用 TreeMap 代替 HashMap 因为自带排序
     * 2. 如果在同一层, 有多个节点同在一个 column 上, 按从小到大排列
     * 3. 如果不在同一层, 按从上到下的先后顺序
     *
     * 时间 O(n * logn) - 因为需要遍历 n 个节点, 同时使用了 TreeMap (sorted). PQ insertion 太少, 忽略不计 (坐标相同的两个节点才需要使用 PQ offer)
     *    n: 节点数量
     *    w: 树的宽度 ~ n / 2
     *    h: 数的高度 ~ logn
     *
     *
     * 空间 O(n) - 包含全部节点的 TreeMap
     */

    class ResultType{
        TreeNode node;
        int column;
        public ResultType(TreeNode _node, int _column) {
            this.node = _node;
            this.column = _column;
        }
    }

    public List<List<Integer>> verticalTraversal_BFS(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        // 使用 TreeMap, 因为其自带 按 key 大小的排序功能

        // value 部分也用 TreeMap<Integer, PriorityQueue<Integer>>>,
        // - 不能简单的定为 ArrayList<Integer>, 因为要保证 same row same column 的节点值按从小到大排列
        // - 也不能定为 PriorityQueue<Integer>, 因为要保证 same column 时 节点从上到下的先后顺序

        // 外层的 integer - column 坐标;  内层的 integer - row 座标

        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

        // Map<Integer, ArrayList<Integer>> map = new TreeMap<>();

        /* 使用 ResultType 替代两个Queue
        Queue<TreeNode> node_q = new LinkedList<>();
        Queue<Integer> column_q = new LinkedList<>();
        node_q.offer(root);
        column_q.offer(0);
         */

        Queue<ResultType> q = new LinkedList<>();
        q.offer(new ResultType(root, 0));


        int row = 0; // 注意 这里要使用单独的 row 变量 记录当前是第几层

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) { // 当前层
                ResultType rt = q.poll();
                TreeNode node = rt.node;
                int column = rt.column;

                // 这里不能这么写, 因为需要 put new list into the map.
                // map.getOrDefault(column, new ArrayList<>()).add(node.val);
                if (!map.containsKey(column)) {
                    map.put(column, new TreeMap<>());
                }

                // 注意 这里的层 要用个 单独的 level 变量, 而不是 i. i 是当前层节点的下标
                if (!map.get(column).containsKey(row)) {
                    map.get(column).put(row, new PriorityQueue<>());
                }

                map.get(column).get(row).offer(node.val);

                if (node.left != null) {
                    q.offer(new ResultType(node.left, column - 1));
                }
                if (node.right != null) {
                    q.offer(new ResultType(node.right, column + 1));
                }
            }

            row++; // 别忘了最后递加层数
        }

        // 把每个 column 的答案取出来
        for (TreeMap<Integer, PriorityQueue<Integer>> val : map.values()) {
            ArrayList<Integer> list = new ArrayList<>();

            // 把相同 row 的答案放在一起
            for (PriorityQueue<Integer> pq : val.values()) {

                // 这里不能写成 for (int i : pq) ; 顺序会出错
                while (!pq.isEmpty()) {
                    list.add(pq.poll());
                }
            }

            ans.add(list);
        }

        return ans;
    }





    /**
     * DFS + TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>
     *
     * 时间 O(n + n + logh) ~ O(n)
     *    n: 节点数量
     *    w: 树的宽度 ~ n / 2
     *    h: 数的高度 ~ logn
     *
     *    一共做不超过 w * h 次 TreeMap insertion, 最多 O(n)
     *    一共做不超过 h 次 PQ insertion, 最多 log(h)
     *
     * 空间 O(n) - 包含全部节点的 TreeMap
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        /*
         (multiple) nodes at location (x, y) -> map.get(x).get(y).poll()

         使用TreeMap的原因是
         相对HashMap来说, TreeMap是sorted, natural ordering

         使用PriorityQueue的原因是
           "If two nodes have the same position, then the value of the node
             that is reported first is the value that is smaller."
           所以同在(x,y)的两个nodes需要按大小排序.
           但是x相同但y不同的nodes(不同层)不需要排序 注意!
         */
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

        // 遍历整个数, 用TreeMap来记录在 (x,y) 点对应 nodes 的值
        // x 相同的节点放在一起 (y 不同)
        // x,y 皆相同时, 按数值大小排列 (所以使用PriorityQueue)
        dfs(root, 0, 0, map);

        List<List<Integer>> ans = new ArrayList<>();

        // x 值从小到大, y 值也从小到大
        for (TreeMap<Integer, PriorityQueue<Integer>> val : map.values()) { // 注意 用 .values()
            ArrayList<Integer> list = new ArrayList<>();

            for (PriorityQueue<Integer> pq : val.values()) {
                while (!pq.isEmpty()) {
                    list.add(pq.poll());
                }
            }

            ans.add(list);
        }

        return ans;
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
            // 同在(x,y)的多个nodes, 按node.val大小排序
            map.get(x).put(y, new PriorityQueue<>());
        }

        map.get(x).get(y).offer(root.val);

        // 注意,递归child nodes时, y的值都是 +1, 因为层数递增
        // 左子节点 x - 1;  右子节点 x + 1
        dfs(root.left, x-1, y+1, map);
        dfs(root.right, x+1, y+1, map);
    }


}
