package lintcode;

import util.ListNode;

import java.util.HashSet;

/**
 * 102. Linked List Cycle
 * Medium
 * #Two Pointers, #Linked List
 * Amazon, Microsoft
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
public class _0102_Linked_List_Cycle {

    /**
     * 也是快慢指针, 另一种写法
     */
    public boolean hasCycle(ListNode head) {
        // 无需此特判
        // if (head == null || head.next == null) { return false; }

        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast != null && fast == slow) { // 貌似可以不用检查 fast 是否为 null
                return true;
            }
        }

        return false;
    }


    /**
     * 使用快慢指针
     */
    public boolean hasCycle_2(ListNode head) {
        if (head == null) { return false; }

        ListNode fast = head, slow = head;

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
     * HashSet - 有使用 O(n) 的 extra space
     */
    public boolean hasCycle_hashmap(ListNode head) {
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
