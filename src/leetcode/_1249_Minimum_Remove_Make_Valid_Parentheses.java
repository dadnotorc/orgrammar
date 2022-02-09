package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 * Medium
 * #String, #Stack
 * Facebook Meta
 *
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
 * so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 * - It is the empty string, contains only lowercase characters, or
 * - It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * - It can be written as (A), where A is a valid string.
 *
 * Example 1:
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 *
 * Example 2:
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 *
 * Example 3:
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 *
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is either'(' , ')', or lowercase English letter.
 */
public class _1249_Minimum_Remove_Make_Valid_Parentheses {
    /*
    follow up 思考 - 如果除了小括号, 也允许大括号, 方括号, 怎么办?

    { ( { ) } } 是否合法 - 互相穿插
    - 如果合法, 用多个 stack
    - 如果不合法, 用同一个 stack

     */

    /**
     * 使用 stack 检查 i 位置的括号 是否合法
     * 使用 boolean array 记录当前字符是否应该保留
     * 时间 O(2 x n) - 两次遍历
     * 空间 O(2 x n) - stack 与 boolean array
     */
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        int n = s.length();

        // 记录 '(' 下标
        Stack<Integer> stack = new Stack<>();
        boolean[] included = new boolean[n];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                included[i] = true;
            } else if (c == '(') {
                stack.push(i);
                // 这里不确定 i 下标的 '(' 是否应该保留, 只有遇到 ')' 的时候才能确定
            } else if (c == ')') {
                if (!stack.isEmpty()) { // 别忘了 判断 stack 是否为空

                    // 当前 ')' 合法, 之前的 '(' 也合法
                    included[i] = true;
                    included[stack.pop()] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (included[i]) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }


    /**
     * 改进? - 如果 n 比 括号的数量 多很多, 可以去掉 boolean array, stack 中留下的都是不合法的 需要去除
     */
    class ResultType{
        char c;
        int index;
        public ResultType(char c, int index) {
            this.c = c;
            this.index = index;
        }
    }

    public String minRemoveToMakeValid_1(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        int n = s.length();

        Stack<ResultType> stack = new Stack<>();
        StringBuilder sb = new StringBuilder(s); // 这里直接把 s 丢进去, 最后来删除不合法的括号

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                continue;
            } else if (c == '(') {
                stack.push(new ResultType(c, i));
            } else if (c == ')') {
                if (!stack.isEmpty() && stack.peek().c == '(') { // 别忘了 判断 stack 是否为空
                    stack.pop();
                } else {
                    stack.push(new ResultType(c, i));
                }
            }
        }

        while (!stack.isEmpty()) { // 从后往前检查所有的 '(' 和 ')'
            sb.deleteCharAt(stack.pop().index);
        }

        return sb.toString();
    }





    /**
     * 有 bug - 相向双指针 但是无法处理 "()()"
     */
    public String minRemoveToMakeValid_bug(String s) {
        if (s == null || s.length() == 0) { return s; }

        int n = s.length();
        int l = 0, r = n - 1;

        StringBuilder sb = new StringBuilder(s);

        while (l <= r) {
            while (l <= r && Character.isAlphabetic(sb.charAt(l))) { l++; }
            while (l <= r && Character.isAlphabetic(sb.charAt(r))) { r--; }

            if (l > r) { break; }

            if (sb.charAt(l) == '(' && sb.charAt(r) == ')') {
                l++;
                r--;
            } else if (sb.charAt(r) == '(') { // 先检查靠后的 r 指针, 避免考前的 l 指针错位
                sb.deleteCharAt(r);
                r--;
            } else if (sb.charAt(l) == ')') {
                sb.deleteCharAt(l);
                r--;
            }
        }

        return sb.toString();
    }


    @Test
    public void test1() {
        String s = "lee(t(c)o)de)";
        String exp = "lee(t(c)o)de";
        // Assert.assertEquals(exp, minRemoveToMakeValid_2points(s));
    }

    @Test
    public void test2() {
        String s = "))((";
        String exp = "";
        // Assert.assertEquals(exp, minRemoveToMakeValid_2points(s));
    }
}
