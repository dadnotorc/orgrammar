package lintcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1533 · N-ary Tree Level Order Traversal
 * Easy
 * #Binary Tree, #BFS
 * Amazon
 * 
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 * 
 * For example, given a 3-ary tree:
 *       1
 *    /  |  \
 *   3   2   4
 *  / \
 * 5   6
 * We should return its level order traversal:
 * [
 *    [1],
 *    [3,2,4],
 *    [5,6]
 * ]
 * 
 * The depth of the tree is at most 1000.
 * The total number of nodes is at most 5000.
 * 
 * Example 1:
 * Input：{1,3,2,4#2#3,5,6#4#5#6}
 * Output：[[1],[3,2,4],[5,6]]
 * Explanation：Pictured above
 * 
 * Example 2:
 * Input：{1,3,2#2#3}
 * Output：[[1],[3,2]]
 * Explanation：
 *  1
 * / \
 * 3 2
 */
public class _1533_N_ary_Tree_Level_Order_Traversal {

    public List<List<Integer>> levelOrder(UndirectedGraphNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) { return ans; }

        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> cur = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                UndirectedGraphNode node = queue.poll();
                cur.add(node.label);

                for (UndirectedGraphNode neighbor : node.neighbors) {
                    if (neighbor != null) {
                        queue.offer(neighbor);
                    }
                }
            }
            ans.add(cur);
        }
        
        return ans;
    }


    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }
}
