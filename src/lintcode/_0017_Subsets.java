/*
Medium
Recursion
Amazon, Facebook, Uber
 */
package lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 17. Subsets
 *
 * Given a set of distinct integers, return all possible subsets.
 *
 * Notice
 * - Elements in a subset must be in non-descending order.
 * - The solution set must not contain duplicate subsets.
 *
 * Example 1:
 * Input: [0]
 * Output:
 * [
 *   [],
 *   [0]
 * ]
 *
 * Example 2:
 * Input: [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * Challenge
 * - Can you do it in both recursively and iteratively?
 */
public class _0017_Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        if (nums == null)
            return ans;

        if (nums.length == 0) {
            ans.add(new ArrayList<>()); // 当输入是 [] 时, 返回应当是[[]]
            return ans;
        }

        Arrays.sort(nums); // 先 sort 的作用是保证 non-descending order

        dfs(nums, ans, new ArrayList<>(), 0);

        return ans;
    }

    /* 解法 1 - DFS */
    private void dfs(int[] nums, List<List<Integer>> ans, ArrayList<Integer> subset, int index) {
        if (index == nums.length) {
            ans.add(new ArrayList<Integer>(subset)); // deep copy
            return;
        }

        // 先把所有值都加上, 然后一个一个移除

        subset.add(nums[index]);
        dfs(nums, ans, subset, index + 1);

        subset.remove(subset.size() - 1); // 移除 subset 中最后一位
        dfs(nums, ans, subset, index + 1);
    }

    /* 解法 2 - DFS */
    private void dfs_2(int[] nums, List<List<Integer>> ans, ArrayList<Integer> subset, int index) {
        ans.add(new ArrayList<Integer>(subset));

        for (int i = index; i < nums.length; i++) {
            subset.add(nums[i]);
            dfs_2(nums, ans, subset, i + 1);
            subset.remove(subset.size() - 1);
        }

        // 递归出口
        // return;
    }
}