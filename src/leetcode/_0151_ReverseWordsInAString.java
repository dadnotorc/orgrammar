/*
Medium
#String
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 151. Reverse Words in a String
 *
 * Given an input string, reverse the string word by word.
 *
 * Example 1:
 * Input: "the sky is blue"
 * Output: "blue is sky the"
 *
 * Example 2:
 * Input: "  hello world!  "
 * Output: "world! hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 *
 * Example 3:
 * Input: "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 * Note:
 * - A word is defined as a sequence of non-space characters.
 * - Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 * - You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 * Follow up:
 * - For C programmers, try to solve it in-place in O(1) extra space.
 */
public class _0151_ReverseWordsInAString {

    /**
     * 易错点:
     * 1. 遍历字符时, 遇到' '时, 只有当sb中有字符, 才将其加入list中.
     *    因为如果是连续' ', sb里是空的, 此时不应该加
     * 2. 遍历完字符时, sb里可能仍有最后一个word, 还没加入到list中, 别忘了
     */
    public String reverseWords(String s) {

        if (s == null) { return null; }

        // 注意 不能做这个特判, 不然会误判 input = " "的情况, output 应该 = ""
        //if (s.length() < 2) { return s; }

        // 不需要trim, 因为下面的for循环里处理了空格的情况
        //s = s.trim();

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                // 遇到连续的空格时, 应该跳过, 此时sb里为空
                if (sb.length() != 0) {
                    list.add(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                sb.append(c);
            }
        }

        // for循环做完时, sb可能保留最后一个word, 别忘了加上
        if (sb.length() != 0) {
            list.add(sb.toString());
        }

        sb = new StringBuilder();
        // 反向加
        for (int i = list.size() - 1; i >= 0; i--) {
            if (i != list.size() - 1) {
                sb.append(" ");

            }
            sb.append(list.get(i));
        }



        return sb.toString();
    }


    /**
     * 易错点:
     * 1. string comparison 应该用 .equal(), 而不是 ==
     * 2. 先添加" ", 后加入单词, 保证每次循环都是单词结尾, 而不是空格结尾
     *    因为此种做法, words 可能等于 ["", "", "a", "word"], 即words[0].equals("")
     *    所以如果先加单词, 后判断是否加空格, 可能导致结尾多一个空格. 如上例, 可能等于 "word a ", 而不是"word a"
     */
    public String reverseWords_api_2(String s) {
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if (words[i].equals("")) { // 注意, 这里不能用 ==, 而应该用 .equal()
                continue;
            }

            if (i != words.length - 1) {
                sb.append(" ");
            }

            sb.append(words[i]);

            // 先添加" ", 后加入单词, 保证每次循环都是单词结尾, 而不是空格结尾
            //sb.append(words[i]).append(i == 0 ? "" : " ");
        }
        return sb.toString();
    }


    /**
     * 使用String api - trim, split.
     * regex使用 \s+ 表示去掉1个或者多个空格
     */
    public String reverseWords_api(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(i == 0 ? "" : " ");
        }
        return sb.toString();
    }
}
