/*
Easy
#String
 */
package lintcode;

/**
 * 1283 · Reverse String
 *
 * Write a function that takes a string as input and returns the string reversed.
 *
 * Example 1：
 * Input："hello"
 * Output："olleh"
 *
 * Example 2：
 * Input："hello world"
 * Output："dlrow olleh"
 */
public class _1283_ReverseString {

    /**
     * StringBuilder 反向加入 字符串
     */
    public String reverseString(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }


    /**
     * 第二种方法 - 转成 char array, 双指针, 首位交换
     */
    public String reverseString_2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        char[] chars = s.toCharArray();
        int l = 0, r = chars.length - 1;

        while (l < r) {
            char tmp = chars[l];
            chars[l] = chars[r];
            chars[r] = tmp;
            l++;
            r--; // 别忘了移动指针
        }

        return String.valueOf(chars);
    }
}
