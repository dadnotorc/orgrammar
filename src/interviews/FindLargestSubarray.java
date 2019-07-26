package interviews;

/**
 * Given an array of integer, find a continuous subarray:
 * 1. with the biggest sum of numbers
 * 2. same order as original
 *
 * (If their are duplicate answer, return the minimum one
 *  in lexicographical order)
 *
 * Example:
 * Input:  [1, -2, 10, -1, 20, -100].
 * Output: [10, -1, 20].
 * Explanation:
 *  10 + (-1) + 20 = 29 has the biggest sum
 *
 * 类似 lintcode 402. Continuous Subarray Sum
 *
 * Amazon phone interview
 */
public class FindLargestSubarray {

    /**
     * 思路: 如果遇到使当前sum变为负数的值, sum(a[0],a[i-2]) <  a[i-1],
     *  说明a[i-1]为绝对值较大的负值.
     *  则记录当前最大sum, 并将左右指针挪动 i 位置重新开始.
     *
     *  如当前sum仍 >=0, 则只挪动右指针
     */
    public int[] continuousSubarraySum(int[] a) {
        if (a == null || a.length < 1) {
            return new int[0];
        }

        int ansStart = 0, ansEnd = 0;
        int l = 0, r = 0;
        int curSum = a[0], maxSum = a[0]; // 也可以用long替代int

        for (int i = 1; i < a.length; i++) {
            if (curSum < 0) { // a[i-1] > sum(a[l], a[i-2]);
                curSum = a[i]; // 重新开始
                l = r = i;
            } else {
                curSum += a[i];
                r = i;
            }

            if (curSum > maxSum) {
                maxSum = curSum;
                ansStart = l;
                ansEnd = r;
            }
        }

        int[] ans = new int[ansEnd - ansStart + 1];
        for (int j = 0; j < ansEnd - ansStart + 1; j++) {
            ans[j] = a[j + ansStart];
        }

        return ans;
    }
}
