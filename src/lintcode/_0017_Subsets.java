/*
Medium
Recursion
Amazon, Facebook, Uber
 */
package lintcode;

import java.util.*;

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
 *
 * 此题与 leetcode 78. Subsets 相同
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

    /* 解法 3 - BFS */
    /**
     * 一层一层的寻找所有子集
     * []
     * [1], [2], [3]
     * [1,2], [1,3], [2,3]
     * [1,2,3]
     *
     * time:  O(2^n)
     */
    public List<List<Integer>> subsets_3(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null)
            return ans;
        if (nums.length == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }

        // 先 sort, 保证队列中数字从小到大排列
        Arrays.sort(nums);

        // BFS
        Queue<List<Integer>> queue = new LinkedList<>();
        queue. offer(new ArrayList<Integer>());

        while (!queue.isEmpty()) {
            List<Integer> subset = queue.poll();
            ans.add(subset);

            for (int i = 0; i < nums.length; i++) {

                // 当前subset为空
                // 或者subset中不包含nums[i] (subset最后一位 < nums[i]) 不用考虑相等的情况, 因为原数组中所有数字的都不相同
                // 在subset基础上, 创建新subset, 并加入数组中未加入的新数字
                if (subset.size() == 0 || subset.get(subset.size() - 1) < nums[i]) {

                    List<Integer> newSubset = new ArrayList<>(subset);
                    newSubset.add(nums[i]);
                    queue.offer(newSubset);
                }
            }
        }

        return ans;
    }

    /* 解法 4 - 使用binary operation */

    /**
     * 数组有n个数组, 所以子集的总数为 2^n, 等于 1 << n
     *         例如 [1,2,3] -> 共有2^3 = 8个子集
     *         每个子集对应[0, 2^n - 1]中一个数字的二进制, 例如
     *         []  --> 0 0 0 --> 0
     *         [1] --> 0 0 1 --> 1
     *         [2] --> 0 1 0 --> 2
     *         [3] --> 1 0 0 --> 4
     *         ...
     *         [1,2,3] --> 1 1 1 --> 7
     *
     * 1<<n 是指位运算中, 1向左移动n位, e.g 1<<4 等于二进制10000 即十进制2^4=16
     *
     *  i=1, j=0, 1 & (2^0) = 1 & 1 = 1
     *  i=1, j=1, 1 & (2^1) = 1 & 10 = 0
     *  i=1, j=2, 1 & (2^2) = 1 & 100 = 0
     *
     *  i=4, j=0, 4 & (2^0) = 100 & 1 = 0
     *  i=4, j=1, 4 & (2^1) = 100 & 10 = 0
     *  i=4, j=2, 4 & (2^2) = 100 & 100 = 100 = 4
     *
     *  i=7, j=0, 7 & (2^0) = 111 & 1 = 1
     *  i=7, j=1, 7 & (2^1) = 111 & 10 = 10 = 2
     *  i=7, j=2, 7 & (2^2) = 111 & 100 = 100 = 4
     */
    public List<List<Integer>> subsets_4(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null)
            return ans;
        if (nums.length == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }

        Arrays.sort(nums);

        for (int i = 0; i < (1<<nums.length); i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1<<j)) != 0) {
                    subset.add(nums[j]);
                }
            }

            ans.add(subset);
        }
        return ans;
    }
}