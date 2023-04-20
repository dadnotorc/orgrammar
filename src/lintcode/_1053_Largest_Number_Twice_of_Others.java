package lintcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1053 · Largest Number At Least Twice of Others
 * Easy
 * #Simulation
 * Google
 *
 * In a given integer array nums, there is always exactly one largest element.
 *
 * Find whether the largest element in the array is at least twice as much as every other number in the array.
 *
 * If it is, return the index of the largest element, otherwise return -1.
 *
 * - nums will have a length in the range [1, 50].
 * - Every nums[i] will be an integer in the range [0, 99].
 *
 * Example 1:
 * Input: nums = [3, 6, 1, 0]
 * Output: 1
 * Explanation: 6 is the largest integer, and for every other number in the array x,
 * 6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
 *
 * Example 2:
 * Input: nums = [1, 2, 3, 4]
 * Output: -1
 * Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
 */
public class _1053_Largest_Number_Twice_of_Others {

    /**
     * 另一种做法
     * 1. 遍历 - 找出 最大值 和 次大值, 并记录其下标
     * 2. 比较两者的值
     *
     * 时间 O(n), 空间 O(1)
     */
    // todo



    /**
     * 使用 ResultType 记录 数值与下标
     * 1. 排序
     * 2. 比较最后一位 vs 倒数第二位
     *
     * 时间 O(nlogn), 空间 O(n)
     */
    public int dominantIndex(int[] nums) {
        if (nums == null || nums.length == 0) { return -1; }
        if (nums.length == 1) { return 0; }

        int n = nums.length;

        ResultType[] resultTypes = new ResultType[n];
        for (int i = 0; i < n; i++) {
            resultTypes[i] = new ResultType(nums[i], i);
        }

        Arrays.sort(resultTypes, new Comparator<ResultType>() {
            @Override
            public int compare(ResultType o1, ResultType o2) {
                return o1.val - o2.val;
            }
        });

        if (resultTypes[n - 1].val < (resultTypes[n - 2].val * 2)) { return -1; }

        return resultTypes[n - 1].index;
    }

    class ResultType {
        int val, index;
        public ResultType(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}
