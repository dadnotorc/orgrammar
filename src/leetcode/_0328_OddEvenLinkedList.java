/*
Medium
#Linked List
 */
package leetcode;

/**
 * 328. Odd Even Linked List
 *
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 *
 * You should try to do it in place.
 * The program should run in O(1) space complexity and O(nodes) time complexity.
 *
 * Example 1:
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 *
 * Example 2:
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 *
 * Note:
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 */
public class _0328_OddEvenLinkedList {

    /**
     * 易错点
     * while循环需要确保 head 与 head.next 皆不为空
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode oddHead = head;
        ListNode evenHead = head.next;

        ListNode evenDummy = new ListNode(-1);
        evenDummy.next = evenHead;

        // 注意这里要判断 head 和 head.next 是否为空. 例如 1->2->3 和 1->2->3->4
        while (evenHead != null && evenHead.next != null) {
            oddHead.next = evenHead.next; // 1->3
            evenHead.next = oddHead.next.next; // 2->null 或者 2->4

            oddHead = oddHead.next; // 跳去3
            evenHead = evenHead.next; // 跳去null或者4
        }

        oddHead.next = evenDummy.next;

        return head;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
