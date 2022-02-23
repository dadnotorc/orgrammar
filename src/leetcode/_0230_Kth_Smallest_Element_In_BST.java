package leetcode;

import java.util.Stack;

/**
 * 230. Kth Smallest Element in a BST
 * Medium
 * #Binary Search, #Tree, #DFS
 *
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 * Example 1:
 * Input: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * Output: 1
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * Output: 3
 *
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find
 * the kth smallest frequently? How would you optimize the kthSmallest routine?
 *
 * Constraints:
 * The number of elements of the BST is between 1 to 10^4.
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 */
public class _0230_Kth_Smallest_Element_In_BST {

    /**
     * 先走到左下角 (最小值), 然后沿着树走k步
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        int step = 1;
        while (step < k) { // 从第1步开始走k-1步, 每步pop一次. 循环结束时, stack最上方为 kth smallest
            step++;

            // 注意! 这里不能pop(), 需要保留在stack中,
            // 因为在之后的else里(往上走时), 需要判断上层node是否时当前node的父节点
            node = stack.peek();

            if (node.right != null) { // 往右下走, 然后往左下走
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            } else { // 需要往上走
                stack.pop();
                while (!stack.isEmpty() && stack.peek().right == node) { // 别忘了判断stack是否为空
                    node = stack.pop();
                }
            }
        }

        return stack.peek().val;
    }

    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /*
          4
        3    6
            5 7
     */
}
