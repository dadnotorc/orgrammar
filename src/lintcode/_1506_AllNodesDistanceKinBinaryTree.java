/*
Medium

Amazon, Facebook
 */
package lintcode;

import util.TreeNode;

import java.util.*;

/**
 * 1506. All Nodes Distance K in Binary Tree
 *
 * We are given a binary tree (with root node root), a target node,
 * and an integer value K.
 *
 * Return a list of the values of all nodes that have a distance K
 * from the target node. The answer can be returned in any order.
 *
 * Notice
 * - The given tree is non-empty.
 * - Each node in the tree has unique values 0 <= node.val <= 500.
 * - The target node is a node in the tree.
 * - 0 <= K <= 1000.
 *
 * Example 1:
 * Input:
 * {3,5,1,6,2,0,8,#,#,7,4}
 * 5
 * 2
 * Output: [7,4,1]
 * Explanation:
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 *      3
 *    /   \
 *   5    1
 *  / \  / \
 * 6  2 0  8
 *   / \
 *  7  4
 *
 * Example 2
 * Input:
 * {1,2,3,4}
 * 2
 * 1
 * Output: [1,4]
 * Explanation:
 * The binary tree is like this:
 *     1
 *    / \
 *   2   3
 *  /
 * 4
 * The node 1 and 4 is a distance 1 away from 2.
 */
public class _1506_AllNodesDistanceKinBinaryTree {

    /**
     * 先遍历整个树, 画出<childNode, parentNode>关系图
     * 使用queue, 先将target放入, 然后按距离由近到远, 将附近nodes放进queue
     * 使用set, 记录已访问过的节点
     */
    public List<Integer> distanceK_1(TreeNode root, TreeNode target, int K) {

        // key = child node; value = parent node
        Map<TreeNode, TreeNode> childParentMap = new HashMap<>();
        dfs(root, null, childParentMap);

        // 获得父子节点关系图后, 从target点出发, 寻找距离为K的节点
        // 可以是子节点, 或者父节点, 或者sibling节点

        Queue<TreeNode> nodesByDistance = new LinkedList<>();
        Set<TreeNode> visitedNodes = new HashSet<>();

        nodesByDistance.offer(target);
        visitedNodes.add(target);

        // 用curNodes 和 newNodes 记录当前距离的节点数量, +1距离后的节点数量
        int distance = 0;

        while (!nodesByDistance.isEmpty()) {

            if (distance == K) {
                List<Integer> ans = new LinkedList<>();
                for (TreeNode n : nodesByDistance)
                    ans.add(n.val);

                return ans;
            } else {
                int curNodes = nodesByDistance.size();
                for (int i = 0; i < curNodes; i++) {
                    TreeNode node = nodesByDistance.poll();

                    if (node.left != null && !visitedNodes.contains(node.left)) {
                        nodesByDistance.offer(node.left);
                        visitedNodes.add(node.left);
                    }

                    if (node.right != null && !visitedNodes.contains(node.right)) {
                        nodesByDistance.offer(node.right);
                        visitedNodes.add(node.right);
                    }

                    TreeNode parent = childParentMap.get(node);
                    if (parent != null && !visitedNodes.contains(parent)) {
                        nodesByDistance.offer(parent);
                        visitedNodes.add(parent);
                    }
                }
                distance++; // 别忘了增加距离
            }
        }

        return new LinkedList<>(); // 未找到任何结果, 返回空值
    }

    /**
     * 此解法使用queue记录target周围节点, 使用null节点来区分距离远近例如
     * null, target, null, 距离1的节点们, null, ..., null, 距离K的节点们
     *
     * 访问到非空节点时, 将其全部未访问过的父子节点加入queue中;
     * 当访问到null节点时, 说明距离target距离相等的所有节点已加入queue
     *
     * 注意距离K可以等于0
     */
    public List<Integer> distanceK_2(TreeNode root, TreeNode target, int K) {

        Map<TreeNode, TreeNode> parentNodeMap = bfs(root);

        // 先加null, 再加target的理由是:
        // distance可以从0开始, 这样满足如果要找的是距离为0的节点(即本身)
        Queue<TreeNode> nodesQueue = new LinkedList<>();
        nodesQueue.offer(null);
        nodesQueue.offer(target);

        // 此set记录当前访问节点是否已被访问过
        Set<TreeNode> visitedSet = new HashSet<>();
        visitedSet.add(null);
        visitedSet.add(target);

        int distance = 0;
        while(!nodesQueue.isEmpty()) {
            TreeNode node = nodesQueue.poll();

            if (node == null) {
                if (distance == K) { // queue中当前所有节点距离target均为K
                    List<Integer> ans = new LinkedList<>();
                    for (TreeNode nodeToAdd : nodesQueue) {
                        ans.add(nodeToAdd.val);
                    }
                    return ans;
                }
                nodesQueue.offer(null);
                distance++;
            } else {
                // 把当前节点的父子节点全部加入queue中

                if (!visitedSet.contains(node.left)) {
                    nodesQueue.offer(node.left);
                    visitedSet.add(node.left);
                }

                if (!visitedSet.contains(node.right)) {
                    nodesQueue.offer(node.right);
                    visitedSet.add(node.right);
                }

                TreeNode parentNode = parentNodeMap.get(node);
                if (!visitedSet.contains(parentNode)) {
                    nodesQueue.offer(parentNode);
                    visitedSet.add(parentNode);
                }
            }
        }

        // 未找到任何结果, 返回空list
        return new LinkedList<Integer>();
    }

    // 用 DFS 遍历整个树, 记录父子节点关系
    private void dfs(TreeNode childNode, TreeNode parentNode,
                     Map<TreeNode, TreeNode> map) {
        if (childNode != null) {
            map.put(childNode, parentNode);
            dfs(childNode.left, childNode, map);
            dfs(childNode.right, childNode, map);
        }
    }

    // 用 BFS 遍历整个树, 记录父子节点关系
    private Map<TreeNode, TreeNode> bfs(TreeNode root) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    map.put(node.left, node);
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    map.put(node.right, node);
                    queue.offer(node.right);
                }
            }
        }

        return map;
    }
}
