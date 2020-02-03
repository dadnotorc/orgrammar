/*
Easy
Hash Table
Facebook, Google
 */
package lintcode;

import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.assertEquals;

/**
 * 838. Subarray Sum Equals K
 * Easy
 * Facebook, Google
 *
 * Given an array of integers and an integer k, you need to find the
 *  total number of continuous subarrays whose sum equals to k.
 *
 * Example1
 * Input: nums = [1,1,1] and k = 2
 * Output: 2
 * Explanation:
 * subarray [0,1] and [1,2]
 *
 * Example2
 * Input: nums = [2,1,-1,1,2] and k = 3
 * Output: 4
 * Explanation:
 * subarray [0,1], [1,4], [0,3] and [3,4]
 *
 * 注意：　数值可正可负
 *
 * 此题关键: 求 [i,j] 之和 = [0,j] 之和 - [0,i] 之和
 */
public class _0838_SubarraySumEqualsK {

    /**
     * 九章算法 解答
     *
     * 假设数组长度n. 获得前缀和 prefix-sum 数组后:
     *   nums[i]表示从 0 位加到 i 位的数组和
     *   nums[j]表示从 0 位加到 j 位的数组和, j > i
     *   nums[j]-nums[i]表示从 i+1 位加到 j 位的数组和
     *
     * hashtable中, key为 nums[i], value 为该数组和出现过的次数
     *
     * 遍历前缀和数组时, 每个nums[i]:
     *   如果hashtable中存在 nums[i]-k 为key的entry, 将其value加入答案
     *   将 nums[i] 出现次数 +1
     *
     * 例2: [2,1,-1,1,2], 前缀和数组为 [2,3,2,3,5]
     *  当前数组和 nums[i] > k 时, 检查是否存在数组和nums[a]满足 nums[i]-k = nums[a] (i > a)
     *  例如,当遍历到 nums[4] = 5 时, nums[4]-3 = 2 = nums[0] = nums[2], 表示
     *    从0+1为开始到4, 数组和为3 -> [1,4]
     *    从2+1为开始到4, 数组和为3 -> [3,4]
     *  当前数组和 nums[i] = k 时, 表示从0位到i位数组和为k, 所以答案 +1
     *  当前数组和 nums[i] < k 时, 表示仍需要继续添加数字, 所以继续向后遍历
     */
    public int subarraySumEqualsK(int[] nums, int k) {
        // 先求出前缀和prefixSum 数组
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        Hashtable<Integer, Integer> tb = new Hashtable<>();
        tb.put(0,1); // 当prefixsum = k, 既找到1组答案, 所以 key = 0, value = 1
        int ans = 0;
        for (int curPrefixSum : nums) {

            if (tb.containsKey(curPrefixSum - k)) { //当前prefixSum与k之间差值
                ans += tb.get(curPrefixSum - k);
            }

            if (tb.containsKey(curPrefixSum)) { //记录当前的前缀数组和
                tb.put(curPrefixSum, tb.get(curPrefixSum) + 1);
            } else {
                tb.put(curPrefixSum, 1);
            }
        }

        return ans;
    }

    /* 根据九章算法参考答案修改/缩短 */
    public int subarraySumEqualsK_s(int[] nums, int k) {
        int curPrefixSum = 0;
        Hashtable<Integer, Integer> tb = new Hashtable<>();
        tb.put(0,1); // 当prefixsum = k, 既找到1组答案, 所以 key = 0, value = 1
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            curPrefixSum += nums[i];
            if (tb.containsKey(curPrefixSum - k)) { //当前prefixSum与k之间差值
                ans += tb.get(curPrefixSum - k);
            }

            if (tb.containsKey(curPrefixSum)) { //记录当前的前缀数组和
                tb.put(curPrefixSum, tb.get(curPrefixSum) + 1);
            } else {
                tb.put(curPrefixSum, 1);
            }
        }

        return ans;
    }

    /**
     * for each integer in nums, find if there is subarray with sum = k
     *
     * time complexity: O(n!) - huge  n! > 2^n
     * space complexity: O(1)
     *
     * Do NOT use!
     */
    public int subarraySumEqualsK_Expensive(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];

                if (sum == k) {
                    ans++;
                    System.out.println("from " + i + " to " + j);
                }
                // Do NOT stop if sum < k || sum > k
                //  as we may have both positive and negative number
                //  in the array. Need to check every single number
            }
        }
        return ans;
    }

    @Test
    public void test1() {
        int[] nums = {1,1,1};
        int k = 2;
        int expected = 2;
        int actual = new _0838_SubarraySumEqualsK().subarraySumEqualsK(nums, k);
        assertEquals(expected, actual);

        nums = new int[]{1,1,1};
        int actual_s = new _0838_SubarraySumEqualsK().subarraySumEqualsK_s(nums, k);
        assertEquals(expected, actual_s);
    }

    @Test
    public void test2() {
        int[] nums = {2,1,-1,1,2};
        int k = 3;
        int expected = 4;
        int actual = new _0838_SubarraySumEqualsK().subarraySumEqualsK(nums, k);
        assertEquals(expected, actual);

        nums = new int[]{2,1,-1,1,2};
        int actual_s = new _0838_SubarraySumEqualsK().subarraySumEqualsK_s(nums, k);
        assertEquals(expected, actual_s);
    }

    @Test
    public void test3() {
        int[] nums = {-4,-1,-6,16,13,2,-1,-4,6,6,-9,13,3,-6,-6,16,8,-10,2,1,0,8,6,16,11,0,10,-6,-5,16};
        int k = 12;
        int expected = 5;
        int actual = new _0838_SubarraySumEqualsK().subarraySumEqualsK(nums, k);
        assertEquals(expected, actual);

        nums = new int[]{-4,-1,-6,16,13,2,-1,-4,6,6,-9,13,3,-6,-6,16,8,-10,2,1,0,8,6,16,11,0,10,-6,-5,16};
        int actual_s = new _0838_SubarraySumEqualsK().subarraySumEqualsK_s(nums, k);
        assertEquals(expected, actual_s);
    }
}
