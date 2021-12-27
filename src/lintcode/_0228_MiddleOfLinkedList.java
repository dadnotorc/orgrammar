/*
Naive
#Two Pointers, #Linked List
 */
package lintcode;

/**
 * 228 · Middle of Linked List
 *
 * Find the middle node of a linked list and return it.
 *
 * Example 1:
 *
 * Input:  1->2->3
 * Output: 2
 * Explanation: return the middle node.
 *
 * Example 2:
 * Input:  1->2
 * Output: 1
 * Explanation: If the length of list is even return the center left one.
 *
 * Challenge
 * - If the linked list is a data stream, can you find the middle node without iterating the linked list again?
 */
public class _0228_MiddleOfLinkedList {


    /**
     * 也是双指针, 写法稍有不同
     */
    public ListNode middleNode_2(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode fast = head, slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }


    /**
     * 双指针, 一个走两步, 另一个走一步
     */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) { // 此特判不可缺少
            return head;
        }

        ListNode first = head.next; // first 先移动一位, 针对 input: 1->2->null, expect output = 1; 如果不移动, output = 2
        ListNode slow = head;

        while (first != null && first.next != null) {
            first = first.next.next;
            slow = slow.next;
        }

        return slow;
    }


    class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
}
