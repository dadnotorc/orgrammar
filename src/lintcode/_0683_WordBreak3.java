/*
Medium
#DP
Amazon
 */
package lintcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 683. Word Break
 *
 * Give a dictionary of words and a sentence with all whitespace removed,
 * return the number of sentences you can form by inserting whitespaces
 * to the sentence so that each word can be found in the dictionary.
 *
 * Notice
 * - Ignore case
 *
 * Example 1
 * Input: "CatMat", ["Cat", "Mat", "Ca", "tM", "at", "C", "Dog", "og", "Do"]
 * Output: 3
 * Explanation: we can form 3 sentences, as follows:
 * "CatMat" = "Cat" + "Mat"
 * "CatMat" = "Ca" + "tM" + "at"
 * "CatMat" = "C" + "at" + "Mat"
 *
 * Example 2
 * Input: "a", []
 * Output: 0
 */
public class _0683_WordBreak3 {

    /**
     * DP 解法 - ct[i]: 表示从当前字符到字符串末端, 能有多少中划分方式
     *
     * 1. 将s和dict全部变成小写
     * 2. 创建int[], 长度为s.length()+1, 每个entry记录从当前下标位字符开始到字符串末端, 有多少种划分方式
     * s="catmat", int[]长度=7
     * "catmat|", sub="" -> ct[6]=1, 即什么都不取
     * "catma|t", sub="t" -> 如字典中含"t", ct[5]+=ct[6]=1
     * "catm|at", 两种情况:
     * - sub="a" -> 如字典中含"a", ct[4]+=ct[5] (ct[5]即sub="t")
     * - sub="at" -> 如字典中含"at", ct[4]+=ct[6] (ct[6]即sub="")
     *
     * 先改小写的原因是, 因为有如下的 test case
     * input: "aaaaaaaa", ["aaaa","Aa","aaa"]
     */
    public int wordBreak3(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict.isEmpty())
            return 0;

        s = s.toLowerCase();
        Set<String> lcDict = toLowerCase(dict);

        int len = s.length();
        int[] ct = new int[len+1];
        ct[len] = 1; // 从后往前, sub string = "", 什么都不取

        for (int i = len - 1; i >= 0; i--) { // sub = s.sub(i, j); i为inclusive, j为exclusive
            for (int j = i + 1; j <= len; j++) {
                if (lcDict.contains(s.substring(i, j))) {
                    ct[i] += ct[j];
                }
            }
        }

        return ct[0];
    }

    private Set<String> toLowerCase(Set<String> dict) {
        Set<String> lcDict = new HashSet<>();
        for (String s : dict) {
            lcDict.add(s.toLowerCase());
        }
        return lcDict;
    }
}
