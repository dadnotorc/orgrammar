package lintcode;

/**
 * 452 · Remove Linked List Elements
 * Easy
 * #Linked List
 *
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example 1:
 * Input: head = 1->2->3->3->4->5->3->null, val = 3
 * Output: 1->2->4->5->null
 *
 * Example 2:
 * Input: head = 1->1->null, val = 1
 * Output: null
 */
public class _0452_Remove_LinkedList_Elements {

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode cur = pre.next;

        while(cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                // 注意! 如果要删除 cur 节点, 暂时不能后移 pre 指针
                // 因为下一个 cur 可能也要删除, 所以保持 pre 不变
            } else {
                pre = cur; // 只有当 cur 节点不需要删除时, 两个指针均后移
            }

            cur = cur.next;
        }

        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
