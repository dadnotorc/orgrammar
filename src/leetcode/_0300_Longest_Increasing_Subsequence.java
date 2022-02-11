package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 300. Longest Increasing Subsequence
 * Medium
 * #Array, #Binary Search, #DP
 *
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by
 * deleting some or no elements without changing the order of the remaining elements.
 * For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 *
 * Constraints:
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 *
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class _0300_Longest_Increasing_Subsequence {

    /*
    暴力穷举 - 每个值都有 选 或者 不选 两个值, 对于 n 个值, 就有 n 个 2 相乘 -> O(2 ^ n)

    DP - dp[i]: 到当前 i 位为止, 最小的 subsequence 的长度 -> O(n ^ 2)
    - 当检查到 i 的时候
       - 左指针 j 从 0 到 i - 1, 比较 dp[j] + 1 VS dp[i]

    改进 - patience sorting - O(n * logn) - https://www.youtube.com/watch?v=l2rCz7skAlk&t=1s
    dp[i]: subsequence 长度为 i 的时候, subsequence 的最后一位能取的最小值
    例如 [10,9,2,5,3,7,101,18]. 依次读取每个值, 当读取到:
    10 -> dp[1] = 10 (表示 subsequence 长度为 1 的话, 当前已知的 subsequence 最后一位的最小值为 10)
     9 -> dp[1] = 9   -> [9]
     2 -> dp[1] = 2   -> [2]
     5 -> dp[2] = 5   -> [2], [*, 5]
     3 -> dp[2] = 3   -> [2], [*, 3]
     7 -> dp[3] = 7   -> [2], [*, 3], [*, *, 7]
   101 -> dp[4] = 101 -> [2], [*, 3], [*, *, 7], [*, *, *, 101]
    18 -> dp[4] = 18  -> [2], [*, 3], [*, *, 7], [*, *, *, 18]

     每次读取, 使用二分法来决定 需要更新的 dp 下标

     */

    /**
     * DP - 时间 O(n^2), 空间 O(n)
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null) { return 0; }

        int n = nums.length;
        int[] dp = new int[n];
        int ans = 1; // 从 1 开始, 因为最小的递增 subsequence 就是 只有一个元素 (另外, 也是为了特判)

        // 所有值自己都可以作为 subsequence 的开头
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }


    /**
     * patience sorting - 使用 list 来记录当前 pile
     * 找寻 pile 的时候
     * 1. 基本做法就是遍历, 如果
     *    - 如果 num >  pile.get(i) => 跳过, 检查下一位 i + 1
     *    - 如果 num <= pile.get(i) => i 就是将被更新的 index
      */
    public int lengthOfLIS_patience_sorting_list(int[] nums) {
        if (nums == null) { return 0; }

        List<Integer> pile = new ArrayList<>();

        for (int num : nums) {

            // 遍历法
            //int index = findPile(pile, num);


            // 使用 二分法 改进
            /* 利用现成的 API
            int index = Collections.binarySearch(pile, num);
            if (index < 0) {
                index = -(index + 1); // 这是 Collections.binarySearch 里未找到 target 时的返回值
            }
             */
            int index = binarySearch(pile, num);

            if (index == pile.size()) { // 需要个新 pile
                pile.add(num);
            } else { // 更新现有 pile 即可
                pile.set(index, num);
            }
        }

        return pile.size();
    }

    /* 方法 1 - 遍历 */
    public int findPile(List<Integer> pile, int target) {
        for (int i = 0; i < pile.size(); i++) {
            if (target <= pile.get(i)) { // 注意 是 <=, 遇到相同的时候, 并不需要个新的pile
                return i;
            }
        }

        return pile.size();
    }

    /* 改进 方法 2 - 二分法 */
    public int binarySearch(List<Integer> pile, int target){
        int l = 0, r = pile.size(); // 注意 这里 r 的取值, 不能时 pile.size() - 1
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (pile.get(mid) == target) {
                return mid;
            }

            if (pile.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid; // 注意 这里不能用 mid - 1, 因为可能会错误的跳过当前的 mid
            }
        }

        return l;
    }






    /**
     * patience sorting - 使用 dp array + 遍历 或者 二分法
     *
     * array 中并不是所有元素都有效, 所以 多加入一个 index_newPile 参数 (从它开始都是无效元素)
     * 此解法用 1-indexed, 所以数组 从 1 到 index_newPile - 1 为有效元素
     * 也就是, 共有 index_newPile - 1 个有效的 pile (此为返回值)
     */
    public int lengthOfLIS_patience_sorting_array(int[] nums) {
        if (nums == null) { return 0; }

        int[] dp = new int[nums.length + 1]; // 这里使用 1-indexed, 为了方便理解. dp[1] 即 subsequence 长度为 1
        int index_newPile = 1; // 1-indexed

        for (int num : nums) {
            // 遍历法
            //int index_pileToUpdate = findPile(dp, 1, index_newPile, int num) {


            // 使用 二分法 改进
            /* 利用现成的 API
            int index = Arrays.binarySearch(dp, 1, size, );
            if (index < 0) {
                index = -(index + 1); // 这是 Arrays.binarySearch 里未找到 target 时的返回值
            }
             */
            int index_pileToUpdate = binarySearch(dp, 1, index_newPile, num);

            dp[index_pileToUpdate] = num;

            // 别忘了, 如果开启个新 pile 时, 要更新 size (这里的 size 可以理解为 下一个 pile 的 下标)
            if (index_pileToUpdate == index_newPile) {
                index_newPile++;
            }
        }

        return index_newPile - 1;
    }
    /* 方法 1 - 遍历 */
    public int findPile(int[] dp, int start, int index_newPile, int target) {
        for (int i = start; i < index_newPile; i++) { // 理论上, 这里必须用 <, 而不是 <=
            if (target <= dp[i]) { // 注意 是 <=
                return i;
            }
        }

        return index_newPile;
    }


    /* 改进 方法 2 - 二分法 */
    public int binarySearch(int[] dp, int start, int index_newPile, int target) {
        while (start < index_newPile) {
            int mid = start + (index_newPile - start) / 2;
            if (dp[mid] == target) {
                return mid;
            }

            if (dp[mid] < target) {
                start = mid + 1;
            } else {
                index_newPile = mid; // 注意 这里不能用 mid - 1, 因为可能会错误的跳过当前的 mid
            }
        }

        return start;
    }












    @Test
    public void test1(){
        int[] nums = {10,9,2,5,3,4};
        org.junit.Assert.assertEquals(3, lengthOfLIS_patience_sorting_list(nums));
    }
}
