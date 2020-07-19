/*
Hard
#Tree, #DFS
Amazon
 */
package leetcode;

import org.junit.Test;

/**
 * 99. Recover Binary Search Tree
 *
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 *
 * Example 1:
 * Input: [1,3,null,null,2]
 *    1
 *   /
 *  3
 *   \
 *    2
 * Output: [3,1,null,null,2]
 *    3
 *   /
 *  1
 *   \
 *    2
 *
 * Example 2:
 * Input: [3,1,4,null,null,2]
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 * Output: [2,1,4,null,null,3]
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 *
 * Follow up:
 * - A solution using O(n) space is pretty straight forward.
 * - Could you devise a constant space solution?
 */
public class _0099_RecoverBinarySearch_Tree {


    /**
     * 基础的DFS解法
     *
     * in-order traversal
     * 代码模板:
     * private void traverse(TreeNode node) {
     *     if (root == nuLL) { return; }
     *     traverse(node.left); // 1.先进left
     *     // 2.处理当前node
     *     traverse(node.right); // 3. 后进right
     * }
     *
     * 假设坐下这颗树被变成右下, 3与6互换
     *       4                4
     *    /    \           /    \
     *   2     6    =>    2     3
     *  / |   / \        / |   / \
     * 1  3  5  7       1  6  5  7
     *
     * in-order traverse 右上的树可以得到: 1,2,6,4,5,3,7
     * 每两个node比较, 可以发现, 6-4反了, 以及 5-3反了.
     * 我们要做的就是将第一组的左边, 与第二组的右边, 互换
     *
     * time: O(n);  space: O(height) height of the tree
     */

    TreeNode nodeToSwap1 = null;
    TreeNode nodeToSwap2 = null;
    TreeNode prevNode = new TreeNode(Integer.MIN_VALUE); // prevNode用来记录 in-order traverse时, 当前node的前一位

    public void recoverTree_DFS(TreeNode root) {
        traverse(root);

        int tmp = nodeToSwap1.val;
        nodeToSwap1.val = nodeToSwap2.val;
        nodeToSwap2.val = tmp;
    }

    // 用于寻找nodeToSwap1和nodeToSwap2
    private void traverse(TreeNode node) {
        if (node == null) { return; }

        // in-order 先进left
        traverse(node.left);

        // in-order 处理当前node

        // 如果被调换的两个数字是consecutive, 例如 123 54 6, 此if会出现一次, 此时将5计入nodeToSwap1, 4计入nodeToSwap2
        // 如果两个数字不是consecutive, 例如 12 6453, 此if会出现两次, 第一次记录6, 第二次记录3(同时覆盖掉之前记录的4)
        if (prevNode.val > node.val) {
            if (nodeToSwap1 == null) {
                nodeToSwap1 = prevNode;
            }
            nodeToSwap2 = node;
        }

        prevNode = node; // 别忘了更新prevNode

        // in-order 后进right
        traverse(node.right);
    }



    /**
     * Morris Traversal
     * time: O(n); space: O(1)
     */
    public void recoverTree_MorrisTraversal(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null; // in-order traversal中 当前节点的前一位
        TreeNode tmp = null; // 用于移动的临时指针, 不要跟pre搞混了
        TreeNode swap1 = null;
        TreeNode swap2 = null;

        while (cur != null) {
            if (cur.left == null) {
                if (pre != null && pre.val > cur.val) {
                    if (swap1 == null) {
                        swap1 = pre;
                    }
                    swap2 = cur;
                }
                pre = cur; // 做过pre与cur的比较后, 记得挪动pre与cur
                cur = cur.right;
            } else {
                // connect threading
                tmp = cur.left;
                while (tmp.right != null && tmp.right != cur) {
                    tmp = tmp.right;
                }

                if (tmp.right == null) {
                    // construct the threading
                    tmp.right = cur;
                    cur = cur.left;
                } else {
                    // the threading already exists
                    if (pre != null && pre.val > cur.val) {
                        if (swap1 == null) {
                            swap1 = pre;
                        }
                        swap2 = cur;
                    }

                    tmp.right = null; // 将树复原
                    pre = cur;  // 做过pre与cur的比较后, 记得挪动pre与cur
                    cur = cur.right;
                }
            }
        }

        // swap
        if (swap1 != null && swap2 != null) {
            int tmpVal = swap1.val;
            swap1.val = swap2.val;
            swap2.val = tmpVal;
        }
    }


    @Test
    public void test1() {
        TreeNode n0 = new TreeNode(5);
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(2);
        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        recoverTree_MorrisTraversal(n0);
    }

    @Test
    public void test2() {
        TreeNode n0 = new TreeNode(3);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(4);
        TreeNode n3 = new TreeNode(2);
        n0.left = n1;
        n0.right = n2;
        n2.left = n3;
        recoverTree_MorrisTraversal(n0);
    }

    @Test
    public void test3() {
        TreeNode n0 = new TreeNode(3);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n0.right = n1;
        n1.left = n2;
        recoverTree_MorrisTraversal(n0);
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
