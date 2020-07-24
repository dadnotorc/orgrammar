package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 797. All Paths From Source to Target
 *
 * Given a directed, acyclic (无环) graph of N nodes.
 * Find all possible paths from node 0 to node N-1, and return them in any order.
 *
 * The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.
 * graph[i] is a list of all nodes j for which the edge (i, j) exists.
 * (graph[0] = {1,2} 说明 (0,1)和(0,2)的edge存在
 *
 * Example:
 * Input: [[1,2], [3], [3], []]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: The graph looks like this:
 * 0--->1
 * |    |
 * v    v
 * 2--->3
 * There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 *
 * Note:
 * - The number of nodes in the graph will be in the range [2, 15].
 * - You can print different paths in any order, but you should keep the order of nodes inside one path.
 */
public class _0797_AllPathsFromSourceToTarget {


    /**
     * 解法 2 - 先把0加入所有答案中, 之后helper只需先加后删一次
     */
    public List<List<Integer>> allPathsSourceTarget_2(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        if (graph == null || graph.length == 0 || graph[0].length == 0) { return res; }
        List<Integer> curList = new ArrayList<>();

        curList.add(0); // 注意! 记得先将0加入cur list, 因为之后没有任何path会指回0
        helper_2(graph, 0, res, curList);

        return res;
    }

    private void helper_2(int[][] graph, int curNode, List<List<Integer>> res, List<Integer> curList) {
        if (curNode == graph.length - 1) {
            res.add(new ArrayList<>(curList)); // 注意! 记得做deep copy
            return;
        }

        for (int nextNode: graph[curNode]) {
            curList.add(nextNode); // 先加
            helper_2(graph, nextNode, res, curList);
            curList.remove(curList.size() - 1); // 后删
        }
    }




    /**
     * 解法 1 - helper里需要先加后删两次
     */
    public List<List<Integer>> allPathsSourceTarget_1(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        if (graph == null || graph.length == 0 || graph[0].length == 0) { return res; }

        helper(graph, 0, res, new ArrayList<>());

        return res;
    }

    private void helper(int[][] graph, int curNode, List<List<Integer>> res, List<Integer> curList) {
        if (curNode == graph.length - 1) {
            curList.add(curNode); // 先加
            res.add(new ArrayList<>(curList)); // 注意! 记得做deep copy
            curList.remove(curList.size() - 1); // 后删
            return;
        }

        for (int nextNode: graph[curNode]) {
            curList.add(curNode); // 先加
            helper(graph, nextNode, res, curList);
            curList.remove(curList.size() - 1); // 后删
        }
    }
}
