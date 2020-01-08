/*
Medium
String
Facebook
 */
package lintcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1041. Reorganize String
 *
 * Given a string S, check if the letters can be rearranged so that
 * two characters that are adjacent to each other are not the same.
 *
 * If possible, output any possible result. If not possible, return
 * the empty string.
 *
 * Notice
 * - S will consist of lowercase letters and have length in range [1, 500].
 *
 * Example 1:
 * Input: S = "aab"
 * Output: "aba"
 *
 * Example 2:
 * Input: S = "aaab"
 * Output: ""
 */
public class _1041_ReorganizeString {

    /**
     * 注意 char c = (char) ('a' + (val % 100)); 转换的时候, 需要使用(char)来cast
     *
     * time:  O(nlogn) 因为用到sorting
     * space: O(n) 创建的char array
     */
    public String reorganizeString(String S) {
        int n = S.length();
        int[] counts = new int[26];


        for (int i = 0; i < 26; i++)
            counts[i] += i;

        for (char c: S.toCharArray())
            counts[c - 'a'] += 100;

        // 两次循环过后, 数组中的值 = 当前字母的值 + 100 * 当前字母出现次数

        // sort的目的是方便安排出现次数较多的字母
        Arrays.sort(counts);

        char[] ans = new char[n];
        int index = 1; // 交错排列 - 先填入偶数位. 这样出现次数较多的字母将之后被放入基数位
        for (int val : counts) {
            int count = val / 100;
            if (count == 0)
                continue;

            char c = (char) ('a' + (val % 100));

            if (count > (n + 1) / 2)
                return "";

            for (int i = 0; i < count; i++) {
                if (index >= n) { // 当前index已过界, 返回开头, 填基数位
                    index = 0;
                }

                ans[index] = c;
                index += 2;
            }

        }

        // 注意不能用 ans.toString()
        return String.valueOf(ans);
    }

    @Test
    public void test1() {
        org.junit.Assert.assertEquals("aba", reorganizeString("aab"));
    }

    @Test
    public void test2() {
        org.junit.Assert.assertEquals("", reorganizeString("aaab"));
    }

    @Test
    public void test3() {
        org.junit.Assert.assertEquals("vlvov", reorganizeString("vvvlo"));
    }
}
