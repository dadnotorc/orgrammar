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
     * 1. 先读出现次数
     * 2. 排序, 因排序后无法使用下标找到当前所指字符
     * 所以对于字符c, 其出现次数为 x * 100 + (c - 'a')
     * 3. 检查是否可以重组: max occurrence > (str_len + 1) / 2 ?
     * 4. 重组:
     * 出现次数多的字符放在 even index (0,2,4...);
     * 出现次数少的字符放在 odd index (1,3,5...)
     *
     * 出现次数: y / 100
     * 当前字符: (char) ('a' + (y % 100)) 注意转换的时候, 需要使用(char)来cast
     *
     * time:  O(nlogn) 因为用到sorting
     * space: O(1)
     */
    public String reorganizeString(String S) {
        if (S == null)
            return "";
        if (S.length() <= 1)
            return S;

        // read all chars and record their occurences
        int[] occurrences = new int[26]; // all lower case letters
        for (int i = 0; i < 26; i++)
            occurrences[i] += i;

        for (char c : S.toCharArray())
            occurrences[c - 'a'] += 100; // 增加100的目的是分别记录, 当前是哪个字符 + 当前字符出现次数

        // sort by occurences (low to high)
        Arrays.sort(occurrences);

        // check max occurence to see if it is possible to construct
        int min = 0, max = 25, index = 0;
        StringBuilder sb = new StringBuilder();

        if ((occurrences[max] / 100) > (S.length() + 1) / 2)
            return "";

        // reorganize string
        // 出现次数多的字符放在even index (0,2,4...); 出现次数少的字符放在odd index (1,3,5...)
        while (index < S.length()) {

            if ((index & 1)  == 0) { // even index, put higher occurence chars
                while ((occurrences[max] / 100) == 0)
                    max--;
                char c0 = (char) ('a' + occurrences[max] % 100);
                sb.append(c0);
                occurrences[max] -= 100;

            } else { // odd index, put lower occurence chars
                while ((occurrences[min] / 100) == 0)
                    min++;
                char c1 = (char) ('a' + occurrences[min] % 100);
                sb.append(c1);
                occurrences[min] -= 100;
            }

            index++;
        }

        return sb.toString();
    }

    /**
     * time:  O(nlogn) 因为用到sorting
     * space: O(n) 创建的char array
     */
    public String reorganizeString_2(String S) {
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
        int index = 1; // 交错排列 - 先填入偶数位. 这样出现次数较多的字母将之后被放入奇数位
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
