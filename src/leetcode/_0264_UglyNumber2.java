/*
Medium
#Math, #DP, #Heap
 */
package leetcode;

/**
 * 264. Ugly Number II
 *
 * Write a program to find the n-th ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *
 * Example:
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 *
 * Note:
 * 1. 1 is typically treated as an ugly number.
 * 2. n does not exceed 1690.
 */
public class _0264_UglyNumber2 {

    /**
     * 2,3,5拥有各自的指针, 指向当前已自己为倍数的值
     * 每次找出2,3,5倍数中最小的一位, 加入res中, 并将其对应指针移动至下一位
     */
    public int nthUglyNumber(int n) {
        if (n <= 1) {
            return n == 1 ? 1 : 0;
        }

        int[] res = new int[n];
        res[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0; // 2,3,5的各自指针

        for (int i = 1; i < n; i++) {
            // 找出2,3,5倍数中最小的一位加入res
            int tmp2 = res[index2] * 2;
            int tmp3 = res[index3] * 3;
            int tmp5 = res[index5] * 5;
            res[i] = Math.min(tmp2, Math.min(tmp3, tmp5));

            // 移动新加入数字对应的指针
            if (res[i] == tmp2) { index2++; }
            if (res[i] == tmp3) { index3++; }
            if (res[i] == tmp5) { index5++; }
        }

        return res[n - 1];
    }


    /**
     * 写法稍微有所不同
     */
    public int nthUglyNumber_2(int n) {
        if (n <= 1) {
            return n == 1 ? 1 : 0;
        }

        int[] res = new int[n];
        res[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int m2 = 2, m3 = 3, m5 = 5;

        for (int i = 1; i < n; i++) {
            res[i] = Math.min(m2, Math.min(m3, m5));
            if (res[i] == m2) { m2 = 2 * res[++index2]; }
            if (res[i] == m3) { m3 = 3 * res[++index3]; }
            if (res[i] == m5) { m5 = 5 * res[++index5]; }
        }

        return res[n - 1];
    }
}
