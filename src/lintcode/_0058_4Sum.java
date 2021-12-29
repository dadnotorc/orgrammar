/*
Medium
#Hash Table, #Two Pointers
 */
package lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 58 · 4Sum
 *
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 *
 * Find all unique quadruplets in the array which gives the sum of target.
 * - Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
 *   The solution set must not contain duplicate quadruplets.
 *
 * Example 1:
 * Input: numbers = [2,7,11,15], target = 3
 * Output: []
 * Explanation: 2 + 7 + 11 + 15 != 3. There is no quadruple satisfying the condition.
 *
 * Example 2:
 * Input: numbers = [1,0,-1,0,-2,2], target = 0
 * Output: [[-1, 0, 0, 1],[-2, -1, 1, 2],[-2, 0, 0, 2]]
 * Explanation: There are three different quadruples whose sum of four numbers is 0.
 */
public class _0058_4Sum {

    /**
     * DFS
     * 1. 排序
     * 2. 队列进行搜索, 每次选取 index 靠后, 且比当前值大的数压入 list，当 list 大小为 4 的时候判断是否四个元素的和为 target
     * - 对数组排序
     * - 用 list 记录选取的元素
     * - 由于已经升序排列，只需要要去找上个元素的后面的位置的元素作为下一个元素
     * - 若 list 大小为4，且和为 target 的时候记录一次答案
     *
     * 时间复杂度 O(n ^ 3) - 最差情况为 n ^ 3
     * 空间复杂度 O(n ^ 2)
     */
    public List<List<Integer>> fourSum(int[] numbers, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numbers == null || numbers.length < 4) { return ans; }

        Arrays.sort(numbers);

        dfs(numbers, target, ans, new ArrayList<>(), 0);

        return ans;
    }

    private void dfs(int[] numbers, int target, List<List<Integer>> ans, List<Integer> curList, int index) {
        if (curList.size() == 4) {
            if (target == 0) {
                ans.add(new ArrayList<>(curList)); // 注意 是加入个新的 list, 而不是直接加入 curList
            }
            return;
        }
        for (int i = index; i < numbers.length; i++) {
            if (i > index && numbers[i] == numbers[i - 1]) { continue; } // 跳过相同的

            curList.add(numbers[i]);
            dfs(numbers, target - numbers[i], ans, curList, i + 1);
            curList.remove(curList.size() - 1);
        }
    }

    /**
     * 双指针
     * 1. 排序
     * 2. 固定前两个值, 对后两个值做双指针
     *
     * 时间复杂度 O(n ^ 3) - 最差情况为 n ^ 3
     * 空间复杂度 O(n ^ 2)
     */
    public List<List<Integer>> fourSum_two_pointers(int[] numbers, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        if (numbers == null || numbers.length < 4) { return ans; }

        Arrays.sort(numbers);
        int n = numbers.length;

        for (int i = 0; i < n - 3; i++) {
            // 不能加, 例如 numbers = [1,0,-1,0,-2,2], target = -3
            // if (numbers[i] > target) { break; }

            if (i > 0 && numbers[i] == numbers[i - 1]) { continue; }

            for (int j = i + 1; j < n - 2; j++) {
                // 不加
                // if (numbers[j] > target - numbers[i]) { break; }

                if (j > i + 1 && numbers[j] == numbers[j - 1]) { continue; }

                int l = j + 1, r = n - 1;
                while (l < r) {
                    int sum = numbers[i] + numbers[j] + numbers[l] + numbers[r];
                    if (sum == target) {
                        ans.add(Arrays.asList(numbers[i], numbers[j], numbers[l], numbers[r]));

                        l++;
                        r--;

                        while (l < r && numbers[l] == numbers[l - 1]) { l++; }
                        while (l < r && numbers[r] == numbers[r + 1]) { r--; }
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }

        return ans;
    }
}
