/*
Easy (Hard on LeetCode)
#DP, #Stack, #String
Amazon
FAQ
 */
package lintcode;

import org.junit.Test;

import java.util.Stack;

/**
 * 193. Longest Valid Parentheses
 *
 * Given a string containing just the characters '(' and ')', find the
 * length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 *
 * Example 2:
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class _0193_LongestValidParentheses {

    /**
     * 注意, 答案要求寻找最长的substring, 必须是连续的.
     * 所以当遇到不符合条件的')'时, 需要重置
     */


    /**
     * DP - 用数组curMax记录当前下标位最长的parentheses substring长度
     * 1. 当 s[i] = '(', curMax[i] = 0 因为以'('结束的字符串不是有效的
     * 2. 当 s[i] = ')'
     *    a) 若 s[i-1] = '(', 即"...()", 则 curMax[i] = curMax[i - 2] + 2
     *    b) 若 s[i-1] = ')', 即"...))",
     *       此时如果 s[i - curMax[i-1] - 1] = '(', 例如"...(())",
     *       则 curMax[i] = curMax[i-1] + 2 + curMax[i - curMax[i-1] - 2]
     *
     * 例如"()(())", i=5时, 数组为[0,2,0,0,2,0], curMax[5] = curMax[4] + 2 + curMax[1] = 6
     * 例如"(()())", i=5时, 数组为[0,0,2,0,4,0], curMax[5] = curMax[4] + 2 + curMax[-1](出界,为0) = 6
     */
    public int longestValidParentheses_dp(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int ans = 0;
        int[] curMax = new int[s.length()];

        for (int i = 1; i < s.length(); i++) { // 从 1 开始, 而不是 0

            if (s.charAt(i) == ')') {

                if (s.charAt(i - 1) == '(') {
                    curMax[i] = i - 2 >= 0 ? (curMax[i - 2] + 2) : 2;
                } else { // s[i-1]=')'
                    if (i - curMax[i - 1] - 1 >= 0 && s.charAt(i - curMax[i - 1] - 1) == '(') {
                        curMax[i] = curMax[i - 1] + 2
                                + ((i - curMax[i - 1] - 2 >= 0) ? curMax[i - curMax[i - 1] - 2] : 0);

                    }
                }

                ans = Math.max(ans, curMax[i]);
            }
            // else s[i]='(', curMax[i]=0, 所以跳过
        }

        return ans;
    }

    /** Stack - stack中记录'('的下标
     * 1. 用左指针 left 指向有效'('的前一位 (所以从-1开始)
     *    需要计算有效字符串长度时, 长度 = 当前指针 i - left = i - 有效'('下标 + 1;
     *    例如 "(()", left = 0, i = 2, length = 2 - 0 = 2
     * 2. 读取char
     *    a) 如果'(', 将其下标放入stack
     *    b) 如果')'
     *       * 当前stack为空 -> 无效')', 移动 left 指向当前位
     *       * 当前stack不空 -> 先pop, 然后
     *         ** 若此时stack为空 -> 所有'('都已找到相应')', 则substring有效长度 = i - left. 取其与ans中较大者
     *         ** 若此时stack不空 -> 仍有闲置'(', 则substring有效长度 = i - 闲置'('下标, 即stack最上层. 取其与ans较大者
     *
     * 例如"))(()())", left = 1.
     * stack->2,3 i = 4; 先pop得到 stack->2 i=4; 有效长度 = 4 - 2 = 2; ans = 2
     * stack->2,5 i = 6; 先pop得到 stack->2 i=6; 有效长度 = 6 - 2 = 4; ans = 4
     * stack->2 i = 7; 先pop得到 stack->null i=7; 有效长度 = 7 - 1(left) = 6; ans = 6
     */

    public int longestValidParentheses_stack(String s) {
        if (s == null || s.length() == 0)
            return 0;

        Stack<Integer> stack = new Stack<>();
        int ans = 0, left = -1;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) { // 无效')'
                    left = i;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        ans = Math.max(ans, i - left);
                    } else {
                        ans = Math.max(ans, i - stack.peek());
                    }
                }
            }
        }

        return ans;
    }


    /*
    下列解法有bug, 无法解决 s="(()(((()"
     */
    public int longestValidParentheses_bug(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int ans = 0, localMax = 0, leftBracket = 0, accumateldMax = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftBracket++;
            } else if (c == ')') {
                if (leftBracket > 0) {
                    leftBracket--;
                    localMax += 2;

                    if (leftBracket == 0) {
                        accumateldMax += localMax;
                        localMax = 0;

                        ans = Math.max(ans, accumateldMax);
                    }

                } else {
                    ans = Math.max(ans, localMax);
                    accumateldMax = 0;
                    localMax = 0;
                }
            }
        }

        return Math.max(ans, localMax); // 注意, 循环结束时, localMax可能大于ans
    }

    @Test
    public void test1() {
        org.junit.Assert.assertEquals(2, longestValidParentheses_dp("(()"));
        org.junit.Assert.assertEquals(2, longestValidParentheses_stack("(()"));
    }

    @Test
    public void test2() {
        org.junit.Assert.assertEquals(4, longestValidParentheses_dp(")()())"));
        org.junit.Assert.assertEquals(4, longestValidParentheses_stack(")()())"));
    }

    @Test
    public void test3() {
        org.junit.Assert.assertEquals(4, longestValidParentheses_dp(")()())()()("));
        org.junit.Assert.assertEquals(4, longestValidParentheses_stack(")()())()()("));
    }

    @Test
    public void test4() {
        org.junit.Assert.assertEquals(2, longestValidParentheses_dp("()(()"));
        org.junit.Assert.assertEquals(2, longestValidParentheses_stack("()(()"));
    }

    @Test
    public void test5() {
        String in = ")(()(()(((())(((((()()))((((()()(()()())())())()))()()()())(())()()(((()))))()((()))(((())()((()()())((())))(())))())((()())()()((()((())))))((()(((((()((()))(()()(())))((()))()))())";
        org.junit.Assert.assertEquals(132, longestValidParentheses_dp(in));
        org.junit.Assert.assertEquals(132, longestValidParentheses_stack(in));
    }

    @Test
    public void test6() {
        String in = "(()(((()";
        org.junit.Assert.assertEquals(2, longestValidParentheses_dp(in));
        org.junit.Assert.assertEquals(2, longestValidParentheses_stack(in));
    }
}
