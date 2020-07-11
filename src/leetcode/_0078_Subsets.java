/*
Medium
#Array, #Backtracking, #Bit Manipulation
 */
package leetcode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 78. Subsets
 *
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
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
 * 此题与 lintcode 17. Subsets 相同
 */
public class _0078_Subsets {


    /**
     * 使用bit manipulation
     *
     * 数组有n个数组, 所以子集的总数为 2^n, 等于 1 << n
     *         例如 [1,2,3] -> 共有2^3 = 8个子集
     *         每个子集对应[0, 2^n - 1]中一个数字的二进制, 例如
     *         []  --> 0 0 0 --> 0
     *         [1] --> 0 0 1 --> 1
     *         [2] --> 0 1 0 --> 2
     *         [3] --> 1 0 0 --> 4
     *         ...
     *         [1,2,3] --> 1 1 1 --> 7 = 2 ^ 3 - 1 = 1<<3 - 1
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
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) { return res; }

        for (int i = 0; i < (1<<nums.length); i++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1<<j)) != 0) {
                    cur.add(nums[j]);
                }
            }
            res.add(cur);
        }

        return res;
    }




    /**
     * DFS
     */
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets_DFS(int[] nums) {
        if (nums == null) { return res; }
        helper_1(nums, 0, new ArrayList<>());
        return res;
    }

    private void helper_1(int[] nums, int index, List<Integer> cur) {
        if (index == nums.length) {
            res.add(new ArrayList<>(cur)); // deep copy
            return;
        }

        cur.add(nums[index]);
        helper_1(nums, index + 1, cur);

        cur.remove(cur.size() - 1);
        helper_1(nums, index + 1, cur);
    }

    private void helper_2(int[] nums, int index, List<Integer> cur) {
        res.add(new ArrayList<>(cur));

        for (int i = index; i < nums.length; i++) {
            cur.add(nums[i]);
            helper_2(nums, i + 1, cur);
            cur.remove(cur.size() - 1);
        }
    }





    /**
     * BFS
     * 1. 先排序
     * 2. 逐层添加子集
     *    在之前的list基础上, 创建新的list, 并加入数组中未加入的新数字 (用排序避免重复添加)
     */
    public List<List<Integer>> subsets_BFS(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) { return res; }

        Arrays.sort(nums);

        Queue<List<Integer>> q = new LinkedList<>();
        q.offer(new ArrayList<>());

        while (!q.isEmpty()) {
            List<Integer> pre = q.poll();
            res.add(pre);

            for (int num : nums) {
                if (pre.size() == 0 || pre.get(pre.size() - 1) < num) {
                    List<Integer> cur = new ArrayList<>(pre);
                    cur.add(num);
                    q.offer(cur);
                }
            }
        }

        return res;
    }
}
