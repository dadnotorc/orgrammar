package interviews;

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

    public void insert(int val) {

    }

    public void delete(int index) {

    }
}
