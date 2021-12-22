/*
Easy
#Enumerate, #String
 */
package lintcode;

/**
 * 936 · Capitalizes The First Letter
 *
 * Given a sentence of English, update the first letter of each word to uppercase.
 * - The given sentence may not be a grammatical sentence.
 * - The length of the sentence does not exceed 100.
 * - Except for the beginning of the sentence, the rest of the letters are all lowercase
 *
 * Example1
 * Input: s =  "i want to get an accepted"
 * Output: "I Want To Get An Accepted"
 *
 * Example2
 * Input: s =  "i jidls    mdijf  i  lsidj  i p l   "
 * Output: "I Jidls    Mdijf  I  Lsidj  I P L   "
 */
public class _0936_CapitalizesFirstLetter {

    /**
     * 如果前一位字符为空格, 且当前字符不为空格, 将其大写
     *
     * 别忘了 i++
     */
    public String capitalizesFirst_2(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] chars = s.toCharArray();
        int i = 0, n = chars.length;

        // 跳过开头的空格
        while (i < n && chars[i] == ' ') {
            i++; // 别忘了 i++
        }

        // 处理第一位非空格字母 - 特殊情况
        if (i < n) {
            chars[i] = Character.toUpperCase(chars[i]);
            i++; // 别忘了 i++
        }

        // 之后, 只有当前一字符为空格, 且当前字符不为空格时, 将其大写
        while (i < n) {
            if (chars[i - 1] == ' ' && chars[i] != ' ') {
                chars[i] = Character.toUpperCase(chars[i]);
                i++; // 别忘了 i++
            }
        }

        return String.valueOf(chars);

    }

    /**
     * 使用一个 flag, 检查当前字符是否是首位.
     */
    public String capitalizesFirst(String s) {
        char[] chars = s.toCharArray();
        boolean isFirst = true;

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c != ' ') {
                if (isFirst) {
                    isFirst = false;
                    chars[i] = Character.toUpperCase(c);
                }
            } else {
                isFirst = true;
            }
        }

        return String.valueOf(chars);
    }
}
