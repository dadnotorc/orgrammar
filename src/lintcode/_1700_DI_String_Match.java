package lintcode;

/**
 * 1700 · DI String Match
 * Easy
 * #Greedy, #Math
 * Amazon, Google
 *
 * Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.
 *
 * Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:
 * If S[i] == "I", then A[i] < A[i+1]
 * If S[i] == "D", then A[i] > A[i+1]
 *
 * 1 <= S.length <= 10000
 * S only contains characters "I" or "D".
 *
 * Example 1:
 * Input："IDID"
 * Output：[0,4,1,3,2]
 * Explanation：according to "IDID",0<4,4>1,1<3,3>2.
 *
 * Example 2:
 * Input："III"
 * Output：[0,1,2,3]
 * Explanation：according to "III",0<1,1<2,2<3.
 */
public class _1700_DI_String_Match {

    /**
     * 'I' 对应最小值, 逐渐递增
     * 'D' 对应最大值, 逐渐递减
     *
     * 最后别忘了 还要多出一位 -> ans.length = S.length() + 1
     */
    public int[] diStringMatch(String S) {
        int n = S.length();

        int[] ans = new int[n + 1];

        int low = 0, high = n;

        for (int i = 0; i < n; i++) {
            char c = S.charAt(i);

            if (c == 'I') {
                ans[i] = low;
                low++;
            } else {
                ans[i] = high;
                high--;
            }
        }

        // ans[n] = S.charAt(n - 1) == 'I' ? low : high;
        // 这里不用比较了, 直接给 low 就行
        ans[n] = low;

        return ans;
    }
}
