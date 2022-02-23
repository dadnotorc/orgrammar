package lintcode;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * 838. Subarray Sum Equals K
 * Easy
 * #Enumerate, #Hash Table, #Array
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
 *
 */
public class _0838_Subarray_Sum_Equals_K {

    /*
    注意：　数值可正可负

    此题关键: 求 [i,j] 之和 = [0,j] 之和 - [0,i] 之和
     */

    /**
     * HashMap 中记录 <前缀和, 该前缀和出现的次数>
     *
     * prefixSum[i]表示从 0 位加到 i 位的数组和
     * prefixSum[j]表示从 0 位加到 j 位的数组和, j > i
     * prefixSum[j]-prefixSum[i]表示从 i+1 位加到 j 位的数组和
     *
     * 访问每个前缀和 prefixSum[i] 时:
     *   1. 如果 hashmap 中存在 key = prefixSum[i] - k, 将其 value 加入答案
     *   2. 将 hashmap 中 prefixSum[i] 出现次数递增 1
     *
     * 例2: [2,1,-1,1,2], 前缀和数组为 [2,3,2,3,5]
     *  当遍历到前缀和 nums[i] 时, 检查是否存在 nums[a]满足 nums[a] = nums[i] - k. 例如:
     *  - 当遍历到 nums[1] = 3 时, nums[1]-3 = 0 = 一个数都不加, 表示 [0,1]数组和 = k
     *  - 当遍历到 nums[3] = 3 时, nums[3]-3 = 0 = 一个数都不加, 表示 [0,3]数组和 = k
     *  - 当遍历到 nums[4] = 5 时, nums[4]-3 = 2 = nums[0] = nums[2], 表示
     *     从0的下一位(1)开始到4, 数组和为3 -> [1,4]
     *     从2的下一位(3)开始到4, 数组和为3 -> [3,4]
     *
     * hashmap 中, 别忘了加上 <0, 1> - 当 subarray 为空时, 前缀和 = 0, 其出现次数 = 1
     *
     * 时间 O(n), 空间 O(n)
     */
    public int subarraySumEqualsK(int[] nums, int k) {
        int prefixSum = 0;

        HashMap<Integer, Integer> map = new HashMap<>(); // <前缀和, 出现次数>
        map.put(0,1); // 易错点, 别忘了!

        int ans = 0;
        for (int num : nums) {
            prefixSum += num;

            if (map.containsKey(prefixSum - k)) {
                ans += map.get(prefixSum - k);
            }

            // 别忘了更新当前前缀和的出现次数
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
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
    public int subarraySumEqualsK_bruteforce(int[] nums, int k) {
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


    /**
     * nums = [1,2,3,4]
     *                   4
     *           3     3+4
     *     2   2+3   2+3+4
     * 1 1+2 1+2+3 1+2+3+4
     *
     * dp[3][3]=nums[3]
     * dp[2][2]=nums[2]; dp[2][3]=dp[2][2]+dp[3][3]
     * dp[1][1]=nums[1]; dp[1][2]=dp[1][1]+dp[2][2]; dp[1][3]=dp[1][1]+dp[2][3]
     * dp[0][0]=nums[0]; dp[0][1]=dp[0][0]+dp[1][1]; dp[0][2]=dp[0][0]+dp[1][2]; dp[0][3]=dp[0][0]+dp[1][3]
     *
     * 时间 n+(n-1)+(n-2)+...+(n-(n-1)) = n*n-(1+2+...+(n-1)) = n^2 + (1+(n-1))*(n-1)/2 = n*n + n*(n-1)/2
     * time:  O(n^2)
     * space: O(n^2)
     *
     * Memory Limit Exceeded
     */
    public int subarraySumEqualsK_dp_1(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        int ans = 0, n = nums.length;
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = nums[i];
                } else {
                    dp[i][j] = dp[i][i] + dp[i+1][j];
                }

                if (dp[i][j] == k)
                    ans++;
            }
        }

        return ans;
    }


    /**
     * nums = [1,2,3,4]
     *                   4
     *           3     3+4
     *     2   2+3   2+3+4
     * 1 1+2 1+2+3 1+2+3+4
     *
     * 这次使用1D array, 每次只记录上一行的答案, 例如
     * i j                 i j                       i j                      i j
     * ---                 ---                       ---                      ---
     * 3 3: dp[3]=nums[3]
     * 2 2: dp[2]=nums[2]; 2 3: dp[3]=dp[2]+老dp[3]
     * 1 1: dp[1]=nums[1]; 1 2: dp[2]=dp[1]+老dp[2]; 1 3: dp[3]=dp[1]+老dp[3]
     * 0 0: dp[0]=nums[0]; 0 1: dp[1]=dp[0]+老dp[1]; 1 2: dp[2]=dp[0]+老dp[2]; 1 3: dp[3]=dp[0]+老dp[3]
     *
     * time:  O(n^2)
     * space: O(n)
     *
     * 可惜仍然会超时 Time Limit Exceeded
     */
    public int subarraySumEqualsK_dp_2(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        int ans = 0, n = nums.length;
        int[] dp = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[j] = nums[j];
                } else {
                    dp[j] = dp[i] + dp[j];
                }

                if (dp[j] == k)
                    ans++;
            }
        }

        return ans;
    }


    /**
     * 有 bug - table中可能含有多个 k - nums[i], 但是答案只增加一次
     * 例如 nums = [2,1,-1,1,2], k = 3
     * prefixSum = [2,3,2,3,5] 答案有4个 [0,1], [1,4], [0,3] and [3,4]
     */
    public int subarraySumEqualsK_withbug(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        int ans = 0;
        HashMap<Integer, Integer> table = new HashMap<>();
        table.put(0, nums[0]); // (pos, value)
        for (int i = 1; i < nums.length; i++) {
            if (table.containsValue(k - nums[i]))
                ans++;

            table.put(i, table.get(i-1) + nums[i]);
        }

        return ans;
    }


    @Test
    public void test1() {
        int[] nums = {1,1,1};
        int k = 2;
        int expected = 2;
        int actual = new _0838_Subarray_Sum_Equals_K().subarraySumEqualsK(nums, k);
        assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        int[] nums = {2,1,-1,1,2};
        int k = 3;
        int expected = 4;
        int actual = new _0838_Subarray_Sum_Equals_K().subarraySumEqualsK(nums, k);
        assertEquals(expected, actual);
    }

    @Test
    public void test3() {
        int[] nums = {-4,-1,-6,16,13,2,-1,-4,6,6,-9,13,3,-6,-6,16,8,-10,2,1,0,8,6,16,11,0,10,-6,-5,16};
        int k = 12;
        int expected = 5;
        int actual = new _0838_Subarray_Sum_Equals_K().subarraySumEqualsK(nums, k);
        assertEquals(expected, actual);
    }
}
