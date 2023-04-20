public class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;

        // head 移动 n 位, 而不是 n + 1, 因为 pre 从 dummy开始
        for (int i = 0; i < n; i++) { // 不做 head 是否为 null 的判断, 因为题目保证 1 <= n <= sz
            head = head.next;
        }

        // 至此, head 已经就位, 开始移动 pre
        while (head != null) {
            head = head.next;
            pre = pre.next;
        }

        // pre 已经就位, 开始删除
        pre.next = pre.next.next;

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
