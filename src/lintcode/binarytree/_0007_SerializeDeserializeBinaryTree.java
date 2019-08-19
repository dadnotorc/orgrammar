/*
Medium
Binary Tree
Amazon, Facebook, Google, LinkedIn, Microsoft, Uber
 */
package lintcode.binarytree;

import org.junit.Test;
import util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


/**
 * 7. Serialize and Deserialize Binary Tree

 * Design an algorithm and write code to serialize and deserialize a binary tree.
 * Writing the tree to a file is called 'serialization' and reading back from
 *  the file to reconstruct the exact same binary tree is 'deserialization'.
 *
 * Example 1:
 *
 * Input：{3,9,20,#,#,15,7}
 * Output：{3,9,20,#,#,15,7}
 * Explanation：
 * Binary tree {3,9,20,#,#,15,7},  denote the following structure:
 * 	  3
 * 	 / \
 * 	9  20
 * 	  /  \
 * 	 15   7
 * it will be serialized {3,9,20,#,#,15,7}
 *
 * Example 2:
 * Input：{1,2,3}
 * Output：{1,2,3}
 * Explanation：
 * Binary tree {1,2,3},  denote the following structure:
 *    1
 *   / \
 *  2   3
 * it will be serialized {1,2,3}
 *
 * Our data serialization use BFS traversal. This is just for when you got
 * Wrong Answer and want to debug the input.
 * You can use other method to do serializaiton and deserialization.
 */
public class _0007_SerializeDeserializeBinaryTree {

    // 用bfs, 把每一层的nodes加到queue中, 然后依次存入String
    public String serialize(TreeNode root) {
        if (root == null) {
            /**
             * 注意: 应返回"{}", 而不是""
             */
            return "{}";
        }

        /**
         * Can't use Deque as it throw NP exception when adding null.
         * Use ArrayList or LinkedList instead
         */
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                TreeNode n = queue.poll();
                if (n != null) {
                    sb.append(n.val);
                    queue.offer(n.left);
                    queue.offer(n.right);
                } else {
                    sb.append("#");
                }

                if (!queue.isEmpty()) {
                    sb.append(",");
                }
            }
        }

        String ans = sb.toString();

        // 删除末端的 ",#"
        while (ans.substring(ans.length() - 2, ans.length()).equals(",#")) {
            ans = ans.substring(0, ans.length() - 2);
        }

        ans += "}";

        return ans;

    }


    // 先把String分割, 把第一个值加入root, 之后每次取两个, 加入left child, right child
    public TreeNode deserialize(String data) {
        if (data == null || data.equals("{}")) {
            return null;
        }

        // 先取substring, 去掉两边的 { } 字符
        // 之后用","分割
        String[] values = data.substring(1, data.length() - 1).split(",");
        if (values == null || values.length < 1) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1; // start from 1 instead of 0 because root has been created
        while (i < values.length) {
            int sz = queue.size();
            for (int j = 0; j < sz; j++) {
                TreeNode parent = queue.poll();
                TreeNode left, right;
                if (values[i] != null && !values[i].equals("#")) { // left child - i
                    left = new TreeNode(Integer.parseInt(values[i]));
                    parent.left = left;
                    queue.offer(left);
                }
                if (values[i+1] != null && !values[i+1].equals("#")) { // right child - i + 1
                    right = new TreeNode(Integer.parseInt(values[i+1]));
                    parent.right = right;
                    queue.offer(right);
                }
                i = i + 2;
            }
        }

        return root;
    }

    @Test
    public void test1() {
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
        String actSe = new _0007_SerializeDeserializeBinaryTree().serialize(n0);
        System.out.println(actSe);
        assertTrue(expSe.equals(actSe));

        TreeNode deRoot = new _0007_SerializeDeserializeBinaryTree().deserialize(actSe);
        System.out.println(deRoot.val);
        System.out.println(deRoot.left.val);
        System.out.println(deRoot.right.val);
        assertNull(deRoot.left.left);
    }

    @Test
    public void test2() {
        TreeNode n0 = null;

        String expSe = "{}";
        String actSe = new _0007_SerializeDeserializeBinaryTree().serialize(n0);
        System.out.println(actSe);
        assertTrue(expSe.equals(actSe));

        TreeNode deRoot = new _0007_SerializeDeserializeBinaryTree().deserialize(actSe);
        assertNull(deRoot);
    }
}
