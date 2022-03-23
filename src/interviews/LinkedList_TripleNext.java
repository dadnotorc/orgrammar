package interviews;

import org.junit.Test;

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

    class Node{
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

    public void insert(int val, int index) {
        if (index <  0 || index > this.length) {
            System.out.println("invalid index");
            return;
        }

        // 找到 cur, 在 cur.next 处添加 new node

        Node node = new Node(val);

        if (index == 0) {
            node.next = this.head;
            this.head = node;
            if (this.length >= 3) {
                node.triple_next = node.next.next.next;
            }
        } else if (index <= 2) {
            Node pre = null;
            Node cur = this.head;
            if (index == 2) {
                pre = cur;
                cur = cur.next;
            }

            node.next = cur.next;
            node.triple_next = cur.triple_next;
            cur.next = node;

            if (this.length >= 3) {
                cur.triple_next = cur.next.next.next;
                if (pre != null) {
                    pre.triple_next = pre.next.next.next;
                }
            }

        } else { // index >= 3 && original length >= 3
            Node pre = this.head; // pre 从 index 0 开始
            Node cur = pre.next.next; // cur 从 index 2 开始

            for (int i = 0; i < index - 3; i++) {
                cur = cur.next;
                pre = pre.next;
            }

            node.next = cur.next;
            node.triple_next = cur.triple_next;
            cur.next = node;

            cur.triple_next = pre.next.triple_next;
            pre.next.triple_next = pre.triple_next;
            pre.triple_next = node;
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
            System.out.print(cur.val + " : ");
            if (cur.triple_next == null) {
                System.out.println("NULL");
            } else {
                System.out.println(cur.triple_next.val);
            }
            cur = cur.next;
        }
    }


    @Test
    public void test1() {
        LinkedList_TripleNext list = new LinkedList_TripleNext();
        list.insert(0, 0);
        list.insert(1, 1);
        list.insert(2, 2);
        list.insert(3, 3);
        list.insert(4, 4);
        list.insert(5, 5);
        list.insert(6, 6);
        list.insert(15, 4);
        list.insert(20, 6);
        list.insert(25, 3);
        list.insert(30, 2);
        list.insert(35, 1);
        list.print();
    }

    @Test
    public void test2() {
        LinkedList_TripleNext list = new LinkedList_TripleNext();
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
    }

    @Test
    public void test3() {
        LinkedList_TripleNext list = new LinkedList_TripleNext();
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
    }
}
