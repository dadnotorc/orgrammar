package leetcode;

/**
 * 758. Bold Words in String
 * Easy
 * #Prime
 * Google
 *
 * Given a set of keywords words and a string S, make all appearances of all keywords in S bold.
 * Any letters between <b> and </b> tags become bold.
 *
 * The returned string should use the least number of tags possible,
 * and of course the tags should form a valid combination.
 *
 * For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d".
 * Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.
 *
 * Note:
 * - words has length in range [0, 50].
 * - words[i] has length in range [1, 10].
 * - S has length in range [0, 500].
 * - All characters in words[i] and S are lowercase letters.
 *
 * lintcode 812
 */
public class _0758_Bold_Words_in_String {

    /*
    使用 boolean array 记录 哪个下标应当被加粗

    对字典里每个字, 找到其出现的起始下标, 并将其对应的下标标记加粗

    最后再遍历字符串 S, 添加 tag
     */

    public String boldWords(String[] words, String S) {

        boolean[] beBold = new boolean[S.length()];

        // 根据 keyword, 觉得 S 中哪些下标需要加黑
        for (String word : words) {
            int index = 0;

            while ((index = S.indexOf(word, index)) >= 0) {
                for (int i = 0; i < word.length(); i++) {
                    beBold[index + i] = true;
                }

                index++;
            }
        }

        /* 前一个 for 可以简写成
        for (int i = S.indexOf(word); i >= 0; i = S.indexOf(word, i + 1)) {
            Arrays.fill(beBold, i, i + word.length(), true);
        }*/

        StringBuilder sb = new StringBuilder(S); // 以 S 做底

        if (beBold[S.length() - 1]) {
            sb.append("</b>");
        }

        for (int i = S.length() - 1; i > 0; i--) { // 到 第二位就结束
            if (beBold[i] && !beBold[i - 1]) {
                sb.insert(i, "<b>");
            } else if (!beBold[i] && beBold[i - 1]) {
                sb.insert(i, "</b>");
            }

            // 到 i 与 i-1 位相同时, 即可直接跳过
        }

        if (beBold[0]) {
            sb.insert(0, "<b>");
        }

        return sb.toString();
    }
}
