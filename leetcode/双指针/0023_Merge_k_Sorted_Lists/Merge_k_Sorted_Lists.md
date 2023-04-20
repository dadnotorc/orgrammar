#hard #双指针 

https://leetcode.com/problems/merge-k-sorted-lists/

You are given an array of `k` linked-lists `lists`, each linked-list is sorted in ascending order.

_Merge all the linked-lists into one sorted linked-list and return it._

**Example 1:**

**Input:** lists = \[[1,4,5],[1,3,4],[2,6]\]
**Output:** [1,1,2,3,4,4,5,6]
**Explanation:** The linked-lists are:
\[
  1->4->5,
  1->3->4,
  2->6
\]
merging them into one sorted list:
1->1->2->3->4->4->5->6

**Example 2:**

**Input:** lists = []
**Output:** []

**Example 3:**

**Input:** lists = [[]]
**Output:** []

**Constraints:**

-   `k == lists.length`
-   `0 <= k <= 10^4`
-   `0 <= lists[i].length <= 500`
-   `-10^4 <= lists[i][j] <= 10^4`
-   `lists[i]` is sorted in **ascending order**.
-   The sum of `lists[i].length` will not exceed `10^4`.


# 解法 - Java - PriorityQueue

使用 `PriorityQueue` 记录并排序每个 `list` 的首位

每次拉链时, 将 `PQ` 的首位放出来

注意! 往 `PQ` 中插入元素时, 要判断是否为 `null`

```java
import java.util.PriorityQueue;

public class _0023_Merge_k_Sorted_Lists {

    public ListNode mergeKLists(ListNode[] lists) {

		if (lists == null) { return null; }
        if (lists.length == 1) { return lists[0]; }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a, b) -> (a.val - b.val)
        );

        for (ListNode list : lists) {
            if (list != null) { // 别忘了判断是否为 null
                pq.offer(list);
            }
        }

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            cur.next = node;
            cur = cur.next;

            if (node.next != null) { // 别忘了判断是否为 null
                pq.offer(node.next);
            }
        }

        return dummy.next;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}

```



# 解法 - Python- divde & conquer

- 通过拆分, 简化成 `merge 2 sorted lists`

```python

class Solution(object):
    def mergeKLists(self, lists):
        """
        :type lists: List[ListNode]
        :rtype: ListNode
        """
        if not lists:
            return None
        if len(lists) == 1:
            return lists[0]

        mid = len(lists) // 2 # 注意是 两条 /

        l1 = self.mergeKLists(lists[:mid])
        l2 = self.mergeKLists(lists[mid:])

        return self.merge(l1, l2)


    # 变成简单地 merge two sorted list
    def merge(self, l1, l2): 
        dummy = p = ListNode(-1)

        while l1 and l2:
            if l1.val < l2.val:
                p.next = l1
                l1 = l1.next
            else:
                p.next = l2
                l2 = l2.next
            p = p.next

        p.next = l1 or l2

        return dummy.next

    
    # 也可以用 recursion 的 merge 解法
    # 参考 leetcode 21



class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
```