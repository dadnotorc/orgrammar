/*
Medium
#Binary Tree, #DFS
Amazon, LinkedIn
 */
package lintcode;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 1357. Path Sum II
 *
 * Given a binary tree and a sum, find all root-to-leaf paths
 * where each path's sum equals the given sum.
 *
 * Notice
 * - A leaf is a node with no children.
 *
 * Example 1:
 * Input: root = {5,4,8,11,#,13,4,7,2,#,#,5,1}, sum = 22
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation:
 * The sum of the two paths is 22：
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 *
 * Example 2:
 * Input: root = {10,6,7,5,2,1,8,#,9}, sum = 18
 *               10
 *              /  \
 *             6    7
 *           /  \   / \
 *           5  2   1  8
 *            \
 *             9
 * Output: [[10,6,2],[10,7,1]]
 * Explanation:
 * The sum of the two paths is 18：
 * 10 + 6 + 2 = 18
 * 10 + 7 + 1 = 18
 */
public class _1357_PathSum2 {

    /**
     * Recursion写法 - 使用DFS
     */
    public List<List<Integer>> pathSum_recursion(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, sum, ans, new ArrayList<>());
        return ans;
    }

    // 另一种写法, 较为简洁
    private void dfs_2(TreeNode node, int remainingSum,
                     List<List<Integer>> ans, List<Integer> curPath) {

        if (node == null)
            return;

        curPath.add(node.val);

        if (node.left == null && node.right == null && node.val == remainingSum) {
            ans.add(new ArrayList<>(curPath)); // 记得创建个新的ArrayList
            curPath.remove(curPath.size() - 1); // 别忘了回溯前必须remove
            return;
        } else {
            dfs(node.left, remainingSum - node.val, ans, curPath);
            dfs(node.right, remainingSum - node.val, ans, curPath);
        }

        curPath.remove(curPath.size() - 1);
    }


    // 定义: 每找到一条从root到leaf的path, 且path_sum = target_sum, 将其加入答案队列中
    //
    // 出口: 到达leaf node (注意: 到达leaf的条件是左右子节点皆为空)
    //      1. 如target_sum - path_sum = remainingSum = 0, 加入答案队列中
    //      2. 如remainingSum != 0, 此条path不符合, 退出
    // 拆解: 减少remainingSum, 将当前path带入下层
    private void dfs(TreeNode node, int remainingSum,
                     List<List<Integer>> ans, List<Integer> curPath) {

        if (node == null)
            return;

        if (node.left == null && node.right == null) {
            if (node.val == remainingSum) {
                curPath.add(node.val);
                // ans.add(curPath); // 注意, 这么写是错误的, 必须创建个新的ArrayList
                ans.add(new ArrayList<>(curPath));
                curPath.remove(curPath.size() - 1); // 这里必须remove
            }
            return;
        }

        curPath.add(node.val);

        dfs(node.left, remainingSum - node.val, ans, curPath);
        dfs(node.right, remainingSum - node.val, ans, curPath);

        curPath.remove(curPath.size() - 1);
    }








    /**
     * Iterative写法 - 使用stack模拟DFS
     */
    public List<List<Integer>> pathSum_iterative(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curPath = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        TreeNode preNode = null;
        int curSum = 0;

        while (curNode != null || !stack.isEmpty()) {

            // 先一直沿左子树走到底
            while (curNode != null) {
                stack.push(curNode);
                curSum += curNode.val;
                curPath.add(curNode.val);
                curNode = curNode.left;
            }

            // curNode走到null, 返回上一层
            curNode = stack.peek(); // 使用peek而不是pop的原因是, 当前还没访问右子树

            // 如果当前节点还有右子树, 说明当前节点并不是leaf node
            // 移动curNode到右子树, 之后回到外层while loop, 继续向下找寻leaf node
            //
            // 如果curNode.right=preNode, 说明右子树已经被访问过了, 则向上返回
            if (curNode.right != null && curNode.right != preNode) {
                curNode = curNode.right;
                continue;
            }

            // 这里有两种可能:
            // 1. curNode到达leaf -> 判断当前path是否符合条件
            // 2. curNode的左右子树均已访问 -> 向上(父节点)返回
            // 两种情况均需要去掉CurNode
            if (curNode.left == null && curNode.right == null && curSum == sum)
                ans.add(new ArrayList<>(curPath));

            preNode = curNode;
            curPath.remove(curPath.size() - 1);
            curSum -= curNode.val;
            stack.pop();
            curNode = null; // 别忘了清空curNode
        }

        return ans;
    }
}
