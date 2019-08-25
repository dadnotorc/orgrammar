package interviews;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Imagine you are building a compiler. Before running any code, the compiler
 * must check that the parentheses in the program are balanced. Every opening
 * bracket must have a corresponding closing bracket. We can approximate this
 * using strings.
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid. An input string is valid if:
 * - Open brackets are closed by the same type of brackets.
 * - Open brackets are closed in the correct order.
 * - Note that an empty string is also considered valid.
 *
 * Example:
 * Input: "((()))"
 * Output: True
 *
 * Input: "[()]{}"
 * Output: True
 *
 * Input: "({[)]"
 * Output: False
 */
public class ValidateParentheses {

    public boolean isValid(String s) {
        if (s == null) {
            return true;
        }

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                    stack.push(')');
                    break;
                case '{':
                    stack.push('}');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case ')':
                case '}':
                case ']':
                    if (stack.size() > 0 && c == stack.peek())
                        stack.pop();
                    else
                        return false;
                    break;
                default:
                    // shouldn't happen
                    return false;
            }
        }

        return stack.isEmpty();
    }

    @Test
    public void test1() {
        String s = "()(){(())";
        org.junit.Assert.assertFalse(isValid(s));
    }

    @Test
    public void test2() {
        String s = "";
        org.junit.Assert.assertTrue(isValid(s));
    }

    @Test
    public void test3() {
        String s = "([{}])()";
        org.junit.Assert.assertTrue(isValid(s));
    }

    @Test
    public void test4() {
        String s = "((()))";
        org.junit.Assert.assertTrue(isValid(s));
    }

    @Test
    public void test5() {
        String s = "[()]{}";
        org.junit.Assert.assertTrue(isValid(s));
    }

    @Test
    public void test6() {
        String s = "({[)]";
        org.junit.Assert.assertFalse(isValid(s));
    }
}
