/*
Medium
#Hash Table, #Heap
 */
package leetcode;

import java.util.*;

/**
 * 347. Top K Frequent Elements
 *
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Note:
 * - You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * - Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * - It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 * - You can return the answer in any order.
 *
 * 此题类似 lintcode 1281. Top K Frequent Elements
 */
public class _0347_TopKFrequentElements {

    /**
     * Bucket sort. 比PQ + min heap快
     *
     * 1. 先统计nums中不同数字出现的次数, 将结果记录到hashmap中
     * 2. 创建 List<Integer>[] buckets, 长度为nums.length + 1
     *    buckets[i]记录nums中出现次数为i的数字(一个或多个)
     * 3. 将hashmap中的entry录入buckets中
     * 4. 把buckets中出现次数最多的k个数字存入答案
     */
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null) { return new int[0]; }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // buckets长度应该为 最多的出现次数+1, 即nums长度+1, 而不是map长度+1
        // 因为可能有n个相同数字(出现次数为n).  map长度只是记录有多少不同的数字

        // 另外, 注意 这里不能用 new List<Integer>[size], 而是 new List[size]

        // 使用list array 而不是int array, buckets[i]记录nums中出现次数为i的数字
        // buckets[i] 可以包含多个数字, 说明有多个数字都出现i次. 例如 nums=[1,2], 1和2都出现了一次
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }

        int[] res = new int[k];
        int index = 0;
        for (int i = buckets.length - 1; i >= 0 && index < k; i--) {
            if (buckets[i] != null) {
                for (int element : buckets[i]) {
                    res[index++] = element;
                }
            }
        }

        return res;
    }



    /**
     * PQ + Min-heap
     */
    public int[] topKFrequent_Min_Heap(int[] nums, int k) {
        if (nums == null) { return new int[0]; }

        Map<Integer, Integer> freqMap = new HashMap<>(); // 记录nums中数字出现的次数
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>( // PQ从小到大, 每次排除时, 从小的删
                (e1, e2) -> e1.getValue() - e2.getValue()
        );
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = pq.poll().getKey();
        }

        return res;
    }
}
