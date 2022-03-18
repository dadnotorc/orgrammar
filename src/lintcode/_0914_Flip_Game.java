package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 914. Flip Game
 * Easy
 * #String
 * Google
 * FAQ++
 *
 * You are playing the following Flip Game with your friend:
 * Given a string that contains only two characters: + and -,
 * you can flip two consecutive "++" into "--", you can only flip one time.
 * Please find all strings that can be obtained after one flip.
 *
 * Write a program to find all possible states of the string after one valid move.
 *
 * Example1
 * Input:  s = "++++"
 * Output:
 * [
 *   "--++",
 *   "+--+",
 *   "++--"
 * ]
 *
 * Example2
 * Input: s = "---+++-+++-+"
 * Output:
 * [
 * 	"---+++-+---+",
 * 	"---+++---+-+",
 * 	"---+---+++-+",
 * 	"-----+-+++-+"
 * ]
 */
public class _0914_Flip_Game {

    /**
     * 转成char[], 每次查看两位, 例如 [0][1] 或者 [1][2] 或者 [2][3]
     *
     * 易错点:
     * 1. 将char[]转回String的时候, 不能用chars.toString(), 而应该用new String(chars)
     *    否则返回值将会类似"[C@7f31245a"
     *
     * time: O(2 * n), space: O(n)
     */
    public List<String> generatePossibleNextMoves_2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() < 2)
            return ans;

        char[] chars = s.toCharArray();
        helper(chars, ans);
        return ans;
    }

    private void helper(char[] chars, List<String> ans) {
        int i = 0, j = 1;

        while (j < chars.length) {
            if (chars[i] == '+' && chars[j] == '+') {
                chars[i] = '-';
                chars[j] = '-';
                ans.add(new String(chars)); // 不能使用toString,
                chars[i] = '+';
                chars[j] = '+';
            }
            i++;
            j++;
        }
    }



    /**
     * 九章解法 - 使用 String indexOf 加上 subtring
     */
    public List<String> generatePossibleNextMoves(String s) {
        List<String> ans = new ArrayList<>();
        // String indexOf(String str, int strt):
        // This method returns the index within this string of the first occurrence of the specified substring,
        // starting at the specified index. If it does not occur, -1 is returned.
        for (int i = -1; (i = s.indexOf("++", i + 1)) >= 0; ) {
            ans.add(s.substring(0, i) + "--" + s.substring(i + 2));
        }
        return ans;
    }
}
