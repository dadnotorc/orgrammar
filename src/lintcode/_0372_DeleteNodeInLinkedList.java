/*
Easy
Linked List
Apple, Adobe, Microsoft, Salesforce
 */
package lintcode;

import util.ListNode;

/**
 * 372. Delete Node in a Linked List
 *
 * Implement an algorithm to delete a node in the middle of a singly linked
 * list, given only access to that node.
 *
 * Example 1:
 * Input:
 * 1->2->3->4->null
 * 3
 * Output:
 * 1->2->4->null
 *
 * Example 2:
 * Input:
 * 1->3->5->null
 * 3
 * Output:
 * 1->5->null
 */
public class _0372_DeleteNodeInLinkedList {

    // 使用下一节点覆盖当前节点, 注意特判
    public void deleteNode(ListNode node) {

        // 特判 #1 - list 本身为空, 所以必须判断当前 node 是否为空
        // 特判 #2 - node 为 list 中最后一位
        if (node == null || node.next == null) {
            node = null;
            return;
        }

        node.val = node.next.val;
        node.next = node.next.next;
    }
}
