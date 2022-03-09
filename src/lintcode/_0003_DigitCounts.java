package lintcode;

/**
 * 3 · Digit Counts
 * Easy
 * #Enumerate, #Math
 * Give a number k, Count the number of k between 0 and n, k can be 0 to 9.
 *
 * 1 <= n <= 10^5
 * 0 <= k <= 9
 *
 * Example 1:
 * Input: k = 1, n = 1
 * Output: 1
 * Explanation: In [0,1], only 1 has the number 1, so it appears once.
 *
 * Example 2:
 * Input: k = 1, n = 12
 * Output: 5
 * Explanation: In [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], 1 appeared in 1, 10, 11, 12, in which
 * 1 appeared twice in 11, so 1 appeared five times.
 */
public class _0003_DigitCounts {

    /**
     * 时间 O(n * log_10 n)
     */
    public int digitCounts(int k, int n) {
        int ans = (k == 0) ? 1 : 0;

        for (int i = k ; i <= n; i++) { // 不用考虑 k 之前的部分
            int cur = i;
            while (cur != 0) {
                if (cur % 10 == k) {
                    ans++;
                }
                cur /= 10;
            }
        }

        return ans;
    }


    /**
     * 有 bug todo 再看看
     */
    public int digitCounts_bug(int k, int n) {
        int ans = (k == 0) ? 1 : 0;
        int unit = 1;
        int last = 0;

        while (n > 0) {
            int rem = n % 10;
            n = n / 10;
            if (n == 0 && k == 0) {
                return ans;
            }

            if (rem > k) {
                ans += (n + 1) * unit;
            } else if (rem == k) {
                ans += n * unit + last + 1;
            } else {
                ans += n * unit;
            }

            if (n > 0 && k == 0 && unit != 1) {
                ans -= unit;
            }

            last += rem * unit;
            unit *= 10;
        }

        return ans;
    }
}
