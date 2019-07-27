package lintcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 1484. The Most Frequent word
 *
 * Give a string s which represents the content of the novel, and then give a
 *  list indicating that the words do not participate in statistics, find the
 *  most frequent word(return the one with the smallest lexicographic order if
 *  there are more than one word)
 *
 * The length of s is not exceed 1000，the size of excludeWords is not exceed 20.
 *
 * Example 1:
 * Input: s = "Jimmy has an apple, it is on the table, he like it"，excludeWords = ["a","an","the"]
 * Output:"it"
 * Explanation:
 * "it" appears twice, the most frequently
 *
 * Example 2:
 * Input: s = "i have a a a a"，excludeWords = ["a"]
 * Output:"have"
 * Explanation:
 * "i" and "have" appear the same number of times, but have dictionary order is small
 */
public class _1484_MostFrequentWord {
	
    /**
     * @param s:            a string
     * @param excludewords: a dict
     * @return: the most frequent word
     */
    public String frequentWord(String s, Set<String> excludewords) {
        // Write your code here
        Map<String, Integer> ans = new HashMap<String, Integer>();
        String res = "";
        int now = -1;
        StringBuilder p = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++)
            if ((s.charAt(i) >= 'A' && s.charAt(i) <= 'Z' || s.charAt(i) >= 'a' && s.charAt(i) <= 'z') == false)
                p.setCharAt(i, '#');
        s = p.toString();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') {
                String tmp = "";
                while (i < s.length() && s.charAt(i) != '#') {
                    tmp += s.charAt(i);
                    i++;
                }
                if (tmp.length() > 0 && (excludewords.contains(tmp) == false)) {
                    int x;
                    if (ans.containsKey(tmp))
                        x = ans.get(tmp).intValue() + 1;
                    else
                        x = 1;
                    ans.put(tmp, Integer.valueOf(x));
                    if (x > now) {
                        now = x;
                        res = tmp;
                    } else if (x == now && tmp.compareTo(res) < 0) {
                        res = tmp;
                    }
                }
            }
        }
        return res;
    }
}