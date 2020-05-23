/*
Medium
#Hash Table, #Heap
 */
package leetcode;

import java.util.*;

/**
 * 451. Sort Characters By Frequency
 *
 * Given a string, sort it in decreasing order based on the frequency of characters.
 *
 * Example 1:
 * Input: "tree"
 * Output: "eert"
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 *
 * Example 2:
 * Input: "cccaaa"
 * Output: "cccaaa"
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 *
 * Example 3:
 * Input: "Aabb"
 * Output: "bbAa"
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 */
public class _0451_SortCharactersByFrequency {

    /**
     * 使用ResultType记录字符与其出现次数
     * 1. 针对256个字符, 每个字符创建一个RT
     * 2. 读取字符串s,获得每个字符出现次数
     * 3. Sort, 次数较大者靠前
     * 4. Build string
     *
     * 易错点:
     * 1. 开始读字符串之前, 需要对256个字符每个创建个数为0的RT
     */

    class ResultType {
        char c;
        int count;
        public ResultType(char c) {
            this.c = c;
            this.count = 0;
        }
    }

    public String frequencySort(String s) {
        if (s == null || s.length() < 2)
            return s;

        ResultType[] count = new ResultType[256];

        for (int i = 0; i < 256; i++) {
            count[i] = new ResultType((char) i);
        }

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)].count++;
        }

        Arrays.sort(count, new Comparator<ResultType>() {
            @Override
            public int compare(ResultType o1, ResultType o2) {
                return o2.count - o1.count;
            }
        });

        StringBuilder sb = new StringBuilder();
        for (ResultType rt : count) {
            if (rt.count == 0) {
                break;
            } else {
                for (int i = 0; i < rt.count; i++) {
                    sb.append(rt.c);
                }
            }
        }

        return sb.toString();
    }


    /**
     * Priority Queue
     */
    public String frequencySort_PQ(String s) {
        if (s == null || s.length() < 2)
            return s;

        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry e = pq.poll();
            for (int i = 0; i < (int) e.getValue(); i++) {
                sb.append((char) e.getKey());
            }
        }

        return sb.toString();
    }




    /**
     * Bucket sort
     * bucket中按字符出现次数排序, 下标为出现次数, value为当前字符
     * 注意同一下标, 可能出现多个不同字符
     */
    public String frequencySort_bucket(String s) {
        if (s == null || s.length() < 2)
            return s;

        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // bucket[i] 表示出现次数为i的所有字符的list
        List<Character> [] bucket = new List[s.length() + 1]; // 注意要 +1, 从0到s.length()

        for (char key : map.keySet()) {
            int freq = map.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }

        StringBuilder sb = new StringBuilder();
        for (int freq = bucket.length - 1; freq > 0; freq--) {
            if (bucket[freq] != null) { // 注意要判断是否为空
                for (char c : bucket[freq]) {
                    for (int i = 0; i < freq; i++) {
                        sb.append(c);
                    }
                }
            }
        }

        return sb.toString();
    }





}
