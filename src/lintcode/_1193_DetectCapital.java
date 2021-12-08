/*
Easy
#String
Google
 */
package lintcode;

/**
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 *
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "lintcode".
 * Only the first letter in this word is capital if it has more than one letter, like "Google".
 * Otherwise, we define that this word doesn't use capitals in a right way.
 *
 * The input will be a non-empty word consisting of uppercase and lowercase latin letters.
 *
 * Example 1:
 * Input: "USA"
 * Output: True
 *
 * Example 2:
 * Input: "FlaG"
 * Output: False
 */
public class _1193_DetectCapital {

    /**
     * 这种写法是遍历所有字符
     *
     * 还可以写成, 在读取每个字符的时候, 判断是否是首位, 或者没有任何小写字符. 提前结束遍历
     *
     * ASCII table - https://www.asciitable.com/
     * A-Z 排在 a-z 之前
     */
    public boolean detectCapitalUse(String word) {
        char[] chars = word.toCharArray();
        int len = chars.length;
        int cap = 0;
        for (char c: chars) {
            if (c < 'a') { // 大写字    也可以写成 >= 'A' && <= 'Z'
                cap++;
            }
        }
        if (cap == len) { // 都是大写
            return true;
        }
        if (cap == 1 && chars[0] < 'a') { // 只有首字母为大写
            return true;
        }
        return cap == 0; // 都是小写 或者出错
    }
}
