/*
Medium
String
Amazon, Google, LinkedIn
 */
package lintcode;

import java.util.*;

/**
 * 1592. Find and Replace Pattern
 * You have a list of words and a pattern, and you want to know which words
 * in words matches the pattern.
 *
 * A word matches the pattern if there exists a permutation of letters p
 * so that after replacing every letter x in the pattern with p(x), we get
 * the desired word.
 *
 * (Recall that a permutation of letters is a bijection from letters to letters:
 * every letter maps to another letter, and no two letters map to the same letter.)
 *
 * Return a list of the words in words that match the given pattern.
 *
 * You may return the answer in any order.
 *
 * 1 <= words.length <= 50
 * 1 <= pattern.length = words[i].length <= 20
 *
 * Example 1:
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["aqq","mee"]
 * Explanation:
 * "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
 * since a and b map to the same letter.
 *
 * Example 2:
 * Input: words = ["a","b","c"], pattern = "a"
 * Output: ["a","b","c"]
 * Explanation:
 * All strings match.
 */
public class _1592_FindReplacePattern {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (match(word, pattern)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private boolean match(String word, String pattern) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char c1 = word.charAt(i);
            char c2 = pattern.charAt(i);
            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) {
                    return false;
                }
            } else {
                if (map.containsValue(c2)) {
                    return false;
                } else {
                    map.put(c1, c2);
                }
            }
        }

        return true;
    }

    /* ~~~~~ */

    public List<String> findAndReplacePattern_CompareArray(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        int[] patternArray = getPatternArray(pattern);
        for (String word : words) {
            int[] wordArray = getPatternArray(word);
            if (Arrays.equals(patternArray, wordArray)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private int[] getPatternArray(String s) {
        int len = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int[] patternArray = new int[len];
        int count = 1;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, count++);
            }
            patternArray[i] = map.get(c);
        }
        return patternArray;
    }
}
