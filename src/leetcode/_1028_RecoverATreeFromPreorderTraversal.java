/*
Hard
#Tree, #DFS
 */
package leetcode;

import java.util.Stack;

/**
 * 1028. Recover a Tree From Preorder Traversal
 *
 * We run a preorder depth first search on the root of a binary tree.
 *
 * At each node in this traversal, we output D dashes (D个横线) (where D is the depth of this node),
 * then we output the value of this node.  (If the depth of a node is D, the depth of its
 * immediate child is D+1.  The depth of the root node is 0.)
 *
 * If a node has only one child, that child is guaranteed to be the left child.
 *
 * Given the output S of this traversal, recover the tree and return its root.
 *
 * Example 1:
 *       1
 *    /    \
 *   2     5
 *  / \   / \
 * 3  4  6  7
 * Input: "1-2--3--4-5--6--7"
 * Output: [1,2,5,3,4,6,7]
 *
 * Example 2:
 *        1
 *      /   \
 *     2    5
 *    /    /
 *   3    6
 *  /    /
 * 4    7
 * Input: "1-2--3---4-5--6---7"
 * Output: [1,2,5,3,null,6,null,4,null,7]
 *
 * Example 3:
 *       1
 *      /
 *    401
 *    /  \
 *  349  88
 *  /
 * 90
 * Input: "1-401--349---90--88"
 * Output: [1,401,null,349,88,90]
 *
 * Note:
 * - The number of nodes in the original tree is between 1 and 1000.
 * - Each node will have a value between 1 and 10^9.
 */
public class _1028_RecoverATreeFromPreorderTraversal {

    /**
     * Recursion
     *
     * 使用index指向字符串, 从前往后.
     * 递归中
     * - 每次获得当前'-'数量, 如果'-'数量与提供的depth不符合, 返回空
     * - 获得'-'之后的数字 (直到下一个'-'之前)
     */
    int index = 0;

    public TreeNode recoverFromPreorder_recursion(String S) {
        return helper(S, 0);
    }

    private TreeNode helper(String s, int depth) {
        int numDash = 0;
        int strLen = s.length();
        // 找出当前数字前dash的数量
        while (index + numDash < strLen && s.charAt(index + numDash) == '-') {
            numDash++;
        }
        if (numDash != depth) {
            return null;
        }

        // 找出dash之后的数字
        int next = index + numDash;
        while (next < strLen && s.charAt(next) != '-') {
            next++;
        }
        int val = Integer.parseInt(s.substring(index + numDash, next)); // [index+numDash, next)

        index = next; // 别忘了将index指去下一个dash

        TreeNode root = new TreeNode(val);
        root.left = helper(s, depth + 1);
        root.right = helper(s, depth + 1);
        return root;
    }


    /**
     * Iterative 使用stack
     */
    public TreeNode recoverFromPreorder(String S) {
        int depth, val;
        int strLen = S.length();
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i < strLen; ) { // 这里不做i++
            for (depth = 0; S.charAt(i) == '-'; i++) {
                depth++;
            }
            for (val = 0; i < strLen && S.charAt(i) != '-'; i++) { //别忘了检查i是否越界
                val = val * 10 + (S.charAt(i) - '0');
            }
            while (stack.size() > depth) { // 别忘了pop多多余的nodes
                stack.pop();
            }

            TreeNode node = new TreeNode(val);
            if (!stack.isEmpty()) {
                if (stack.peek().left == null) {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
            }
            stack.push(node);
        }
        while (stack.size() > 1) { // 别忘了pop多多余的nodes, 只留下root
            stack.pop();
        }
        return stack.pop();
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
