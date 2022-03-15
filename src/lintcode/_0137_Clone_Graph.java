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
 *
 * leetcode 133
 */
public class _0137_Clone_Graph {

    /*
    面试中, 询问
    - node 的数量 / node.val 的值的范围
    - 是否有不同的 node 包含相同的 val

    如果题目中给出 node value 的 range 是 [0, 100], 共有 101 个, 可用 array 取代 hashmap
     */
    public UndirectedGraphNode cloneGraph_array(UndirectedGraphNode node) {
        if (node == null) { return null; }

        // 使用 array 取代 hashmap, 所以这不是 boolean array, 而是 node array
        // array[oldNode.val] = newNode
        UndirectedGraphNode[] mapping = new UndirectedGraphNode[101]; // 101 是 node.val 的range
        Arrays.fill(mapping, null);

        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        dfs(node, newNode, mapping);

        return newNode;
    }

    private void dfs(UndirectedGraphNode oldNode,
                     UndirectedGraphNode newNode,
                     UndirectedGraphNode[] mapping) {
        mapping[newNode.label] = newNode;

        for (UndirectedGraphNode oldNeighbor : oldNode.neighbors) {
            if (mapping[oldNeighbor.label] == null) {
                UndirectedGraphNode newNeighbor = new UndirectedGraphNode(oldNeighbor.label);

                // 这里不用在 mapping 中添加, 因为会在 dfs 下一层中加入

                newNode.neighbors.add(newNeighbor);
                dfs(oldNeighbor, newNeighbor, mapping);
            } else {
                newNode.neighbors.add(mapping[oldNeighbor.label]);
            }
        }
    }


    /**
     * 三次遍历
     * 1. 从原图中获得所有的点
     * 2. 复制点 (获得新的nodes)
     * 3. 复制边 (获得新的edges)
     *
     * 遍历时, 注意保证节点的独立性, 因为节点是undirected, 所以邻居的邻居可以是本身
     * 跟新node添加neighbor的时候, 注意要选择新建出来的neighbor nodes
     */
    public UndirectedGraphNode cloneGraph_3(UndirectedGraphNode node) {
        if (node == null) { return null; }

        // list existing vertexes
        HashSet<UndirectedGraphNode> oldNodes = new HashSet<>();
        getNodes(node, oldNodes);

        // copy vertexes
        UndirectedGraphNode[] mapping = new UndirectedGraphNode[101];
        for (UndirectedGraphNode oldNode : oldNodes) {
            mapping[oldNode.label] = new UndirectedGraphNode(oldNode.label);
        }

        // copy edges
        for (UndirectedGraphNode oldNode : oldNodes) {
            UndirectedGraphNode newNode = mapping[oldNode.label];

            for (UndirectedGraphNode neighbor : oldNode.neighbors) {
                newNode.neighbors.add(mapping[neighbor.label]);
            }
        }

        return mapping[node.label];
    }

    private void getNodes(UndirectedGraphNode oldNode, HashSet<UndirectedGraphNode> oldNodes) {
        if (!oldNodes.contains(oldNode)) {
            oldNodes.add(oldNode);
            for (UndirectedGraphNode neighbor : oldNode.neighbors) {
                getNodes(neighbor, oldNodes);
            }
        }
    }




    /**
     * 原来的写法
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;

        // 先获得图中所有节点, 存入list中
        // 使用BFS
        ArrayList<UndirectedGraphNode> oldNodes = getNodes_BFS(node);

        // 使用DFS Recursion, 这里只要 HashSet 即可, 无需 ArrayList
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
