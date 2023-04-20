public class Solution {

    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(-1, head);  // 别忘了 next 指向 head
        ListNode dummy_tmp = new ListNode(-1);
        ListNode pre = dummy, pre_tmp = dummy_tmp;

        while (head != null) {
            if (head.val < x) {
                pre = pre.next;
                head = head.next;
            } else {
                pre_tmp.next = head;
                pre_tmp = pre_tmp.next;

                head = head.next;
                pre.next = head;
                // 注意: 这里不能移动 pre = pre.next, 否则 pre 就指向 head 了
                
                pre_tmp.next = null; // 必须要断开新链
            }
        }

        // 将 两条链 连接上
        pre.next = dummy_tmp.next;

        return dummy.next;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
