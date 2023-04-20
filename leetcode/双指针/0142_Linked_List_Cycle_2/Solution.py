class Solution(object):
    def detectCycle(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        fast = slow = head

        while fast and fast.next:
            fast = fast.next.next
            slow = slow.next

            if (fast == slow):
                break
        
        if not fast or not fast.next:
            return None
        
        fast = head
        while fast != slow:
            fast, slow = fast.next, slow.next

        return fast


class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
