/*
Medium
#Hash Table, #Two Pointers, #Sliding Window
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. Find All Anagrams in a String
 *
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only
 * and the length of both strings s and p will not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * Example 1:
 * Input: s: "cbaebabacd" p: "abc"
 * Output: [0, 6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 * Example 2:
 * Input: s: "abab" p: "ab"
 * Output: [0, 1, 2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class _0438_FindAllAnagramsInAString {

    /**
     * slide window 解法
     */
    public List<Integer> findAnagrams_2(String s, String p) {
        int l = 0, r = 0;
        List<Integer> res = new ArrayList<>();
        int matchSize = p.length();
        int[] map = new int[26];
        for (int i = 0; i < matchSize; i++) {
            char c = p.charAt(i);
            map[c - 'a']++;
        }

        while (r < s.length()) {
            if (map[s.charAt(r) - 'a'] > 0) { // r所指的char存在于p中, 所以减少matchSize
                matchSize--;
            }
            map[s.charAt(r) - 'a']--;

            // 到达window size
            if (r - l + 1 == p.length()) {
                if (matchSize == 0) {
                    res.add(l);
                }

                if (map[s.charAt(l) - 'a'] >= 0) { // l所指的char也存在于p中, 所以增加matchSize
                    matchSize++;
                }
                map[s.charAt(l) - 'a']++;

                l++; // 别忘了移动左指针
            }

            r++;  // 别忘了移动右指针
        }

        return res;
    }


    /**
     * 暴力解法
     */
    public List<Integer> findAnagrams(String s, String p) {
        int[] pCounts = new int[26];
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            pCounts[c - 'a']++;
        }

        List<Integer> ans = new ArrayList<>();

        int l = 0;
        int[] count = pCounts.clone();
        while (l < s.length()) {
            if (count[s.charAt(l) - 'a'] > 0) {
                count[s.charAt(l) - 'a']--;
                int r = l + 1;
                int len = 1;
                while (r < s.length() && len < p.length()) {
                    if (count[s.charAt(r) - 'a'] > 0) {
                        count[s.charAt(r) - 'a']--;
                        len++;
                        r++;
                    } else {
                        break;
                    }
                }
                if (len == p.length()) {
                    ans.add(l);
                }
            }
            count = pCounts.clone();
            l++;
        }

        return ans;
    }


    // "abacbabc" & "abc"
}
