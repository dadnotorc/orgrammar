/*
Naive
#Array, #String

 */
package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 25 · Print X
 *
 * Enter a positive integer 'N'. You need to return a list of strings as shown in the Example.
 * 1 <= n <= 15
 *
 * Example 1:
 * Input: n = 1
 * Output: ["X"]
 * Explanation: The answer list can be seen as the following shape:
 * X
 *
 * Example 2:
 * Input: n = 2
 * Output: ["XX", "XX"]
 * Explanation: The answer list can be seen as the following shape:
 * XX
 * XX
 *
 * Example 3:
 * Input: n = 3
 * Output: ["X X", " X ", "X X"]
 * Explanation: The answer list can be seen as the following shape:
 * X X
 *  X
 * X X
 *
 * Example 4:
 * Input: n = 4
 * Output: ["X  X", " XX ", " XX ", "X  X"]
 * Explanation: The answer list can be seen as the following shape:
 * X  X
 *  XX
 *  XX
 * X  X
 *
 * Example 5:
 * Input: n = 5
 * Output: ["X   X", " X X ", "  X  ", " X X ", "X   X"]
 * Explanation: The answer list can be seen as the following shape:
 * X   X
 *  X X
 *   X
 *  X X
 * X   X
 */
public class _0025_PrintX {

    /**
     * i = 0: X 在 0 与 n - 1 位
     * i = 1: X 在 1 与 n - 1 - 1 位
     * i = a: X 在 i 与 n - 1 - a 位
     *
     * 使用 StringBuilder
     *
     */
    public List<String> printX(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < n; j++) {
                if (j == i || j == n - 1 - i) {
                    sb.append('X');
                } else {
                    sb.append(' ');
                }
            }
            ans.add(sb.toString());
        }
        return ans;
    }

    /**
     * 使用 char array
     */
    public List<String> printX_2(int n) {
        List<String> ans = new ArrayList<>();
        char[] curLine = new char[n];
        for (int i = 0; i < n; i++) {
            // 这里不能这么写, 不然output会出现
            // ["X\u0000\u0000\u0000X","XX\u0000XX","XXXXX","XXXXX","XXXXX"]
//            for (char c : curLine) {
//                c = ' ';
//            }
            for (int j = 0; j < n; j++) {
                curLine[j] = ' ';
            }
            curLine[i] = 'X';
            curLine[n - 1 - i] = 'X';
            ans.add(String.valueOf(curLine));
        }
        return ans;
    }
}
