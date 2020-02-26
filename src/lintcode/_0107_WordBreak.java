/*
Medium
#DP, #String
Amazon, Facebook, Google, Uber
FAQ++
 */
package lintcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 107. Word Break
 *
 * Given a string s and a dictionary of words dict, determine if s can be
 * broken into a space-separated sequence of one or more dictionary words.
 *
 * Example 1:
 * Input:  "lintcode", ["lint", "code"]
 * Output:  true
 *
 * Example 2:
 * Input: "a", ["a"]
 * Output:  true
 */
public class _0107_WordBreak {

    /*
    非DP解法 - 查看s是否可以可以用字典中的字完整的组成
    先看s是否以某个字开头, 如果是去s后半部分的substring, 继续遍寻字典
     */


    /**
     * DP
     */
    public boolean wordBreak(String s, Set<String> dict) {

        // 字符串有length+1个分割点, 例如 _l_i_n_t_
        // canSegment[i]表示substring(0,i)在字典中存在, 所以能分割
        // 例如, "lintcode", ["lint", "code"]
        // canSegment = {T, F, F, F, T, F, F, F, T}
        boolean[] canSegment = new boolean[s.length() + 1];

        // 因为substring(0,0)是empty string, 可以被分割出来, 所以等于true
        canSegment[0] = true;

        for (int i = 1; i <= s.length(); i++) { // 查当前substring(0,i)是否可以被分割
            for (int j = 0; j < i; j++) {

                // 如果substring(0,j)可以被分割, 且substring(j,i)在字典中存在 -> (0,i)也可以被分割
                if (canSegment[j] && dict.contains(s.substring(j, i))) {
                    canSegment[i] = true;
                    break;
                }
            }
        }

        return canSegment[s.length()];
    }

    @Test
    public void test1() {
        Set<String> dict = new HashSet<>();
        dict.add("lint");
        dict.add("code");

        org.junit.Assert.assertTrue(wordBreak("lintcode", dict));
    }
}
