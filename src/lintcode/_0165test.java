package lintcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class _0165test {

    @Test
    void test1() {
        // Input: list1 = null, list2 = 0->3->3->null
        // Output: 0->3->3->null

        _0165.ListNode l1 = null;

        _0165.ListNode l2 = new _0165.ListNode(0);
        _0165.ListNode node2 = l2;
        node2.next = new _0165.ListNode(3);
        node2 = node2.next;
        node2.next = new _0165.ListNode(3);
        node2 = node2.next;
        node2.next = null;

        _0165.ListNode l3 = new _0165.ListNode(0);
        _0165.ListNode node3 = l3;
        node3.next = new _0165.ListNode(3);
        node3 = node3.next;
        node3.next = new _0165.ListNode(3);
        node3 = node3.next;
        node3.next = null;

        _0165.ListNode rstList = _0165.mergeTwoLists(l1, l2);

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

        _0165.ListNode l1 = new _0165.ListNode(1);
        _0165.ListNode node1 = l1;
        node1.next = new _0165.ListNode(3);
        node1 = node1.next;
        node1.next = new _0165.ListNode(8);
        node1 = node1.next;
        node1.next = new _0165.ListNode(11);
        node1 = node1.next;
        node1.next = new _0165.ListNode(15);
        node1 = node1.next;
        node1.next = null;

        _0165.ListNode l2 = new _0165.ListNode(2);
        _0165.ListNode node2 = l2;
        node2.next = null;

        _0165.ListNode l3 = new _0165.ListNode(1);
        _0165.ListNode node3 = l3;
        node3.next = new _0165.ListNode(2);
        node3 = node3.next;
        node3.next = new _0165.ListNode(3);
        node3 = node3.next;
        node3.next = new _0165.ListNode(8);
        node3 = node3.next;
        node3.next = new _0165.ListNode(11);
        node3 = node3.next;
        node3.next = new _0165.ListNode(15);
        node3 = node3.next;
        node3.next = null;

        _0165.ListNode rstList = _0165.mergeTwoLists(l1, l2);

        while (l3 != null || rstList !=null) {
            assertTrue(l3.val == rstList.val);
            l3 = l3.next;
            rstList = rstList.next;
        }
        assertNull(l3);
        assertNull(rstList);
    }
}