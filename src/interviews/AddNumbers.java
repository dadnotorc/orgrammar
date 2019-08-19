package interviews;

import util.ListNode;
import util.helper;

public class AddNumbers {

    /**
     * You are given two linked-lists representing two non-negative integers.
     * The digits are stored in reverse order and each of their nodes contain
     * a single digit. Add the two numbers and return it as a linked list.
     *
     * Example:
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     *
     * Microsoft
     */
    public static ListNode add (ListNode n1, ListNode n2) {
        ListNode ans = new ListNode(0); // 初始不能用 = null
        ListNode last = ans;
        int carryOver = 0;

        while (n1 != null && n2 != null) {
            carryOver += n1.val + n2.val;
            last.next = new ListNode(carryOver % 10);
            carryOver = carryOver / 10;
            n1 = n1.next;
            n2 = n2.next;
            last = last.next;
        }

        while (carryOver != 0) {
            if (n1 != null) {
                carryOver += n1.val;
                n1 = n1.next;
            } else if (n2 != null) {
                carryOver += n2.val;
                n2 = n2.next;
            }

            last.next = new ListNode(carryOver % 10);
            carryOver /= 10;
            last = last.next;
        }

        return ans.next;
    }

    public static void main(String[] args) {
        // 1542 + 465 = 2007
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        l1.next.next.next = new ListNode(1);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        helper.log(add(l1, l2));

        // 99 + 9999 = 10098
        ListNode l3 = new ListNode(9);
        l3.next = new ListNode(9);

        ListNode l4 = new ListNode(9);
        l4.next = new ListNode(9);
        l4.next.next = new ListNode(9);
        l4.next.next.next = new ListNode(9);

        helper.log(add(l3, l4));
    }
}