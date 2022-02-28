package leetcode;

import java.util.*;

/**
 * Group Anagrams
 * Medium
 * #Hash Table, #String
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
public class _0049_Group_Anagrams {

    /*
    读取每个字符串, 记录其中每个字符的出现次数, 对其取 hash

    hashmap 记录 <hash, 该 hash 对应的所有字符串 (队列) >
     */

    public List<List<String>> groupAnagrams_3(String[] strs) {
        List<List<String>> ans = new ArrayList<>();

        if (strs == null || strs.length == 0) {
            return ans;
        }

        Map<Integer, List<String>> map = new HashMap<>();

        for (String s : strs) {
            int[] counts = new int[26];
            for (int i = 0; i < s.length(); i++) {
                counts[s.charAt(i) - 'a']++;
            }

            int hash = Arrays.hashCode(counts);
            // 要不要考虑 hash conflict. 如果有 conflict, int[] 转 string 可能更合适
            // String key = String.valueOf(counts);

            // 这里不能直接将 int[] 作为 map 的 key, 必须转化一下

            if (!map.containsKey(hash)) {
                map.put(hash, new ArrayList<>());
            }


            map.get(hash).add(s);
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
