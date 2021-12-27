/*
Medium
#Linked List, #Sort
 */
package lintcode;

import org.junit.Test;

import java.util.List;

/**
 * 98 · Sort List
 *
 * Sort a linked list in O(nlogn) time using constant space complexity.
 *
 * Example 1:
 * Input:  list = 1->3->2->null
 * Output: 1->2->3->null
 *
 * Example 2:
 * Input:  list = 1->7->2->6->null
 * Output: 1->2->6->7->null
 *
 * Challenge
 * - Solve it by merge sort & quick sort separately.
 */
public class _0098_SortList {

    /**
     * Merge sort
     * 易错点: 别忘了移动 list 上的指针;  merge 时, 返回的不是 dummy, 而是 dummy.next
     */
    public ListNode sortList_merge(ListNode head) {
        if (head == null || head.next == null) { return head; }

        ListNode mid = findMid(head);

        // 先 sort 右半部分, 之后将 mid.next 设为空 (分割开左右 list), 再 sort 左半部分
        ListNode right = sortList(mid.next);
        mid.next = null; // 分割 list
        ListNode left = sortList(head);

        return merge(left, right);
    }

    // find and return the middle node from the list, so to divide the list into 2 halves
    // 使用快慢指针
    public ListNode findMid(ListNode head) {
        if (head == null) { return null; }

        ListNode fast = head, slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    // merge the 2 lists in a sorted order, and return the head of the list
    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode newHead = dummy;

        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                newHead.next = head1;
                head1 = head1.next; // 别忘了移动原队列的指针
            } else {
                newHead.next = head2;
                head2 = head2.next;
            }
            newHead = newHead.next; // 也要移动新队列的指针
        }

        newHead.next = head1 == null ? head2 : head1;

        return dummy.next; // 注意 不是 return dummy, 而是 return dummy的下一位
    }


    /**
     * Quick sort
     * 1. 每次将首位值当作pivot
     *    - 小于 pivot 的节点, 放入 left list
     *    - 等于 pivot 的节点, 放入 mid list
     *    - 大于 pivot 的节点, 放入 right list
     * 2. 再对 left list 与 right list 分别进行 quick sort
     * 3. 最后将 left, mid 与 right list 合并
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) { return head; }

        // partition
        // int pivot_val = getTail(head).val;
        int pivot_val = head.val; // 没有必要再去找最后一位, 或者中间位

        ListNode left_dummy = new ListNode(0), left_head = left_dummy;
        ListNode right_dummy = new ListNode(0), right_head = right_dummy;
        ListNode mid_dummy = new ListNode(0), mid_head = mid_dummy;

        while (head != null) {
            if (head.val < pivot_val) {
                left_head.next = head;
                left_head = left_head.next;
            } else if (head.val > pivot_val) {
                right_head.next = head;
                right_head = right_head.next;
            } else {
                mid_head.next = head;
                mid_head = mid_head.next;
            }
            head = head.next; // 别忘了后移 head 指针
        }

        // 必须给每个 list 尾部加入 null 节点来终结, 不然会 TLE
        left_head.next = null;
        right_head.next = null;
        mid_head.next = null;

        left_head = sortList(left_dummy.next);
        right_head = sortList(right_dummy.next);

        return concatenate(left_head, right_head, mid_dummy.next); // 注意, 这里 mid 是提供 mid_dummy.next
    }

    // 易错点: 忘记做 null check
    public ListNode concatenate(ListNode left_head, ListNode right_head, ListNode mid_head) {
        ListNode dummy = new ListNode(0), new_head = dummy;

        if (left_head != null) { // 必须做 null 的特判
            new_head.next = left_head;
            new_head = getTail(left_head);
        }

        new_head.next = mid_head;
        new_head = getTail(mid_head);

        if (right_head != null) { // 必须做 null 的特判
            new_head.next = right_head;
        }

        return dummy.next;
    }

    public ListNode getTail(ListNode head) {
        if (head == null) { return null; }

        while (head.next != null) {
            head = head.next;
        }

        return head;
    }






    class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }



    @Test
    public void test1() {
        ListNode n1 = new ListNode(25);
        ListNode n2 = new ListNode(25);
        n1.next = n2;
        n2.next = null;

        sortList(n1);
    }
}

