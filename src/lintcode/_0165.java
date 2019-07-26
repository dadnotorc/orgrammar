package lintcode;

/**
 * 165. Merge Two Sorted Lists
 *
 * Merge two sorted (ascending) linked lists and return it as a new sorted list.
 * The new sorted list should be made by splicing together the nodes of the
 *  two lists and sorted in ascending order.
 *
 * time complexity:  O(n) n是l1和l2中, 较短一只的长度
 * space complexity: O(1)
 */
public class _0165 {
    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * @param l1: ListNode l1 is the head of the linked list
     * @param l2: ListNode l2 is the head of the linked list
     * @return: ListNode head of linked list
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write your code here
        ListNode result = new ListNode(0); // always point to the beginning of the returning list
        ListNode lastNode = result;

        while (l1 != null && l2 != null) {
            // compare 2 non-empty list and merge
            if (l1.val <= l2.val) {
                lastNode.next = l1;
                l1 = l1.next;
            } else {
                lastNode.next = l2;
                l2 = l2.next;
            }
            lastNode = lastNode.next;
        }

        if (l1 != null) {
            lastNode.next = l1;
        } else {
            lastNode.next = l2;
        }

        return result.next;
    }
}
