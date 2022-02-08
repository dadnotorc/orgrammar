package leetcode;

import java.util.*;

/**
 * 15. 3Sum
 * Medium
 * #Array, #Two Pointers
 *
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that
 * i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 *
 * Example 2:
 * Input: nums = []
 * Output: []
 *
 * Example 3:
 * Input: nums = [0]
 * Output: []
 *
 *
 * Constraints:
 * 0 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 *
 *
 *  此题与 lintcode 57. 3Sum 完全相同
 */
public class _0015_3Sum {

    /**
     * 1. 排序
     * 2. 对每一个数字i, 对其后的数组做 2sum (target为 -i)
     * 3. 找到解之后, 跳过之后相同的数字, 即相同的解
     *    - 这里要注意, 不是简单的跳过已遇到的数字
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return res;
        }

        Arrays.sort(nums);

        // i到倒数第三位停止, 与最后两位做最后一次运算
        // 另外, 因为数组已排序, 如果当前i下标对应值 >0, 便无法与之后更大的数字加和为0
        for (int i = 0; i < nums.length - 2 && nums[i] <= 0; i++) {
            // 保证 i 跳过已scan过的数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int l = i + 1, r = nums.length - 1;
            int target = - nums[i];
            while (l < r) {
                if (nums[l] + nums[r] == target) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r])); // 也可以新建个list, 将3者加入

                    // 注意这里不能停止, 因为之后可能仍有答案
                    l++;
                    r--;

                    // 跳过重复的l,r对应值 - 即跳过重复的解
                    // 注意 - 这里是为了跳过重复的解, 而不是单纯的跳过相同的值. 如果放在 if 之外跳, 那样会跳过 [-1,-1,2] 这样的值
                    while (l < r && nums[l] == nums[l - 1]) { l++; }
                    while (l < r && nums[r] == nums[r + 1]) { r--; }
                } else if (nums[l] + nums[r] < target) {
                    l++;
                    // 这里以及 else 里也可以单独跳 l 或者 r, 可加可不加
                } else {
                    r--;
                }
            }
        }

        return res;
    }
}
