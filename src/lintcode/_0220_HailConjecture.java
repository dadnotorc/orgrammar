/*
Easy
#Math
Bloomberg
 */
package lintcode;

/**
 * 220 · Hail conjecture
 *
 * Mathematicians have put forward a famous conjecture —— hail conjecture.
 * For any natural number n, if n is even, replace n with n / 2;
 * If n is odd, replace n with 3 * n + 1.
 * According to this rule, the final result must be 1.
 * How many times will the number change to 1?
 *
 * 1<=n<=1000
 *
 * Example 1:
 * Input:  4
 * Output: 2
 * Explanation:
 * First round: 4 / 2 = 2
 * Second round: 2 / 2 = 1
 */
public class _0220_HailConjecture {

    public int getAnswer(int num) {
        int ans = 0;

        while (num != 1) {
            if (isEven(num)) {
                num /= 2;
            } else {
                num = 3 * num + 1;
            }
            ans++;
        }
        return ans;
    }

    public boolean isEven (int num) {
        return (num & 1) == 0;
    }
}
