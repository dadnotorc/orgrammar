/*
Easy
#String
Facebook
 */
package lintcode;

/**
 * 420 · Count and Say
 *
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 *
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 *
 * Given an integer n, generate the nth sequence.
 *
 * The sequence of integers will be represented as a string.
 *
 * Example 1:
 * Input: 1
 * Output: "1"
 *
 * Example 2:
 * Input: 5
 * Output: "111221"
 */
public class _0420_CountAndSay {

    /**
     * 遍历当前字符串, 读取相同数字的个数
     */
    public String countAndSay(int n) {
        String cur = "1";
        if (n < 1) { return ""; }

        for (int i = 2; i <= n; i++) {
            cur = getNext(cur);
        }

        return cur;
    }

    public String getNext(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        int curCharCount = 1;
        for (int i = 0; i < chars.length; i++) {
            if (i + 1 == chars.length || chars[i] != chars[i + 1]) { // 下一位越界 或者 下一位与当前位不相同
                sb.append(curCharCount).append(chars[i]);
                // 别忘了 reset
                curCharCount = 1;
            } else {
                curCharCount++;
            }
        }
        return sb.toString();
    }
}
