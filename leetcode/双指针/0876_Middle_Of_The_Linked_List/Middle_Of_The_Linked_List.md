#easy #双指针 

https://leetcode.com/problems/middle-of-the-linked-list/

Given the `head` of a singly linked list, return _the middle node of the linked list_.

If there are two middle nodes, return **the second middle** node.

**Example 1:**

![](https://assets.leetcode.com/uploads/2021/07/23/lc-midlist1.jpg)

**Input:** head = [1,2,3,4,5]
**Output:** [3,4,5]
**Explanation:** The middle node of the list is node 3.

**Example 2:**

![](https://assets.leetcode.com/uploads/2021/07/23/lc-midlist2.jpg)

**Input:** head = [1,2,3,4,5,6]
**Output:** [4,5,6]
**Explanation:** Since the list has two middle nodes with values 3 and 4, we return the second one.

**Constraints:**

-   The number of nodes in the list is in the range `[1, 100]`.
-   `1 <= Node.val <= 100`


# 解法 - Java

## 1. 暴力 - 两次遍历
- 第一次遍历, 统计节点数量
- 计算中点
- 第二次遍历, 确定中点

时间复杂度 - `O(1.5*n)`
空间复杂度 - `O(1)`

## 2. 快慢指针

快指针每次走 2 步, 慢指针每次走 1 步

当 list 是偶数个的时候, 例如 1 -> 2 -> 3 -> 4
- 如果要返回中间==靠前==的节点, 例如 2, 需要把 `slow` 指向 `head`, `fast` 指向 `head.next`
- 如果要返回中间==靠后==的节点, 例如 3, 需要把 快慢指针 都指向 `head`

当 list 是奇数个的时候, 两者都可以

```java
public class Solution {

    public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
}

```

## 3. 其他想法

### 3.1
慢指针: ==偶==数时进一步, ==基==数时原地不动

### 3.2
创建一个ListNode的array, 第一次遍历时, 把当前node存入array.
然后返回位于array中间位的node
时间复杂度 - `O(n)`
空间复杂度 - `O(n)`




# 解法 - Python

```python
class Solution(object):
    def middleNode(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        slow = fast = head

        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next

        return slow



class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

```