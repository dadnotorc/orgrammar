/*
Easy
#Stack
 */
package lintcode;

import java.util.Stack;

/**
 * 263 · Matching of parentheses
 *
 * Given a string containing just the characters '(', ')', determine if the input string is valid.
 *
 * The brackets must close in the correct order, "()" and "()" are all valid but "(]" and ")(" are not.
 *
 * Example 1:
 * Input: ")("
 * Output: False
 *
 * Example 2:
 * Input: "()"
 * Output: True
 */
public class _0263_MatchingOfParentheses {

    /**
     * 使用 stack
     */
    public boolean matchParentheses_stack(String string) {
        if (string == null || string.length() == 0) { return true; }

        Stack<Character> stack = new Stack<>();
        for (char c : string.toCharArray()) {
            if (c == '(') {
                stack.add(')');
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }


    /**
     * 简单的计数
     */
    public boolean matchParentheses(String string) {
        if (string == null || string.length() == 0) { return true; }

        int count = 0;
        for (char c : string.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                if (count == 0) { // 注意 这里是 == 0
                    return false;
                } else {
                    count--;
                }
            } else {
                return false;
            }
        }

        return count == 0;
    }
}
