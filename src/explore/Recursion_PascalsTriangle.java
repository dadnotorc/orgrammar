package explore;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. Pascal's Triangle
 * Easy
 *
 * Given a non-negative integer numRows, generate
 *  the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum
 *  of the two numbers directly above it.
 *
 * Example:
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class Recursion_PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> ans = new ArrayList<>();

        helper(numRows, ans);

        return ans;
    }

    private void helper(int row, List<List<Integer>> ans) {
        /**
         * row number 从1开始
         */
        if (row < 1) {
            return;
        }

        helper(row - 1, ans);

        List<Integer> cur = new ArrayList<>();
        List<Integer> pre = new ArrayList<>();
        if (row > 2) {
            pre = ans.get(row - 2);
        }
        for (int col = 0; col < row; col++) {
            if (col == 0 || col == row - 1) {
                cur.add(1);
            } else {
                cur.add(pre.get(col-1) + pre.get(col));
            }
        }

        ans.add(cur);
    }
}
