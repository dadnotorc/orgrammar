import java.util.PriorityQueue;

public class _0023_Merge_k_Sorted_Lists {
    
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null) { return null; }
        if (lists.length == 1) { return lists[0]; }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a, b) -> (a.val - b.val)
        );

        for (ListNode list : lists) {
            if (list != null) { // 别忘了判断是否为 null
                pq.offer(list);
            }
        }

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            cur.next = node;
            cur = cur.next;

            if (node.next != null) { // 别忘了判断是否为 null
                pq.offer(node.next);
            }
        }

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
