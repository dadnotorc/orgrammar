public class Solution {

    /**
     * 拼接上, 同时遍历
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode p1 = headA, p2 = headB;

        while (p1 != p2) {

            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }
  
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }

            // 可以简写成
            // p1 = (p1 == null ? headB : p1.next);
            // p2 = (p2 == null ? headA : p2.next);




            // 注意这么写就错了, 会跳过 B 链表的第一位
            /*
            if (p1 == null) { p1 = headB; }
            if (p2 == null) { p2 = headA; }
            p1 = p1.next;
            p2 = p2.next;
            */
            
        }

        return p1;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
}
