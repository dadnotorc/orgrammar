/*
Medium
#Two Pointers, #Array, #Sort
Adobe, Amazon, Facebook, Microsoft
 */
package lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 57. 3Sum
 *
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Notice
 * - Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * - The solution set must not contain duplicate triplets.
 *
 * Example 1:
 * Input:[2,7,11,15]
 * Output:[]
 *
 * Example 2:
 * Input:[-1,0,1,2,-1,-4]
 * Output:	[[-1, 0, 1],[-1, -1, 2]]
 *
 * 此题与 leetcode 15. 3Sum 完全相同
 */
public class _0057_3Sum {

    /*
    暴力解法 - 3层循环, 列出所有的 combination, 计算其和是否满足条件 - 时间 O(n^3), 空间 O(1)

    改进 - 先固定一个点, 然后做 2 sum - 时间 O(n^2), 空间 O(1)
     */

    /**
     * 双指针
     * 1. 排序
     * 2. 遍历数组 - 从 首位 到 倒数第三位, [0] ~ [n - 3], 以保证最后有两位数字
     */
    public List<List<Integer>> threeSum(int[] numbers) {
        if (numbers == null || numbers.length < 3)
            return new ArrayList<>();

        // 先排序
        Arrays.sort(numbers);

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < numbers.length - 2; i++) { // 从0到倒数第三位 (保证i之后还有两位数字)

            // 可省略此判断, 除非test data中, 有很多>0的数字
            // if (numbers[i] > 0) { // 因为数组已排序, 当前值 > 0, 之后所有值均 > 0, 不可能找到三个数之和等于0的情况
            //     break;
            // }

            if (i > 0 && numbers[i] == numbers[i-1]) { // 如果当前值与前一位相等, 无需重复计算
                continue;
            }

            int second = i + 1, third = numbers.length - 1;
            int target = -numbers[i]; // 取负值

            while (second < third) {
                if (numbers[second] + numbers[third] == target) {

                    // 较慢
                    // ans.add(Arrays.asList(numbers[i], numbers[second], numbers[third]));

                    List<Integer> curList = new ArrayList<>();
                    Collections.addAll(curList, numbers[i], numbers[second], numbers[third]);
                    // curList.add(numbers[i]);
                    // curList.add(numbers[second]);
                    // curList.add(numbers[third]);
                    ans.add(curList);

                    second++;
                    third--;

                    // 跳过重复的答案
                    while (second < third && numbers[second -1] == numbers[second])
                        second++;
                    while (second < third && numbers[third] == numbers[third + 1])
                        third--;
                } else if (numbers[second] + numbers[third] < target) {
                    second++;
                } else {
                    third--;
                }
            }
        }

        return ans;
    }
}
