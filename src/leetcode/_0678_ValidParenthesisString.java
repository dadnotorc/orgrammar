/*
Medium
#String
 */
package leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * 678. Valid Parenthesis String
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
public class _0678_ValidParenthesisString {

    /**
     * 用max记录最多有多少'('可以被pair
     * 用min记录最少有多少'('必须被pair
     * 例如:
     * "("   -> min=1, max=1;
     * "(*(" -> min=1, max=3; (最少的情况为"()(", 最多的情况为"((("
     *
     * max永远 >= min
     *
     * String is valid if both conditions are met
     * 1. 循环中max必须 >= 0; 否则说明')'数量大于所有可能的'('
     * 2. 结束时, min需要 == 0, 表示所有'('都能被配对
     */
    public boolean checkValidString(String s) {
        if (s == null || s.equals(""))
            return true;

        int min = 0, max = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                min++;
                max++;
            } else if (c == ')') {
                if (min > 0) { // 如果min已经==0, 则无需再递减
                    min--;
                }
                max--;
            } else { // '*'的情况, max增加, min减少(直到0) 因为'*'可以作为')'减少所需的'('
                if (min > 0) {
                    min--;
                }
                max++;
            }

            if (max < 0) { // 在循环中, 任何时间max<0, 说明")"出现在首位, 所以无效
                return false;
            }
        }

        // 循环结束时, 如果min== 0, 说明已经没有任何'('被配对, 则有效; 否则无效
        return min == 0;
    }

    /**
     * 使用两个stack, 一个记录'('的下标, 另一个记录'*'的下标
     */
    public boolean checkValidString_stack(String s) {
        if (s == null || s.equals(""))
            return true;

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '*') {
                s2.add(i);
            } else if (c == '(') {
                s1.add(i);
            } else {
                if (!s1.isEmpty()) {
                    s1.pop();
                } else if (!s2.isEmpty()) {
                    s2.pop();
                } else {
                    return false;
                }
            }
        }

        // 循环做完, 剩下的可能:
        // 1. 只有"(" -> false
        // 2. 只有"*" -> true
        // 3. "*"在"("左边 -> false
        // 4. "*"在"("右边 -> 消除"("并判断数量

        while (!s1.isEmpty() && !s2.isEmpty()) {
            if (s1.pop() > s2.pop()) {
                return false;
            }
        }

        if (s1.isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * 这种做法有bug, 无法区分"*(" 和 "(*" (前者false, 后者true)
     * 如果不记录"("左边的"*", 则遇到"*())"时会误判
     */
    public boolean checkValidString_bug(String s) {
        if (s == null || s.equals(""))
            return true;

        int parenthesisCount = 0;
        int starCount = 0;

        for (char c : s.toCharArray()) {
            if (c == '*') {
                starCount++;
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
