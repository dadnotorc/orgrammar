/*
Easy

Google
 */
package lintcode;

/**
 * 888. Valid Word Square
 *
 * Given a sequence of words, check whether it forms a valid word square.
 *
 * A sequence of words forms a valid word square if the k^th row and column
 * read the exact same string, where 0 ≤ k < max(numRows, numColumns).
 *
 * Notice
 * - The number of words given is at least 1 and does not exceed 500.
 * - Word length will be at least 1 and does not exceed 500.
 * - Each word contains only lowercase English alphabet a-z.
 *
 * Example1
 * Input:
 * [
 *   "abcd",
 *   "bnrt",
 *   "crmy",
 *   "dtye"
 * ]
 * Output: true
 * Explanation:
 * The first row and first column both read "abcd".
 * The second row and second column both read "bnrt".
 * The third row and third column both read "crmy".
 * The fourth row and fourth column both read "dtye".
 * Therefore, it is a valid word square.
 *
 * Example2
 * Input:
 * [
 *   "abcd",
 *   "bnrt",
 *   "crm",
 *   "dt"
 * ]
 * Output: true
 * Explanation:
 * The first row and first column both read "abcd".
 * The second row and second column both read "bnrt".
 * The third row and third column both read "crm".
 * The fourth row and fourth column both read "dt".
 * Therefore, it is a valid word square.
 *
 * Example3
 * Input:
 * [
 *   "ball",
 *   "area",
 *   "read",
 *   "lady"
 * ]
 * Output: false
 * Explanation:
 * The third row reads "read" while the third column reads "lead".
 * Therefore, it is NOT a valid word square.
 */
public class _0888_ValidWordSquare {

    /**
     * 比较 words[i].charAt(j) != words[j].charAt(i)
     *
     * 易错点:
     * 1. 第二个for loop里, 注意! j的边界是words[i].length()
     * 2. for loop中的if判断, 注意判断边界, 不要越界
     */
    public boolean validWordSquare(String[] words) {
        if (words.length != words[0].length()) // 列数 != 行数
            return false;

        for (int i = 0 ; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) { // 注意这里边界是 words[i].length()
                if (j >= words.length || i >= words[j].length() || words[i].charAt(j) != words[j].charAt(i)) {
                    // 如何理解? 因为要判断 words[i].charAt(j) != words[j].charAt(i)
                    // 等号左边的i和j由两个for loop保证不会越界
                    // 所以这里if需要判断等号右边i和j不越界
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * 先将String[] 转换成 char[][]
     *
     * 易错点:
     * 1. 第一个for循环里, 需要判断当前字符串的长度是否过长 words[i].length() vs words.length
     * 2. 在两层for循环里, 第二层j的边界是i
     */
    public boolean validWordSquare_2(String[] words) {

        int wordCount = words.length;

        char[][] chars = new char[wordCount][wordCount];

        for (int i = 0; i < wordCount; i++) {
            if (words[i].length() > wordCount) {
                return false;
            }
            chars[i] = words[i].toCharArray();
        }

        for (int i = 0; i < wordCount; i++) {
            for (int j = 0; j <= i; j++) { // 注意! 这里j的边界是i
                if (chars[i][j] != chars[j][i]) {
                    return false;
                }
            }
        }

        return true;
    }
}
