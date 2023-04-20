#medium #双指针

https://leetcode.com/problems/partition-list/description/

Given the `head` of a linked list and a value `x`, partition it such that all nodes **less than** `x` come before nodes **greater than or equal** to `x`.

You should **preserve** the original relative order of the nodes in each of the two partitions.

**Example 1:**

![](https://assets.leetcode.com/uploads/2021/01/04/partition.jpg)

**Input:** head = [1,4,3,2,5,2], x = 3
**Output:** [1,2,2,4,3,5]

**Example 2:**

**Input:** head = [2,1], x = 2
**Output:** [1,2]

**Constraints:**

-   The number of nodes in the list is in the range `[0, 200]`.
-   `-100 <= Node.val <= 100`
-   `-200 <= x <= 200`


# 思路

1. 把原链表分成两个小链表
	- 链表一中的元素都  ` < x`
	- 链表二中的元素都  `>= x`
2. 再把这两条链表接到一起


# 解法 - Java

注意点:
- 当 `head.val >= x` 时
	- 原链中, 移动 `head` 之后, `pre` 不能后移, 否则会与 `head` 重复
	- 新链中, 节点的 `next` 一定要 `null`


```java
public class Solution {

    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(-1, head);  // 别忘了 next 指向 head
        ListNode dummy_tmp = new ListNode(-1);
        ListNode pre = dummy, pre_tmp = dummy_tmp;

        while (head != null) {
            if (head.val < x) {
                pre = pre.next;
                head = head.next;
            } else {
                pre_tmp.next = head;
                pre_tmp = pre_tmp.next;

                head = head.next;
                pre.next = head;
                // 注意: 这里不能移动 pre = pre.next, 否则 pre 就指向 head 了
                
                pre_tmp.next = null; // 必须要断开新链
            }
        }

        // 将 两条链 连接上
        pre.next = dummy_tmp.next;

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


# 解法 - Python

```python
class Solution(object):
    def partition(self, head, x):
        """
        :type head: ListNode
        :type x: int
        :rtype: ListNode
        """
        dummy = ListNode(-1, head)
        dummy_tmp = ListNode(-1)
        p1 = dummy
        p2 = dummy_tmp

        while head:
            if head.val < x:
                p1 = p1.next
                head = head.next
            else:
                p2.next = head
                p2 = p2.next

                head = head.next
                p1.next = head

                p2.next = None
        
        p1.next = dummy_tmp.next

        return dummy.next


class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
```