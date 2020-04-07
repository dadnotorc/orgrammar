/*
Medium
#Hash Table, #String
 */
package leetcode;

import java.util.*;

/**
 * Group Anagrams
 *
 * Given an array of strings, group anagrams together.
 *
 * Example:
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * Note:
 * - All inputs will be in lowercase.
 * - The order of your output does not matter.
 *
 * LintCode 772. Group Anagrams 是同样的题目
 */
public class _0049_GroupAnagrams {

    /**
     * 不用sort, 而且是遍历字符串并记录每个字符出现次数, 以此作为map的key
     *
     * 注意, map的key不能用char[], 读取字符得出的charCount转成String是一样, 但是本身不一样 -> map.containsKey会认为都是新的
     */
    public List<List<String>> groupAnagrams＿２(String[] strs) {
        if (strs == null || strs.length == 0)
            return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] charCount = new char[26]; // 26个小写字母
            for (char c : s.toCharArray()) {
                charCount[c - 'a']++;
            }
            String key = String.valueOf(charCount);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }


    /**
     * 把String转成char[], 然后sort
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);

            if (!map.containsKey(key)) {
                map.putIfAbsent(key, new ArrayList<>()); // value用new ArrayList<>, 因为List<>是interface
            }

            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }
}
