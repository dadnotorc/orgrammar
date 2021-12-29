/*
Easy
#Simulation, #String
Google
 */
package lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 1214 · License Key Formatting
 *
 * You are given a license key represented as a string S which consists only alphanumeric character and dashes.
 * The string is separated into N+1 groups by N dashes.
 *
 * Given a number K, we would want to reformat the strings such that each group contains exactly K characters,
 * except for the first group which could be shorter than K, but still must contain at least one character.
 * Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.
 *
 * Given a non-empty string S and a number K, format the string according to the rules described above.
 *
 * - The length of string S will not exceed 12,000, and K is a positive integer.
 * - String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
 * - String S is non-empty.
 *
 * Example
 * Input: S = "5F3Z-2e-9-w", K = 4
 * Output: "5F3Z-2E9W"
 * Explanation: The string S has been split into two parts, each part has 4 characters.
 * Note that the two extra dashes are not needed and can be removed.
 *
 * Input: S = "2-5g-3-J", K = 2
 * Output: "2-5G-3J"
 * Explanation: The string S has been split into three parts, each part has 2 characters
 * except the first part as it could be shorter as mentioned above.
 */
public class _1214_LicenseKeyFormatting {

    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (c == '-') {
                continue;
            } else if (c >= 'a' && c <= 'z') {
                c = Character.toUpperCase(c);
            }
            sb.append(c);
        }

        // 最初的想法是从末尾开始递减, 每过 K 个字母加一次 '-'
        // 每次 -1 不如 每次 -K, 也不需要判断是否经过 K 个字母了
        int index = sb.length() - K;
        while (index > 0) {
            sb.insert(index, '-');
            index -= K;
        }

        return sb.toString();
    }

    @Test
    public void test1() {
        String s = "5F3Z-2e-9-w";
        org.junit.Assert.assertEquals("5F3Z-2E9W", licenseKeyFormatting(s, 4));
    }
}
