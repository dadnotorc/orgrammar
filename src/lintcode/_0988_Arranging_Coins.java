package lintcode;

/**
 * 988 · Arranging Coins
 * Easy
 * #Math, #Binary Search
 *
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 *
 * Given n, find the total number of full staircase rows that can be formed.
 *
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 *
 * Example 1:
 * input:n = 5
 * output:2
 * Explanation:
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤
 * Because the 3rd row is incomplete, we return 2.
 *
 * Example 2:
 * input:n = 8
 * output:3
 * Explanation:
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 * Because the 4th row is incomplete, we return 3.
 */
public class _0988_Arranging_Coins {

    /*
    1 - 1
    2 - 3
    3 - 6
    4 - 10
    5 - 15
     */

    /**
     * 时间 O(sqrt(n)), 空间 O(1)
     */
    public int arrangeCoins(int n) {
        if (n < 2) {
            return n;
        }

        int curSum = 0, ans = 0;
        while (curSum < n) {
            ans++; // 顺序很重要
            curSum = curSum + ans;
        }

        return curSum == n ? ans : ans - 1;
    }


    /**
     * 二分法 - 用 long, 避免 integer overflow
     * 时间 O(logn), 空间 O(1)
     */
    public int arrangeCoins_bs(int n) {
        if (n < 2) {
            return n;
        }

        long l = 1, r = n; // 注意 用 long, 避免 integer overflow
        while (l < r) {
            long mid = l + (r - l) / 2;
            long curSum = getSum(mid);
            if (curSum == n) {
                return (int) mid;
            }

            if (curSum < n) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return getSum(l) <= n ? (int) l : (int) l - 1; // 注意 这里很容易错, 要判断 <=, 而不是 ==
    }

    public long getSum(long n) {
        return (1 + n) * n / 2;
    }
}
