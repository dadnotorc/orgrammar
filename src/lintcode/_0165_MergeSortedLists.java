package lintcode;

import org.junit.Test;
import util.ListNode;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
    public void test1() {
        // Input: list1 = null, list2 = 0->3->3->null
        // Output: 0->3->3->null

        ListNode l1 = null;

        ListNode l2 = new ListNode(0);
        ListNode node2 = l2;
        node2.next = new ListNode(3);
        node2 = node2.next;
        node2.next = new ListNode(3);
        node2 = node2.next;
        node2.next = null;

        ListNode l3 = new ListNode(0);
        ListNode node3 = l3;
        node3.next = new ListNode(3);
        node3 = node3.next;
        node3.next = new ListNode(3);
        node3 = node3.next;
        node3.next = null;

        ListNode rstList = _0165_MergeSortedLists.mergeTwoLists(l1, l2);

        while (l3 != null || rstList !=null) {
            assertTrue(l3.val == rstList.val);
            l3 = l3.next;
            rstList = rstList.next;
        }
        assertNull(l3);
        assertNull(rstList);
    }

    @Test
    public void test2() {
        // Input:  list1 =  1->3->8->11->15->null, list2 = 2->null
        // Output: 1->2->3->8->11->15->null

        ListNode l1 = new ListNode(1);
        ListNode node1 = l1;
        node1.next = new ListNode(3);
        node1 = node1.next;
        node1.next = new ListNode(8);
        node1 = node1.next;
        node1.next = new ListNode(11);
        node1 = node1.next;
        node1.next = new ListNode(15);
        node1 = node1.next;
        node1.next = null;

        ListNode l2 = new ListNode(2);
        ListNode node2 = l2;
        node2.next = null;

        ListNode l3 = new ListNode(1);
        ListNode node3 = l3;
        node3.next = new ListNode(2);
        node3 = node3.next;
        node3.next = new ListNode(3);
        node3 = node3.next;
        node3.next = new ListNode(8);
        node3 = node3.next;
        node3.next = new ListNode(11);
        node3 = node3.next;
        node3.next = new ListNode(15);
        node3 = node3.next;
        node3.next = null;

        ListNode rstList = _0165_MergeSortedLists.mergeTwoLists(l1, l2);

        while (l3 != null || rstList !=null) {
            assertTrue(l3.val == rstList.val);
            l3 = l3.next;
            rstList = rstList.next;
        }
        assertNull(l3);
        assertNull(rstList);
    }
}
