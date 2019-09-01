package explore;

import org.junit.Test;
import util.ListNode;
import util.helper;

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

    @Test
    public void test1() {
        util.ListNode n1 = new util.ListNode(1);
        n1.next = new util.ListNode(2);
        n1.next.next = new util.ListNode(3);
        n1.next.next.next = new util.ListNode(4);

        System.out.println("Input:  " + helper.listToString(n1));
        ListNode act = swapPairs(n1);
        System.out.println("Output: " + helper.listToString(act));
    }
}
