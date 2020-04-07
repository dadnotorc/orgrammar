/*
Medium
#String
Amazon, Microsoft, Uber
 */
package lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 927. Reverse Words in a String II
 *
 * Given an input character array, reverse the array word by word.
 * A word is defined as a sequence of non-space characters.
 *
 * The input character array does not contain leading or trailing spaces
 * and the words are always separated by a single space.
 *
 * Example1
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 *
 * Example2
 * Input: "a b c"
 * Output: "c b a"
 *
 * Challenge
 * - Could you do it in-place without allocating extra space?
 */
public class _0927_ReverseWordsInAString2 {

    /**
     * 先将每个词组反转, 最后将整个char array反转. in-place, no extra space
     *
     * 易错点:
     * 1. l指向要反转部分的首位, r指向要反转部分的下一位 (r-1为末位)
     */
    public char[] reverseWords(char[] str) {
        if (str == null || str.length < 3) // <3 - 因为长度为1或者2的话, 只能是一个词, 不可能包含空格
            return str;

        int l = 0;

        for (int r = 1; r <= str.length; r++) {
            if (r == str.length || str[r] == ' ') { // 注意顺序, 先判断是否越界, 后判断内容(保证不越界)
                swap(str, l, r - 1);
                l = r + 1;
            }
        }

        swap(str, 0, str.length - 1);
        return str;
    }

    private void swap(char[] str, int l, int r) {
        while (l < r) {
            char temp = str[l];
            str[l] = str[r];
            str[r] = temp;
            l++;
            r--;
        }
    }


    /**
     * 用list反向记录词组
     *
     * 易错点:
     * 1. 在list中加词的时候, 要反向 (每次加到0位)
     * 2. 第一个for循环读取str中词组完成后, 别忘了将最后一个词加入list中
     * 3. 用list中词组组成char array时, 记得最后一个词之后没用空格
     */
    public char[] reverseWords_2(char[] str) {
        if (str == null || str.length < 3)
            return str;

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= str.length; i++) { // <= - 因为要读取词组之后的" ", 或者char array的边界
            if (i == str.length || str[i] == ' ') {
                list.add(0, sb.toString()); // 要反向加
                sb = new StringBuilder();
            } else {
                sb.append(str[i]);
            }
        }

        for (String word : list) {
            sb.append(word + " ");
        }

        sb.deleteCharAt(sb.length() - 1); // 删除多余的" "

        return sb.toString().toCharArray();



//        List<String> list = new ArrayList<>();
//
//        char[] ans = new char[str.length];
//
//        StringBuilder sb = new StringBuilder();
//
//        for (char c : str) {
//            if (c == ' ') {
//                list.add(0, sb.toString());
//                sb = new StringBuilder();
//            } else {
//                sb.append(c);
//            }
//        }
//
//        list.add(0, sb.toString());
//
//        int index = 0;
//        for (int i = 0; i < list.size(); i++) {
//            for (char c : list.get(i).toCharArray()) {
//                ans[index++] = c;
//            }
//            if (index < ans.length) {
//                ans[index++] = ' ';
//            }
//        }
//
//        return ans;
    }

    @Test
    public void test1() {
        char[] in = "the sky is blue".toCharArray();
        char[] exp = "blue is sky the".toCharArray();
        org.junit.Assert.assertArrayEquals(exp, reverseWords(in));
    }
}
