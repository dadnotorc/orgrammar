/*
Medium
#Heap, #Priority Queue, #Hash Table
Amazon, Uber
 */
package lintcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 471. Top K Frequent Words
 *
 * Given a list of words and an integer k, return the top k frequent words in the list.
 *
 * Notice
 * - You should order the words by the frequency of them in the return list, the most frequent one comes first.
 *   If two words has the same frequency, the one with lower alphabetical order come first.
 *
 * Example 1:
 * Input:
 *   [
 *     "yes", "lint", "code",
 *     "yes", "code", "baby",
 *     "you", "baby", "chrome",
 *     "safari", "lint", "code",
 *     "body", "lint", "code"
 *   ]
 *   k = 3
 * Output: ["code", "lint", "baby"]
 *
 * Example 2:
 * Input:
 *   [
 *     "yes", "lint", "code",
 *     "yes", "code", "baby",
 *     "you", "baby", "chrome",
 *     "safari", "lint", "code",
 *     "body", "lint", "code"
 *   ]
 *   k = 4
 * Output: ["code", "lint", "baby", "yes"]
 *
 * Challenge
 * - Do it in O(nlogk) time and O(n) extra space.
 */
public class _0471_TopKFrequentWords {

    /**
     * HashMap + Priority Queue
     *
     * time:  O(nlogk)
     * space: O(n)
     *
     * 易错点:
     * 1. 要注意min-heap的排列顺序. 答案要求 higher freq + lower alphabet,
     *    所以在PQ中, lower freq + higher alphabet 应当靠前 ->　需要删除时，poll出去的都是不需要的
     * 2. PQ内entry顺序跟答案顺序相反, 别忘了反转
     */
    public String[] topKFrequentWords(String[] words, int k) {

        String[] ans = new String[k];

        Map<String, Integer> map = new HashMap<>();
        for (String s: words) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }

        // 因为用的是min-heap, 要找的是freq高+alphabet较小 (value大, key小)
        // 所以pq排列顺序正好相反, freq低+alphabet较大者靠前,
        // 这样当pq长度超过k时, 需要poll的时候, 去掉的是freq最低+alphabet最大的
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>( // 注意是 Map.Entry
                // value相同时, 比较key的字母顺序; 否则,
                (a, b) -> a.getValue() == b.getValue() ?
                        b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()
        );

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        // PQ内顺序跟答案要求顺序相反, 别忘了反转
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = pq.poll().getKey();
        }

        return ans;
    }

}
