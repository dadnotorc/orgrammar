/*
Easy
Linked List
 */
package leetcode;

import util.ListNode;

/**
 * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
 * If there are two middle nodes, return the second middle node.
 *
 * Example 1:
 * Input: [1,2,3,4,5]
 * Output: Node 3 from this list (Serialization: [3,4,5])
 * The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
 * Note that we returned a ListNode object ans, such that:
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
 *
 * Example 2:
 * Input: [1,2,3,4,5,6]
 * Output: Node 4 from this list (Serialization: [4,5,6])
 * Since the list has two middle nodes with values 3 and 4, we return the second one.
 *
 * Note:
 * The number of nodes in the given list will be between 1 and 100.
 */
public class _0876_MiddleOfTheLinkedList {

    // 第一种解法, 偶数时mid进一步, 基数时mid原地不动
    public ListNode middleNode_Even(ListNode head) {
        int length = 1;
        ListNode cur = head, mid = head;

        while (cur.next != null) {
            cur = cur.next;
            length++;
            if ((length & 1) == 0)
                mid = mid.next;
        }

        return mid;
    }

    // 第二种解法 (参考评论)
    // 一个快指针每次走两布, 一个慢指针每次走一步
    public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 第三种解法, 先遍历, 统计list长度, 计算中位, 再次便利寻找中位并返回
    // time: O(1.5 * n)
    // space: O(1)


    // 第四种解法,创建一个ListNode的array, 第一次遍历时, 把当前node存入array.
    // 然后返回位于array中间位的node
    // time: O(n)
    // space: O(n)


    /*
     * [1] -> 1
     * [1,2] -> 2
     * [1,2,3] -> 2
     * [1,2,3,4] -> 3
     * [1,2,3,4,5] -> 3
     * [1,2,3,4,5,6] -> 4
     * [1,2,3,4,5,6,7] -> 4
     */
}
