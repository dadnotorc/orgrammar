package leetcode;

/**
 * 2. Add Two Numbers
 * Medium
 * #Linked Lst, #Math, #Recursion
 * Adobe, Airbnb, Amazon, Apple, Facebook meta, Google, Intel, Microsoft, Oracle, SAP, ....
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 * Constraints:
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class _0002_Add_Two_Numbers {

    /* 面试时, 需要确认
    1. 是否有 leading 0
    2. 是否有 负数
    3. 每个 node 是否只有一个数字
    4. list 的顺序, 数字从前往后 还是 从后往前
    5. 直接在原 node 上修改, 还是创建新的 list
     */

    /**
     * 注意 写法
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(-1);
        ListNode head = dummy; // 注意 这里不是 dummy.next
        int carryover = 0;

        ListNode i1 = l1, i2 = l2;
        while (i1 != null || i2 != null) {
            int cur = carryover; // 先加上 carryover
            if (i1 != null) {
                cur += i1.val;
                i1 = i1.next;
            }
            if (i2 != null) {
                cur += i2.val;
                i2 = i2.next;
            }
            head.next = new ListNode(cur % 10);
            carryover = cur / 10;
            head = head.next;
        }

        if (carryover != 0) { // 别忘哦
            head.next = new ListNode(carryover);
        }

        return dummy.next;
    }


    /**
     * 写法有 bug
     */
    public ListNode addTwoNumbers_bug(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(-1);
        ListNode head = dummy.next; // 不能这么写
        int carryover = 0;

        ListNode i1 = l1, i2 = l2;
        while (i1 != null && i2 != null) { // 太罗嗦了
            int cur = carryover + i1.val + i2.val;
            head = new ListNode(cur % 10);
            carryover = cur / 10;
            head = head.next;
            i1 = i1.next;
            i2 = i2.next;
        }

        while (i1 != null) {
            int cur = carryover + i1.val;
            head = new ListNode(cur % 10);
            carryover = cur / 10;
            head = head.next;
            i1 = i1.next;
        }

        while (i2 != null) {
            int cur = carryover + i2.val;
            head = new ListNode(cur % 10);
            carryover = cur / 10;
            head = head.next;
            i2 = i2.next;
        }

        return dummy.next;
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
