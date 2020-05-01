package leetcode;

/**
 * Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree
 *
 * Given a binary tree where each path going from the root to any leaf form a valid sequence,
 * check if a given string is a valid sequence in such binary tree.
 *
 * We get the given string from the concatenation of an array of integers arr and the concatenation
 * of all values of the nodes along a path results in a sequence in the given binary tree.
 *
 * Example 1:
 * Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
 *          0
 *       /     \
 *      1      0
 *    /   \   /
 *   0    1  0
 *    \  / |
 *    1 0  0
 * Output: true
 * Explanation:
 * The path 0 -> 1 -> 0 -> 1 is a valid sequence (green color in the figure).
 * Other valid sequences are:
 * 0 -> 1 -> 1 -> 0
 * 0 -> 0 -> 0
 *
 * Example 2:
 * Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
 *          0
 *       /     \
 *      1      0
 *    /   \   /
 *   0    1  0
 *    \  / |
 *    1 0  0
 * Output: false
 * Explanation: The path 0 -> 0 -> 1 does not exist, therefore it is not even a sequence.
 *
 * Example 3:
 *          0
 *       /     \
 *      1      0
 *    /   \   /
 *   0    1  0
 *    \  / |
 *    1 0  0
 * Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
 * Output: false
 * Explanation: The path 0 -> 1 -> 1 is a sequence, but it is not a valid sequence.
 *
 *
 * Constraints:
 * - 1 <= arr.length <= 5000
 * - 0 <= arr[i] <= 9
 * - Each node's value is between [0 - 9].
 */
public class _0000_IsValidSequence {

    /**
     * DFS - 将当前array index带入下一层递归
     * - index到达最后一位时, 确认当前node不空且与array末位值相对, 并且已是leaf node -> return true
     * - index未到达最后位之前, 确认当前node不空且与array末位值相对
     *
     * 易错点
     * 1. 比较值之前, 一定要确保node不为null
     */
    public boolean isValidSequence(TreeNode root, int[] arr) {
        return helper(root, arr, 0);
    }

    // 比另一种写法更简洁些
    private boolean helper(TreeNode node, int[] arr, int index) {
        if (node == null || node.val != arr[index])
            return false;

        if (index == arr.length - 1)
            return node.left == null && node.right == null;

        boolean left = helper(node.left, arr, index + 1);
        boolean right = helper(node.right, arr, index + 1);

        return left || right;
    }



    private boolean helper_2(TreeNode node, int[] arr, int index) {
        if (index == arr.length -1 && node != null) // 注意别忘了检查node是否为null
            return node.val == arr[index] && node.left == null && node.right == null;

        if (node == null || node.val != arr[index]) // 注意别忘了检查node是否为null
            return false;

        boolean left = helper_2(node.left, arr, index + 1);
        boolean right = helper_2(node.right, arr, index + 1);

        return left || right;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
