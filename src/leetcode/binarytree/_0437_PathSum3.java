package leetcode.binarytree;

import util.TreeNode;

import java.util.HashMap;

/**
 * 437. Path Sum III
 * Easy
 *
 * You are given a binary tree in which each node contains an integer value.
 *
 * Find the number of paths that sum to a given value.
 *
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (traveling only from parent nodes to child nodes).
 *
 * The tree has no more than 1,000 nodes and the values are in the range
 * -1,000,000 to 1,000,000.
 *
 * Example:
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * Return 3. The paths that sum to 8 are:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 *
 */
public class _0437_PathSum3 {

    /**
     * 给一个节点和一个目标值，返回以这个节点为根的树中，
     * 和为目标值的路径总数。
     *
     * 时间花费较高
     * time: O(n^2) in worst case (no branching);
     *       O(nlogn) in best case (balanced tree).
     * space: O(n) due to recursion
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSumFromCurNode(root, sum) // 根节点为开头的路径数
                + pathSum(root.left, sum) // 左侧路径总数
                + pathSum(root.right, sum); // 右侧路径总数
    }

    // 给一个节点和一个目标值，返回以这个节点为根的树中，
    // 能凑出几个以该节点为路径开头，和为目标值的路径总数。
    public int pathSumFromCurNode(TreeNode node, int tar) {
        if (node == null) {
            return 0;
        }
        return (node.val == tar ? 1 : 0) // 加上当前节点, 是否满足目标值
                // 左侧能凑几个target - node.val
                + pathSumFromCurNode(node.left, tar - node.val)
                // 右侧能凑几个target - node.val
                + pathSumFromCurNode(node.right, tar - node.val);
    }

    /* **************************** */

    /**
     *
     */
    public int pathSum_HashMap(TreeNode root, int target) {
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1); // 目标值为0, 有一条路径, 即空路径
        return dfs(root, target, 0, preSum);
    }

    private int dfs(TreeNode node, int target,
                    int curSum, HashMap<Integer, Integer> preSum) {
        if (node == null) { return 0; }

        curSum += node.val;

        // 在HM中找curSum-target的原因是,当curSum大于target时,两者差值如果存在于当期路径
        // 则减掉相应的前置路径即可满足. 例如, 当前路径为 1->2->3, target=5
        // curSum-target=1,即首位节点. 去掉该节点就找到和为目标值的路劲 2->3
        int result = preSum.getOrDefault(curSum - target, 0);

        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);

        result += dfs(node.left, target, curSum, preSum)
                + dfs(node.right, target, curSum, preSum);

        // 减1的原因是, 当前路径已检测完, 且当前路径不应作为其他路径的前置. 例如,
        // left subtree不应作为right subtree的前置
        preSum.put(curSum, preSum.get(curSum) - 1);

        return result;
    }
}
