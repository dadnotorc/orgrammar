/*
Easy
#Two Pointers, #Stack
 */
package leetcode;

import org.junit.Test;

/**
 * 844. Backspace String Compare
 *
 * Given two strings S and T, return if they are equal when both are typed into empty text editors.
 * # means a backspace character.
 *
 * Example 1:
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 *
 * Example 2:
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 *
 * Example 3:
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 *
 * Example 4:
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 *
 * Note:
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 *
 * Follow up:
 * - Can you solve it in O(N) time and O(1) space?
 */
public class _0844_BackspaceStringCompare {

    /**
     * 从后往前读字符, 统计#数量:
     * 如果当前#数量为0, 表示继续扫描下去的非#字符为有效字符 -> 需要对比
     * 如果不为0, 表示下一位非#字符为无效字符 ->　skip
     */
    public boolean backspaceCompare_2(String S, String T) {

        int i = S.length() - 1, j = T.length() - 1; // 两根指针指向S, T的尾端

        int numPound = 0; // 统计#数量

        while (i >= 0 || j >= 0) { // 用 || 而不是 &&, 因为一条扫描完时, 另一条还剩无效字符, 例如test1
            // 先扫描S, 跳过无效字符
            numPound = 0;
            while (i >= 0 && (numPound > 0 || S.charAt(i) == '#')) {

//                if (S.charAt(i) == '#')
//                    numPound++;
//                else
//                    numPound--;

                numPound += S.charAt(i) == '#' ? 1 : -1; // 如果是#, 数量++; 否则, 数量--
                i--;
            }

            // 再扫面T, 跳过无效字符
            numPound = 0;
            while (j >= 0 && (numPound > 0 || T.charAt(j) == '#')) {
                numPound += T.charAt(j) == '#' ? 1 : -1;
                j--;
            }

            // 至少一条已扫完, 跳出循环, 比较下标
            if (i < 0 || j < 0) {
                break;
            }

            // 当前下标都未越界, 需要对比对应字符
            if (S.charAt(i) != T.charAt(j)) {
                return false;
            }

            // 当前字符相符 ->　别忘了向左移动下标, 继续扫描
            i--;
            j--;
        }

        // 已经扫描完至少一条字符串, 判断另一条是否也已扫描结束
        return i == j;
    }


    /**
     * 使用stack或者StringBuilder来获得去除#的final string
     *
     * 易错点:
     * 1. 字符串 .compareTo() 返回一个int, 而不是boolean
     */
    public boolean backspaceCompare(String S, String T) {
        String a = buildStr(S);
        String b = buildStr(T);

        return a.equals(b);
        //return (a.compareTo(b) == 0); // 注意compareTo返回的是int而不是boolean
    }

    private String buildStr(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c == '#') {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    @Test
    public void test1() {
        String S = "nzp#o#g";
        String T = "b#nzp#o#g";

        org.junit.Assert.assertTrue(backspaceCompare_2(S, T));
    }
}
