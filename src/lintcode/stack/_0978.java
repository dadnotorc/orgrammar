package lintcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

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
public class _0978 {

    public int calculate(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Boolean> opStack = new ArrayDeque<>();
        int curNum = 0, ans = 0;

        /**
         * default to + because all numbers are non-negative
         *  1 + 2 -> 0 + 1 + 2;      1 - 2 -> 0 + 1 - 2
         */
        boolean isPlus = true;

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c >= '0' && c <= '9') { // is a digit
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
                numStack.push(ans);
                opStack.push(isPlus);
                // reset for the operations between parentheses
                ans = 0;
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

        /**
         * 注意 别忘了完成最后一步运算
         */
        if (curNum != 0) {
            ans = isPlus ? ans + curNum : ans - curNum;
        }

        return ans;
    }
}
