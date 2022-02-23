package leetcode;

/**
 * 24. Swap Nodes in Pairs
 * Medium
 * #Linked List, #Recursion
 * Amazon, Apple, Facebook Meta, Google, Microsoft, Oracle, SAP, Uber
 *
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem
 * without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 *
 * Example 2:
 * Input: head = []
 * Output: []
 *
 * Example 3:
 * Input: head = [1]
 * Output: [1]
 *
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 */
public class _0024_Swap_Nodes_n_Pairs {

    /**  pre    head   next
     *   0  ->   1    -> 2   -> 3
     * 先连 1 -> 3
     * 再连 2 -> 1
     * 再连 d -> 2
     * 最后移动 pre 到 head, head 到 head.next
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);

        helper(dummy, head);

        return dummy.next;
    }

    private void helper(ListNode pre, ListNode head) {
        if (head ==  null || head.next == null) {
            return;
        }

        ListNode next = head.next;

        head.next = next.next;
        next.next = head;
        pre.next = next;

        helper(head, head.next);
    }


    /**
     * 另一种写法
     */
    public ListNode swapPairs_2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        head.next.next = swapPairs(head.next.next);

        ListNode tmp = head.next;
        head.next = head.next.next;
        tmp.next = head;
        return tmp;
    }



    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
