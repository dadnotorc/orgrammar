package lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1598 · Uncommon Words from Two Sentences
 * Easy
 * #Hash Table, #String
 *
 * We are given two sentences A and B. (A sentence is a string of space separated words.
 * Each word consists only of lowercase letters.)
 *
 * A word is uncommon if it appears exactly once in one of the sentences,
 * and does not appear in the other sentence.
 *
 * Return a list of all uncommon words.
 * You may return the list in any order.
 *
 * 0 <= A.length <= 200
 * 0 <= B.length <= 200
 * A and B both contain only spaces and lowercase letters.
 *
 * Example 1:
 * Input: A = "this apple is sweet", B = "this apple is sour"
 * Output: ["sweet","sour"]
 *
 * Example 2:
 * Input: A = "apple apple", B = "banana"
 * Output: ["banana"]
 */
public class _1598_Uncommon_Words_from_Two_Sentences {

    /**
     * 1. 对两个字符串使用 split(" "), 获得所有的 word
     * 2. 将所有的 word 存入 hashmap, 并记录其出现次数
     * 3. 遍历 hashmap (map.entrySet()), 将出现次数为 1 的 word 加入答案队列
     * 4. 将答案队列 转成 array
     *
     * 时间 O(m + n), 空间 O(m + n)
     */
    public String[] uncommonFromSentences(String A, String B) {
        if (A == null || A.length() == 0 || B == null || B.length() == 0) {
            return new String[0];
        }

        List<String> ans = new ArrayList<>();

        HashMap<String, Integer> map = new HashMap<>();

        String[] words_A = A.split(" ");
        String[] words_B = B.split(" ");

        for (String word : words_A) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (String word : words_B) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                ans.add(entry.getKey());
            }
        }

        return ans.toArray(new String[0]);
    }
}
