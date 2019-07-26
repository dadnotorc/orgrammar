package lintcode.binarytree;

import org.junit.jupiter.api.Test;
import util.TreeNode;

import static org.junit.jupiter.api.Assertions.*;

class _0007Test {


    @Test
    void test1() {
        TreeNode n0 = new TreeNode(3);
        TreeNode n1 = new TreeNode(9);
        TreeNode n2 = new TreeNode(20);
        TreeNode n3 = new TreeNode(15);
        TreeNode n4 = new TreeNode(7);
        n0.left = n1;
        n0.right = n2;
        n2.left = n3;
        n2.right = n4;

        String expSe = "{3,9,20,#,#,15,7}";
        String actSe = new _0007().serialize(n0);
        System.out.println(actSe);
        assertTrue(expSe.equals(actSe));

        TreeNode deRoot = new _0007().deserialize(actSe);
        System.out.println(deRoot.val);
        System.out.println(deRoot.left.val);
        System.out.println(deRoot.right.val);
        assertNull(deRoot.left.left);
    }

    @Test
    void test2() {
        TreeNode n0 = null;

        String expSe = "{}";
        String actSe = new _0007().serialize(n0);
        System.out.println(actSe);
        assertTrue(expSe.equals(actSe));

        TreeNode deRoot = new _0007().deserialize(actSe);
        System.out.println(deRoot.val);
        System.out.println(deRoot.left.val);
        System.out.println(deRoot.right.val);
        assertNull(deRoot.left.left);
    }





}