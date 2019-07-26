package explore;

/**
 * 24. Swap Nodes in Pairs
 * Medium
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes, only nodes itself
 *  may be changed.
 *
 * Example:
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class Recursion_SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        head.next.next = swapPairs(head.next.next);

        ListNode newHead = head.next;
        head.next = head.next.next;
        newHead.next = head;
        return newHead;
    }



    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
