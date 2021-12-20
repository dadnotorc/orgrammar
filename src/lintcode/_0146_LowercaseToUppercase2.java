/*
Naive
#String

 */
package lintcode;

/**
 * 146 · Lowercase to Uppercase II
 *
 * Implement an upper method to convert all characters in a string to uppercase.
 * The characters not in alphabet don't need to convert.
 *
 * Example 1:
 * Input: str = "abc"
 * Output: "ABC"
 *
 * Example 2:
 * Input: str = "aBc"
 * Output: "ABC"
 *
 * Example 3:
 * Input: str = "abC12"
 * Output: "ABC12"
 */
public class _0146_LowercaseToUppercase2 {

    /**
     * 使用 char array
     */
    public String lowercaseToUppercase2_1(String letters) {
        if (letters == null || letters.length() == 0) {
            return letters;
        }

        char[] chars = letters.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                chars[i] -= 32;
            }
        }

        return String.valueOf(chars);
    }


    /**
     * 使用 StringBuilder
     */
    public String lowercaseToUppercase2_2(String letters) {
        if (letters == null || letters.length() == 0) {
            return letters;
        }

        StringBuilder sb = new StringBuilder(letters);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (Character.isLowerCase(c)) {
                sb.setCharAt(i, Character.toUpperCase(c));
            }
        }

        return sb.toString();
    }
}
