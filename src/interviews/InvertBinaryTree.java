package interviews;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given the root of a binary tree. Invert the binary tree in place.
 * That is, all left children should become right children, and all right
 * children should become left children.
 *
 * Example:
 *
 *     a
 *    / \
 *   b   c
 *  / \  /
 * d   e f
 *
 * The inverted version of this tree is as follows:
 *
 *   a
 *  / \
 *  c  b
 *  \  / \
 *   f e  d
 *
 * Twitter
 */
public class InvertBinaryTree {

    public void invert(TreeNode root) {
        if (root == null)
            return;

        Deque<TreeNode> deque = new ArrayDeque<>();
        // todo
    }

    class TreeNode {
        public String val;
        public TreeNode left, right;
        public TreeNode(String val) {
            this.val = val;
            this.left = this.right = null;
        }

        public void preorder() {
            System.out.print(this.val + " ");
            if (this.left != null)
                this.left.preorder();
            if (this.right != null)
                this.right.preorder();
        }
    }

    @Test
    public void test1() {
        TreeNode n0 = new TreeNode("a");
        TreeNode n1 = new TreeNode("b");
        TreeNode n2 = new TreeNode("c");
        TreeNode n3 = new TreeNode("d");
        TreeNode n4 = new TreeNode("e");
        TreeNode n5 = new TreeNode("f");

        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;

        n0.preorder(); // output: a b d e c f
        System.out.println("\n");
        invert(n0);
        n0.preorder(); // output: a c f b e d
    }
}
