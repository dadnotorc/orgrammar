package lintcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
public class _0165_MergeSortedLists {
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

    @Test
    void test1() {
        // Input: list1 = null, list2 = 0->3->3->null
        // Output: 0->3->3->null

        _0165_MergeSortedLists.ListNode l1 = null;

        _0165_MergeSortedLists.ListNode l2 = new _0165_MergeSortedLists.ListNode(0);
        _0165_MergeSortedLists.ListNode node2 = l2;
        node2.next = new _0165_MergeSortedLists.ListNode(3);
        node2 = node2.next;
        node2.next = new _0165_MergeSortedLists.ListNode(3);
        node2 = node2.next;
        node2.next = null;

        _0165_MergeSortedLists.ListNode l3 = new _0165_MergeSortedLists.ListNode(0);
        _0165_MergeSortedLists.ListNode node3 = l3;
        node3.next = new _0165_MergeSortedLists.ListNode(3);
        node3 = node3.next;
        node3.next = new _0165_MergeSortedLists.ListNode(3);
        node3 = node3.next;
        node3.next = null;

        _0165_MergeSortedLists.ListNode rstList = _0165_MergeSortedLists.mergeTwoLists(l1, l2);

        while (l3 != null || rstList !=null) {
            assertTrue(l3.val == rstList.val);
            l3 = l3.next;
            rstList = rstList.next;
        }
        assertNull(l3);
        assertNull(rstList);
    }

    @Test
    void test2() {
        // Input:  list1 =  1->3->8->11->15->null, list2 = 2->null
        // Output: 1->2->3->8->11->15->null

        _0165_MergeSortedLists.ListNode l1 = new _0165_MergeSortedLists.ListNode(1);
        _0165_MergeSortedLists.ListNode node1 = l1;
        node1.next = new _0165_MergeSortedLists.ListNode(3);
        node1 = node1.next;
        node1.next = new _0165_MergeSortedLists.ListNode(8);
        node1 = node1.next;
        node1.next = new _0165_MergeSortedLists.ListNode(11);
        node1 = node1.next;
        node1.next = new _0165_MergeSortedLists.ListNode(15);
        node1 = node1.next;
        node1.next = null;

        _0165_MergeSortedLists.ListNode l2 = new _0165_MergeSortedLists.ListNode(2);
        _0165_MergeSortedLists.ListNode node2 = l2;
        node2.next = null;

        _0165_MergeSortedLists.ListNode l3 = new _0165_MergeSortedLists.ListNode(1);
        _0165_MergeSortedLists.ListNode node3 = l3;
        node3.next = new _0165_MergeSortedLists.ListNode(2);
        node3 = node3.next;
        node3.next = new _0165_MergeSortedLists.ListNode(3);
        node3 = node3.next;
        node3.next = new _0165_MergeSortedLists.ListNode(8);
        node3 = node3.next;
        node3.next = new _0165_MergeSortedLists.ListNode(11);
        node3 = node3.next;
        node3.next = new _0165_MergeSortedLists.ListNode(15);
        node3 = node3.next;
        node3.next = null;

        _0165_MergeSortedLists.ListNode rstList = _0165_MergeSortedLists.mergeTwoLists(l1, l2);

        while (l3 != null || rstList !=null) {
            assertTrue(l3.val == rstList.val);
            l3 = l3.next;
            rstList = rstList.next;
        }
        assertNull(l3);
        assertNull(rstList);
    }
}
