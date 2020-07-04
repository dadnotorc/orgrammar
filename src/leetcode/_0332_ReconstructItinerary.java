/*
Medium
#DFS, #Graph
 */
package leetcode;

import java.util.*;

/**
 * 332. Reconstruct Itinerary
 *
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.
 *
 * Note:
 * 1. If there are multiple valid itineraries, you should return the itinerary that has the smallest
 *    lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a
 *    smaller lexical order than ["JFK", "LGB"].
 * 2. All airports are represented by three capital letters (IATA code).
 * 3. You may assume all tickets form at least one valid itinerary.
 * 4. One must use all the tickets once and only once.
 *
 * Example 1:
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *
 * Example 2:
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 */
public class _0332_ReconstructItinerary {

    /**
     * 递归
     * 1. 先将所有tickets读入hashmap中, key=出发城市(String), value=到达城市(PriorityQueue 按字母大小排列)
     * 2. 开始递归 - 传入参数是前一层的到达城市, 也就是当前层的出发城市
     *    出口: 如果无法从前一程的到达城市再次出发 (map中没有entry 或者 entry value已空) 说明此城市需放在答案队列中的最后一位
     *         但是注意因为是递归出口, 之后的城市需放在队列靠前位置, 添加答案时应当 res.add(0, from);
     *    拆解: 如果从当前层可再次出发, 将第一个(字母顺序最小)的一程从PriortyQueue中poll出来, 进入下一层递归
     */
    Map<String, PriorityQueue<String>> map = new HashMap<>();
    List<String> res = new ArrayList<>();

    public List<String> findItinerary_DFS(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
//            String from = ticket.get(0);
//            String to = ticket.get(1);
//            if (!map.containsKey(from)) {
//                map.put(from, new PriorityQueue<>());
//            }
//            map.get(from).add(to);
            map.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }
        getPath("JFK");
        return res;
    }

    private void getPath(String from) {
        while (map.containsKey(from) && !map.get(from).isEmpty()) {
            getPath(map.get(from).poll());
        }
        res.add(0, from);
    }


    /**
     * 循环解法 - 使用Stack, 顶层为前一程的到达城市, 也就是当前的出发城市.
     * - 如果无法从当前城市再次出发, 将其pop出stack, 加入答案队列尾段 (注意因为也是反向添加, 所以需要 res.add(0, stack.pop())
     * - 如果可以从当前城市再次出发, 将第一个(字母顺序最小)的一程从PriortyQueue中poll出来, 加入stack顶层
     *   注意这里都不可pop, 均使用peek
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        List<String> res = new ArrayList<>();

        for (List<String> ticket : tickets) {
//            String from = ticket.get(0);
//            String to = ticket.get(1);
//            if (!map.containsKey(from)) {
//                map.put(from, new PriorityQueue<>());
//            }
//            map.get(from).add(to);
            map.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }

        Stack<String> stack = new Stack<>();
        stack.push("JFK");

        while (!stack.isEmpty()) {
            // 注意 不可以在这获得 String from = stack.peek(), 因为stack的最上层在下一个while循环中是不停变动的
            while (map.containsKey(stack.peek()) && !map.get(stack.peek()).isEmpty()) {
                stack.push(map.get(stack.peek()).poll());
            }
            res.add(0, stack.pop());
        }

        return res;
    }
}
