/*
Medium
Priority Queue, Heap
LinkedIn
 */
package lintcode;

import java.util.*;

/**
 * 486. Merge K Sorted Arrays
 * Given k sorted integer arrays, merge them into one sorted array.
 *
 * Example 1:
 * Input:
 *   [
 *     [1, 3, 5, 7],
 *     [2, 4, 6],
 *     [0, 8, 9, 10, 11]
 *   ]
 * Output: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
 *
 * Example 2:
 * Input:
 *   [
 *     [1,2,3],
 *     [1,2]
 *   ]
 * Output: [1,1,2,2,3]
 *
 * Challenge: Do it in O(N log k).
 * - N is the total number of integers.
 * - k is the number of arrays.
 */
public class _0486_MergeKSortedArrays {

    /**
     * 使用minheap
     * 保持一个size为K的Priority Queue,用来存储K个数组中,各自当前最小值
     * 当把PQ首位(最小值)存入ans后,从该最小值所在数组中再次读取下一位最小值放去PQ
     * 为记录PQ中数值所在的数组及其位置,需要新建Element class
     *
     * time:  O(n logk)
     * space: O(n + k)
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null || arrays.length == 0) { return new int[0]; }

        // 先用list存储因为 n (number of integers) 仍未知
        List<Integer> list = new ArrayList<>();
        PriorityQueue<Element> pq = new PriorityQueue<>(
                arrays.length, new MyComparator());

        // 添加K数值中的各自首位
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length != 0) {
                pq.offer(new Element(arrays[i][0], i, 0));
            }
        }

        while (!pq.isEmpty()) {
            // 一次移除一位(当前最小值)
            Element cur = pq.poll();
            list.add(cur.val);

            // 从cur所在数组中读取下一位最小值 (如果存在的话)
            if (cur.column + 1 < arrays[cur.row].length) {
                pq.offer(new Element(arrays[cur.row][cur.column + 1],
                        cur.row, cur.column + 1));
            }
        }

        // 转换 list 为 int array
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private static class MyComparator implements Comparator<Element> {
        @Override
        public int compare(Element e1, Element e2) {
            return e1.val - e2.val;
        }
    }

    private class Element {
        int val, row, column;
        private Element (int val, int row, int column) {
            this.val = val;
            this.row = row;
            this.column = column;
        }
    }
}
