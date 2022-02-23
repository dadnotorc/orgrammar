package leetcode;

import org.junit.Test;

/**
 * 1427. Perform String Shifts
 * Easy
 * #Prime
 *
 * You are given a string s containing lowercase English letters,
 * and a matrix shift, where shift[i] = [direction, amount]:
 *
 * - direction can be 0 (for left shift) or 1 (for right shift).
 * - amount is the amount by which string s is to be shifted.
 * - A left shift by 1 means remove the first character of s and append it to the end.
 * - Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
 *
 * Return the final string after all operations.
 *
 * Example 1:
 * Input: s = "abc", shift = [[0,1],[1,2]]
 * Output: "cab"
 * Explanation:
 * [0,1] means shift to left by 1. "abc" -> "bca"
 * [1,2] means shift to right by 2. "bca" -> "cab"
 *
 * Example 2:
 * Input: s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
 * Output: "efgabcd"
 * Explanation:
 * [1,1] means shift to right by 1. "abcdefg" -> "gabcdef"
 * [1,1] means shift to right by 1. "gabcdef" -> "fgabcde"
 * [0,2] means shift to left by 2. "fgabcde" -> "abcdefg"
 * [1,3] means shift to right by 3. "abcdefg" -> "efgabcd"
 *
 * Constraints:
 *     1 <= s.length <= 100
 *     s only contains lower case English letters.
 *     1 <= shift.length <= 100
 *     shift[i].length == 2
 *     0 <= shift[i][0] <= 1
 *     0 <= shift[i][1] <= 100
 */
public class _1427_PerformStringShifts {

    /**
     * left shift和right shift会互相抵消, 所以先计算抵消后的移动方向及步数
     *
     * 易错点:
     * 1. loop完shift后, 需要做一次mod, 将movement数值控制在 (-strLen, strLen)之间.
     * 2. 如果movement为负值, 记得 +strLen 变正值 (后续会使用movement值做下标操作)
     * 3. 如果使用char[], 每一步查下标的动作也要做mod
     * 4. 如果使用substring, 下标要算对
     */
    public String stringShift(String s, int[][] shift) {

        int movement = 0; // 正数表示向右移动的步数, 负数表示向左移动的步数

        for (int[] eachStep : shift) {
//            if (eachStep[0] == 0) { // left shift
//                movement -= eachStep[1];
//            } else { // right shift
//                movement += eachStep[1];
//            }
            movement += eachStep[0] == 0 ? -eachStep[1] : eachStep[1];
        }

        int strLen = s.length();
        movement %= strLen; // 注意这里! 要多次mod
        if (movement < 0)
            movement += strLen;

//        char[] inputStrChars = s.toCharArray();
//        char[] outputStrChars = new char[strLen];
//        for (int i = 0; i < strLen; i++) {
//            outputStrChars[(i + movement) % strLen] = inputStrChars[i]; // 注意, 这里也需要mod
//        }
//
//        return new String(outputStrChars);

        return s.substring(strLen - movement) + s.substring(0, strLen - movement); // 记得下标要反过来
    }

    @Test
    public void test1() {
        String s = "abcdefg";
        int[][] shift = {{0, 25}};
        String output = stringShift(s, shift);
    }
}
