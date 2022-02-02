package leetcode;

import java.util.HashMap;

/**
 * 523. Continuous Subarray Sum
 * medium
 * #Array, #Hash Table, #Prefix Sum
 * Facebook
 *
 * Given an integer array nums and an integer k, return true if nums has a continuous subarray
 * of size at least two whose elements sum up to a multiple of k, or false otherwise.
 *
 * An integer x is a multiple of k if there exists an integer n such that x = n * k.
 * 0 is always a multiple of k.
 *
 * Example 1:
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 *
 * Example 2:
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 *
 * Example 3:
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= sum(nums[i]) <= 2^31 - 1
 * 1 <= k <= 2^31 - 1
 *
 * 类似 lintcode 402, 但是 lintcode 的题是找拥有最大 sum 的一组 subarray
 * 此题是, 找是否存在 sum 为 k 的倍数的 subarray
 *
 */
public class _0523_Continuous_Subarray_Sum {

    /*
    题目限制了 nums[i] 为 非负整数, 所以前缀和只会越来越多

    此题的关键在于
    1. subarray之和 = 前缀和之差

    2. subarray [j + 1, i] 之和
    (prefixSum[i] - prefixSum[j]) % k = 0  => prefixSum[i] % k - prefixSum[j] % k = 0
                                               => prefixSum[i] % k = prefixSum[j] % k
       所以题目要找的就是相等的两组 前缀和 % k

    3. 特殊情况
       前缀和为 0 时, 无需再寻找另一组, 因为题目中 0 是 k 的倍数
       所以要先将 0 加入已知的前缀和中

       还有种特殊情况, [0, 0], 所以遇到第一个 0 时, 会提前出错

     易错点
     1. 使用 map 而不是 set的原因是, 可以记录下标, 用于判断两个前缀和之间是否有至少 2 个 元素
     2. map 中遇到相同的 key 之后, 别忘了验证 下标之差 是否大于 1, 表示至少有 2 个元素
     */



    /**
     * 前缀和解法 + 空间消耗的改进
     * 时间 O(n) - 遍历
     * 空间 O(1)
     */
    public boolean checkSubarraySum_1(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        if (k == 0) {
            return true;
        }

        // <前缀和, 当前下标>
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // 前缀和为 0 是已知, 就是什么都不取

        int curSum = 0;

        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            curSum %= k; // 这里直接 mod 就行, 无需单独创建个新变量. 因为 (a+(n*x)) % x is same as  a % x

            if (map.containsKey(curSum)) {
                if (i - map.get(curSum) > 1) { // 至少有两个元素, 当前元素 + 前一个. 这里别忘了
                    return true;
                }
            } else {
                map.put(curSum, i);
            }
        }

        return false;
    }



    /**
     * 前缀和解法
     * 时间 O(n) - 遍历
     * 空间 O(n) - 前缀和
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        if (k == 0) {
            return true;
        }

        // <前缀和, 当前下标>
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // 前缀和为 0 是已知, 就是什么都不取


        int[] prefixSum = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
            int val = prefixSum[i + 1] % k;

            if (map.containsKey(val)) {
                if (i - map.get(val) > 1) { // 至少有两个元素, 当前元素 + 前一个
                    return true;
                }
            } else {
                map.put(val, i);
            }
        }

        return false;
    }


    /**
     * 有 bug
     */
    public boolean checkSubarraySum_bug(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        if (k == 0) {
            return true;
        }

        // <前缀和, 当前下标>
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // 前缀和为 0 是已知, 就是什么都不取


        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        map.put(prefixSum[0] % k, 0); // bug - 如果遇上 [0, 0], 这里会将 map 中 <0, -1> 改写, 导致提前出错

        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
            int val = prefixSum[i] % k;

            if (map.containsKey(val)) {
                if (i - map.get(val) > 1) { // 至少有两个元素, 当前元素 + 前一个
                    return true;
                }
            } else {
                map.put(val, i);
            }
        }

        return false;
    }
}
