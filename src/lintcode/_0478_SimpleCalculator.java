/*
Naive
#Math

 */
package lintcode;

/**
 * 478 · Simple Calculator
 *
 * Given two integers a and b, an operator, choices: +, -, *, /
 * Calculate a <operator> b.
 *
 * Example 1:
 * Input: a = 1, b = 2,  operator = +
 * Output: 3
 *
 * Example 2:
 * Input: a = 10, b = 20, operator = *
 * Output: 200
 *
 * Example 3:
 * Input: a = 3,  * b = 2, operator = /
 * Output: 1
 *
 * Example 4:
 * Input: a = 10, b = 11, operator = -
 * Output: -1
 */
public class _0478_SimpleCalculator {

    public int calculate(int a, char operator, int b) {
        int ans = 0;
        switch (operator) {
            case '+':
                ans = a + b;
                break;
            case '-':
                ans = a - b;
                break;
            case '*':
                ans = a * b;
                break;
            case '/':
                ans = a / b;
                break;
            default:
                return 0;
        }
        return ans;
    }
}
