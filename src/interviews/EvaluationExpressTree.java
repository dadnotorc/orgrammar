package interviews;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Given a simple expression tree, consisting of basic binary operators
 * i.e., + , – ,* and / and some integers, evaluate the expression tree.
 *
 * Example 1:
 * Input: [+, *, -, 5, 4, 100, 20]
 *        +
 *      /  \
 *    *     -
 *  / \    / \
 * 5  4  100  20
 * Output: 100
 * Explanation: (5 * 4) + (100 - 20) = 100
 *
 * Example 2:
 * Input: [+, *, -, 5, 4, 100, /, null, null, null, null, null, null, 20, 2]
 *        +
 *      /  \
 *    *     -
 *  / \    / \
 * 5  4  100  /
 *          /  \
 *         20   2
 * Output: 110
 * Explanation: (5 * 4) + (100 - (20 / 2) = 110
 */
public class EvaluationExpressTree {

    /**
     * 1. 如果当前节点为空, 返回 0
     * 2. 如果当前节点为数字 operand, 返回该数字
     * 3. 如果不是, 说明是运算符号 operator, 递归进下一层, 返回后进行计算
     */
    public int evaTree(Node root) {
        if (root == null) {
            return 0;
        }

        // 这个解法是用过 regex 判断 root 的值
        // 也可以简单的判断 root 是否有左右 子节点
        if (isNumber(root.val)) {
            return toInt(root.val);
        }

        int left = evaTree(root.left);
        int right = evaTree(root.right);

        return calculate(left, right, root.val);
    }

    public boolean isNumber(String s) {
        Pattern int_pattern = Pattern.compile("-?[0-9]+"); // 正负整数, 但不考虑小数 或者 scientific notation

        Pattern float_pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*"); // 正负整数,小数, 但不考虑 scientific notation

        // 如果不考虑 scentific notation, 直接使用 int_pattern 或者 float_pattern 判断
        Matcher isNum = int_pattern.matcher(s);


        /* 考虑科学记数法
        String bigStr;
        try {
            bigStr = new BigDecimal(s).toString();
        } catch (Exception e) {
            return false; // 有非数字
        }
        Matcher isNum = float_pattern.matcher(bigStr);
        */

        return isNum.matches();
    }

    public int toInt(String s) {
        int ans = 0;
        boolean isNegative = s.charAt(0) == '-';

        int i = isNegative ? 1 : 0;
        int n = s.length();
        while (i < n) {
            ans = 10 * ans + (s.charAt(i) - '0');
            i++;
        }

        return isNegative ? -ans : ans;
    }

    public int calculate(int a, int b, String operator) {
        int ans = 0;
        switch (operator) {
            case "+":
                ans = a + b;
                break;
            case "-":
                ans = a - b;
                break;
            case "*":
                ans = a * b;
                break;
            case "/":
                ans = a / b;
                break;
            default:
                System.out.println("invalid operator: " + operator);
                break;
        }
        return ans;
    }





    class Node{
        public String val; // 用 String, 因为这里的值可以是 运算符号 或者 一个数字
        public Node left, right;
        public Node(String _val) {
            this.val = _val;
            this.left = this.right = null;
        }
    }


    @Test
    public void test_toInt() {
        String s1 = "123";
        String s2 = "-1";
        String s3 = "0";

        Assert.assertTrue(isNumber(s1));
        Assert.assertTrue(isNumber(s2));
        Assert.assertTrue(isNumber(s3));

        Assert.assertEquals(123, toInt(s1));
        Assert.assertEquals(-1, toInt(s2));
        Assert.assertEquals(0, toInt(s3));
    }

    @Test
    public void test1() {
        Node root = new Node("+");
        root.left = new Node("*");
        root.left.left = new Node("5");
        root.left.right = new Node("4");
        root.right = new Node("-");
        root.right.left = new Node("100");
        root.right.right = new Node("20");

        Assert.assertEquals(100, evaTree(root));
    }

    @Test
    public void test2() {
        Node root = new Node("+");
        root.left = new Node("*");
        root.left.left = new Node("5");
        root.left.right = new Node("4");
        root.right = new Node("-");
        root.right.left = new Node("100");
        root.right.right = new Node("/");
        root.right.right.left = new Node("20");
        root.right.right.right = new Node("2");

        Assert.assertEquals(110, evaTree(root));
    }
}
