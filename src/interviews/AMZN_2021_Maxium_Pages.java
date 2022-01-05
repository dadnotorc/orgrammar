package interviews;

import org.junit.Test;

/**
 * Break a large document into an even number of volumes. 大文件拆分成偶数个小文件
 * Each volume:
 * - is stored in a node instance as a SinglyLinkedListNode
 * - has a page count stored in data
 * - has a pointer to the next volume stored in next
 *
 * Customer will read the first and last volumes each day, removing the volumes from the list after reading them.
 * GIven a reference to the head of a singly-linked list, calculate the maximum number of pages read on any day.
 *
 * Note: create a solution with space complexity of O(1)
 *
 * Example:
 * The linked list is as:
 * 1 -> 4 -> 3 -> 2
 * On the first day, the customer reads the volumes with page counts 1 and 2, and removes them from the list. The
 * number of pages read on the first day is 1 + 2 = 3. The new list is:
 * 4 -> 3
 * On the second day, the customer reads the two remaining volumes with page counts 4 and 3. The number of pages read
 * on the second day is 4 + 3 = 7. The list is now empty.
 * Since 7 > 3, the maximum number of page reads is 7.
 *
 * Constraints:
 * 2 <= n <= 10 ^ 5, where n is even
 * 1 <= SinglyLinkedListNode[i].data <= 10 ^ 9, where (0 <= i < n)
 */
public class AMZN_2021_Maxium_Pages {

    /*
    简单的做法就是
    1. 创建个长度为 10^5 的数组
    2. 将 list 的每个值加入数组中
       同时统计 list 长度.
    3. 将数组中, 对应的首尾两个值相加, 寻找最大值

    时间 O(n) - 遍历两次
    空间 O(n) - 长度为 10^5 的数组, 记录 n 个数值
     */

    /**
     * 1. 使用快慢指针找到中点, 将 list 分成两半
     * 2. 将后半段 list 反转
     * 3. 前后两半段 list 相加, 找最大值
     * 1->2->3->4->5->6
     * 慢针指到 3, 分割编程
     * 1->2->3->6->5->4
     *
     * @param head a reference to the head of a singly-linked list of n integers
     * @return the maximum number of pages read on any day
     */
    public int maximumPages(SinglyLinkedListNode head) {
        SinglyLinkedListNode middle = findMiddle(head);
        reverse(middle);
        middle = middle.next; // 指向后半段第一位

        int ans = 0;
        while (middle != null) {
            ans = Math.max(head.data + middle.data, ans);
            head = head.next;
            middle = middle.next;
        }

        return ans;
    }

    // 找到 middle=n/2 处的节点, 因为我们想找中间靠前的那个节点, 所以把 fast 设置为 head.next
    // 参考 leetcode 876. Middle of the Linked List
    public SinglyLinkedListNode findMiddle(SinglyLinkedListNode head) {
        SinglyLinkedListNode slow = head, fast = head.next;

        while (fast.next != null) { // 这里小心, 别写成 fast != null. 因为当 fast 到达最后一位是, fast.next 为空, fast.next.next 会 NullPointerException
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    // 将 middle 节后之后的部分反转
    // 参考 leetcode 206. Reverse Linked List
    public void reverse(SinglyLinkedListNode middle) {

        // 这里 pre 不能定为 middle, pre 一开始必须是 null, 才能将 reversed 后的队尾连上 null
        SinglyLinkedListNode pre = null, cur = middle.next, next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 别忘了把 middle 与 reversed list 的头连上
        middle.next = pre;
    }




    class SinglyLinkedListNode {
        public int data; // contains the page count
        public SinglyLinkedListNode next; // contains the pointer to the next volume (node)

        public SinglyLinkedListNode(int _data) {
            this.data = _data;
            next = null;
        }
    }


    @Test
    public void test1() {
        SinglyLinkedListNode n0 = new SinglyLinkedListNode(3);
        SinglyLinkedListNode n1 = new SinglyLinkedListNode(1);
        SinglyLinkedListNode n2 = new SinglyLinkedListNode(1);
        SinglyLinkedListNode n3 = new SinglyLinkedListNode(3);
        n0.next = n1;
        n1.next = n2;
        n2.next = n3;

        org.junit.Assert.assertEquals(6, maximumPages(n0));
    }

}
