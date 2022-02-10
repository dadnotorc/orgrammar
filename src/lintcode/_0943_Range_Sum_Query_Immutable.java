package lintcode;

import java.util.ArrayList;

/**
 * 943 · Range Sum Query - Immutable
 *
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * The elements corresponding to i and j are also included.
 * - You may assume that the array does not change.
 * - There are many calls to sumRange function.
 *
 * Example1
 * Input: nums = [-2, 0, 3, -5, 2, -1]
 * sumRange(0, 2)
 * sumRange(2, 5)
 * sumRange(0, 5)
 * Output:
 * 1
 * -1
 * -3
 * Explanation:
 * sumRange(0, 2) -> (-2) + 0 + 3 = 1
 * sumRange(2, 5) -> 3 + (-5) + 2 + (-1) = -1
 * sumRange(0, 5) -> (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
 *
 * Example2
 * Input:
 * nums = [-4, -5]
 * sumRange(0, 0)
 * sumRange(1, 1)
 * sumRange(0, 1)
 * sumRange(1, 1)
 * sumRange(0, 0)
 * Output:
 * -4
 * -5
 * -9
 * -5
 * -4
 * Explanation:
 * sumRange(0, 0) -> -4
 * sumRange(1, 1) -> -5
 * sumRange(0, 1) -> (-4) + (-5) = -9
 * sumRange(1, 1) -> -5
 * sumRange(0, 0) -> -4
 */
public class _0943_Range_Sum_Query_Immutable {
}

/*
注意 - 题目中题到了 There are many calls to sumRange function.
所以我们应当减少 sumRange 中的计算

此题最基本的做法, 就是初始化一个数组, 将 nums 赋值 给它.  然后在 sumRange() 里做遍历 与 加法
但是缺点是, sumRange 会重复计算

改进就是在初始化的时候, 计算 prefix sum.
然后在 sumRange() 里, 只需要计算前缀和的差值即可
 */


/**
 * 这个解法中用了 数组 来记录 prefix sum. 也可以使用 arraylist
 */
class NumArray_array {
    int[] prefixSum;
    int length; // 原数组的长度, 而不是 prefixSum 数组的长度

    public NumArray_array(int[] nums) {
        if (nums == null || nums.length < 1) {
            this.length = 0;
            this.prefixSum = new int[0];
        } else {
            this.length = nums.length;
            this.prefixSum = new int[length + 1]; // 前缀和数组多一位, 方便之后的差值就算 (从 0 到 i 的和)

            for (int i = 0; i < length; i++) {
                this.prefixSum[i + 1] = this.prefixSum[i] + nums[i];
            }
        }
    }

    public int sumRange(int i, int j) {
        if (i < 0 || j >= this.length) {
            return 0;
        }

        return this.prefixSum[j + 1] - this.prefixSum[i];
    }
}


/**
 * 使用 list
 */
class NumArray_list{
    ArrayList<Integer> prefixSum;
    int length; // 此为原数组长度, 而非 前缀和 长度

    public NumArray_list(int[] nums) {
        prefixSum = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            length = 0;
        } else {
            length = nums.length;

            prefixSum.add(0); // 先加个 0 进去, 想法跟 前缀和数组 多一位 是一样的
            for (int i = 0; i < length; i++) {
                prefixSum.add(prefixSum.get(i) + nums[i]);
            }
        }
    }

    public int sumRange(int i, int j) {
        if (i < 0 || j >= length) {
            return 0;
        }

        return prefixSum.get(j + 1) - prefixSum.get(i);
    }
}



/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */