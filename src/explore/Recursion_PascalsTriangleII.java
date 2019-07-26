package explore;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. Pascal's Triangle II
 * Easy
 *
 * Given a non-negative index k where k ≤ 33, return
 *  the kth index row of the Pascal's triangle.
 *
 * Note that the row index starts from 0.
 *
 * In Pascal's triangle, each number is the sum of
 *  the two numbers directly above it.
 *
 * Example:
 * Input: 3
 * Output: [1,3,3,1]
 *
 * Follow up:
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public class Recursion_PascalsTriangleII {

    public List<Integer> getRow(int rowIndex) {

        List<Integer> cur = new ArrayList<>();
        /**
         * row index 从0开始
         */
        if (rowIndex < 0) {
            return cur;
        }

        List<Integer> pre = getRow(rowIndex - 1);

        for (int col = 0; col < rowIndex + 1; col++) {
            if (col == 0 || col == rowIndex) {
                cur.add(1);
            } else {
                cur.add(pre.get(col - 1) + pre.get(col));
            }
        }
        return cur;
    }

}
