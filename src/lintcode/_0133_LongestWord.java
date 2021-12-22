/*
Easy
#Enumerate, #String, #Array
 */
package lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 133 · Longest Word
 *
 * Given a dictionary, find all of the longest words in the dictionary.
 *
 * Example 1:
 * 	Input: {
 * 		"dog",
 * 		"google",
 * 		"facebook",
 * 		"internationalization",
 * 		"blabla"
 *                }
 * 	Output: ["internationalization"]
 *
 *
 * Example 2:
 * 	Input:  {
 * 		"like",
 * 		"love",
 * 		"hate",
 * 		"yes"
 *        }
 * 	Output: ["like", "love", "hate"]
 *
 * Challenge
 * It's easy to solve it in two passes, can you do it in one pass?
 */
public class _0133_LongestWord {

    /**
     * Only 1 pass - 把每个字放入与之字数相同的 list 中
     */
    public List<String> longestWords(String[] dictionary) {
        if (dictionary == null || dictionary.length == 0) {
            return new ArrayList<>();
        }

        // key = word length, value = list of words of the same length
        HashMap<Integer, List<String>> map = new HashMap<>();
        int max = 0;
        for (String s : dictionary) {
            int len = s.length();
            max = Math.max(max, len);
            if (!map.containsKey(len)) {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(len, list);
            } else {
                map.get(len).add(s);
            }
        }

        return map.get(max);
    }



    /**
     * Pass 1 - find the longest length
     * Pass 2 - find the words with the longest length
     */
    public List<String> longestWords_2pass(String[] dictionary) {
        List<String> ans = new ArrayList<>();
        if (dictionary == null || dictionary.length == 0) {
            return ans;
        }

        int max = 0;
        for (String s : dictionary) {
            max = Math.max(max, s.length());
        }

        for (String s : dictionary) {
            if (s.length() == max) {
                ans.add(s);
            }
        }

        return ans;
    }
}
