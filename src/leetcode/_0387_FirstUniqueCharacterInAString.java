/*
Easy
#Hash Table, #String
 */
package leetcode;

import java.util.Arrays;

/**
 * 387. First Unique Character in a String
 *
 * Given a string, find the first non-repeating character in it and return it's index.
 * If it doesn't exist, return -1.
 *
 * Examples:
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 *
 * Note: You may assume the string contain only lowercase letters.
 */
public class _0387_FirstUniqueCharacterInAString {

    /**
     * 比前两种写法快, 使用String API  indexOf() 和 lastIndexOf()
     * 查看26个字母在字符串中第一次出现的下标是否等同最后一次出现的下标
     */
    public int firstUniqChar_2(String s) {
        int ans = s.length();

        for (char c = 'a'; c <= 'z'; c++) {
            int index = s.indexOf(c);

            if (index != -1 && index == s.lastIndexOf(c)) {
                ans = Math.min(ans, index);
            }
        }

        return ans == s.length() ? -1 : ans;
    }



    /**
     * 另外一种写法, 第一次遍历字符串并用数组记录每个字母出现的次数.
     * 之后第二次遍历字符串, 查看当前字符出现次数是否为1. 如果是, 返回其下标; 不是, 则跳过
     * 第二次遍历结束后, 如果仍未返回, 说明所有字符皆repeat过, 则返回-1
     */


    /**
     * 记录每个字母第一次出现的下标, 如果多次出现, 将其定为-1
     * 最后遍历26个字母, 选取最小却非-1的值
     *
     * 注意, 可能所有字母皆重复, 此时ans仍未初始值, 注意返回前要判断
     */
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0)
            return -1;

        int length = s.length();
        int[] loc = new int[26]; // 记录每个字母第一次出现的位置
        Arrays.fill(loc, length);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (loc[c - 'a'] == length) { // non-repeating char
                loc[c - 'a'] = i;
            } else {
                loc[c - 'a'] = -1;
            }
        }

        int ans = length;
        for (int index : loc) {
            if (index != -1 && index < ans)
            {
                ans = index;
            }
        }

        return ans == length ? -1 : ans;
    }
}
