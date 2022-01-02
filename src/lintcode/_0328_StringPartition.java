/*
Medium
#Two Pointers
Meituan
 */
package lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 328 · String Partition
 *
 * Given a string with all characters in uppercase, please divide the string into as many parts as possible
 * so that each letter appears in only one part. Return an array containing the length of each part.
 *
 * S.length <= 1000
 *
 * Example 1:
 * Input: "MPMPCPMCMDEFEGDEHINHKLIN"
 * Output: [9,7,8]
 * Explanation:
 * "MPMPCPMCM"
 * "DEFEGDE"
 * "HINHKLIN"
 *
 * 非常类似 leetcode 763. Partition Labels
 */
public class _0328_StringPartition {

    /**
     * 1. 遍历 s, 统计每个字符最后出现的下标
     * 2. 再次遍历, 使用 i 指针
     *    - l 指针指向当前区间第一个字符, 保持不动
     *    - r 指针指向当前 i 字符最后出现的位置, 即当前区间最后一位
     *    当 i 与 r 重合时, 说明当前区间结束, 加入答案. l 右移, 然后继续
     */
    public List<Integer> splitString(String s) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0) { return ans; }

        char[] chars = s.toCharArray();

        int[] lastPosition = new int[26]; // 26 个大写字母在 s 中最后出现的位置
        Arrays.fill(lastPosition, -1);

        for (int i = 0; i < chars.length; i++) {
            lastPosition[chars[i] - 'A'] = i;
        }

        int l = 0, r = 0;
        // 遍历字符串
        // - l 指针指向当前区间第一个字符, 保持不动
        // - r 指针指向当前 i 字符最后出现的位置
        for (int i = 0; i < chars.length; i++) {
            r = Math.max(r, lastPosition[chars[i] - 'A']);
            if (i == r) { // r 左边的字符在之后都不会再出现, 即完成一个区间
                ans.add(r - l + 1); // 注意要 + 1
                l = r + 1;
            }
        }

        return ans;
    }
}
