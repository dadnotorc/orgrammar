package leetcode;

/**
 * 707. Design Linked List
 *
 * Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
 * A node in a singly linked list should have two attributes: val and next.
 * val is the value of the current node, and next is a pointer/reference to the next node.
 * If you want to use the doubly linked list, you will need one more attribute prev to indicate
 * the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
 *
 * Implement the MyLinkedList class:
 *
 * - MyLinkedList() Initializes the MyLinkedList object.
 * - int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
 * - void addAtHead(int val) Add a node of value val before the first element of the linked list.
 *   After the insertion, the new node will be the first node of the linked list.
 * - void addAtTail(int val) Append a node of value val as the last element of the linked list.
 * - void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list.
 *   If index equals the length of the linked list, the node will be appended to the end of the linked list.
 *   If index is greater than the length, the node will not be inserted.
 * - void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.
 *
 *
 * Example 1:
 * Input
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
 * [[], [1], [3], [1, 2], [1], [1], [1]]
 * Output
 * [null, null, null, null, 2, null, 3]
 *
 * Explanation
 * MyLinkedList myLinkedList = new MyLinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
 * myLinkedList.get(1);              // return 2
 * myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
 * myLinkedList.get(1);              // return 3
 *
 *
 * Constraints:
 *
 * 0 <= index, val <= 1000
 * Please do not use the built-in LinkedList library.
 * At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex.
 */
public class _0707_Design_Linkedlist {

    /* singly linked list */

    private int length;
    private Node head;

    class Node {
        int val;
        Node next;
        public Node (int val) {
            this.val = val;
            this.next = null;
        }
    }


    //  Initializes the MyLinkedList object.
    public _0707_Design_Linkedlist() {
        this.length = 0;
        this.head = null;
    }

    // Get the value of the indexth node in the linked list.
    // If the index is invalid, return -1.
    public int get(int index) {
        if (index >= this.length) { return  -1; }

        Node cur = this.head;
        for (int i = 1; i <= index; i++) {
            cur = cur.next;
        }

        return cur.val;
    }

    // Add a node of value val before the first element of the linked list.
    // After the insertion, the new node will be the first node of the linked list.
    public void addAtHead(int val) {
        Node node = new Node(val);
        node.next = this.head;
        this.head = node;
        this.length++;
    }

    // Append a node of value val as the last element of the linked list.
    public void addAtTail(int val) {
        Node node = new Node(val);
        this.length++;

        if (this.head == null) {
            this.head = node;
        } else {
            Node cur = head;
            while (cur.next != null) { // 注意! 这里不能用 cur != null, 不然无法链接最后一位
                cur = cur.next;
            }
            cur.next = node;
        }
    }

    // Add a node of value val before the indexth node in the linked list.
    // If index equals the length of the linked list, the node will be appended to the end of the linked list.
    // If index is greater than the length, the node will not be inserted.
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > this.length) {
            return;
        }

        if (index == 0) {
            addAtHead(val);
        } else {
            Node node = new Node(val);
            this.length++;

            Node cur = head;
            for (int i = 1; i < index; i++) { // 这里要小心, 到达 index - 1 就结束, 将新值插入 index 位置
                cur = cur.next;
            }

            node.next = cur.next;
            cur.next = node;
        }

    }

    // Delete the indexth node in the linked list, if the index is valid.
    public void deleteAtIndex(int index) {
        if (index >= this.length) {
            return;
        }

        this.length--; //别忘咯

        if (index == 0) {
            this.head = this.head.next;
        } else {
            Node cur = this.head;
            for (int i = 1; i < index; i++) { // 到 index - 1 就停止, 因为要删掉 / 跳过 index 位
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }


    }

    /**
     * double linked list - 加入 tail, (如果有很多 addAtTail 操作的话)
     */
    /*
    private int length;
    private Node head;
    private Node tail;

    class Node {
        int val;
        Node next;
        public Node (int val) {
            this.val = val;
            this.next = null;
        }
    }

    //  Initializes the MyLinkedList object.
    public _0707_Design_Linkedlist() {
        this.length = 0;
        this.head = this.tail = null;
    }

    // Get the value of the indexth node in the linked list.
    // If the index is invalid, return -1.
    public int get(int index) {
        if (index < 0 || index >= this.length) { return -1; }

        Node cur = this.head;
        for (int i = 1; i <= index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    // Add a node of value val before the first element of the linked list.
    // After the insertion, the new node will be the first node of the linked list.
    public void addAtHead(int val) {
        Node node = new Node(val);
        this.length++;

        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            node.next = this.head;
            this.head = node;
        }
    }

    // Append a node of value val as the last element of the linked list.
    public void addAtTail(int val) {
        Node node = new Node(val);
        this.length++;

        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }
    }

    // Add a node of value val before the indexth node in the linked list.
    // If index equals the length of the linked list, the node will be appended to the end of the linked list.
    // If index is greater than the length, the node will not be inserted.
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > this.length) { return; }

        if (index == 0) {
            addAtHead(val);
        } else if (index == this.length) {
            addAtTail(val);
        } else {
            Node node = new Node(val);
            this.length++;

            Node cur = this.head;
            for (int i = 1; i < index; i++) {
                cur = cur.next;
            }
            node.next = cur.next;
            cur.next = node;
        }
    }

    // Delete the indexth node in the linked list, if the index is valid.
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= this.length) { return; }

        this.length--;

        if (index == 0) {
            this.head = this.head.next;
            if (this.length == 0) { // 这里要小心, 因为 length 已经 -- 了
                this.tail = null;
            }
        } else {
            Node cur = this.head;
            for (int i = 1; i < index; i++) {
                cur = cur.next;
            }
            if (index == this.length) {  // 这里要小心, 因为 length 已经 -- 了
                this.tail = cur;
            }
            cur.next = cur.next.next;
        }
    }
    */
}