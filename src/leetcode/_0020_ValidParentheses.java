/*
Easy
String, Stack
 */
package leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 20. Valid Parentheses
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * An input string is valid if:
 * - Open brackets must be closed by the same type of brackets.
 * - Open brackets must be closed in the correct order.
 *
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 * Input: "()"
 * Output: true
 *
 * Example 2:
 * Input: "()[]{}"
 * Output: true
 *
 * Example 3:
 * Input: "(]"
 * Output: false
 *
 * Example 4:
 * Input: "([)]"
 * Output: false
 *
 * Example 5:
 * Input: "{[]}"
 * Output: true
 */
public class _0020_ValidParentheses {

    public boolean isValid_short(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty()     // 当前字符为close bracket, 且堆为空, 比如 "}","[]}"
                    || stack.pop() != c) // 当前的close bracket与最近的open bracket不匹配, 比如 "{)"
                return false;
        }

        // 无需单独判断string是否为空,或者长度是否为奇数
        // 前者, stack也为空, 则返回true
        // 后者, 处理完string时, stack肯定不空, 则返回false
        return stack.isEmpty();
    }

    public boolean isValid(String s) {
        if (s == null || s.equals(""))
            return true;

        if ((s.length() & 1) == 1) // 奇数个
            return false;

        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;

                char pre = stack.pop();
                if (pre == '(' && c == ')'
                        || pre == '[' && c == ']'
                        || pre == '{' && c == '}') {
                    continue;
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    @Test
    public void test0() {
        org.junit.Assert.assertTrue(isValid(""));
    }

    @Test
    public void test1() {
        org.junit.Assert.assertTrue(isValid("()"));
    }

    @Test
    public void test2() {
        org.junit.Assert.assertTrue(isValid("()[]{}"));
    }

    @Test
    public void test3() {
        org.junit.Assert.assertFalse(isValid("(]"));
    }

    @Test
    public void test4() {
        org.junit.Assert.assertFalse(isValid("([)]"));
    }

    @Test
    public void test5() {
        org.junit.Assert.assertTrue(isValid("{[]}"));
    }

    @Test
    public void test6() {
        org.junit.Assert.assertFalse(isValid("}(){"));
    }

    @Test
    public void test7() {
        org.junit.Assert.assertFalse(isValid("{"));
    }
}
