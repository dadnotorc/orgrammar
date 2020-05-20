/*
Medium
#Two Pointers, #Sliding Window
 */
package leetcode;

/**
 * 567. Permutation in String
 *
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
 * In other words, one of the first string's permutations is the substring of the second string.
 *
 * Example 1:
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 *
 * Example 2:
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 *
 * Note:
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 */
public class _0567_PermutationInString {

    /**
     * slide window
     */
    public boolean checkInclusion(String s1, String s2) {
        int l = 0, r = 0;
        int matchSize = s1.length();
        int[] map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            map[s1.charAt(i) - 'a']++;
        }

        while (r < s2.length()) {
            if (map[s2.charAt(r) - 'a'] > 0) {
                matchSize--;
            }
            map[s2.charAt(r) - 'a']--;

            if (r - l + 1 == s1.length()) { // 到达 window size
                if (matchSize == 0) {
                    return true;
                }

                if (map[s2.charAt(l) - 'a'] >= 0) {
                    matchSize++;
                }
                map[s2.charAt(l) - 'a']++;

                l++;
            }

            r++;
        }

        return false;
    }
}
