#easy #双指针 

https://leetcode.com/problems/intersection-of-two-linked-lists/

Given the heads of two singly linked-lists `headA` and `headB`, return _the node at which the two lists intersect_. If the two linked lists have no intersection at all, return `null`.

For example, the following two linked lists begin to intersect at node `c1`:

![](https://assets.leetcode.com/uploads/2021/03/05/160_statement.png)

The test cases are generated such that there are no cycles anywhere in the entire linked structure.

**Note** that the linked lists must **retain their original structure** after the function returns.

**Custom Judge:**

The inputs to the **judge** are given as follows (your program is **not** given these inputs):

-   `intersectVal` - The value of the node where the intersection occurs. This is `0` if there is no intersected node.
-   `listA` - The first linked list.
-   `listB` - The second linked list.
-   `skipA` - The number of nodes to skip ahead in `listA` (starting from the head) to get to the intersected node.
-   `skipB` - The number of nodes to skip ahead in `listB` (starting from the head) to get to the intersected node.

The judge will then create the linked structure based on these inputs and pass the two heads, `headA` and `headB` to your program. If you correctly return the intersected node, then your solution will be **accepted**.

**Example 1:**

![](https://assets.leetcode.com/uploads/2021/03/05/160_example_1_1.png)

**Input:** intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
**Output:** Intersected at '8'
**Explanation:** The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
- Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references. In other words, they point to two different locations in memory, while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.

**Example 2:**

![](https://assets.leetcode.com/uploads/2021/03/05/160_example_2.png)

**Input:** intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
**Output:** Intersected at '2'
**Explanation:** The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.

**Example 3:**

![](https://assets.leetcode.com/uploads/2021/03/05/160_example_3.png)

**Input:** intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
**Output:** No intersection
**Explanation:** From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.

**Constraints:**

-   The number of nodes of `listA` is in the `m`.
-   The number of nodes of `listB` is in the `n`.
-   `1 <= m, n <= 3 * 104`
-   `1 <= Node.val <= 105`
-   `0 <= skipA < m`
-   `0 <= skipB < n`
-   `intersectVal` is `0` if `listA` and `listB` do not intersect.
-   `intersectVal == listA[skipA] == listB[skipB]` if `listA` and `listB` intersect.

**Follow up:** Could you write a solution that runs in `O(m + n)` time and use only `O(1)` memory?



# 解法 - Java

## 1. 暴力解法 - 2次遍历

1.  遍历第一个 `list` 并使用 `HashSet` 记录所有节点
2.  遍历第二个 `list` 并做比对

时间复杂度: `O(m + n)`
空间复杂度: `O(n)` ← ==缺点==

## 2. 改进 - 将两条链表连在一起 **

难点在于，由于两条链表的长度可能不同，两条链表之间的节点无法对应：

![Untitled](https://labuladong.github.io/algo/images/%e9%93%be%e8%a1%a8%e6%8a%80%e5%b7%a7/5.jpeg)

**解决这个问题的关键是，通过某些方式，让 `p1` 和 `p2` 能够同时到达相交节点 `c1`**。

所以，让 `p1` 遍历完链表 `A` 之后开始遍历链表 `B`，让 `p2` 遍历完链表 `B` 之后开始遍历链表 `A`，这样相当于「逻辑上」两条链表接在了一起。

如果这样进行拼接，就可以让 `p1` 和 `p2` 同时进入公共部分，也就是同时到达相交节点 `c1`

![Untitled](https://labuladong.github.io/algo/images/%e9%93%be%e8%a1%a8%e6%8a%80%e5%b7%a7/6.jpeg)

当两个链表没有相交点时, 相当于 `c1` 节点是 null 空指针，可以正确返回 null。

时间复杂度: `O(m + n)`
空间复杂度: `O(1)`

```java
public class  {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) { return null; }

        ListNode p1 = headA, p2 = headB;

        while (p1 != p2) {
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }
  
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }

            // 可以简写成
            // p1 = (p1 == null ? headB : p1.next);
            // p2 = (p2 == null ? headA : p2.next);

            // 注意这么写就错了, 会跳过 B 链表的第一位
            /*
            if (p1 == null) { p1 = headB; }
            if (p2 == null) { p2 = headA; }
            p1 = p1.next;
            p2 = p2.next;
            */     
        }

        return p1;
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

## 3. 别的想法 - 转换成「寻找环起点」的问题

如果把两条链表首尾相连，那么「寻找两条链表的交点」的问题转换成了前面讲的「寻找环起点」的问题：

![https://labuladong.github.io/algo/images/链表技巧/7.png](https://labuladong.github.io/algo/images/%e9%93%be%e8%a1%a8%e6%8a%80%e5%b7%a7/7.png)

这是一个很巧妙的转换！不过需要注意的是，这道题说不让你改变原始链表的结构，所以你把题目输入的链表转化成环形链表求解之后记得还要改回来，否则无法通过。





# 解法 - Python


```python
class Solution(object):
    def getIntersectionNode(self, headA, headB):
        """
        :type head1, head1: ListNode
        :rtype: ListNode
        """
        if not headA or not headB:
            return None

        p1, p2 = headA, headB

        while p1 != p2:
            p1 = headB if not p1 else p1.next
            p2 = headA if not p2 else p2.next

            # 等同 Java 中, p1 = (p1 == null ? headB : p1.next);

        return p1



class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

```