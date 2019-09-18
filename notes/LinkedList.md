# 审题时，要注意

* singly linked VS doubly list

* 是否是loop

* 是否需要construct a ListNode class

```java
class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
```

* 输入和输出是否均为ListNode


# Find middle of the linked list

找中间位时, 使用快慢指针, 快指针每次走两步, 慢指针每次走一步