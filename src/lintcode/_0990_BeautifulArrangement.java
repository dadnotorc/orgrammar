/*
Medium
#Backtracking
Google
 */
package lintcode;

/**
 * 990. Beautiful Arrangement
 *
 * Suppose you have N integers from 1 to N. We define a beautiful arrangement
 * as an array that is constructed by these N numbers successfully if one of
 * the following is true for the ith position (1 <= i <= N) in this array:
 *
 * 1. The number at the ith position is divisible by i.
 * 2. i is divisible by the number at the ith position.
 *
 * Now given N, how many beautiful arrangements can you construct?
 *
 * Notice
 * - N is a positive integer and will not exceed 15.
 *
 * Example1
 * Input: 2
 * Output: 2
 * Explanation:
 * The first beautiful arrangement is [1, 2]:
 *   Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
 *   Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
 * The second beautiful arrangement is [2, 1]:
 *   Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
 *   Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 *
 * Example2
 * Input: 3
 * Output: 3
 */
public class _0990_BeautifulArrangement {

    int ans = 0;

    public int countArrangement(int N) {
        helper(new int[N + 1], N);
        return ans;
    }

    private void helper(int[] res, int num) {
        if (num == 0) {
            ans++; // 找到一组答案, ++, 返回
            return;
        }

        for (int i = 1; i < res.length; i++) { // 注意这里仍是 < res.length
            if (res[i] == 0 && (num % i == 0 || i % num == 0)) { // 当前res[i]仍未找到答案 且满足两个条件之一
                res[i] = num;
                helper(res, num - 1);
                res[i] = 0; // 别忘了清除
            }
        }
    }
}
