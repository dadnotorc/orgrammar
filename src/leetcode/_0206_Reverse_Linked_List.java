package leetcode;

/**
 * 206. Reverse Linked List
 * Easy
 * #Linked List
 *
 * Reverse a singly linked list.
 *
 * Example:
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 *
 *         1 -> 2 -> 3 -> 4 -> 5 -> NULL
 * NULL <- 1 <- 2 <- 3 <- 4 <- 5
 *
 * Follow up:
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class _0206_Reverse_Linked_List {

    /**
     * iterative
     */
    public ListNode reverseList_iterative(ListNode head) {
        ListNode pre = null, cur = head, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 循环结束时, cur 指去了 null, 所以要返回 pre
        return pre;
    }


    /**
     * recursive
     */
    public ListNode reverseList_recursive(ListNode head) {
        return helper(head, null);
    }

    public ListNode helper(ListNode cur, ListNode pre) {
        if (cur == null) {
            return pre;
        }

        ListNode next = cur.next;
        cur.next = pre;

        return helper(next, cur);
    }




    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
