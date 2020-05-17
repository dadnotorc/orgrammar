/*
Medium
#Array
 */
package leetcode;

/**
 * 918. Maximum Sum Circular Subarray
 *
 * Given a circular array C of integers represented by A,
 * find the maximum possible sum of a non-empty subarray of C.
 *
 * Here, a circular array means the end of the array connects to the beginning of the array.
 * (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)
 *
 * Also, a subarray may only include each element of the fixed buffer A at most once.
 * (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist
 * i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)
 *
 * Example 1:
 * Input: [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3
 *
 * Example 2:
 * Input: [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
 *
 * Example 3:
 * Input: [3,-1,2,-1]
 * Output: 4
 * Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
 *
 * Example 4:
 * Input: [3,-2,2,-3]
 * Output: 3
 * Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
 *
 * Example 5:
 * Input: [-2,-3,-1]
 * Output: -1
 * Explanation: Subarray [-1] has maximum sum -1
 *
 * Note:
 * -30000 <= A[i] <= 30000
 * 1 <= A.length <= 30000
 *
 * 此题在 53. Maximum Subarray 基础上, 增加些难度
 */
public class _0918_MaximumSumCircularSubarray {

    /**
     * 最大值两种情况:
     * 1. subarray在A的范围之内, 0到n-1之间
     * 2. subarray取A的前一段与尾一段, 不取中间
     * 所以在A内寻找:
     * - 最大的subarray之和 maxSum
     * - 最小的subarray之和 minSum
     * - 所有数字之和 tota
     *
     * 答案 = Math.max(maxSum, total - minSum)
     *
     * 注意有一种特殊情况, 如果A中所有值均为负值: maxSum = max(A), minSum = sum(A) = total
     * total - minSum = 0 > maxSum
     * 如果此时返回 Math.max(maxSum, total - minSum) = 0, 即不取任何数字, 这与题目不符, 题目要求non-empty subarray of C
     * 所以返回 max(A) = maxSum
     */
    public int maxSubarraySumCircular(int[] A) {
        int total = 0; // 计算 sum(A)
        int curMax = 0;
        int maxSum = -30000; // 初始取最小值
        int curMin = 0;
        int minSum = 30000; // 初始取最大值

        for (int i : A) {
            total += i;

            // 考虑到当前数字为止 (必须包括当前值), 最大的subarry之和
            // 如果curMax + i < i, 则 curMax = i;
            curMax = Math.max(i, curMax + i);

            // 不考虑circular的情况下, 最大的subarray和
            maxSum = Math.max(maxSum, curMax);

            // 考虑到当前数字为止 (必须包括当前值), 最小的subarry之和
            // 如果i < curMin + i, 则 curMin = i;
            curMin = Math.min(i, curMin + i);

            // 不考虑circular的情况下, 最小的subarray和
            minSum = Math.min(minSum, curMin);
        }

        if (maxSum <= 0)
            return maxSum;

        return Math.max(maxSum, total - minSum);
    }
}
