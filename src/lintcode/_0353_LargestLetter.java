/*
Easy
#String
 */
package lintcode;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 353 · Largest letter
 *
 * Given a string S, find the largest alphabetic character, whose both uppercase and lowercase appear in S.
 * The uppercase character should be returned. If there is no such character, return "NO".
 * - 1 <= len(s) <= 10^6
 *
 * Example
 * Input: S = "admeDCAB"
 * Output: "D"
 *
 * Input: S = "adme"
 * Output: "NO"
 */
public class _0353_LargestLetter {

    /**
     * 跟前一个做法类似, 使用两个 HashSet, 分别存入大写和小写字符
     *
     * 之后从字母表最后一位开始, 看那个字母存在于两个 HashSet 中
     *
     * 时间 O(n) - 遍历字符串
     * 空间 O(1) - 每个 HashSet 最大只有包含 26 个字母
     */
    public String largestLetter_2(String s) {
        HashSet<Character> lowerSet = new HashSet<>();
        HashSet<Character> upperSet = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                lowerSet.add(c);
            } else if (c >= 'A' && c <= 'Z') {
                upperSet.add(c);
            }
        }

        for (int i = 25; i >= 0; i--) {
            char lowerChar = (char)('a' + i);
            char upperChar = (char)('A' + i);
            if (lowerSet.contains(lowerChar) && upperSet.contains(upperChar)) {
                return String.valueOf(upperChar);
            }
        }

        return "NO";
    }

    /**
     * 遍历两个 HashSet.  这么做复杂了, 不如前一个, 直接在字母表上从后往前找
     */
    public String largestLetter(String s) {
        HashSet<Character> lower = new HashSet<>();
        HashSet<Character> upper = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                lower.add(c);
            } else if (c >= 'A' && c <= 'Z') {
                upper.add(c);
            }
        }

        char max = ' ';
//        Iterator<Character> it = lower.iterator();
//        while (it.hasNext()) {
//            char c = (char) it.next();
//        }
        for (Character c : upper) {
            if (lower.contains(Character.toLowerCase(c)) && c > max) {
                max = c;
            }
        }

        if (Character.isUpperCase(max)) {
            return String.valueOf(max);
        }

        return "NO";
    }
}
