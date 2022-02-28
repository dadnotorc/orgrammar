package leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 20. Valid Parentheses
 * Easy
 * #String, #Stack
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 * - Open brackets must be closed by the same type of brackets.
 * - Open brackets must be closed in the correct order.
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
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of parentheses only '()[]{}'.
 */
public class _0020_Valid_Parentheses {

    /*
    面试时要确认
    - 空 string 是否为 valid
     */

    /**
     * 写法较短
     */
    public boolean isValid_short(String s) {
        if (s == null || s.equals("")) { return true; }

        if ((s.length() & 1) == 1) { return false; } // 奇数个

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty()  || stack.pop() != c) {
                // 当前字符为 close bracket, 且堆为空, 比如 "}","[]}"
                // 或者, 当前的 close bracket 与最近的 open bracket 不匹配, 比如 "{)"
                return false;
            }
        }

        return stack.isEmpty(); // 别忘了最后这步的检查
    }


    /**
     *
     */
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
