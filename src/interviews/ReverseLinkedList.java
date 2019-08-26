package interviews;

import org.junit.Test;
import util.ListNode;
import util.helper;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ReverseLinkedList {

    /**
     * Given a singly-linked list, reverse the list.
     * This can be done iteratively or recursively.
     *
     * Example:
     * Input: 4 -> 3 -> 2 -> 1 -> 0 -> NULL
     * Output: 0 -> 1 -> 2 -> 3 -> 4 -> NULL
     *
     * Google
     */

    public ListNode reverseIteratively(ListNode head) {
        if (head == null)
            return null;

        Deque<Integer> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }

        ListNode tmp = new ListNode(stack.pop());
        ListNode ans = tmp;
        while (!stack.isEmpty()) {
            tmp.next = new ListNode(stack.pop());
            tmp = tmp.next;
        }

        return ans;
    }

    // todo fix bug
    public ListNode reverseRecursively(ListNode head) {
        if (head == null || head.next == null)
            return head;

        head.next = reverseRecursively(head.next);
        ListNode newHead = head.next;
        head.next = null;

        return newHead;
    }

    @Test
    public void test1() {
        ListNode n1 = new ListNode(4);
        n1.next = new ListNode(3);
        n1.next.next = new ListNode(2);
        n1.next.next.next = new ListNode(1);
        n1.next.next.next.next = new ListNode(0);

        System.out.println("Original:    " + helper.printList(n1));
        ListNode actIteratively = reverseIteratively(n1);
        System.out.println("Iteratively: " + helper.printList(actIteratively));

        System.out.println("Original:    " + helper.printList(n1));
        ListNode actRecursively = reverseRecursively(n1);
        System.out.println("Recursively: " + helper.printList(actRecursively));
    }

    @Test
    public void test2() {
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(0);

        System.out.println("Original:    " + helper.printList(n1));
        ListNode actIteratively = reverseIteratively(n1);
        System.out.println("Iteratively: " + helper.printList(actIteratively));

        System.out.println("Original:    " + helper.printList(n1));
        ListNode actRecursively = reverseRecursively(n1);
        System.out.println("Recursively: " + helper.printList(actRecursively));
    }
}
