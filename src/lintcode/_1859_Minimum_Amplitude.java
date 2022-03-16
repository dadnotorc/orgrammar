package lintcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1859 · Minimum Amplitude
 * Easy
 * Google
 *
 * Given an array A consisting of N integers.
 * In one move, we can choose any element in this array and replace it with any value.
 * The amplitude of an array is the difference between the largest and the smallest values it contains.
 * Return the smallest amplitude of array A that we can achieve by performing at most three moves.
 *
 * N is an integer within the range: [2, 10000]
 * each element of array A is an integer within the range: [-50, 50]
 *
 * Example 1
 * Input: A = [-9, 8, -1]
 * Output: 0
 * Explanation: We can replace -9 and 8 with -1 so that all element are equal to -1, and then the amplitude is 0
 *
 * Example 2
 * Input: A = [14, 10, 5, 1, 0]
 * Output: 1
 * Explanation: To achieve an amplitude of 1, we can replace 14, 10 and 5 with 1 or 0.
 *
 * Example 3
 * Input: A = [11, 0, -6, -1, -3, 5]
 * Output: 3
 * Explanation: This can be achieved by replacing 11, -6 and 5 with three values of -2.
 *
 * input: [-9,-9,5,4,-2,8,-6,-1]
 * output: 8
 */
public class _1859_Minimum_Amplitude {

    /*
    对一个已经排好序的数组, 要改变最多 3 个数字来减少振幅, 我们可以:
    - 去掉 最大的 3 位               = A[n - 4] - A[0] = 振幅
    - 去掉 最大的 2 位 和 最小的 1 位  = A[n - 3] - A[1]
    - 去掉 最大的 1 位 和 最小的 2 位  = A[n - 2] - A[2]
    - 去掉 最小的 3 位               = A[n - 1] - A[3]
    在这 4 种情况中, 取得最小值
     */
    public int MinimumAmplitude(int[] A) {
        if (A == null || A.length < 4) { return 0; }

        int ans = Integer.MAX_VALUE;

        Arrays.sort(A);

        for (int i = 0; i < 4; i++) {
            ans = Math.min(ans, A[A.length - 1 - 3 + i] - A[i]);
        }

        return ans;
    }
}
