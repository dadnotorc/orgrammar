package lintcode.binarytree;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 376. Binary Tree Path Sum
 * Easy
 *
 * Given a binary tree, find all paths that sum of the nodes in the path
 *  equals to a given number target.
 *
 * A valid path is from root node to any of the leaf nodes.
 *
 * Example 1:
 * Input:
 * {1,2,4,2,3}
 * 5
 * Output: [[1, 2, 2],[1, 4]]
 * Explanation:
 * The tree is look like this:
 * 	     1
 * 	    / \
 * 	   2   4
 * 	  / \
 * 	 2   3
 * For sum = 5 , it is obviously 1 + 2 + 2 = 1 + 4 = 5
 *
 * Example 2:
 * Input:
 * {1,2,4,2,3}
 * 3
 * Output: []
 * Explanation:
 * The tree is look like this:
 * 	     1
 * 	    / \
 * 	   2   4
 * 	  / \
 * 	 2   3
 * Notice we need to find all paths from root node to leaf nodes.
 * 1 + 2 + 2 = 5, 1 + 2 + 3 = 6, 1 + 4 = 5
 * There is no one satisfying it.
 */
public class _0376 {

    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // write your code here
        List<List<Integer>> ans = new ArrayList<>();
//        dfs(root, target, ans);

        return ans;
    }

//    public int dfs (TreeNode n, int target, List<List<Integer>> list) {
//        if (n == null) {
//            return 0;
//        }
//
//
//    }
}
