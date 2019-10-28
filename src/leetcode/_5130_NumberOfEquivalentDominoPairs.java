package leetcode;

import org.junit.Test;

import java.util.Hashtable;

/**
 * 5130. Number of Equivalent Domino Pairs
 * Easy
 *
 * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to
 *  dominoes[j] = [c, d] if and only if either (a==c and b==d), or
 *  (a==d and b==c) - that is, one domino can be rotated to be equal
 *  to another domino.
 *
 * Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length,
 *  and dominoes[i] is equivalent to dominoes[j].
 *
 * Example 1:
 * Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * Output: 1
 *
 * Constraints:
 *
 * - 1 <= dominoes.length <= 40000
 * - 1 <= dominoes[i][j] <= 9
 */
public class _5130_NumberOfEquivalentDominoPairs {

    // todo
    public int numEquivDominoPairs(int[][] dominoes) {
        int ans = 0;

        Hashtable<int[], Integer> tb = new Hashtable<>();
//        for (int i = 0; i < dominoes.length; i++) {
//            if
//        }


        return ans;
    }

    class domino {
        int[] d;
        public domino (int[] arr) {
            d = arr;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) { return true; }

            if (obj == null || obj.getClass() != this.getClass()) { return false; }

            int[] target = (int[]) obj;

            return ((d[0] == target[0] && d[1] == target[1]) ||
                    (d[0] == target[1] && d[1] == target[0]));
        }

//        @Override
//        public int hashCode() {
//
//        }
    }


    /**
     * Input: [[1,2],[1,2],[1,1],[1,2],[2,2]]
     * Expected: 3
     */
    @Test
    public void test1() {

    }
}
