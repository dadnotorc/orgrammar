/*
Easy
#String, #Array
 */
package lintcode;

/**
 * 8 · Rotate String
 *
 * Given a string of char array and an offset, rotate the string by offset in place. (from left to right).
 * - offset >= 0
 * - the length of str >= 0
 * - In place means you should change strings in the function. You don't return anything.
 *
 * Example 1:
 * Input: str = ""abcdefg", offset = 3
 * Output: "efgabcd"
 *
 * Example 2:
 * Input: str = ""abcdefg", offset = 0
 * Output: "abcdefg"
 *
 * Example 3:
 * Input: str = ""abcdefg", offset = 1
 * Output: "gabcdef"
 *
 * Example 4:
 * Input: str = ""abcdefg", offset = 2
 * Output: "fgabcde"
 *
 * Example 5:
 * Input: str = ""abcdefg", offset = 10
 * Output: "efgabcd"
 *
 * 类似 lintcode 1334 · Rotate Array
 */
public class _0008_RotateString {

    /**
     * 翻转 3 次
     */
    public void rotateString_2(char[] str, int offset) {
        if (str == null ||str.length == 0 || offset == 0 || offset == str.length) {
            return;
        }

        int n = str.length;
        offset %= n;

        reverse(str, 0, n - 1 - offset);
        reverse(str, n - offset, n - 1);
        reverse(str, 0, n - 1);
    }

    public void reverse(char[] str, int l, int r) {
        for (int i = l, j = r; i < j; i++, j--) {
            char tmp = str[i];
            str[i] = str[j];
            str[j] = tmp;
        }
    }

    /**
     * 获得 offset % n 之后, 向右平移 offset 次
     * O(n^2)
     */
    public void rotateString(char[] str, int offset) {
        if (str == null ||str.length == 0 || offset == 0 || offset == str.length) {
            return;
        }

        int n = str.length;
        offset %= n;

        for (int i = 0; i < offset; i++) {
            // 每次集体右移动一位
            char last = str[n - 1];
            System.arraycopy(str, 0, str, 1, n - 1);
//            for (int j = n - 2; j >= 0; j--) {
//                str[j + 1] = str[j];
//            }
            str[0] = last;
        }
    }
}
