/*
Hard
Backtracking, BFS, DFS
Amazon, Facebook, Yelp
 */
package lintcode;

import java.util.*;

/**
 * 121. Word Ladder II
 *
 * Given two words (start and end), and a dictionary, find all shortest
 * transformation sequence(s) from start to end, output sequence in dictionary order.
 *
 * Transformation rule such that:
 * 1. Only one letter can be changed at a time
 * 2. Each intermediate word must exist in the dictionary
 *
 * Notice
 * - All words have the same length.
 * - All words contain only lowercase alphabetic characters.
 * - At least one solution exists.
 *
 * Example 1:
 * Input：start = "a"，end = "c"，dict =["a","b","c"]
 * Output：[["a","c"]]
 * Explanation：
 * "a"->"c"
 * Example 2:
 *
 * Input：start ="hit"，end = "cog"，dict =["hot","dot","dog","lot","log"]
 * Output：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation：
 * 1."hit"->"hot"->"dot"->"dog"->"cog"
 * 2."hit"->"hot"->"lot"->"log"->"cog"
 * The dictionary order of the first sequence is less than that of the second.
 */
public class _0121_WordLadder2 {

    /**
     * 解法1 - 先 end->start BFS, 后 start->end DFS
     * 使用 BFS 从后往前 构建所有可能的路径, 并记录路径距离
     * 使用 DFS 从前往后 寻找答案
     *
     * 易错点:
     * 1. BFS之前, 先将start和end加入字典中
     * 2. BFS开始时, 先initialize字符串变形的HashMap
     * 3. BFS中, 获得当前字符串的变形队列preList后, 只有在distances中不含有pre的时候, 才将pre加入distances中, 并将pre加入queue. 这样保证distances最短
     *    另外, distances是记录到达end的最短距离, 所以 distances.get(pre) = distance.get(cur) + 1. 前者大, 后者小
     * 4. transform时, 只有当字典中含有新变形出来的字符串时, 才将其加入队列中, 避免找到不存在的字符串
     * 5. DFS中, 递归出口, 需要create a copy, 因为path之后会被改变
     * 6. DFS中, 递归拆解, 获得当前字符串的变形队列nextList后, 只有当next存在于distances中, 并且只距离cur为1时, 才对其进行递归
     *    distances.get(cur) = distance.get(next) + 1
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> ans = new ArrayList<List<String>>();

        Map<String, List<String>> map = new HashMap<>(); // 记录从当前字符串变形可达的所有字符串
        Map<String, Integer> distances = new HashMap<>(); // 记录从当前字到达end的距离

        dict.add(start); // 别忘了先加将start和end加入字典
        dict.add(end);

        bfs_end_start(map, distances, end, dict);

        List<String> path = new ArrayList<>();

        dfs_start_end(ans, path, start, end, map, distances);

        return ans;
    }

    // end -> start
    private void bfs_end_start(Map<String, List<String>> map, Map<String, Integer> distance,
                               String end, Set<String> dict) {

        Queue<String> queue = new LinkedList<>();
        queue.offer(end);
        distance.put(end, 0); // 从end到end距离为0

        for (String s : dict)
            map.put(s, new ArrayList<>()); // 别忘了先initialize

        while (!queue.isEmpty()) {
            String cur = queue.poll();

            // 这个list记录由哪些字符串变形可达cur
            List<String> preList = transform(cur, dict);

            for (String pre : preList) {
                if (!map.get(pre).contains(cur)) {
                    map.get(pre).add(cur); // 注意: map记录从前往后变形, 所以这里要反向
                }

                if (!distance.containsKey(pre)) {
                    distance.put(pre, distance.get(cur) + 1);
                    queue.offer(pre);
                }
            }
        }
    }

    private List<String> transform(String str, Set<String> dict) {
        List<String> nextList = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != str.charAt(i)) {
                    String transform = str.substring(0, i) + c + str.substring(i + 1);
                    if (dict.contains(transform))
                        nextList.add(transform);
                }
            }
        }

        return nextList;
    }

    // start -> end
    private void dfs_start_end(List<List<String>> ans, List<String> path, String cur, String end,
                               Map<String, List<String>> map, Map<String, Integer> distance) {

        path.add(cur);

        if (cur.equals(end)) {
            ans.add(new ArrayList<>(path)); // 注意, 这里要做份copy, 不然之后path的内容会变动
        } else {
            List<String> nextList = map.get(cur);
            for (String next : nextList) {
                if (distance.containsKey(next) && distance.get(cur) == distance.get(next) + 1)
                    dfs_start_end(ans, path, next, end, map, distance);
            }
        }

        path.remove(path.size() - 1);
    }

}
