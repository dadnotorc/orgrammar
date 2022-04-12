package lintcode;

import org.junit.Test;

import java.util.*;

/**
 * 892. Alien Dictionary
 * Hard
 * Directed Graph, Topological Sort
 * Airbnb, Facebook, Google, Snapchat, Twitter
 *
 * There is a new alien language which uses the latin alphabet.
 * However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary,
 * where words are sorted lexicographically by the rules of this
 * new language. Derive the order of letters in this language.
 *
 * Notice
 * - You may assume all letters are in lowercase.
 * - The dictionary is invalid, if a is prefix of b and b is appear before a.
 * - If the order is invalid, return an empty string.
 * - There may be multiple valid order of letters, return the smallest in
 *   normal lexicographical order
 *
 * Example 1:
 * Input：["wrt","wrf","er","ett","rftt"]
 * Output："wertf"
 * Explanation：
 * from "wrt"and"wrf" ,we can get 't'<'f'
 * from "wrt"and"er" ,we can get 'w'<'e'
 * from "er"and"ett" ,we can get 'r'<'t'
 * from "ett"and"rftt" ,we can get 'e'<'r'
 * So return "wertf"
 *
 * Example 2:
 * Input：["z","x"]
 * Output："zx"
 * Explanation：
 * from "z" and "x"，we can get 'z' < 'x'
 * So return "zx"
 */
public class _0892_Alien_Dictionary {

    /**
     * Step 1: 创建 graph Map, key为words中出现过的所有字符'c', value为words中 > 'c' 的字符 (用Set记录)
     * Step 2: 创建 indegree Map, key为所有字符, value为字典中先于该字符的数量 (Interger)
     * Step 3: 拓扑排序, 先把入度为0的字符放去Priority Queue.
     *         每次取出一个入度为0的字符'c', 找出 > 'c'的其他字符, 将其入度-1. 如该字符入度为0, 加入PQ中
     */
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = constructGraph(words);

        // 应对 输入为 ["abc","ab"] 这种 invalid input
        if (graph == null) {
            return "";
        }

        Map<Character, Integer> indegree = getIndegree(graph);

        return topologicalSorting(graph, indegree);
    }

    private Map<Character, Set<Character>> constructGraph (String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();

        // create nodes - 遍历每个词中的所有字符 c, 创建对应的 HashSet 记录字母表中在 c 之后的字符
        for (String s : words) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!graph.containsKey(c)) {
                    graph.put(c, new HashSet<Character>());
                }
            }
        }

        // create edges - 比较相邻的两个词中字符先后顺序
        for (int i = 0; i < words.length - 1; i++) {
            int index = 0; // 每次都需归零, 从第一个字符开始
            while (index < words[i].length() && index < words[i+1].length()) {
                char c1= words[i].charAt(index);
                char c2= words[i+1].charAt(index);
                if (c1 != c2) {
                    // 按先后顺序, 得到 c1 < c2
                    graph.get(c1).add(c2);
                    break; // 注意: 找到不同后退出, 不要在比较后续字符!
                }
                index++;
            }

            // 应对 输入为 ["abc","ab"] 这种 invalid input, 因为题目要求
            // The dictionary is invalid, if string a is prefix of string b and b is appear before a.
            if (index < words[i].length() && index == words[i + 1].length()) {
                return null;
            }
        }

        return graph;
    }

    // indegree map = 字典中, 有多少字符先于当前字符
    private Map<Character, Integer> getIndegree(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = new HashMap<>();

        // 不合并以下两个loop的原因是, 要先保证所有字符在 indegree 图中已经存在一个 entry

        for (Character c : graph.keySet()) {
            indegree.put(c, 0);
        }

        for (Character c : graph.keySet()) {
            for (Character d : graph.get(c)) { // 对于字典中 c 之后的每个字符 d
                indegree.put(d, indegree.get(d) + 1);
            }
        }

        return indegree;
    }

    private String topologicalSorting(Map<Character, Set<Character>> graph, Map<Character, Integer> indegree) {

        StringBuilder sb = new StringBuilder();

        // 先加入度为0的字符

        // 使用PriorityQueue的原因是, "return the smallest in normal lexicographical order"
        PriorityQueue<Character> pq = new PriorityQueue<>();

        // 先把没有 indegree 的字符 加入 PQ
        for (Character c : indegree.keySet()) {
            if (indegree.get(c) == 0)
                pq.offer(c);
        }

        while (!pq.isEmpty()) {
            Character head = pq.poll();
            sb.append(head);

            // 入度中的字符已取走, 把后续字符的入度 - 1
            // 如果后续字符入度也变成 0, 加入队列中
            for (Character c : graph.get(head)) {
                indegree.put(c, indegree.get(c) - 1);
                if (indegree.get(c) == 0)
                    pq.offer(c);
            }
        }

        // 别忘了特判
        if (sb.length() != graph.size())
            return "";

        return sb.toString();
    }







    @Test
    public void test1() {
        String[] words = {"wrt","wrf","er","ett","rftt"};
        org.junit.Assert.assertTrue("output = " + alienOrder(words),"wertf".equals(alienOrder(words)));
    }

    @Test
    public void test2() {
        String[] words = {"ze", "yf", "xd", "wd", "vd", "ua", "tt", "sz",
                "rd", "qd", "pz", "op", "nw", "mt", "ln", "ko", "jm", "il",
                "ho", "gk", "fa", "ed", "dg", "ct", "bb", "ba"};
        org.junit.Assert.assertTrue("output = " + alienOrder(words),"zyxwvutsrqponmlkjihgfedcba".equals(alienOrder(words)));
    }

    @Test
    public void test3() {
        // 如果使用Queue, 而非PriorityQueue, 此测试会fail
        String[] words = {"zy","zx"};
        org.junit.Assert.assertTrue("output = " + alienOrder(words),"yxz".equals(alienOrder(words)));
    }
}
