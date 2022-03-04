package leetcode;

/**
 * 19. Remove Nth Node From End of List
 * Medium
 *
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Example 2:
 * Input: head = [1], n = 1
 * Output: []
 *
 * Example 3:
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 *
 * Constraints:
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 *
 * Follow up: Could you do this in one pass?
 */
public class _0019_Remove_Nth_Node_From_End_of_List {

    // 如果 n > list 长度, 直接返回 head

    /*
    1. 两次 pass 解 - 先遍历, 确定 list 长度, 然后确定需要删除的 target (从头开始数),


    2. 一次 pass 解
    假设 n = 2
  target       head ->
    1      2    3       4      5     NULL
                     target          head
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode target; // 将被删除的 node (其实可以省略)
        ListNode pre; // target node 前一位

        while (n > 0 && head != null) { // target 经过 n 个 next 即为 head
            head = head.next;
            n--;
        }

        if (n != 0) { return dummy.next; } // 可以不写, 因为题目保证 1 <= n <= sz

        // 至此, head 已经就位, 上 pre 与 target

        pre = dummy;
        target = pre.next;

        while (head != null) {
            head = head.next;
            pre = pre.next;
            target = target.next; // 可以省略的
        }

        pre.next = target.next;

        return dummy.next;
    }




    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
