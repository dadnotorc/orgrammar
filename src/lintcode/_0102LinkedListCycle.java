/*
Medium
#Two Pointers, #Linked List
Amazon, Microsoft
 */
package lintcode;

import util.ListNode;

import java.util.HashSet;

/**
 * 102. Linked List Cycle
 *
 * Given a linked list, determine if it has a cycle in it.
 *
 * Example 1:
 * Input: 21->10->4->5,  then tail connects to node index 1(value 10).
 * Output: true
 *
 * Example 2:
 * Input: 21->10->4->5->null
 * Output: false
 *
 * Challenge
 * - Can you solve it without using extra space?
 */
public class _0102LinkedListCycle {

    /**
     * 使用快慢指针
     */
    public boolean hasCycle_2(ListNode head) {
        if (head == null)
            return false;

        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }


    /**
     * HashSet
     */
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();

        while (head != null) {
            if (set.contains(head)) {
                return true;
            }

            set.add(head);
            head = head.next;
        }

        return false;
    }
}
