/*
Medium
#Design
 */
package leetcode;

import java.util.HashMap;

/**
 * 146. LRU Cache
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
 * otherwise return -1.
 *
 * put(key,value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 * LRUCache cache = new LRUCache(2); // capacity = 2
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4, 4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */
public class _0146_LRUCache {

    /*
    Double Linked List中的节点, 有(key,val) pair, 另外带有前后指针
     */
    private class DoubleLinkedNode {
        int key, val;
        DoubleLinkedNode prev, next;
        public DoubleLinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = this.next = null;
        }
    }

    private int capacity;
    HashMap<Integer, DoubleLinkedNode> map = new HashMap<>();
    // head.next指向list首位, tail.prev指向list末位
    DoubleLinkedNode head = new DoubleLinkedNode(-1, -1);
    DoubleLinkedNode tail = new DoubleLinkedNode(-1, -1);

    public _0146_LRUCache(int capacity) {
        this.capacity = capacity;
        // 别忘了在constructor里将head与tail连上
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        // 找到目标节点, 将其从当前位置去除(将其前后节点连上), 然后将目标节点挪至队列末位, 返回节点val
        DoubleLinkedNode node = map.get(key);
        node.prev.next = node.next;
        node.next.prev = node.prev;

        moveToLast(node);

        return node.val;
    }

    public void put(int key, int value) {
        // 这里使用get(), 而不是判断map.containsKey
        // 因为前者包含后者, 而且当存在时, 能将此节点挪至队尾, 节省步骤
        if (get(key) != -1) {
            map.get(key).val = value;
            return;
        }

        // (key,value)pair不存在, 需要创建新节点. 记得判断当前是否已经到达capacity, 是的话需要移除队首节点 (LRU 节点)
        if (map.size() == capacity) {
            // 需要将队列首位从list中移除, 并将其对应entry从HashMap中移除
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }

        DoubleLinkedNode node = new DoubleLinkedNode(key, value);
        moveToLast(node);
        map.put(key, node);
    }

    // 移至队尾 = 插入至当前队尾 与 tail之间
    private void moveToLast(DoubleLinkedNode node) {
        node.next = tail;
        node.prev = tail.prev;

        node.prev.next = node;
        tail.prev = node;
    }
}
