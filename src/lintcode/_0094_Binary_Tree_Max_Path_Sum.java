package lintcode;

import util.TreeNode;

/**
 * 94. Binary Tree Maximum Path Sum
 * Medium
 * #Recursion, #Divide and Conquer, #DP
 * Amazon, FB Meta, Microsoft
 * FAQ
 *
 * Given a binary tree, find the maximum path sum.
 * The path may start and end at any node in the tree.
 *
 * Example 1:
 * Input:  For the following binary tree（only one node）:
 * 2
 * Output：2
 *
 * Example 2:
 * Input:  For the following binary tree:
 *
 *      1
 *     / \
 *    2   3
 * Output: 6
 *
 * Example from LeetCode
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 *
 * leetcode 124. Binary Tree Maximum Path Sum
 */
public class _0094_Binary_Tree_Max_Path_Sum {

    /**
     * 解法1
     *
     * 易错点:
     * 1. 计算左右single path max的时候, 注意pathSum可能为负值, 此时则取0 (即不取此子树)
     */
    public int maxPathSum(TreeNode root) {
        // 注意:
        // 1. 递归不能直接传 int, 因为传入的是值, 而不是指针. 所以用 数组 代替
        // 2. 初始值必须是Integer.MIN_VALUE, 而不能是0. 例如 input: {-1}; output应该也是-1, 而不是0

        int[] ans = new int[1];
        ans[0] = Integer.MIN_VALUE;
        getSinglePathMaxDFS(root, ans);
        return ans[0];
    }

    /*
     * helper - 后序遍历
     * 1. 考虑以当前节点为root, subtree的最大sum, 即左子树最大值 + 右子树最大值 + 当前节点值
     *    注意, 如果左右子树最大值为负值, 可选择不取此子树 (将其最大值定为0)
     * 2. 比较当前最大值与全局最大值
     * 3. helper返回值为, 以当前节点为父节点, 单一子树方向的最大值 (parent-child), 只考虑左右子树中较大者, 将其与当前节点值相加
     *
     * 易错点:
     * 1. 在helper返回值里要注意, 类似计算curMax, 左右子树可能为负, 此时考虑不取 (将其最大值定为0)
     *
     * 观察#1和#3后, 发现如果左右子树为负, 两者都会将其值定为0, 所以获得子树最大和之时即可比较, 之后即无需多次比较
     */
    private int getSinglePathMaxDFS(TreeNode node, int[] ans) {
        if (node == null) { return 0; }

        // 注意: 必须取0或者dfs返回值, 因为后者可能返回负值. 这样的话, 可以选择不取子树, 而只取0
        int leftMax = Math.max(0, getSinglePathMaxDFS(node.left, ans));
        int rightMax = Math.max(0, getSinglePathMaxDFS(node.right, ans));

        // 在每个节点, 用 当前节点值 加上 左右子树各自最大的pathSum, 得到以当前节点为拐点, 最大的pathSum
        ans[0] = Math.max(ans[0], node.val + leftMax + rightMax);

        return node.val + Math.max(leftMax, rightMax); // 只取左右子树中的较大者与当前节点组合
    }



    /**
     * 解法2 - 使用 post order traversal (左右上) + ResultType
     *
     * 易错点:
     * 1. 递归出口, 当node为null时, 返回ResultType(0, MIN). singlePath=0表示不取该点, maxPath=MIN_VALUE因为其他path可能有负数出现
     * 2. 递归拆解, 计算singlePath时, 注意可能返回值为负数, 此时取0
     */
    public class ResultType{
        int singlePath, maxPath;
        public ResultType (int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    public int maxPathSum_2(TreeNode root) {
        ResultType resultType = helper(root);
        return resultType.maxPath;
    }

    public ResultType helper(TreeNode node) {
        if (node == null)
            return new ResultType(0, Integer.MIN_VALUE); // 后者取MIN, 因为可能有负值node存在

        ResultType left = helper(node.left);
        ResultType right = helper(node.right);

        int curSinglePath = node.val + Math.max(left.singlePath, right.singlePath);
        curSinglePath = Math.max(0, curSinglePath); // 如果取此path得到负值, 不然不取 -> 取0

        int curMaxPath = node.val + left.singlePath + right.singlePath; // 当前点加上左右子树的最大path
        int preMaxPath = Math.max(left.maxPath, right.maxPath); // 上一层的最大值
        curMaxPath = Math.max(curMaxPath, preMaxPath);

        return new ResultType(curSinglePath, curMaxPath);
    }
}
