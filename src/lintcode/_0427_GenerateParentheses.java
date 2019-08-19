/*
Medium
String, Backtracking, Recursion, DFS
Google, LinkedIn, Uber
 */
package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 427. Generate Parentheses
 * Given n, and there are n pairs of parentheses, write a function to generate
 * all combinations of well-formed parentheses.
 *
 * Example 1:
 * Input: 3
 * Output: ["((()))", "(()())", "(())()", "()(())", "()()()"]
 *
 * Example 2:
 * Input: 2
 * Output: ["()()", "(())"]
 */
public class _0427_GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n <= 0) { return ans; }

        helper(ans, "", n, n);
        return ans;
    }

    /**
     * 递归中：
     * 1. 左右括号剩余数为 0 时, 添加答案到 list
     * 2. 左括号剩余 = 0 时, 在字符串中填满右括号, 添加答案
     * 3. 左括号剩余 > 0 时:
     *    - 左括号剩余数 <　右括号剩余数： 可添加左括号或者右括号, 递归进下一层
     *    - 左括号剩余数 =　右括号剩余数： 只能添加左括号, 递归进下一层
     *    - 左括号剩余数 >　右括号剩余数： 不可能存在
     */
    private void helper(List<String> l, String s, int left, int right) {
        if (left == 0 && right == 0) { // 递归出口
            l.add(s);
            return; // 别忘了结束递归
        }


    }
}
