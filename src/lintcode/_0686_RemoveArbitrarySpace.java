/*
Easy
#String
 */
package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 686 · Remove Arbitrary Space
 *
 * Remove arbitrary spaces from a sentence
 *
 * Example 1:
 * Input: s = "The  sky   is blue"
 * Output: "The sky is blue"
 *
 * Example 2:
 * Input: s = "  low               ercase  "
 * Output: "low ercase"
 *
 *
 * 注意特殊情况, s = "    ", expected = ""
 */
public class _0686_RemoveArbitrarySpace {

    /**
     * 1. 用 String API, trim() 和 split(), 读取字符串中的有效单词 (去掉空格)
     * 2. 把文字按顺序加入新 StringBuilder
     * 3. 注意空格, 除了最后一位, 其他地方要加空格
     */
    public String removeExtra(String s) {
        if (s == null || s.length() == 0) { return s; }

        String[] words = s.trim().split("\\s+"); // 注意 不能 split(" "), 而应该用 regex \\s+
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    /**
     * 用 list 记录遇到的有效单词
     * 1. 把字符串转成 char array, 读取每个 char
     *    - 如果当前 char 不为空格 -> 加入 sb
     *    - 如果当前 char 是空格
     *       - 如 sb 不空, 即有单词在内, 此处的空格表示单词完结 -> 将单词加入 list, sb 清空
     *       - 如 sb 为空, 即此空格多余, 跳过即可
     *
     * 2. 把 list 中单词加回 sb 时, 需要注意
     *    - 如果 list 不为空, 最后一位单词结尾不要加空格
     *    - 如果 list 为空, 即没有任何单词, 别去直接删除 sb 最后一位, 要做特判. 特别要注意!
     */
    public String removeExtra_2(String s) {
        if (s == null || s.length() == 0) { return s; }

        List<String> words = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (sb.length() != 0) { // 当前单词结束
                    words.add(sb.toString());
                    sb.setLength(0); // 清零  也可以用 sb = new StringBuilder(), 但是后者会明显较慢
                }
                // 如 sb 为空, 说明未遇到有效单词, 则跳过
            } else {
                sb.append(c);
            }
        }

        if(sb.length() != 0) { // 别忘了 可能还有一个单词
            words.add(sb.toString());
            sb.setLength(0);
        }

        for (String word : words) {
            sb.append(word).append(' ');
        }
        if (sb.length() != 0) { // 这里要特别小心! 如果 s = "   ", 则此时 sb 为空
            sb.deleteCharAt(sb.length() - 1); // 去掉多余的空格
        }

        // 或者写成下面的形式
//        for (int i = 0; i < words.size(); i++) {
//            if (i != 0) {
//                sb.append(" ");
//            }
//            sb.append(words.get(i));
//        }

        return sb.toString();
    }
}
