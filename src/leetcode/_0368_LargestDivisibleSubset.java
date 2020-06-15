/*
Medium
#Math, #DP
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 368. Largest Divisible Subset
 *
 * Given a set of distinct positive integers, find the largest subset such that
 * every pair (Si, Sj) of elements in this subset satisfies:
 *
 * Si % Sj = 0 or Sj % Si = 0.
 *
 * If there are multiple solutions, return any subset is fine.
 *
 * Example 1:
 * Input: [1,2,3]
 * Output: [1,2] (of course, [1,3] will also be ok)
 *
 * Example 2:
 * Input: [1,2,4,8]
 * Output: [1,2,4,8]
 */
public class _0368_LargestDivisibleSubset {

    // TODO 另一种解法 https://leetcode.com/problems/largest-divisible-subset/discuss/84013/Very-short-Java-DFS-solution-using-Memoization

    /**
     * 1. 先sort, 保证i值之前都小于i
     * 2. 对每个i值, 遍历0至i-1位, 找出上一位可整除i的值
     *    同时, 更新largest divisible subset的长度, 及其对应的i值下标
     * 3. 从拥有largest divisible subset的i开始, 向前找出所有可整除i的值, 加入答案队列中
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        int[] subsetSize = new int[nums.length]; // 记录每个i值对应的divisible subset长度
        int[] prev = new int[nums.length]; // i值对应的subset中的最大值, 可整除i值的上一位

        Arrays.sort(nums);

        int max = 0; // largest divisible subset的长度
        int index = -1; // 拥有largest divisible subset的i值的下标

        for (int i = 0; i < nums.length; i++) {
            // 初始值 - 每个i都能被自己整除, 而上一个能整除i的值的下标为-1
            subsetSize[i] = 1;
            prev[i] = -1;

            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && subsetSize[j] + 1 > subsetSize[i]) {
                    subsetSize[i] = subsetSize[j] + 1;
                    prev[i] = j;
                }
            }

            if (subsetSize[i] > max) {
                max = subsetSize[i];
                index = i;
            }
        }

        while (index != -1) {
            res.add(nums[index]);
            index = prev[index];
        }

        return res;
    }
}
