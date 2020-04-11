/*
Easy
#String, #Mathematics
Google
 */
package lintcode;

/**
 * 1017. Similar RGB Color
 *
 * In the following, every capital letter represents some hexadecimal digit from 0 to f.
 *
 * The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand.
 * For example, "#15c" is shorthand for the color "#1155cc".
 *
 * Now, say the similarity between two colors "#ABCDEF" and "#UVWXYZ" is
 * -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2.
 *
 * Given the color "#ABCDEF", return a 7 character color that is most similar to #ABCDEF,
 * and has a shorthand (that is, it can be represented as some "#XYZ")
 *
 * Notice
 * - color is a string of length 7.
 * - color is a valid RGB color: for i > 0, color[i] is a hexadecimal digit from 0 to f
 * - Any answer which has the same (highest) similarity as the best answer will be accepted.
 * - All inputs and outputs should use lowercase letters, and the output is 7 characters.
 *
 * Example 1:
 * Input: color = "#09f166"
 * Output: "#11ee66"
 * Explanation:
 * The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
 * This is the highest among any shorthand color.
 *
 * Example 2:
 * Input: color = "#010000"
 * Output: "#000000"
 * Explanation:
 * The similarity is -(0x01 - 0x00)^2 -(0x00 - 0x00)^2 - (0x00 - 0x00)^2 = -1 -0 -0 = -1.
 * This is the highest among any shorthand color.
 */
public class _1017_SimilarRGBColor {

    /**
     * 速度更快
     */
    public String similarRGB_2(String color) {
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for (int i = 1; i < color.length(); i += 2) {
            sb.append(getNearestHex(color.substring(i, i + 2)));
        }
        return sb.toString();
    }

    private String getNearestHex(String hexVal) {
        int decVal = Integer.parseInt(hexVal, 16);
        int hexIndex = (decVal / 17) + (decVal % 17 > 8 ? 1 : 0);

        // todo 读读 string format
        return String.format("%02x", hexIndex * 17);
    }



    /**
     * 十六位进制中类似AA,BB的数, 皆为17的倍数. 例如 hex 55 = decimal 85
     * 每两个字符代表一个十六位进制, 我们要找最接近该数字, 且形式类似AA,BB
     */
    public String similarRGB(String color) {
        // assume color will always be in the format of #xxxxxx
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for (int i = 1; i < color.length(); i += 2) {

            char c1 = color.charAt(i);
            char c2 = color.charAt(i + 1);

            // d1, d2 十六位进制字符对应的数字 0 ~ 15
            int d1 = c1 <= '9' ? c1 - '0' : 10 + (c1 - 'a');
            int d2 = c2 <= '9' ? c2 - '0' : 10 + (c2 - 'a');
            // 也可以写成
            // int d1 = Character.isDigit(c1) ? c1 - '0' : 10 + (c1 - 'a');

            // 算出当前对应的十进制数值, 找出与其距离最近的17的倍数 (形式为AA的十六位进制数字)
            int sum = d1 * 16 + d2;
            int hexIndex = sum / 17;
            int reminder = sum % 17;
            if (reminder > 8) { // 8 = 17 / 2, 超过一半, 则更靠近下一位
                hexIndex++;
            }

            // 将数字形式的 (hexIndex hexIndex) 转成用0~f表示的十六位进制
            char c = hexIndex > 9 ? (char)('a' + (hexIndex - 10)) : (char)('0' + hexIndex);
            sb.append(c);
            sb.append(c);
        }
        return sb.toString();
    }
}
