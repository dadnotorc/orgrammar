package interviews;

import org.junit.Test;
import util.TreeNode;
import util.helper;

/**
 * Given an integer k and a binary search tree, find the floor (less than
 * or equal to) of k, and the ceiling (larger than or equal to) of k.
 * If either does not exist, then print them as None.
 *
 *        8
 *      /   \
 *    4     12
 *  /  \   /  \
 * 2   6  10  14

 * print findCeilingFloor(root, 5)
 * # (4, 6)
 *
 * Apple
 */
public class FindFloorCeilingInBinarySearchTree {

    public int[] findCeilingFloor(TreeNode root, int k) {
        int[] pair= new int[2];
        pair[0] = Integer.MIN_VALUE;
        pair[1] = Integer.MAX_VALUE;

        helper(root, k, pair);

        return pair;
    }

    private void helper(TreeNode node, int k, int[] pair) {
        if (node == null) {
            return;
        }

        if (k == node.val) {
            pair[0] = pair[1] = k;
            return;
        }

        if (k < node.val) {
            pair[1] = Math.min(pair[1], node.val);
            helper(node.left, k, pair);
        } else {
            pair[0] = Math.max(pair[0], node.val);
            helper(node.right, k, pair);
        }
    }

    @Test
    public void test1() {
        TreeNode n0 = new TreeNode(8);
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(12);
        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(6);
        TreeNode n5 = new TreeNode(10);
        TreeNode n6 = new TreeNode(14);

        n0.left = n1;
        n0.right = n2;

        n1.left = n3;
        n1.right = n4;

        n2.left = n5;
        n2.right = n6;

        int[] pair = findCeilingFloor(n0, 5);
        System.out.println(helper.arrayToString(pair));
    }
}
