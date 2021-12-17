/*
Easy


 */
package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 768 · Yang Hui Triangle
 *
 * Given an integer n, return the first n-line Yang Hui triangle.
 *
 * 1. 0 <= n <= 20
 * 2. Yang Hui’s Triangle also called Pascal's triangle. --(Wikipedia)
 *
 * Example 1:
 * Input : n = 4
 * Output :
 * [
 *  [1]
 *  [1,1]
 *  [1,2,1]
 *  [1,3,3,1]
 * ]
 */
public class _0768_YangHuiTriangle {

    public List<List<Integer>> calcYangHuisTriangle(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        if (n < 1) { return ans; }

        for (int i = 0; i < n; i++) {
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            for (int j = 1; j < i; j++) { // 这题关键在 j 的取值
                cur.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
            }

            if (i > 0) {
                cur.add(1);
            }

            ans.add(cur);
        }

        return ans;
    }
}
