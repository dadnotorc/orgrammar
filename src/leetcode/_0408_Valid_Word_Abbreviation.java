package leetcode;

/**
 * 408. Valid Word Abbreviation
 * Easy
 * #Prime
 * Facebook meta, Google
 *
 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
 *
 * A string such as "word" contains only the following valid abbreviations:
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * Notice that only the above abbreviations are valid abbreviations of the string "word".
 * Any other string is not a valid abbreviation of "word".
 *
 * Note:
 * Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
 *
 * Example 1:
 * Given s = "internationalization", abbr = "i12iz4n":
 * Return true.
 *
 * Example 2:
 * Given s = "apple", abbr = "a2e":
 * Return false.
 *
 * 等同 lintcode 637
 */
public class _0408_Valid_Word_Abbreviation {

    /* 要跟面试官确认的地方
    1. s 是不是只包含 小写字母
    2. abbr 是不是只包含 小写字母 与 数字
    3. "0" 或者 "01", 以 0 开头的 是否合法, 如何处理

    此题中, 以 0 开头的 都不合法. 例如 word = "aa", abbr = "a0a", exp = false
     */


    /* 之前犯过的错
    1. abbr 中出现的数字不一定只有1位, 遇到数字后, 要继续看下一位, 直到数字读完才能移动 word 指针
    2. 检查 abbr 下一位是否是数字时, 别忘了检查是否越界
    3. abbr 中, "0" 或者以 0 开头的数字, 例如 "03", 都是错误的
     */

    /**
     * 第二版
     */
    public boolean validWordAbbreviation_2(String word, String abbr) {
        if (word == null || word.length() == 0 || abbr == null || abbr.length() == 0) {
            return false;
        }

        int w_index = 0, a_index = 0;

        while (w_index < word.length() && a_index < abbr.length()) {
            char a_char = abbr.charAt(a_index);
            char w_char = word.charAt(w_index);

            if (a_char == w_char) {
                a_index++;
                w_index++;
            } else if (a_char > '0' && a_char <= '9') { // 注意 不要 == 0, 因为不能以 0 开头
                int num = a_char - '0';
                a_index++;

                while (a_index <abbr.length() && Character.isDigit(abbr.charAt(a_index))) { // 别忘了检查越界
                    num = num * 10 + (abbr.charAt(a_index) - '0');
                    a_index++;
                }

                w_index += num;

            } else {
                // 这里包含:
                // 1. 都不是数字, 且不相同
                // 2. abbr中 含有 "0" 或者 以 0 开头的数字, 例如 "03"
                return false;
            }
        }

        return w_index == word.length() && a_index == abbr.length();
    }




    /**
     * 第一版
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        if (word == null || word.length() == 0 || abbr == null || abbr.length() == 0) {
            return false;
        }

        int w_index = 0, a_index = 0;

        while (w_index < word.length() && a_index < abbr.length()) {
            char a_char = abbr.charAt(a_index);
            char w_char = word.charAt(w_index);

            if (Character.isDigit(a_char)) { // 之前的错误点 1 - abbr 遇到数字就直接移动 word 指针. 应当继续读, 直到数字读完
                int num = a_char - '0';

                if (num == 0) {  // 错误点 3 - "0" 或者 以 0 开头的数字, 例如 "03", 都是错误的
                    return false;
                }

                a_index++;

                while (a_index <abbr.length() && Character.isDigit(abbr.charAt(a_index))) { // 错误点 2 - 别忘了检查 abbr 指针是否越界
                    num = num * 10 + (abbr.charAt(a_index) - '0');
                    a_index++;
                }

                w_index += num;

            } else {
                if (a_char != w_char) {
                    return false;
                }
                w_index++;
                a_index++;
            }
        }

        return w_index == word.length() && a_index == abbr.length();
    }



}
