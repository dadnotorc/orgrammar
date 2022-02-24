package leetcode;

import org.junit.Test;

/**
 * 6. Zigzag Conversion
 * Medium
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 * Example 1:
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 * Example 2:
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * Example 3:
 * Input: s = "A", numRows = 1
 * Output: "A"
 *
 * Constraints:
 * 1 <= s.length <= 1000
 * s consists of English letters (lower-case and upper-case), ',' and '.'.
 * 1 <= numRows <= 1000
 */
public class _0006_Zigzag_Conversion {

    /*
    0         10      i, i + (2 x numRows - 2)  减2 是去掉首位的两行
    1       9 11
    2     8   12      中间这些
    3   7     13      i, i + (2 x numRows - 2) - (curRow x 2), i + (2 x numRows - 2)
    4 6       14
    5         15

    注意 numRows 可以等于 1
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) { return s; } // 没有这个特判的话, 后面 2 * numRows - 2 会导致死循环

        StringBuilder sb = new StringBuilder();
        int n = s.length();

        for (int i = 0; i < numRows; i++) {
            int j = i;

            while (j < n) {
                sb.append(s.charAt(j));
                j += 2 * numRows - 2;

                if (i != 0 && i != numRows - 1) { // 注意 后者别写成 n - 1
                    int k = j - 2 * i;
                    if (k < n) { // 别忘了检查越界
                        sb.append(s.charAt(k));
                    }
                }
            }
        }

        return sb.toString();
    }




    @Test
    public void test1() {
        String s = "PAYPALISHIRING";
        int n = 3;
        String out = convert(s, n);
        String exp = "PAHNAPLSIIGYIR";

        org.junit.Assert.assertEquals(exp, out);
    }
}
