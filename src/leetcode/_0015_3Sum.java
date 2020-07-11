/*
Medium
#Array, #Two Pointers
 */
package leetcode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 15. 3Sum
 *
 * Given an array nums of n integers, are there elements a, b, c in nums such that
 * a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 此题与 lintcode 57. 3Sum 完全相同
 */
public class _0015_3Sum {

    /**
     * 1. 排序
     * 2. 对每一个数字i, 对其后的数组做 2sum (target为 -i)
     * 3. 跳过与前一个数字相同的数字
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
            int target = 0 - nums[i];
            while (l < r) {
                if (nums[l] + nums[r] == target) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r])); // 也可以新建个list, 将3者加入

                    // 注意这里不能停止, 因为之后可能仍有答案
                    l++;
                    r--;

                    // 跳过重复的l,r对应值
                    while (l < r && nums[l] == nums[l - 1]) { l++; }
                    while (l < r && nums[r] == nums[r + 1]) { r--; }
                } else if (nums[l] + nums[r] < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return res;
    }
}
