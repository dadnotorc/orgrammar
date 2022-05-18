package lintcode;

import java.util.Arrays;

/**
 * 310 · Digital distortion
 * Easy
 * #String
 * Tesla
 *
 * Now the question gives a string number A.
 * The string number B is a deformation of A, formed by alternating the digits of the string number A.
 *
 * In order, it is the first digit of the right digit of A,
 * the first digit of the left digit,
 * the second digit of the right digit .....
 * and so on to obtain the string number B.
 *
 * 0 <= A <= 1e200
 *
 * Example
 * input: "12345678"
 * output:"81726354"
 */
public class _0310_Digital_distortion {

    /**
     * 双指针, 分别指向首尾
     * 注意 - 字符串长度
     * - 长度为偶数时, 最后两位 l 与 r 相邻, 之后就不能再加
     * - 长度为奇数时, 最后一位 l == r, 要加入 l
     */
    public String distortion(String a) {
        if (a == null || a.length() < 2) { return a; }

        int l = 0, r = a.length() - 1;
        StringBuilder sb = new StringBuilder();

        while (l < r) {
            sb.append(a.charAt(r--));
            sb.append(a.charAt(l++));
        }

        if (l == r) {
            sb.append(a.charAt(l));
        }

        return sb.toString();
    }


    /**
     * 使用 char array 取代 StringBuilder
     */
    public String distortion_2(String a) {
        if (a == null || a.length() < 2) { return a; }

        int i = 0, n = a.length();
        int l = 0, r = n - 1;
        char[] chars = new char[n];

        while (i < n) {
            chars[i++] = a.charAt(r--);
            if (l < r) { // 注意 这里不能用 l != r. 因为如果时奇数, 最后一位 l = r, 完成上一步 r-- 后, 就已经 l > r 了. 所以要保证 l < r
                chars[i++] = a.charAt(l++);
            }
        }

        return String.valueOf(chars);

        // 不能这么写, 这样的结果是 "[1,2,3]", 而不是 "123"
        // return Arrays.toString(chars);
    }
}
