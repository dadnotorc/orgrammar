/*
Easy
#Linked List
Adobe, Airbnb, Amazon, Microsoft
FAQ++
 */
package lintcode;

import util.ListNode;

/**
 * 167. Add Two Numbers
 *
 * You have two numbers represented by a linked list, where each node contains a single digit.
 * The digits are stored in reverse order, such that the 1's digit is at the head of the list.
 * Write a function that adds the two numbers and returns the sum as a linked list.
 *
 * Example 1:
 * Input: 7->1->6->null, 5->9->2->null
 * Output: 2->1->9->null
 * Explanation: 617 + 295 = 912, 912 to list:  2->1->9->null
 *
 * Example 2:
 * Input:  3->1->5->null, 5->9->2->null
 * Output: 8->0->8->null
 * Explanation: 513 + 295 = 808, 808 to list: 8->0->8->null
 */
public class _0167_AddTwoNumbers {

    /**
     * 简化一点
     *
     * 易错点:
     * 1. while结束后, 别忘了将carry加上
     */
    public ListNode addLists_2(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        int carry = 0, sum = 0;
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;

        while (l1 != null || l2 != null) { // 注意这里用 ||  (两者有一不为null即可)
            sum = carry + (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val); // 别忘了加carry
            head.next = new ListNode(sum % 10);
            carry = sum / 10;

            head = head.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // 注意! 别忘了carry
        if (carry != 0) {
            head.next = new ListNode(carry);
        }

        return dummy.next;
    }




    /**
     * 易错点:
     * 1. 第一个while结束后, 别忘了将carry加上
     * 2. 第二个while结束后, 仍然别忘了还有carry!!!
     */
    public ListNode addLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        int carry = 0, sum = 0;
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;

        while (l1 != null && l2 != null) { // 注意这里用&& 两者皆不可为null
            sum = l1.val + l2.val + carry; // 别忘了加carry
            head.next = new ListNode(sum % 10);
            carry = sum / 10;

            head = head.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        // 注意! 不能直接将head.next连上l1/l2, 别忘了加上carry
        ListNode tail = l1 != null ? l1 : l2;

        while (tail != null) {
            sum = tail.val + carry;
            head.next = new ListNode(sum % 10);
            carry = sum / 10;

            head = head.next;
            tail = tail.next;
        }

        // 注意! 别忘了carry
        if (carry != 0) {
            head.next = new ListNode(carry);
        }

        return dummy.next;
    }
}
