/*
Medium
#Two Pointers, #Greedy
Amazon
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 763. Partition Labels
 *
 * A string S of lowercase English letters is given. We want to partition this
 * string into as many parts as possible so that each letter appears in at most
 * one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 *
 * Note:
 * - S will have length in range [1, 500].
 * - S will consist of lowercase English letters ('a' to 'z') only.
 *
 * 类似 lintcode 328 · String Partition
 * 类似 lintcode 1045. Partition Labels
 *
 * Amazon OA - break video shots into scenes
 */
public class _0763_PartitionLabels {

    /**
     * 1. 遍历S, 找出所有字符最后出现的位置
     * 2. 再次遍历S, l指向当前区间的开始, r指向当前区间的末尾
     *    针对每个字符, 取其最后出现的位置与r两者较大值, 目的是圈定当前区间, 保证期间所有字符不在之后的区间内存在
     */
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();

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
                res.add(r - l + 1);
                l = r + 1; // 别忘了移动左指针
            }
        }

        return res;
    }
}
