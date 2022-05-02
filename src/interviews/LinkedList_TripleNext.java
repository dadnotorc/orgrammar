package interviews;

import java.util.Scanner;

/**
 * 写一个 class 对链表进行操作.
 *
 * 链表每个node的属性是:
 * - Node *next (next指向下一个元素)
 * - Node *triple_next (指向下一个的下一个的下一个元素)
 * - int val
 *
 * 有2个function:
 * - insert: 插入一个节点
 * - delete: 删除一个节点
 */
public class LinkedList_TripleNext {

    static class Node{
        int val;
        Node next;
        Node triple_next;
        public Node (int val) {
            this.val = val;
        }
    }

    private int length;
    private Node head;

    public LinkedList_TripleNext() {
        this.length = 0;
        this.head = null;
    }

    // prepend 其实就是 insert() 里, index == 0 的情况
    public void prepend(int val) {
        Node node = new Node(val);

        node.next = this.head;
        this.head = node;
        if (this.length >= 3) {
            node.triple_next = node.next.next.next;
        }
        this.length++;
    }

    public void insert(int val, int index) {
        if (index <  0 || index > this.length) {
            System.out.println("invalid index");
            return;
        }

        // 找到 cur, 在 cur.next 处添加 new node

        Node node = new Node(val);

        if (index == 0) { // prepend to the begining
            node.next = this.head;
            this.head = node;
            if (this.length >= 3) {
                node.triple_next = node.next.next.next;
            }

        } else if (index <= 2) { // insert to 2nd / 3rd place

            // 添加到 index_1 或者 index_2 的时候, pre 与 cur 是相邻的

            Node pre = null;
            Node cur = this.head;
            if (index == 2) {
                pre = cur;
                cur = cur.next;
            }

            node.next = cur.next;
            node.triple_next = cur.triple_next;
            cur.next = node;

            if (cur.next.next != null) { // 就是 node.next
                cur.triple_next = cur.next.next.next;
            }
            if (pre != null) {
                pre.triple_next = pre.next.next.next; // 其实就是 node.next
            }

        } else { // index >= 3 && original length >= 3

            // 添加 index_3 或者 更后的, pre 与 cur 之间隔一位
            // 原因是插入后, pre, pre.next, 还有 cur 的 triple_next都需要向前移一位

            // 前一种情况 插入 index_1 或者 index_2 不需要这么做的原因是
            // 不存在某个节点的 triple_next 要指向新 node

            Node pre = this.head; // pre 从 index 0 开始
            Node cur = pre.next.next; // cur 从 index 2 开始, 如此, pre.triple_next 和 cur.next 都会变成 new node

            for (int i = 0; i < index - 3; i++) {
                cur = cur.next;
                pre = pre.next;
            }

            node.next = cur.next;
            node.triple_next = cur.triple_next;
            cur.next = node;

            cur.triple_next = pre.next.triple_next;
            pre.next.triple_next = pre.triple_next;
            pre.triple_next = node; // 就是这步 所以要求 pre 与 cur 之间要隔开一位

        }

        this.length++; // 别忘了
    }


    /*
    0   4   5   6   N   N   N
    1 - 2 - 3 - 0 - 4 - 5 - 6

    4   5   6   N   N   N
    1 - 2 - 3 - 4 - 5 - 6
     */
    public void delete(int index) {
        if (index < 0 || index >= this.length) {
            System.out.println("invalid index");
            return;
        }

        // 找到 cur, 删除 cur.next 节点

        if (index == 0) {
            this.head = this.head.next;
        } else if (index <= 2) {
            // 删除 index_1 或者 index_2 的时候, pre 与 cur 是相邻的
            Node pre = null;
            Node cur = this.head;
            if (index == 2) {
                pre = cur;
                cur = cur.next;
            }

            if (pre != null) {
                pre.triple_next = cur.triple_next;
            }
            cur.triple_next = cur.next.triple_next;
            cur.next = cur.next.next;

        } else { // index >= 3
            // 删除 index_3 或者 更后的, pre 与 cur 之间隔一位
            Node pre = this.head;
            Node cur = pre.next.next;

            for (int i = 0; i < index - 3; i++) {
                cur = cur.next;
                pre = pre.next;
            }

            pre.triple_next = pre.next.triple_next;
            pre.next.triple_next = cur.triple_next;
            cur.triple_next = cur.next.triple_next;
            cur.next = cur.next.next;
        }

        this.length--;
    }

    public void print() {
        Node cur = this.head;
        while (cur != null) {
            System.out.println(cur.val + "\t:\t"
                    + (cur.triple_next == null ? "NULL" : cur.triple_next.val));
            cur = cur.next;
        }
    }



    public static void main(String[] args) {
        LinkedList_TripleNext list = new LinkedList_TripleNext();
        System.out.println("Enter a number to choose a test to run");
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();

        if (testNum == 1) {
            list.insert(0, 0);
            list.insert(1, 1);
            list.insert(2, 2);
            list.insert(3, 3);
            list.insert(4, 4);
            list.insert(5, 5);
            list.insert(6, 6);
            list.print();

            System.out.println("----------");

            list.insert(15, 4);
            list.insert(20, 6);
            list.insert(25, 3);
            list.insert(30, 2);
            list.insert(35, 1);
            list.print();

        } else if (testNum == 2) {
            list = new LinkedList_TripleNext();
            list.insert(7, 0);
            list.insert(6, 0);
            list.insert(5, 0);
            list.insert(4, 0);
            list.insert(3, 0);
            list.insert(2, 0);
            list.insert(1, 0);
            list.insert(0, 0);

            list.delete(8);
            list.print();
        } else if (testNum == 3) {
            list = new LinkedList_TripleNext();
            list.insert(2, 0);
            list.insert(1, 0);
            list.insert(0, 0);

            list.insert(5, 1);
            list.print();

            System.out.println("deleting index 1");
            list.delete(1);
            list.print();

            System.out.println("insert at index 2");
            list.insert(5, 2);
            list.print();
        } else if (testNum == 4) {
            list = new LinkedList_TripleNext();
            list.prepend(3);
            list.prepend(1);
            list.prepend(0);
            list.print();
            System.out.println("----------");
            list.insert(2,2);
            list.print();
        }
    }
}
