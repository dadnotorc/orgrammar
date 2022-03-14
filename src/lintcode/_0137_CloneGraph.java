package lintcode;

import java.util.*;

/**
 * 137. Clone Graph
 * Medium
 * #BFS, #DFS, #Hash Table
 * Facebook, Google, Uber
 * FAQ
 *
 * Clone an undirected graph. Each node in the graph contains a label
 * and a list of its neighbors. Nodes are labeled uniquely.
 *
 * You need to return a deep copied graph, which has the same structure
 * as the original graph, and any changes to the new graph will not have
 * any effect on the original graph.
 *
 * Notice
 * - You need return the node with the same label as the input node.
 *
 * Example1
 * Input:
 * {1,2,4#2,1,4#4,1,2}
 * Output:
 * {1,2,4#2,1,4#4,1,2}
 * Explanation:
 * 1------2
 *  \     |
 *   \    |
 *    \   |
 *     \  |
 *       4

 * Clarification
 * How we serialize an undirected graph: http://www.lintcode.com/help/graph/
 */
public class _0137_CloneGraph {






    /**
     * 三次遍历
     * 1. 从原图中获得所有的点
     * 2. 复制点 (获得新的nodes)
     * 3. 复制边 (获得新的edges)
     *
     * 遍历时, 注意保证节点的独立性, 因为节点是undirected, 所以邻居的邻居可以是本身
     * 跟新node添加neighbor的时候, 注意要选择新建出来的neighbor nodes
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;

        // 先获得图中所有节点, 存入list中
        // 使用BFS
        ArrayList<UndirectedGraphNode> oldNodes = getNodes_BFS(node);

        // 使用DFS Recursion
//        ArrayList<UndirectedGraphNode> oldNodes = new ArrayList<>();
//        HashSet<UndirectedGraphNode> set = new HashSet<>();
//        getNodes_DFS_Recursive(node, oldNodes, set);

        // 使用DFS Non-recursion
//        ArrayList<UndirectedGraphNode> oldNodes = getNode_DFS_NonRecursive(node);


        // 复制节点, 使用 HashMap 存储旧节点->新节点的mapping
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

        for (UndirectedGraphNode oldNode : oldNodes) {
            map.put(oldNode, new UndirectedGraphNode(oldNode.label));
        }

        // 复制边 (节点的neighbors)
        for (UndirectedGraphNode oldNode : oldNodes) {
            UndirectedGraphNode newNode = map.get(oldNode);

            for (UndirectedGraphNode oldNodeNeighbor : oldNode.neighbors) {
                UndirectedGraphNode newNodeNeighbor = map.get(oldNodeNeighbor); // 注意这一步, 要选新的节点作为neighbor
                newNode.neighbors.add(newNodeNeighbor);
            }
        }

        return map.get(node);
    }

    /* 解法1.1 - 使用BFS, 获得图中所有节点 */
    private ArrayList<UndirectedGraphNode> getNodes_BFS(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        HashSet<UndirectedGraphNode> set = new HashSet<>(); // 作用是确保节点的独立性

        queue.offer(node);
        set.add(node);

        while (!queue.isEmpty()) {
            UndirectedGraphNode curNode = queue.poll();
            for (UndirectedGraphNode neighbor : curNode.neighbors) {
                if (!set.contains(neighbor)) {
                    set.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return new ArrayList<UndirectedGraphNode>(set); // 注意 HashSet 到 ArrayList 的转换
    }

    /* 解法1.2 - 使用DFS + Recursion, 获得图中所有节点 */
    private void getNodes_DFS_Recursive(UndirectedGraphNode node,
                                        ArrayList<UndirectedGraphNode> nodes,
                                        HashSet<UndirectedGraphNode> set) {
        if (set.contains(node))
            return;

        set.add(node);
        nodes.add(node);
        for (UndirectedGraphNode neighbor : node.neighbors)
            getNodes_DFS_Recursive(neighbor, nodes, set);
    }

    /* 解法1.3 - 使用DFS + Stack (non recursion), 获得图中所有节点 */
    private ArrayList<UndirectedGraphNode> getNode_DFS_NonRecursive(UndirectedGraphNode node) {
        Stack<StackElement> stack = new Stack<>();
        HashSet<UndirectedGraphNode> set = new HashSet<>();

        stack.push(new StackElement(node, -1));
        set.add(node);

        while (!stack.isEmpty()) {
            StackElement curEle = stack.peek(); // 先不pop, 确认其所有neighbor都已访问后再pop
            curEle.neighborIndex++;

            // 已访问过该节点所有neighbor, 无需保留
            if (curEle.neighborIndex == curEle.node.neighbors.size()) {
                stack.pop();
                continue;
            }

            UndirectedGraphNode neighbor = curEle.node.neighbors.get(curEle.neighborIndex);

            if (set.contains(neighbor))
                continue;

            stack.push(new StackElement(neighbor, -1));
            set.add(neighbor);
        }

        return new ArrayList<UndirectedGraphNode>(set);
    }

    class StackElement {
        UndirectedGraphNode node;
        int neighborIndex;
        public StackElement(UndirectedGraphNode node, int neighborIndex) {
            this.node = node;
            this.neighborIndex = neighborIndex;
        }
    }






    /**
     * map 存储 旧节点->新节点 mapping
     * list 存储原图中所有节点 uniquely
     *
     * 1. 从原图中获得所有节点, 找出其所有的邻居
     * 2. 对每个邻居
     *    - 如之前未遇见过, 在 map 和 list 中加入
     *    - 将此邻居加入新建节点的 邻居列表中
     */
    public UndirectedGraphNode cloneGraph_2(UndirectedGraphNode node) {
        if (node == null) { return null; }

        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        ArrayList<UndirectedGraphNode> oldNodes = new ArrayList<>();

        map.put(node, new UndirectedGraphNode(node.label));
        oldNodes.add(node);

        int index = 0;
        while (index < oldNodes.size()) {
            UndirectedGraphNode oldNode = oldNodes.get(index);
            for (UndirectedGraphNode oldNeighbor : oldNode.neighbors) {
                if (!map.containsKey(oldNeighbor)) {
                    map.put(oldNeighbor, new UndirectedGraphNode(oldNeighbor.label));
                    oldNodes.add(oldNeighbor);
                }

                map.get(oldNode).neighbors.add(map.get(oldNeighbor));
            }
            index++; // 别忘了更新index
        }

        return map.get(node);
    }




    // Definition for Undirected graph.
     class UndirectedGraphNode {
         int label;
         List<UndirectedGraphNode> neighbors;
         UndirectedGraphNode(int x) {
             label = x;
             neighbors = new ArrayList<UndirectedGraphNode>();
         }
     }
}
