/*
Naive
#String
 */
package lintcode;

/**
 * 13 · Implement strStr()
 *
 * For a given source string and a target string, you should output the first index (from 0)
 * of target string in the source string. If the target does not exist in source, just return -1.
 *
 * - Do I need to implement KMP Algorithm in a real interview?
 *   Not necessary. When you meet this problem in a real interview, the interviewer may just want
 *   to test your basic implementation ability. But make sure you confirm how to implement with the interviewer first.
 *
 * Example 1:
 *
 * Input:
 * source = "source"
 * target = "target"
 * Output: -1
 * Explanation: If the source does not contain the target's content, return - 1.
 *
 * Example 2:
 * Input:
 * source = "abcdabcdefg"
 * target = "bcd"
 * Output: 1
 * Explanation:
 * If the source contains the target's content, return the location where the target first appeared in the source.
 *
 * Challenge
 * O(nm) is acceptable. Can you implement an O(n) algorithm? (hint: KMP)
 */
public class _0013_ImplementstrStr {

    /**
     * 注意特判
     * 特殊情况 source = "", target = ""
     */
    public int strStr(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }

        if (source.equals(target) || target.equals("")) {
            return 0;
        }

        char[] sourceChars = source.toCharArray();
        char[] targetChars = target.toCharArray();

        int i = 0;
        for (; i <= sourceChars.length - targetChars.length; i++) { // 注意 这里是 <=, 例如 source = "abcde", target = "e"
            int j = 0;
            for (; j < targetChars.length; j++) {
                if (sourceChars[i + j] != targetChars[j]) {
                    break;
                }
            }
            if (j == targetChars.length) {
                return i;
            }
        }

        return -1;
    }
}
