package interviews;

import org.junit.Test;

import java.util.Stack;

/**
 * Amazon OA - 2022 年 1月
 *
 * Three rules for a valid string:
 * 1. An empty string is valid
 * 2. You can add same character to a valid string X, and create another valid string yXy
 * 3. You can concatenate two valid strings X and Y, so XY will also be valid.
 *
 * Ex: vv, xbbx, bbccdd, xyffyxdd are all valid.
 */
public class AMZN_2022_Valid_String {

    /**
     * it's essentially the valid parentheses question but with alphabets instead of parentheses
     * https://leetcode.com/problems/valid-parentheses/.
     * This can be solve in O(n) with a stack.
     */
    public boolean isValid(String s) {
       if (s == null || s.length() < 2) {
           return false;
       }

       char[] chars = s.toCharArray();

        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (stack.isEmpty() || stack.peek() != c) {
                stack.push(c);
            } else if (stack.peek() == c) {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    @Test
    public void test1() {
        String[] ss = {
                "vv",
                "xbbx",
                "bbccdd",
                "xyffyxdd",
        };

        for (String s : ss) {
            org.junit.Assert.assertTrue(isValid(s));
        }
    }
}
