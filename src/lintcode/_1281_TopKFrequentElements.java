/*
Medium
#Hash Table, #Heap
Amazon, Yelp
 */
package lintcode;

import org.junit.Test;

import java.util.*;

/**
 * 1281. Top K Frequent Elements
 *
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Notice
 * - You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * - Your algorithm's time complexity must be better than O(n log n),
 *   where n is the array's size.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 *
 * 如果数组中有两个数字出现频率一样, 题目中没有规定保留哪一个
 * 例如 Input: nums = [1,1,1,2,2,3,3], k = 2
 * LintCode output: [1,2]
 * LeetCode output: [1,3]
 *
 * update: 题目中说 1 ≤ k ≤ number of unique elements. 可能表达的意思是每个数字出现的频率都不同
 *
 */
public class _1281_TopKFrequentElements {

    /**
     * 可以使用最小堆,或者桶排来实现
     * 最小堆 - 因为最终需要返回前 k 个频率最大的元素，通过维护一个元素数目为 k 的最小堆，
     *        每次都将新的元素与堆顶端的元素（堆中频率最小的元素）进行比较，如果新的元素
     *        的频率比堆顶端的元素大，则弹出堆顶端的元素，将新的元素添加进堆中。最终，
     *        堆中的 k 个元素即为前 k 个高频元素。
     * 桶排 - 将数组中的元素按照出现频次进行分组，即出现频次为 i 的元素存放在第 i 个桶。
     *       最后，从桶中逆序取出前 k 个元素。
     */



    /**
     * 假设 每个数字出现的频率都不同 unique frequency
     *
     * 1. 遍历数组, 使用HashMap记录记录每个数字(key)的出现频率(value)
     * 2. 创建一个长度为 n+1 的List数组 (当做桶). 下标为频率(value), 值为一个List, 包含作为key的数字
     *    注意: 桶不能用int数组, 原因是当找到某个下标(频率)对应为空时, 说明没有任何数字出现过该频率次
     *    可是int[]默认值为0, 会有歧义 (0这个数字出现过该频率次)
     * 3. 遍历HashMap, 将每个数字(key)按其出现频率(value)放入上述数组
     * 4. 在桶的数组中, 从后往前找k个元素
     *
     * 注意:
     * a) Map中每个entry -> Map.Entry
     * b) 或者Map中entry的集合 -> map.entrySet()
     */
    public List<Integer> topKFrequent_BucketSort(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // +1的原因是, 对n个数, 出现频率最大值为n, 所以bucket的下标需要从0到n, 即n+1个数
        List<Integer>[] bucket = new List[nums.length + 1];

        for (int i : nums) {
            frequencyMap.put(i, frequencyMap.getOrDefault(i, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }

            bucket[frequency].add(key);
        }

        // 这里假设每个数字(key)的出现频率(value)都唯一
        // 如果此假设不成立, 这里就需要修改, 不能用addAll(), 而是将bucket[i]这个list中的每个数字key单独加入
        // 而且每加一个key的时候需要判断ans.size是否仍 <k
        for (int i = bucket.length - 1; i >= 1 && ans.size() < k; i--) {
            if (bucket[i] != null) {
                ans.addAll(bucket[i]);
            }
        }

        return ans;
    }




    /**
     * 1. 遍历数组, 使用HashMap记录记录每个数字的出现次数
     * 2. 遍历HashMap, 将每个Map.Entry放入PriorityQueue.
     *    保持PQ长度为k, 当超过时, 将当前entry与PQ中最小值对比.
     *
     * 注意:
     * a) Map中每个entry -> Map.Entry
     * b) 或者Map中entry的集合 -> map.entrySet()
     *
     * PQ 的 time complexity 是 O(n*logk). Worst case, 当k=n时, O(n*logn)
     */
    public List<Integer> topKFrequent_MinHeap(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> frequencyMap = new HashMap<>();
//        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<Map.Entry<Integer, Integer>>(
//                new Comparator<Map.Entry<Integer, Integer>>() {
//                    @Override
//                    public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
//                        return e1.getValue() - e2.getValue(); // 按value从小到大排列
//                    }
//                }
//        );

        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
                (e1,e2) -> e1.getValue() - e2.getValue()); // 按value从小到大排列


        // 遍历数组, 将每个数字的出现次数存入HashMap
        for (int i : nums) {
            frequencyMap.put(i, frequencyMap.getOrDefault(i, 0) + 1);
        }

        // 遍历HashMap, 将每个Map.Entry放入PQ. 记住, 要保持PQ长度为k
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (heap.size() < k) {
                heap.offer(entry);
            } else {
                // 比较当前entry与PQ中最小值, 保留较大者
                if (heap.peek().getValue() < entry.getValue()) {
                    heap.poll();
                    heap.offer(entry);
                }
            }
        }

        // 将PQ中的keys按从前往后的顺序加入答案队列 (values从小到大)
        for (Map.Entry<Integer, Integer> entry : heap) {
            ans.add(entry.getKey());
        }

        Collections.reverse(ans); // 反转, 按frequent从多到少排列

        return ans;
    }

}
