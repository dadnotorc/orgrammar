/*
Hard
#Scanning Line
Amazon
Ladder
 */
package lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 1379. The Longest Scene
 *
 * A string, each character representing a scene. Between two identical characters
 * is considered to be a continuous scene. For example: abcda, you can think of these
 * five characters as the same scene. Or acafghbeb can think of two aca and beb scenes.
 * If there is a coincidence between the scenes, then the scenes are combined.
 * For example, abcab, where abca and bcab are coincident, then the five characters
 * are considered to be the same scene. Give a string to find the longest scene.
 *
 * Notice
 * - 1 <= |str| <= 1e5
 * - str contains only lowercase letters
 *
 * Example 1
 * Input: "abcda"
 * Output: 5
 * Explanation:
 * The longest scene is "abcda". 第四个字母是 d
 *
 * Example 2
 * Input: "abcab"
 * Output: 5
 * Explanation:
 * The longest scene is "abcab". 第四个字母与第一个字母都是 a
 */
public class _1379_TheLongestScene {

    /**
     * 这个解法跟 leetcode 763. Partition Labels 类似
     */
    public int getLongestScene(String S) {
        int res = 0;

        int[] lastPosition = new int[26]; // 记录26个字母最后出现的位置
        Arrays.fill(lastPosition, -1);
        for (int i = 0; i < S.length(); i++) {
            lastPosition[S.charAt(i) - 'a'] = i;
        }

        int l = 0, r = 0;
        // 遍历每个字符, l指针保持不动, r指针指向当前i字符最后出现的位置
        for (int i = 0; i < S.length(); i++) {
            r = Math.max(r, lastPosition[S.charAt(i) - 'a']);
            if (r == i) { // 如果两者相等, 说明r左边的所有字符之后不再出现, 即l至r之间的字符只在当前区间内出现
                res = Math.max(res, r - l + 1);
                l = r + 1; // 别忘了移动左指针
            }
        }

        return res;
    }





    // https://www.jiuzhang.com/solution/the-longest-scene/


    /**
     * 使用interval记录每个出现过的字母首位与末位
     */
    class Interval {
        int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    class MyComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b) {
            if (a.start == b.start) {
                return a.end - b.end;
            } else {
                return a.start - b.start;
            }
        }
    }

    public int getLongestScene_2(String S) {
        Interval[] intervals = new Interval[26]; // 每个字母第一次出现与最后一次出现的interval
        int len = S.length();
        for (int i = 0; i < 26; i++) { // 初始化intervals
            intervals[i] = new Interval(len, -1);
        }

        for (int i = 0; i < len; i++) { // 找出S中所有字母出现的interval
            int index = S.charAt(i) - 'a';
            intervals[index].start = Math.min(intervals[index].start, i);
            intervals[index].end = Math.max(intervals[index].end, i);
        }

        Arrays.sort(intervals, new MyComparator()); // 排序, interval靠前者排在前

        int res = intervals[0].end - intervals[0].start + 1;
        for (int i = 1; i < 26; i++) {
            if (intervals[i].end == -1) { break; }

            // 如当前字母的interval与前一个interval重叠
            // 将当前interval拉长
            if (intervals[i].start <= intervals[i - 1].end) {
                intervals[i].start = intervals[i - 1].start;
                intervals[i].end = Math.max(intervals[i - 1].end, intervals[i].end);
            }

            res = Math.max(res, intervals[i].end - intervals[i].start + 1);
        }

        return res;
    }




    @Test
    public void test1() {
        String s = "abcda";
        org.junit.Assert.assertEquals(5, getLongestScene_2(s));
    }

    @Test
    public void test2() {
        String s = "abcab";
        org.junit.Assert.assertEquals(5, getLongestScene_2(s));
    }

    @Test
    public void test3() {
        String s = "acafghbeb";
        org.junit.Assert.assertEquals(3, getLongestScene_2(s));
    }
}
