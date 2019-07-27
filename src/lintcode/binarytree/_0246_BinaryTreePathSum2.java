package lintcode.binarytree;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 246. Binary Tree Path Sum II
 * Easy
 * Amazon
 *
 * Your are given a binary tree in which each node contains a value.
 * Design an algorithm to get all paths which sum to a given value.
 * The path does not need to start or end at the root or a leaf,
 *  but it must go in a straight line down.
 *
 * Example 1:
 * Input:
 * {1,2,3,4,#,2}
 * 6
 * Output:
 * [[2, 4],[1, 3, 2]]
 * Explanation:
 * The binary tree is like this:
 *     1
 *    / \
 *   2   3
 *  /   /
 * 4   2
 * for target 6, it is obvious 2 + 4 = 6 and 1 + 3 + 2 = 6.
 *
 * Example 2:
 * Input:
 * {1,2,3,4}
 * 10
 * Output:
 * []
 * Explanation:
 * The binary tree is like this:
 *     1
 *    / \
 *   2   3
 *  /
 * 4
 * for target 10, there is no way to reach it.
 *
 * 注意: node值可能为负数
 */
public class _0246_BinaryTreePathSum2 {

    /**
     * DFS
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // write your code here
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(root, target, ans, cur);
        return ans;
    }

    private void dfs(TreeNode node, int tar,
                     List<List<Integer>> list, List<Integer> cur) {
        if (node == null) { return; }

        int sum = 0;
        cur.add(node.val);

        for (int i = cur.size() - 1; i >= 0; i--) {
            sum += cur.get(i);

            if (sum == tar) {
                list.add(new ArrayList<>(cur.subList(i, cur.size())));
            }
        }

        dfs(node.left, tar, list, cur);
        dfs(node.right, tar, list, cur);

        cur.remove(cur.size() - 1);
    }

    // 这段有bug
//    private void dfs(TreeNode node, int tar, int sum,
//                     List<List<Integer>> list, List<Integer> cur) {
//        if (node == null) {
//            return;
//        }
//
//        int val = node.val;
//        sum += val;
//        cur.add(val);
//
//        if (sum == tar) {
//            list.add(new ArrayList<>(cur));
//            cur = new ArrayList<>();
//            sum = 0;
//        } else if (sum > tar) {
//            while (sum >= tar) {
//                if (sum == tar) {
//                    list.add(new ArrayList<>(cur));
//                    cur = new ArrayList<>();
//                    sum = 0;
//                    break;
//                } else {
//                    sum -= cur.remove(0);
//                }
//            }
//        }
//
//        dfs(node.left, tar, sum, list, cur);
//        dfs(node.right, tar, sum, list, cur);
//
//        if (cur.size() != 0) {
//            cur.remove(cur.size() - 1);
//        }
//    }
}
