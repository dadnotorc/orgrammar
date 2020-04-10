/*
Medium
#Two Pointers, #Array, #Sort
Adobe, Amazon, Facebook, Microsoft
 */
package lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

    public List<List<Integer>> threeSum(int[] numbers) {
        if (numbers == null || numbers.length < 3)
            return new ArrayList<>();

        // 先排序
        Arrays.sort(numbers);

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < numbers.length - 2; i++) { // 从0到倒数第三位 (保证i之后还有两位数字)

            // 无需做此判断, 除非test data中, 有很多>0的数字
//            if (numbers[i] > 0) { // 当前最小值已大于0, 之后不可能找到三个数之和等于0的情况
//                break;
//            }

            if (i > 0 && numbers[i] == numbers[i-1]) { // 如果当前值与前一位相等, 无需重复计算
                continue;
            }

            int second = i + 1, third = numbers.length - 1;
            int target = -numbers[i];

            while (second < third) {
                if (numbers[second] + numbers[third] == target) {

                    // 较慢
//                    ans.add(Arrays.asList(numbers[i], numbers[second], numbers[third]));

                    List<Integer> curList = new ArrayList<>();
                    curList.add(numbers[i]);
                    curList.add(numbers[second]);
                    curList.add(numbers[third]);
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
