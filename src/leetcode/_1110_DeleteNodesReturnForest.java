package leetcode;

import util.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1110. Delete Nodes And Return Forest
 * Medium
 *
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a
 *  forest (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest.
 * You may return the result in any order.
 *
 * Example 1:
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 *
 * Constraints:
 * - The number of nodes in the given tree is at most 1000.
 * - Each node has a distinct value between 1 and 1000.
 * - to_delete.length <= 1000
 * - to_delete contains distinct values between 1 and 1000.
 */
public class _1110_DeleteNodesReturnForest {

    /**
     * Time complexity:  O(n) - 遍历所有nodes
     * Space complexity: O(l) - l为int array to_delete长度
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

        List<TreeNode> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        Set<Integer> delSet = new HashSet<>();
        for (int d : to_delete) {
            delSet.add(d);
        }

        root = helper(root, delSet, ans);

        if (root != null && !delSet.contains(root.val)) {
            ans.add(0, root); // root加为队列首位
        }

        return ans;
    }

    public TreeNode helper(TreeNode n, Set delSet, List<TreeNode> list) {

        if (n == null) {
            return null;
        }

        n.left = helper(n.left, delSet, list);
        n.right = helper(n.right, delSet, list);

        // 如果当前node需要被删除, 将其child nodes加入队列
        if (delSet.contains(n.val)) {
            if (n.left != null) {
                list.add(n.left);
            }
            if (n.right != null) {
                list.add(n.right);
            }
            return null;
        }

        return n;
    }

}
