package leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * Valid Parenthesis String
 *
 * Given a string containing only three types of characters: '(', ')' and '*',
 * write a function to check whether this string is valid.
 * We define the validity of a string by these rules:
 *
 * 1. Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * 2. Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * 3. Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * 4. '*' could be treated as a single right parenthesis ')'
 *    or a single left parenthesis '(' or an empty string.
 * 5. An empty string is also valid.
 *
 * Note:
 * - The string size will be in the range [1, 100].
 *
 * Example 1:
 * Input: "()"
 * Output: True
 *
 * Example 2:
 * Input: "(*)"
 * Output: True
 *
 * Example 3:
 * Input: "(*))"
 * Output: True
 */
public class _0000_ValidParenthesisString {

    public boolean checkValidString(String s) {
        if (s == null || s.equals(""))
            return true;

        int parenthesisCount = 0;
        int starCount = 0;

        for (char c : s.toCharArray()) {
            if (c == '*') {
                if (parenthesisCount != 0) {
                    starCount++;
                }
            } else if (c == '(') {
                parenthesisCount++;
            } else {
                if (parenthesisCount > 0) {
                    parenthesisCount --;
                } else if (starCount > 0) {
                    starCount--;
                } else {
                    return false;
                }
            }
        }

        return parenthesisCount <= starCount;
    }

    @Test
    public void test1() {
        String s = "(())((())()()(*)(*()(())())())()()((()())((()))(*";
//        String s = "**("; 这种情况需要false
        org.junit.Assert.assertFalse(checkValidString(s));
    }
}
