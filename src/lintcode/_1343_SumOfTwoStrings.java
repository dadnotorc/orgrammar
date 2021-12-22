/*
Easy
#String
Quora
 */
package lintcode;

/**
 * 1343 · Sum of Two Strings
 *
 * Given you two strings which are only contain digit character.
 * You need to return a string spliced by the sum of the bits.
 * - A and B are strings which are composed of numbers
 *
 * Example1:
 * Input: A = "99", B = "111"
 * Output: "11010"
 * Explanation: because 9 + 1 = 10, 9 + 1 = 10, 0 + 1 = 1,connect them，so answer is "11010"
 *
 * Example2:
 * Input: A = "2", B = "321"
 * Output: "323"
 * Explanation: because 2 + 1 = 3, 2 + 0 = 2, 3 + 0 = 3, connect them，so answer is "323"
 */
public class _1343_SumOfTwoStrings {

    public String SumofTwoStrings(String A, String B) {
        if (A == null || A.length() == 0) { return B; }
        if (B == null || B.length() == 0) { return A; }

        StringBuilder sb = new StringBuilder();

        int i = A.length() - 1, j = B.length() - 1;
        for (; i >= 0 && j >= 0; i--, j--) {
            sb.insert(0, (A.charAt(i) - '0') + (B.charAt(j) - '0'));
        }

        while (i >= 0) {
            sb.insert(0, A.charAt(i--));
        }
        while (j >= 0) {
            sb.insert(0, B.charAt(j--));
        }

        return sb.toString();
    }
}
