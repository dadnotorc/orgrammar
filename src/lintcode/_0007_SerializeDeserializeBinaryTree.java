package lintcode;

import org.junit.Test;
import util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * 7. Serialize and Deserialize Binary Tree
 * Medium
 * #Binary Tree
 * Amazon, Facebook, Google, LinkedIn, Microsoft, Uber
 *
 * Design an algorithm and write code to serialize and deserialize a binary tree.
 * Writing the tree to a file is called 'serialization' and reading back from
 * the file to reconstruct the exact same binary tree is 'deserialization'.
 *
 * Example 1:
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
 * You can use other method to do serialization and deserialization.
 *
 * leetcode 297
 */
public class _0007_SerializeDeserializeBinaryTree {

    /**
     * BFS - 把每一层的nodes加到queue中, 然后依次存入String
     *
     * 易错点:
     * 1. 不管子树是否为空, 都将其加入queue中, 因为可能当前层后续nodes子树不为空
     * 2. 别忘了加入"{" 和 "}"
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "{}"; // 注意: 应返回"{}", 而不是""
        }

        // Can't use Deque as it throw NP exception when adding null.
        // Use ArrayList or LinkedList instead
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode node = q.poll();

                if (node != null) {
                    sb.append(node.val);
                    /*                              3
                    不在这里判断左右子树是否为空,       / \
                    因为当前层后续可能仍有nodes, 例如  9  20
  	                9的子树为空, 但是20的子树不空        /  \
  	                                                15  7
  	                所以第三层
                     */
                    q.offer(node.left);
                    q.offer(node.right);
                } else {
                    sb.append("#");
                }

                sb.append(",");
            }
        }

        // 删除末端多余的 ',' 和 '#'
        // 如果 input={3,9,20,#,#,15}, 需要将 20 的右子树(null节点)删掉
        int i = sb.length() - 1;
        while (sb.charAt(i) == ',' || sb.charAt(i) == '#') {
            sb.deleteCharAt(i);
            i--;
        }

        sb.append('}'); // 别忘了关括号

        return sb.toString();
    }


    /**
     * 先把String分割, 把第一个值加入root, 之后每次取两个, 加入left child, right child
     *
     * 易错点:
     * 1. 别忘了考虑input="{}"
     * 2. 判断左右子树时, 记得检查index是否越界, 因为两颗子树可能为空 (values[]的index已越界)
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty() || data.equals("{}")) {
            return null;
        }

        // 先取substring, 去掉两边的 { } 字符. 之后用","分割
        String[] values = data.substring(1, data.length() - 1).split(",");
        // 就算 data为empty string "", values的length最小也是1

        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int index = 1; // start from 1 instead of 0 because root has been created
        while (index < values.length) { // 判断index是否越界, 而不是queue是否为空, 以免array out of index
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode parent = q.poll();
                TreeNode left, right;
                if (index < values.length && !values[index].equals("#")) { // left child - i
                    left = new TreeNode(Integer.parseInt(values[index]));
                    parent.left = left;
                    q.offer(left);
                }
                if (index+1 < values.length && !values[index+1].equals("#")) { // right child - i + 1
                    right = new TreeNode(Integer.parseInt(values[index+1]));
                    parent.right = right;
                    q.offer(right);
                }
                index += 2;
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
        assertNull(deRoot.left.right);
        System.out.println(deRoot.right.left.val);
        System.out.println(deRoot.right.right.val);

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

    @Test
    public void test3() {
        TreeNode n0 = new TreeNode(3);
        TreeNode n1 = new TreeNode(9);
        TreeNode n2 = new TreeNode(20);
        TreeNode n3 = new TreeNode(15);
        n0.left = n1;
        n0.right = n2;
        n2.left = n3;

        String expSe = "{3,9,20,#,#,15}";
        String actSe = new _0007_SerializeDeserializeBinaryTree().serialize(n0);
        System.out.println(actSe);
        assertTrue(expSe.equals(actSe));

        TreeNode deRoot = new _0007_SerializeDeserializeBinaryTree().deserialize(actSe);
        System.out.println(deRoot.val);
        System.out.println(deRoot.left.val);
        System.out.println(deRoot.right.val);
        assertNull(deRoot.left.left);
        assertNull(deRoot.left.right);
        System.out.println(deRoot.right.left.val);
        assertNull(deRoot.right.right);
    }

    @Test
    public void test4() {
        TreeNode n0 = new TreeNode(3);
        TreeNode n1 = new TreeNode(9);
        TreeNode n2 = new TreeNode(20);
        TreeNode n3 = new TreeNode(15);
        n0.left = n1;
        n0.right = n2;
        n1.left = n3;

        String expSe = "{3,9,20,15}";
        String actSe = new _0007_SerializeDeserializeBinaryTree().serialize(n0);
        System.out.println(actSe);
        assertTrue(expSe.equals(actSe));

        TreeNode deRoot = new _0007_SerializeDeserializeBinaryTree().deserialize(actSe);
        System.out.println(deRoot.val);
        System.out.println(deRoot.left.val);
        System.out.println(deRoot.right.val);
        System.out.println(deRoot.left.left.val);
        assertNull(deRoot.left.right);
        assertNull(deRoot.right.left);
        assertNull(deRoot.right.right);
    }
}
