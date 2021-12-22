/*
Easy
#String
 */
package lintcode;

/**
 * 1535 · To Lower Case
 *
 * Implement function ToLowerCase() that has a string parameter str.
 * And convert the uppercase letters in the string to lowercase letters, and then return the new string.
 *
 * Example 1:
 * Input: "Hello"
 * Output: "hello"
 *
 * Example 2:
 * Input: "here"
 * Output: "here"
 *
 * Example 3:
 * Input: "LOVELY"
 * Output: "lovely"
 */
public class _1535_ToLowerCase {

    // 可以用 StringBuilder 或者 char array

    public String toLowerCase(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.setCharAt(i, (char) (c - 'A' + 'a'));
            }
        }

        return sb.toString();
    }
}
