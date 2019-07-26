package lintcode.topological;

import java.util.*;

/**
 * 127. Topological Sorting
 * Medium
 *
 * Given an directed graph, a topological order of the graph nodes is
 *  defined as follow:
 *
 * For each directed edge A -> B in graph, A must before B in the order list.
 * The first node in the order can be any node in the graph with no nodes
 *  direct to it. Find any topological order for the given graph.
 *
 * You can assume that there is at least one topological order in the graph.
 *
 * Example
 * For graph as follow:
 *
 *    > 1 \
 *   /    / > 4
 * 0 -> 2
 *   \    \ > 5
 *    > 3 /
 *
 * The topological order can be:
 *
 * [0, 1, 2, 3, 4, 5]
 * [0, 2, 3, 1, 5, 4]
 * ...
 *
 * Challenge
 * - Can you do it in both BFS and DFS?
 */
public class _0127_TopologicalSorting {

    /**
     * BFS
     */
    public ArrayList<DirectedGraphNode> topSort_BFS(ArrayList<DirectedGraphNode> graph) {

        ArrayList<DirectedGraphNode> ans = new ArrayList<>();

        /**
         * 统计每个node入度. 不存在的node说明该点入度为0
         * hashmap中, key为node, value为该node入度
         */
        HashMap<DirectedGraphNode, Integer> map = new HashMap<>();
        /**
         * 对拓扑图中每个node, 统计它的neighbor => 每个neighbor的值即为它的入度
         * 说明有多少指向线指去该neighbor
         */
        for (DirectedGraphNode node :graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (map.containsKey(neighbor)) {
                    map.put(neighbor, map.get(neighbor) + 1);
                } else {
                    map.put(neighbor, 1);
                }
            }
        }

        Queue<DirectedGraphNode> q = new LinkedList<>();
        /**
         * 先将入度为0的nodes加入queue (不在map中的node即入度为0)
         */
        for (DirectedGraphNode n : graph) {
            if (!map.containsKey(n)) {
                q.offer(n);
            }
        }
        /**
         * 从入度为0的nodes开始,将它们的neighbor入度递减
         * 当再次遇到入度为0的nodes时, 等于已满足先序条件, 则将其加入ans中
         */
        while (!q.isEmpty()) {
            DirectedGraphNode n = q.poll();
            ans.add(n); // queue中的node都是入度为0
            for (DirectedGraphNode neighbor : n.neighbors) {
                map.put(neighbor, map.get(neighbor) - 1);

                if (map.get(neighbor) == 0) {
                    q.offer(neighbor);
                }
            }
        }

        return ans;
    }


    /* ~~~~~~~ */

    /**
     * DFS
     */
    public ArrayList<DirectedGraphNode> topSort_DFS(
            ArrayList<DirectedGraphNode> graph) {

        ArrayList<DirectedGraphNode> ans = new ArrayList<>();

        if (graph == null || graph.size() < 1) {
            return ans;
        }

        /**
         * 两个set, 分别记录:
         * gray - 当前访问的节点
         * dark - 已存入ans的节点
         */
        Set<DirectedGraphNode> gray = new HashSet<>();
        Set<DirectedGraphNode> dark = new HashSet<>();

        for (DirectedGraphNode n : graph) {
            if (!gray.contains(n) && !dark.contains(n)) {
                dfs(n, ans, gray, dark);
            }
        }

        return new ArrayList<DirectedGraphNode>(ans);
    }

    /**
     * DFS - 从开始节点, 尝试它的每个neighbor.
     *       如果能走到末端(再无任何neighbor), 则将当前路线计入ans
     */
    public void dfs(DirectedGraphNode n, ArrayList<DirectedGraphNode> ans,
                    Set<DirectedGraphNode> gray, Set<DirectedGraphNode> dark) {

        // 先在 gray set 记录当前访问节点
        gray.add(n);

        // 尝试当前节点的所有neighbors
        for (DirectedGraphNode neighbor : n.neighbors) {
            if (!gray.contains(neighbor) && !dark.contains(neighbor)) {
                // 未访问过的节点
                dfs(neighbor, ans, gray, dark);
            }
        }

        /**
         * 递归到拓扑图末端时, 上面的 for 循环不再执行, 同时也找到了一条可行的路线, 记录进ans
         *
         * dark set 从末端开始反向记录当前路线, 确保递归不再尝试已走过的路线
         *
         * gray set 移除当前节点
         */
        dark.add(n);
        gray.remove(n);

        // 因为是从图中末端开始添加, 所以每次添加到list第一位
        ans.add(0, n);
    }





    class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }
}
