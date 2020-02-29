/*
Medium
Hash Table, Linked List
Amazon, Facebook, Microsoft, Uber
FAQ+
 */
package lintcode;

import java.util.HashMap;

/**
 * 105. Copy List with Random Pointer
 *
 * A linked list is given such that each node contains an additional
 * random pointer which could point to any node in the list or null.
 *
 * Return a deep copy of the list.
 *
 * Challenge
 * - Could you solve it with O(1) space?
 */
public class _0105_CopyListwithRandomPointer {

    /*
    审题时注意：
    1. singly linked VS doubly list?
    2. need to construct a ListNode class?
    3. 输入和输出是否均为ListNode

    考虑是否使用recursion:
    链表长度为 n 时, 如果使用 recursion, 深度会很深, 可能导致栈溢出 stack overflow
     */

    /**
     * 解法1 - 使用HashMap
     * 此解重点是使用HashMap记录原先的节点以及deep copy所得的新节点
     * 在HashMap中, key = 原先的node, value = 新拷贝的node
     * time:  O(n)
     * space: O(n)
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;

        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode pre, newNode;
        pre = dummy;

        while (head != null) {
            // 拷贝当前head节点
            if (map.containsKey(head)) {
                newNode = map.get(head);
            } else {
                newNode = new RandomListNode(head.label);
                map.put(head, newNode);
            }

            // 拷贝当前head节点的random指针
            if (head.random != null) { // 别忘了先判断是否为空
                if (map.containsKey(head.random)) {
                    newNode.random = map.get(head.random);
                } else {
                    newNode.random = new RandomListNode(head.random.label);
                    map.put(head.random, newNode.random);
                }
            }

            // 将newHead下一位指去新节点
            pre.next = newNode;
            // 向后挪动newHead的指针
            pre = newNode;
            // 向后挪动当前head节点指针
            head = head.next;

            //当前新节点的指针将会在下次循环时更新
        }

        return dummy.next;
    }


    /**
     * 解法2 - 三次遍历, 不使用HashMap
     *       +--+ (表示random link)
     * src:  1->2->3
     *
     * 第一次遍历: 按next链接拷贝节点, 将新节点插入到原先节点下一位
     *       +------+
     * src:  1->1'->2->2'->3->3'
     *
     * 第二次遍历: 关联新节点之间的random链接
     *       +------+
     * src:  1->1'->2->2'->3->3'
     *          +------+
     * 第三次遍历: 将原节点与新节点分割开
     *       +--+
     * src:  1->2->3
     *
     *       +---+
     * dest: 1'->2'->3'
     */
    public RandomListNode copyRandomList_2(RandomListNode head) {
        if (head == null)
            return null;

        copyAndInsertNext(head);
        copyRandom(head);
        return splitRandomLists(head);

    }

    private RandomListNode splitRandomLists(RandomListNode head) {
        // 分割链表
        /*
           从下面
                 +------+
           src:  1->1'->2->2'->3->3'
                    +------+
           变成
                 +--+
           src:  1->2->3
                 +---+
           dest: 1'->2'->3'
         */
        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode pre, newHead;
        pre = dummy; // dest

        while (head != null) {
            newHead = head.next; // newHead指去1'

            head.next = newHead.next; // 把1->1'的指向变成1->2
            head = newHead.next; // head指针从1指去2

            pre.next = newHead; // 把dest链表连上1'
            pre = newHead; // pre指针从dummy指去1'
        }

        return dummy.next;
    }

    // 传入的是head的指针
    private void copyAndInsertNext(RandomListNode head) {
        while (head != null) {
            // 创建新节点 (1')
            /*
                     +----+
               src:  1 -> 2->3
                       1'/^
             */
            RandomListNode newNode = new RandomListNode(head.label);
            newNode.next = head.next;

            // 转移head.next节点指向新节点 (把1->2的指向变成1->1')
            /*
                     +-------+
               src:  1       2->3
                      \> 1' /^
             */
            head.next = newNode;

            // 挪动head指向原链接中的下一位 (head从1,跳过1',挪到2)
            /*
                     +------+
               src:  1->1'->2->3
             */
            head = newNode.next;
        }
    }

    private void copyRandom(RandomListNode head) {
        while (head != null) {
            /*
               从下面
                     +------+
               src:  1->1'->2->2'->3->3'
               变成
                     +------+
               src:  1->1'->2->2'->3->3'
                        +------+
             */
            if (head.random != null) {
                head.next.random = head.random.next;
            }

            // 挪动head从1,跳过1',挪到2
            head = head.next.next;
        }
    }

    // Definition for singly-linked list with a random pointer.
    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    }
}
