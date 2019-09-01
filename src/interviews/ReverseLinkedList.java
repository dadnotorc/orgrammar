package interviews;

import org.junit.Test;
import util.ListNode;
import util.helper;

import java.util.ArrayDeque;
import java.util.Deque;

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

    /* Method 1 - Iteratively with stack */

    public ListNode reverseIteratively_Stack(ListNode head) {
        if (head == null || head.next == null)
            return head;

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

    /* Method 2 - Recursively */

    public ListNode reverseRecursively(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode newHead = new ListNode(0);
        reverse(head, newHead);

        return newHead.next;
    }

    // 当cur到达倒数第二个值时,再次递归到达最后一位,在递归出口将new head指最后一个值
    // 将cur.next.next指向自己(原本指向NULL),并将cur.next清除
    // 此时,new head指向最后一位,最后一位.next指向cur, 完成转向
    private void reverse(ListNode cur, ListNode head) {
        if (cur.next == null) {
            head.next = cur;
            return;
        }

        reverse(cur.next, head);
        cur.next.next = cur;
        cur.next = null;
    }

    /* Method 3 - Interatively */

    public ListNode reverseIteratively(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null;
        ListNode next;

        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    @Test
    public void test1() {
        ListNode n1 = new ListNode(4);
        n1.next = new ListNode(3);
        n1.next.next = new ListNode(2);
        n1.next.next.next = new ListNode(1);
        n1.next.next.next.next = new ListNode(0);

        System.out.println("Original:      " + helper.listToString(n1));
        ListNode actIteratively_stack = reverseIteratively_Stack(n1);
        System.out.println("Iteratively_s: " + helper.listToString(actIteratively_stack));

        n1 = new ListNode(4);
        n1.next = new ListNode(3);
        n1.next.next = new ListNode(2);
        n1.next.next.next = new ListNode(1);
        n1.next.next.next.next = new ListNode(0);

        System.out.println("Original:      " + helper.listToString(n1));
        ListNode actIteratively = reverseIteratively(n1);
        System.out.println("Iteratively:   " + helper.listToString(actIteratively));

        n1 = new ListNode(4);
        n1.next = new ListNode(3);
        n1.next.next = new ListNode(2);
        n1.next.next.next = new ListNode(1);
        n1.next.next.next.next = new ListNode(0);

        System.out.println("Original:      " + helper.listToString(n1));
        ListNode actRecursively = reverseRecursively(n1);
        System.out.println("Recursively:   " + helper.listToString(actRecursively));
    }

    @Test
    public void test2() {
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(0);

        System.out.println("Original:      " + helper.listToString(n1));
        ListNode actIteratively_stack = reverseIteratively_Stack(n1);
        System.out.println("Iteratively_s: " + helper.listToString(actIteratively_stack));

        n1 = new ListNode(1);
        n1.next = new ListNode(0);

        System.out.println("Original:      " + helper.listToString(n1));
        ListNode actIteratively = reverseIteratively(n1);
        System.out.println("Iteratively:   " + helper.listToString(actIteratively));

        n1 = new ListNode(1);
        n1.next = new ListNode(0);

        System.out.println("Original:      " + helper.listToString(n1));
        ListNode actRecursively = reverseRecursively(n1);
        System.out.println("Recursively:   " + helper.listToString(actRecursively));
    }
}
