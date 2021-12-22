/*
Easy
#DFS
 */
package lintcode;

/**
 * 771 · Double Factorial
 *
 * Given a number n, return the double factorial of the number.
 * In mathematics, the product of all the integers from 1 up to some non-negative integer n
 * that have the same parity (odd or even) as n is called the double factorial.
 * - We guarantee that the result does not exceed long.
 *  - n is a positive integer
 *
 * Example1 :
 * Input: n = 5
 * Output: 15
 * Explanation:
 * 5!! = 5 * 3 * 1 = 15
 *
 * Example2:
 * Input: n = 6
 * Output: 48
 * Explanation:
 * 6!! = 6 * 4 * 2 = 48
 */
public class _0771_DoubleFactorial {


    /**
     * iterative
     */
    public long doubleFactorial(int n) {
        long ans = n; // 注意 ans 必须是 long
        while (n > 2) {
            n -= 2;
            ans *= n;
        }
        return ans;
    }

    /**
     * 九章
     */
    public long doubleFactorial_recursive_1(int n) {
        if (n <= 2) {
            return n;
        }

        return n * doubleFactorial_recursive_1(n - 2);
    }

    /**
     * recursive
     */
    long ans = 1;

    public long doubleFactorial_recursive(int n) {
        ans *= n;

        if (n <= 2) {
            return ans;
        }

        return doubleFactorial_recursive(n - 2);
    }
}
