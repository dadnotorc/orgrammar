/*167. Add Two Numbers
Easy
#Stack
Amazon, Airbnb, Facebook, Google, Microsoft, Twitter, Uber
FAQ++
 */
package lintcode;

import org.junit.Test;

import java.util.Stack;

/**
 * 423. Valid Parentheses
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid
 * but "(]" and "([)]" are not.
 *
 * Example 1:
 * Input: "([)]"
 * Output: False
 *
 * Example 2:
 * Input: "()[]{}"
 * Output: True
 *
 * Challenge
 * - Use O(n) time, n is the number of parentheses.
 */
public class _0423_ValidParentheses {

    /**
     * 使用stack消除成对的括号
     *
     * 易错点:
     * 1. 结尾时, 需判断 stack.isEmpty()
     */
    public boolean isValidParentheses(String s) {
        if (s == null || s.length() == 0)
            return true;

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    if (c != stack.pop()) {
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty(); // 如果stack中仍有剩余, 说明仍有开括号未找到对应的关括号
    }

    @Test
    public void test1() {
        String s = "([)]";
        org.junit.Assert.assertFalse(isValidParentheses(s));
    }

    @Test
    public void test2() {
        String s = "()[]{}";
        org.junit.Assert.assertTrue(isValidParentheses(s));
    }

    @Test
    public void test3() {
        String s = "";
        org.junit.Assert.assertTrue(isValidParentheses(s));
    }
}
