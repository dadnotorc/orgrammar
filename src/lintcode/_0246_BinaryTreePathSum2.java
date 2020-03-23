/*
Easy
DFS, Recursion, Binary Tree
Amazon
 */
package lintcode;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 246. Binary Tree Path Sum II
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
     *
     * 易错点:
     * 1. 在每个点求path sum时, 是从当前node往root方向, 避免错过起点不是root的path
     * 2. 找到一个答案时, 不能break循环, 因为当前path继续往后仍有答案
     * 3. 加入ans队列时, 要做份current list的拷贝
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // write your code here
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, target, ans, new ArrayList<>());
        return ans;
    }

    private void dfs(TreeNode node, int tar,
                     List<List<Integer>> ans, List<Integer> curPath) {
        if (node == null) { return; }

        int sum = 0;
        curPath.add(node.val);

        // 注意, 从当前node往上求和, 而不是从root往下. 因为后者会错过起点不是root的path
        for (int i = curPath.size() - 1; i >= 0; i--) {
            sum += curPath.get(i);

            if (sum == tar) {
                // 注意这里要做份current list拷贝, 存入ans中
                ans.add(new ArrayList<>(curPath.subList(i, curPath.size())));
                // 找到一个后, 不能停, 因为后续可能还有
            }
        }

        dfs(node.left, tar, ans, curPath);
        dfs(node.right, tar, ans, curPath);

        curPath.remove(curPath.size() - 1);
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
