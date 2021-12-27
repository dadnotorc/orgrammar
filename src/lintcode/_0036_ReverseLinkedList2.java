/*
Medium
#Linked List
 */
package lintcode;

/**
 * 36 · Reverse Linked List II
 *
 * Reverse a linked list from position m to n.
 * - m and n satisfy the following condition: 1 <= m <= n <= length of list.
 *
 * Example 1:
 * Input: linked list = 1->2->3->4->5->NULL, m = 2. n = 4
 * Output: 1->4->3->2->5->NULL
 * Explanation: Reverse the [2,4] position of the linked list.
 *
 * Example 2:
 * Input: linked list = 1->2->3->4->null, m = 2, n = 3
 * Output: 1->3->2->4->NULL
 * Explanation: Reverse the [2,3] position of the linked list.
 *
 * Challenge
 * Reverse it in-place and in one-pass
 */
public class _0036_ReverseLinkedList2{

    // todo 已正确, 但是要多读读
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) { return head; }

        // dummy 的作用是, 记录队列的首位. 不直接记录当前 head, 是因为 m 可能等于 1, 即队列可能从开开始反转
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy; // head 前移的目的, 是为了针对 5->null, m = 1, n = 1 的情况

        // 移动 head 到 m节点前一位
        for (int i = 1; i < m; i++) {
            if (head == null) { return null; } // 别忘了此特判
            head = head.next;
        }

        ListNode preM = head; // m 节点前一位
        ListNode mNode = head.next;
        ListNode nNode = mNode, postN = nNode.next;

        for (int i = 0; i < n - m; i++) { // 已假设 m <= n
            if (postN == null) { return null; }  // 别忘了此特判

            ListNode temp = postN.next;
            postN.next = nNode;
            nNode = postN;
            postN = temp;
        }

        /*
        pre    m         n    post
          1 -> 2 -> 3 -> 4 -> 5     初始
          1    2 <- 3 <- 4    5     for 循环完成后
          ...
          1 -> 4 -> 3 -> 2 -> 5     结果
         */
        mNode.next = postN;
        preM.next = nNode;

        return dummy.next;
    }


    class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
}


