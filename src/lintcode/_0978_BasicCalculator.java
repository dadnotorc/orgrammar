package lintcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertEquals;

/**
 * 978. Basic Calculator
 * Medium
 * Airbnb, Facebook, Google
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open '(' and closing parentheses ')',
 *  the plus '+' or minus sign '-', non-negative integers and empty spaces ' '.
 *
 * You may assume that the given expression is always valid.
 *
 * - Do not use the eval built-in library function.
 *
 * Example 1
 * Input："1 + 1"
 * Output：2
 *
 * Example 2
 * Input："(1+(4+5+2)-3)+(6+8)"
 * Output：23
 */
public class _0978_BasicCalculator {

    public int calculate(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Boolean> opStack = new ArrayDeque<>();
        int curNum = 0, ans = 0;

        // isPlus 函数的作用是, 当遇到任何运算符号时, 把之前的数字计算好
        // 然后记录下一次计算用什么运算符号

        //default to + because all numbers are non-negative
        //  1 + 2 -> 0 + 1 + 2;      1 - 2 -> 0 + 1 - 2
        boolean isPlus = true;

        char[] chars = s.toCharArray();

        // 遇到 + 或者 - 的时候
        //    1. 上一步的运算
        //    2. 通过当前的字符确定下一步的运算符号
        //    3. reset curNum
        // 遇到 ( 的时候
        //    1. 把答案 ans 推入 stack
        //    2. 把上一步的运算符号也推入 stack.
        //    3. reset ans, 而不是 curNum. 因为后者已经被 reset 过
        //    4. reset isPlus 到 true, 类似 从头开始
        // 遇到 ) 的时候
        //    1. 完成上一步的运算 - 这是括号内的
        //    2. 把两个 stack 中的 数字 以及 运算符号都 pop 出来. 这是括号外的 运算结果 以及上一步的运算符号
        //    3. 再次完成运算 - 这是括号外的, 注意做减法时, 使用 pop 出来的数字 减去 step 1 中得到的数字
        //    4. reset curNum
        //    5. 不要动 ans, 也不用理会 isPlus. 前者是当前的答案, 后者 在关括号后, 肯定会遇到 + - 或者 结束
        for (char c : chars) {
            if (c >= '0' && c <= '9') {
                curNum = curNum * 10 + (c - '0');
            } else if (c == '+') {
                ans = isPlus ? ans + curNum : ans - curNum;
                curNum = 0; // reset
                isPlus = true; // 传给下一个数字
            } else if (c == '-') {
                ans = isPlus ? ans + curNum : ans - curNum;
                curNum = 0;
                isPlus = false;
            } else if (c == '(') {
                numStack.push(ans); // 注意 这里 push 进去的是 ans
                opStack.push(isPlus);
                ans = 0;            // reset for the operations between parentheses
                isPlus = true;
            } else if (c == ')') {
                ans = isPlus ? ans + curNum : ans - curNum;

                curNum = numStack.pop();
                isPlus = opStack.pop();
                ans = isPlus ? curNum + ans : curNum - ans; // 注意 顺序转向
                curNum = 0;
            } else {
                // empty space - skip
            }
        }

        //注意 别忘了完成最后一步运算
        if (curNum != 0) {
            ans = isPlus ? ans + curNum : ans - curNum;
        }

        return ans;
    }

    @Test
    public void test1() {
        String s = "1 + 1";
        int actual = new _0978_BasicCalculator().calculate(s);
        assertEquals(2, actual);
    }

    @Test
    public void test2() {
        String s = "(1+(4+5+2)-3)+(6+8)";
        int actual = new _0978_BasicCalculator().calculate(s);
        assertEquals(23, actual);
    }
}
