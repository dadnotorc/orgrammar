package leetcode;

import java.util.HashMap;

/**
 * 242. Valid Anagram
 * Easy
 *
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 5 * 10^4
 * s and t consist of lowercase English letters.
 *
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */
public class _0242_Valid_Anagram {

    /**
     *
     */
    public boolean isAnagram_2(String s, String t) {
        if (s.length() != t.length()) { return false; }

        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }

        for (int i : counts) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 用 hashmap 对这题有点overkill了
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) { return false; }

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }

        for (int i : map.values()) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }



    /**
     * 有 bug, 无法应对 s = "ac", t = "bb"
     */
    public boolean isAnagram_bug(String s, String t) {
        if (s.length() != t.length()) { return false; }
        int val = 0;

        for (int i = 0; i < s.length(); i++) {
            val += s.charAt(i);
            val -= t.charAt(i);
        }

        return val == 0;
    }
}
