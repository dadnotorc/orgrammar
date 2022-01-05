/*
Easy
#Hash Table
JD, Meituan
 */
package lintcode;

import java.util.HashSet;

/**
 * 1905 · Character deletion
 *
 * Given the two strings str and sub, your task is to completely delete the characters in str that exist in sub.
 *
 * String contains spaces
 * 1 <= len(str),len(sub) <= 10^5
 *
 * Example 1:
 * Input: str="They are students"，sub="aeiou"
 * Output: "Thy r stdnts"
 */
public class _1905_CharacterDeletion {

    /**
     * 使用 256 位 array 记录 sub 中出现的字符 - 比用 set 快
     */
    public String CharacterDeletion_2(String str, String sub) {
        int[] counts = new int[256]; // 记录 sub 中出现的字符

        for (int i = 0; i < sub.length(); ++i) {
            counts[sub.charAt(i)] = 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < str.length(); ++j) {
            char c = str.charAt(j);
            if (counts[c] == 0) { // sub 中不存在的字符
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 使用 HashSet 记录 sub 中的字符, 再遍历 str, 只添加 set 中 不存在的字符
     *
     * time O(m + n), m 是 str 长度, n 是 sub 长度
     * space O(1)
     */
    public String CharacterDeletion(String str, String sub) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < sub.length(); ++i) {
            set.add(sub.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < str.length(); ++j) {
            char c = str.charAt(j);
            if (!set.contains(c)) {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
