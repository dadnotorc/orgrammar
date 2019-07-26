package lintcode.recursion;

import java.util.*;

/**
 * 15. Permutations
 * Medium
 * LinkedIn, Facebook, Microsoft
 *
 * Given a list of numbers, return all possible permutations.
 * - You can assume that there is no duplicate numbers in the list.
 *
 * Example 1:
 * Input: [1]
 * Output:
 * [
 *   [1]
 * ]
 *
 * Example 2:
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * Challenge
 * - Do it without recursion.
 */
public class _0015 {

    /* bfs */

    public List<List<Integer>> permute_bfs(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null) {
            return ans;
        }
        if (nums.length == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }

        Deque<List<Integer>> q = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> l = new ArrayList<>(Arrays.asList(nums[i]));
            q.offer(l);
        }

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                List<Integer> curL = q.poll();
                if (curL.size() == nums.length) {
                    ans.add(new ArrayList<>(curL));
                } else {
                    for (int n : nums) {
                        if (!curL.contains(n)) {
                            List<Integer> newL = new ArrayList<>(curL);
                            newL.add(n);
                            q.offer(newL);
                        }
                    }
                }
            }
        }

        return ans;
    }


    /* recursion - DFS */

    public List<List<Integer>> permute_dfs(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null) {
            return ans;
        }
        if (nums.length == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }

        List<Integer> permutation = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        helper(nums, permutation, set, ans);

        return ans;
    }

    public void helper (int[] n, List<Integer> permutation,
                        Set<Integer> set, List<List<Integer>> ans) {

        // recursion exit
        if (permutation.size() == n.length) {
            ans.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < n.length; i++) {
            if (set.contains(n[i])) {
                continue;
            }

            permutation.add(n[i]);
            set.add(n[i]);
            helper(n, permutation, set, ans);
            set.remove(n[i]);
            permutation.remove(permutation.size() - 1);
        }
    }
}
