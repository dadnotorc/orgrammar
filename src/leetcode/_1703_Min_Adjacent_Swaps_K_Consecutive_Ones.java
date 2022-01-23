package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1703. Minimum Adjacent Swaps for K Consecutive Ones
 * Hard
 * #Array, #Greedy, #Sliding Window, #Prefix Sum
 * Microsoft
 *
 * You are given an integer array, nums, and an integer k.
 * nums comprises of only 0's and 1's. In one move, you can choose two adjacent indices and swap their values.
 *
 * Return the minimum number of moves required so that nums has k consecutive 1's.
 *
 * Example 1:
 * Input: nums = [1,0,0,1,0,1], k = 2
 * Output: 1
 * Explanation: In 1 move, nums could be [1,0,0,0,1,1] and have 2 consecutive 1's.
 *
 * Example 2:
 * Input: nums = [1,0,0,0,0,0,1,1], k = 3
 * Output: 5
 * Explanation: In 5 moves, the leftmost 1 can be shifted right until nums = [0,0,0,0,0,1,1,1].
 *
 * Example 3:
 * Input: nums = [1,1,0,1], k = 2
 * Output: 0
 * Explanation: nums already has 2 consecutive 1's.
 *
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * nums[i] is 0 or 1
 * 1 <= k <= sum(nums)
 */
public class _1703_Min_Adjacent_Swaps_K_Consecutive_Ones {

    /**
     * 1. 统计 1's 的下标位置 - 用 list
     * 2. 计算 1's 下标的 prefix sum, 表示要 1's 之间的距离
     *
     */
    public int minMoves(int[] nums, int k) {
        if (nums == null || nums.length < k || k < 2) {
            return 0;
        }

        List<Integer> onesIndex = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                onesIndex.add(i);
            }
        }

        int[] prefixSum = new int[onesIndex.size() + 1];
        for (int j = 0; j < onesIndex.size(); j++) {
            prefixSum[j + 1] = prefixSum[j] + onesIndex.get(j);
        }

        int ans = Integer.MAX_VALUE;

        // 左边界 i; 右边界 i + k; 两侧的 1 向中间靠拢
        // 计算 minimum distance to their median sequence
        for (int i = 0; i < onesIndex.size() - k + 1; i++) {

            int leftIndexSum = prefixSum[k / 2 + i] - prefixSum[i]; // mid - left
            int rightIndexSum = prefixSum[i + k] - prefixSum[(k + 1) / 2 + i]; // right - mid

            ans = Math.min(ans, rightIndexSum - leftIndexSum);
        }

        // 外侧的 1 向中间靠近时, 上面的计算是直接移动到中位数上,
        // 但是当靠内的 1 已经连续, 这就是过度移动了, 所以需要减少对应的步数
        // todo 这里 再看看
        ans -= (k / 2) * ((k + 1) / 2);
        return ans;
    }


    /* 算中位数的时候
    假设 k 为偶数 k = 8
    prefixSum: 0 | 1 2 3 4 | 5 6 7 8    (记得 prefixSum 多一位)
             左边 -> 4 - 0    右边 -> 8 - 4;

    假设 k 为计数 k = 7
    prefixSum: 0 | 1 2 3 | 4 | 5 6 7
            左边 -> 3 - 0     右边 7 - 4

    leftIndexSum = prefixSum[k / 2 + i] - prefixSum[i];
    rightIndexSum = prefixSum[i + k] - prefixSum[(k + 1) / 2 + i];
     */
}
