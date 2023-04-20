#medium #双指针 

https://leetcode.com/problems/remove-nth-node-from-end-of-list/

Given the `head` of a linked list, remove the `nth` node from the end of the list and return its head.

**Example 1:**

![](https://assets.leetcode.com/uploads/2020/10/03/remove_ex1.jpg)

**Input:** head = [1,2,3,4,5], n = 2
**Output:** [1,2,3,5]

**Example 2:**

**Input:** head = [1], n = 1
**Output:** []

**Example 3:**

**Input:** head = [1,2], n = 1
**Output:** [1]

**Constraints:**

-   The number of nodes in the list is `sz`.
-   `1 <= sz <= 30`
-   `0 <= Node.val <= 100`
-   `1 <= n <= sz`

**Follow up:** Could you do this in one pass?


# 1. 暴力解法 - 2次遍历

第1次遍历 - 获得 `list` 长度 `l`

第2次遍历 - 找寻第 `l - n` 个元素 (为被删除元素的前一位)

-   指针`pre` 从头走到 `l - n` 元素时, `pre.next = pre.next.next`

# 2. 改进

两个指针 `pre` 和 `head`

`head` 先移动 `n` 步, 然后 `pre` 从 `dummy` 开始, 两者同时移动. 此时, 两者之间 间隔 `n` 个元素 (不包括指针自己)

`head` 离开 `list` 时, `pre` 指向需删除的元素的前一位

这样是为了对应以下**特殊情况**

```java
Input: head = [1], n = 1
Output: []
```

所以 `pre` 不能从第一位开始, 而需从 `dummy` 节点开始, 既第一位之前

```java
public class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;

        // head 移动 n 位, 而不是 n + 1, 因为 pre 从 dummy开始
        for (int i = 0; i < n; i++) { // 不做 head 是否为 null 的判断, 因为题目保证 1 <= n <= sz
            head = head.next;
        }

        // 至此, head 已经就位, 开始移动 pre
        while (head != null) {
            head = head.next;
            pre = pre.next;
        }

        // pre 已经就位, 开始删除
        pre.next = pre.next.next;

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


# 解法 - python

```python
class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        p = dummy = ListNode(-1, head)

        for _ in range(n):
            head = head.next

        while head:
            head = head.next
            p = p.next
        
        p.next = p.next.next

        return dummy.next


class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

```