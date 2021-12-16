/*
Naive
#String

 */
package lintcode;

/**
 * 145 · Lowercase to Uppercase
 *
 * Convert a lowercase character to uppercase.
 * - You can assume that the input is always lowercase.
 *
 * Example 1:
 * Input: 'a'
 * Output: 'A'
 *
 * Example 2:
 * Input: 'b'
 * Output: 'B'
 */
public class _0145_LowercasetoUppercase {

    public char lowercaseToUppercase_1(char character) {
        return (char) (character - 'a' + 'A'); // 后面的括号 不能省略
    }

    public char lowercaseToUppercase(char character) {
        return Character.toUpperCase(character);
    }
}
