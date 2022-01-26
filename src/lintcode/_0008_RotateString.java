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
     * 用两个临时变量
     * 变量 1 - 记录当前用于交换的值
     * 变量 2 - 记录将被取代的对象, 也就是下一次用于交换的值
     *
     * 两个指针
     * 指针 1 - 指去当前要处理的位置
     * 指针 2 - 指去已经处理过的位置
     *
     * 一般情况 - 变量 2 记录将被交换的值, 然后将其所在位置用变量 1 取代
     *
     * 特殊情况 - 如果 当前位置的值已被换走, 则将指针 2 移动到下一位
     *
     * 时间 O(n) 遍历 1 次数组 - 比 3 次翻转更快
     * 空间 O(1)
     */
    public void rotateString_1(char[] str, int offset) {
        if (str == null || str.length == 0 || offset % str.length == 0) {
            return;
        }

        int n = str.length;
        offset %= n;

        int curIndex = 0;
        int prevIndex = 0;

        char curTmp;
        char nextTmp = str[0];

        for (int i = 0; i < n; i++) {
            curTmp = nextTmp;
            curIndex = (curIndex + offset) % n;

            if (curIndex == prevIndex) { // "abccba", offset = 3
                // 这里不先做 nextTmp = str[curIndex]; 是因为 当前的值已经被挪走了.
                str[curIndex] = curTmp;

                curIndex++;
                prevIndex++;
                nextTmp = str[curIndex];
            } else { // "abcdefg", offset = 3
                nextTmp = str[curIndex];
                str[curIndex] = curTmp;
            }
        }
    }



    /**
     * 针对这种题的特殊解法, 分割 反转 再反转 - 可以做到 in-place
     * [a,b,c,d,e,f,g], k = 3
     * 先划分成    [a,b,c,d] [e,f,g]
     * 反转成      [d,c,b,a] [g,f,e]
     * 合起来再反转 [e,f,g,a,b,c,d]
     *
     * 时间 O(n) 遍历两次数组
     * 空间 O(1)
     */
    public void rotateString_2(char[] str, int offset) {
        if (str == null || str.length == 0 || offset % str.length == 0) {
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
     * 暴力解法 - 时间 O(n^2), 空间 O(1)
     * 获得 offset % n 之后, 向右平移 offset 次 (使用 System.arraycopy)
     */
    public void rotateString(char[] str, int offset) {
        if (str == null || str.length == 0 || offset % str.length == 0) {
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
