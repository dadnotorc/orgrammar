/*
Easy

 */
package lintcode;

/**
 * 422 · Length of Last Word
 *
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 * return the length of last word in the string.
 *
 * If the last word does not exist, return 0.
 *
 * A word is defined as a character sequence consists of non-space characters only.
 *
 * Example 1:
 * Input: "Hello World"
 * Output: 5
 *
 * Example 2:
 * Input: "Hello LintCode"
 * Output: 8
 */
public class _0422_LengthOfLastWord {

    /**
     * 从末端开始, 跳过开头的空格, 数字数直到遇到下一个空格
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();
        int len = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                if (len > 0) {
                    return len;
                }
            } else {
                len++;
            }
        }

        return len;
    }
}
