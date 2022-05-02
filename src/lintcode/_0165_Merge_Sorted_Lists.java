package lintcode;

import org.junit.Test;
import util.ListNode;

import static org.junit.Assert.*;

/**
 * 165. Merge Two Sorted Lists
 * Easy
 * #Linked List
 * Amazon, Apple, LinkedIn, Microsoft
 * FAQ++
 *
 * Merge two sorted (ascending) linked lists and return it as a new sorted list.
 * The new sorted list should be made by splicing together the nodes of the
 *  two lists and sorted in ascending order.
 *
 * time complexity:  O(n) n是 l1 和 l2 中, 较短一只的长度
 * space complexity: O(1)
 *
 * Leetcode 21
 */
public class _0165_Merge_Sorted_Lists {

    /**
     * 每次对比两条 list 的 head node, 将较小者连上
     *
     * 易错点:
     * 1. while循环结束时, 可能仍有一条list仍未读完, 需要将其连上队尾
     */
    public ListNode mergeTwoLists_Iterative(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }

        ListNode dummy = new ListNode(0); // always point to the beginning of the returning list
        ListNode head = dummy;

        while (l1 != null && l2 != null) {
            // compare 2 non-empty list and merge
            if (l1.val <= l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }

        // while循环结束时, 其中一条list可能仍未读完, 需要连上
        if (l1 != null) {
            head.next = l1;
        } else {
            head.next = l2;
        }

        return dummy.next;
    }


    /**
     * 定义: 返回当前两条list的head nodes中的较小值
     * 出口: 如果某一个head node为空, 返回另一条的head node;
     *       如果两者皆不为空, 返回较小者
     * 拆解: 将较小node的next node带入递归, 寻找下一次的较小者, 将其连接上当前较小node的next指针
     */
    public ListNode mergeTwoLists_Recursion(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists_Recursion(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists_Recursion(l1, l2.next);
            return l2;
        }
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

        ListNode rstList = mergeTwoLists_Iterative(l1, l2);

        while (l3 != null && rstList != null) {
            assertEquals(l3.val, rstList.val);
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

        ListNode rstList = mergeTwoLists_Iterative(l1, l2);

        while (l3 != null && rstList !=null) {
            assertEquals(l3.val, rstList.val);
            l3 = l3.next;
            rstList = rstList.next;
        }
        assertNull(l3);
        assertNull(rstList);
    }
}
