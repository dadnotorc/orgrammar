/*
Medium
#Bit Manipulation
 */
package leetcode;

/**
 * 201. Bitwise AND of Numbers Range
 *
 * Given a range [m, n] where 0 <= m <= n <= 2147483647,
 * return the bitwise AND of all numbers in this range, inclusive.
 *
 * Example 1:
 * Input: [5,7]
 * Output: 4
 *
 * Example 2:
 * Input: [0,1]
 * Output: 0
 */
public class _0201_BitwiseANDofNumbersRange {

    /**
     * m,n分别向右移动, 每次一位, 直到两者相等. 然后将之前移动的位置补上0
     */
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0)
            return 0;

        int steps = 0;
        while (m != n) {
            // m,n分别向右移动一位, 并递增移动次数
            m >>= 1;
            n >>= 1;
            steps++;
        }

        // 当移动到m==n时, 表明之前移动的bit位都不同 -> 将这些位变为0
        return m << steps;
    }




    // m = 0, n = 2147483647, 会Time Limit Exceeded
    public int rangeBitwiseAnd_TLE(int m, int n) {
        int ans = m;
        for (int i = m + 1; i <= n; i++) {
            ans = ans & i;
        }
        return ans;
    }

}
