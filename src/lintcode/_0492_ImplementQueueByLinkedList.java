/*
Easy
#Queue
 */
package lintcode;

import java.util.LinkedList;

/**
 * 492 · Implement Queue by Linked List
 *
 * Implement a Queue by linked list. Support the following basic methods:
 * 1. enqueue(item). Put a new item in the queue.
 * 2. dequeue(). Move the first item out of the queue, return it.
 *
 * Example 1:
 * Input:
 * enqueue(1)
 * enqueue(2)
 * enqueue(3)
 * dequeue() // return 1
 * enqueue(4)
 * dequeue() // return 2
 *
 * Example 2:
 * Input:
 * enqueue(10)
 * dequeue()// return 10
 */

public class _0492_ImplementQueueByLinkedList {

    /**
     *
     */
    class Node {
        public int val;
        public Node next;

        public Node (int _val) {
            this.val = _val;
            next = null;
        }
    }

    public Node first, last;

    public _0492_ImplementQueueByLinkedList() {
        first = last = null; // initialize
    }

    public void enqueue(int item) {
        if (first == null) {
            last = new Node(item);
            first = last;
        } else {
            last.next = new Node(item);
            last = last.next;
        }
    }

    public int dequeue() {
        if (first != null) {
            int item = first.val;
            first = first.next;
            return item;
        }

        return Integer.MIN_VALUE;
    }


    /**
     *
     */
    LinkedList<Integer> list = new LinkedList<>();

    public void enqueue_1(int item) {
        list.add(item);
    }

    public int dequeue_1() {
        if (list.size() > 0) {
            return list.remove(0);
        }
        return Integer.MIN_VALUE;
    }
}


