package interviews;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Check If Two Expression Trees are Equivalent
 *
 * A binary expression tree is a kind of binary tree used to represent arithmetic expressions.
 * Each node of a binary expression tree has either zero or two children.
 * Leaf nodes (nodes with 0 children) correspond to operands (variables),
 * and internal nodes (nodes with two children) correspond to the operators.
 * In this problem, we only consider the '+' operator (i.e. addition).
 *
 * You are given the roots of two binary expression trees, root1 and root2.
 * Return true if the two binary expression trees are equivalent. Otherwise, return false.
 *
 * Two binary expression trees are equivalent if they evaluate to the same value regardless of what the variables are set to.
 *
 * Example 1:
 * Input: root1 = [x], root2 = [x]
 * Output: true
 *
 * Example 2:
 * Input: root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,c]
 *     +             +
 *   /  \          /  \
 * a     +        +    a
 *      / \      / \
 *     b   c    b   c
 * Output: true
 * Explaination: a + (b + c) == (b + c) + a
 *
 * Example 3:
 * Input: root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,d]
 *     +             +
 *   /  \          /  \
 * a     +        +    a
 *      / \      / \
 *     b   c    b   d
 * Output: false
 * Explaination: a + (b + c) != (b + d) + a
 *
 * Constraints:
 * The number of nodes in both trees are equal, odd and, in the range [1, 4999].
 * Node.val is '+' or a lower-case English letter.
 * It's guaranteed that the tree given is a valid binary expression tree.
 */
public class GOOG_2022_CompareTwoExpressionTrees {

    /**
     * 只考虑 + 运算时, 无需考虑前后顺序, 只需要确认两者中, 所有字符都相等即可
     */
    public boolean checkEquivalence(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        // 记录遇到的小写字母数量
        int[] count1 = new int[26];
        int[] count2 = new int[26];

        helper(root1, count1);
        helper(root2, count2);

        return Arrays.equals(count1, count2);
    }

    // 可以用 Queue 做层级遍历 / 或者 递归 pre-order traversal

    // 这里用递归 - pre-order
    public void helper(Node node, int[] count) {
        if (node == null) {
            return;
        }

        if (node.val >= 'a' && node.val <= 'z') {
            count[node.val - 'a']++;
        } else if (node.val == '+') {
            helper(node.left, count);
            helper(node.right, count);
        } else {
            System.out.println("invalid val:" + node.val);
        }
    }





    class Node{
        public char val;
        public Node left, right;
        public Node(char _val) {
            this.val = _val;
            this.left = this.right = null;
        }
    }



    @Test
    public void test1() {
        Node root1 = new Node('+');
        root1.left = new Node('a');
        root1.right = new Node('+');
        root1.right.left = new Node('b');
        root1.right.right = new Node('c');

        Node root2 = new Node('+');
        root2.left = new Node('+');
        root2.left.left = new Node('b');
        root2.left.right = new Node('c');
        root2.right = new Node('a');

        Assert.assertTrue(checkEquivalence(root1, root2));
    }

    @Test
    public void test2() {
        Node root1 = new Node('+');
        root1.left = new Node('a');
        root1.right = new Node('+');
        root1.right.left = new Node('b');
        root1.right.right = new Node('c');

        Node root2 = new Node('+');
        root2.left = new Node('+');
        root2.left.left = new Node('b');
        root2.left.right = new Node('d');
        root2.right = new Node('a');

        Assert.assertFalse(checkEquivalence(root1, root2));
    }
}

