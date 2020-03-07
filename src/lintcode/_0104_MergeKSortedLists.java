/*
Medium
#Heap, #Divde and Conquer, #Priority Queue, #Linked List
Airbnb, Amazon, Facebook, Google, LinkedIn, Microsoft, Twitter, Uber
FAQ++
 */
package lintcode;

import util.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 104. Merge K Sorted Lists
 *
 * Merge k sorted linked lists and return it as one sorted list.
 *
 * Analyze and describe its complexity.
 *
 * Example 1:
 * 	Input:   [2->4->null,null,-1->null]
 * 	Output:  -1->2->4->null
 *
 * Example 2:
 * 	Input: [2->6->null,5->null,7->null]
 * 	Output:  2->5->6->7->null
 */
public class _0104_MergeKSortedLists {

    /**
     * 类似 486. Merge K Sorted Arrays
     * Minheap 解法
     * 1. 创建一个PriorityQueue, PQ规定val较小者靠前. 保持PQ长度为k (k为lists长度)
     * 2. 在PQ中存入每条list的首位node(每条list当前最小值), 共有n个
     * 3. 每从PQ中取出一个node, 将其下一位(node.next)加入PQ. 同时, 将取出的node加入答案list
     *
     * 注意:
     * PQ不接受null, 所以加入node时必须判断是否为null
     */
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0)
            return null;

        if (lists.size() == 1)
            return lists.get(0);

        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (ListNode node : lists) {
            if (node !=null) { // 注意这里, null node不能加入pq中
                pq.offer(node);
            }
        }

        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            if (cur.next != null) { // 这里也别忘了!
                pq.offer(cur.next);
            }

            head.next = cur;
            head = head.next;
        }

        return dummy.next;
    }
}
