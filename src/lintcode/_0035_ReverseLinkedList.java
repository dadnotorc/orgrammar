/*
Easy
#Linked List
Adobe, Amazon, Apple, Facebook, Microsoft, Twitter, Uber
 */
package lintcode;

import util.ListNode;

/**
 * 35. Reverse Linked List
 *
 * Reverse a linked list.
 *
 * Example 1:
 * Input: 1->2->3->null
 * Output: 3->2->1->null
 *
 * Example 2:
 * Input: 1->2->3->4->null
 * Output: 4->3->2->1->null
 *
 * Challenge
 * - Reverse it in-place and in one-pass
 */
public class _0035_ReverseLinkedList {
    /**
     * 递归定义: 将当前节点的指针反转指向前一位, 并返回队列的最后一位 (反转后的第一位)
     */
    public ListNode reverse_recursion(ListNode head) {
        // 出口 - 找到当前队列的最后一位时返回, 此节点将成为新队列的首位
        if (head == null || head.next == null)
            return head;

        ListNode tail = reverse_recursion(head.next); // 将当前队列的末尾传递回去

        head.next.next = head; // 反转指针
        head.next = null; // 单向指针 - 当前递归层里, 移除向后的指针, 回到递归上一层时, 指针反转向前

        return tail;
    }

    /**
     * 循环
     */
    public ListNode reverse_iterative(ListNode head) {
        // 这个特判可以不做
//        if (head == null || head.next == null)
//            return head;

        ListNode pre = null;
        ListNode next;

        /*
        1. 先确定 next 节点
        2. head.next 指针转向, 指向 pre
        3. 将 pre 与 head 指针向下一位移动
         */
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        // 这里return pre, 是因为 head 已经挪到 null 去了
        return pre;
    }
}
