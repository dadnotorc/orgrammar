package lintcode;

import util.ListNode;

/**
 * 372. Delete Node in a Linked List
 * Easy
 * Linked List
 * Apple, Adobe, Microsoft, Salesforce
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
public class _0372_Delete_Node_In_Linked_List {
    /*
    这道题没有给出 head 指针, 所以做法是将要删除的 node 用它下一位覆盖掉

    要小心注意特判
    - 如果当前 node 或者 下一位 为空, 则将当前 node 变为空
    - 否则, 用下一个 node 的值覆盖
     */

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
