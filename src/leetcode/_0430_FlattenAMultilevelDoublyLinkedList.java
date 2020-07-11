/*
Medium
#Linked List, #DFS
 */
package leetcode;

/**
 * 430. Flatten a Multilevel Doubly Linked List
 *
 * You are given a doubly linked list which in addition to the next and previous pointers,
 * it could have a child pointer, which may or may not point to a separate doubly linked list.
 * These child lists may have one or more children of their own, and so on, to produce a
 * multilevel data structure, as shown in the example below.
 *
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list.
 * You are given the head of the first level of the list.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * Output: [1,2,3,7,8,11,12,9,10,4,5,6]
 * 解释参见 https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 *
 * Example 2:
 * Input: head = [1,2,null,3]
 * Output: [1,3,2]
 * Explanation:
 * The input multilevel linked list is as follows:
 *   1---2---NULL
 *   |
 *   3---NULL
 *
 * Example 3:
 * Input: head = []
 * Output: []
 *
 *
 * How multilevel linked list is represented in test case:
 * We use the multilevel linked list from Example 1 above:
 *  1---2---3---4---5---6--NULL
 *          |
 *          7---8---9---10--NULL
 *              |
 *              11--12--NULL
 *
 * The serialization of each level is as follows:
 * [1,2,3,4,5,6,null]
 * [7,8,9,10,null]
 * [11,12,null]
 *
 * To serialize all levels together we will add nulls in each level to signify
 * no node connects to the upper node of the previous level. The serialization becomes:
 * [1,2,3,4,5,6,null]
 * [null,null,7,8,9,10,null]
 * [null,11,12,null]
 *
 * Merging the serialization of each level and removing trailing nulls we obtain:
 * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 *
 * Constraints:
 * - Number of Nodes will not exceed 1000.
 * - 1 <= Node.val <= 10^5
 */
public class _0430_FlattenAMultilevelDoublyLinkedList {

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    /**
     *  1---2---3---4---5---6--NULL
     *          |
     *          7---8---9---10--NULL
     *              |
     *              11---12--NULL
     * 转成
     *  1---2---3---7---8---9---10---4---5---6--NULL
     *                  |
     *                 11---12--NULL
     * 转成
     *  1---2---3---7---8---11---12---9---10---4---5---6--NULL
     *
     * 注意插入child链表时, 先插入尾巴, 再插入头.
     * 插入尾巴时, 注意判断p.next是否为空
     *
     * 插入child链表后, 记得清空p.child
     */
    public Node flatten_iterative(Node head) {
        Node p = head; // p指针

        while (p != null) {
            // 如果当前节点没有child指针, 则跳过不理
            // 否则, 将child指针指向的链表插入 p 与 p.next 之间
            if (p.child != null) {
                Node tmp = p.child;
                while (tmp.next != null) { // 跳到child链表的末尾
                    tmp = tmp.next;
                }

                // 在 p 与 p.next之间插入child链表
                // 先插入尾巴
                tmp.next = p.next;
                if (p.next != null) { // 注意判断p是否已到链表队尾
                    p.next.prev = tmp;
                }
                // 再插入头
                p.child.prev = p;
                p.next = p.child;
                // 别忘了清除p.child
                p.child = null;
            }

            p = p.next; // 最后别忘了移动指针
        }

        return head;
    }


    /**
     * Recursion
     * 递归function的作用是找到并返回当前链表的末端, 用于与上一层链表相连
     * 递归中可能遇到的情况:
     * 1. 当前node没有child, 即node.child == null
     *    a. 如果当前node已是末位, 即node.next == null, 直接返回node
     *    b. 如果链表仍有后续, 则将下一位带入递归
     * 2. 当前node有child, 先找到child链表末端, 然后将child链表插入node与node.next之间
     *    a. 如果child链表末端已无后续, 即已到当前链表末端, 则返回child链表末端
     *    b. 如果child链表仍有后续, 则将其下一位带入递归
     *
     * 别忘了原始链表head为空的特判
     *
     * 注意插入child链表时, 先插入尾巴, 再插入头.
     * 插入尾巴时, 注意判断node.next是否为空
     *
     * 插入child链表后, 记得清空node.child
     */
    public Node flatten_Recursion(Node head) {
        if (head == null) { return head; } // 别忘了特判
        flattenTail(head);
        return head;
    }

    // 返回当前链表的末位
    private Node flattenTail(Node node) {
        // 当前node没有child
        // - 如果node没有next, 即已是最后一位, 直接返回
        // - 如果仍有next, 则进行递归
        if (node.child == null) {
            if (node.next == null) {
                return node;
            }
            return flattenTail(node.next);

        } else {
            // node有child, 先找到child链表的末端
            // 然后child链表插入node与node.next之间
            Node childTail = flattenTail(node.child);
            //先插入尾巴
            childTail.next = node.next;
            if (node.next != null) { // 注意判断当前node是否已到链表队尾
                node.next.prev = childTail;
            }
            // 再插入头
            node.child.prev = node;
            node.next = node.child;
            // 别忘了清除node.child
            node.child = null;

            // 如果child链表末端节点的下一位为空, 说明已到当前链表末端, 则返回
            // 否则, 继续递归
            if (childTail.next == null) {
                return childTail;
            }
            return flattenTail(childTail.next);
        }
    }
}
