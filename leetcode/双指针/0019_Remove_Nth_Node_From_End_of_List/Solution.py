class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        p = dummy = ListNode(-1, head)

        for _ in range(n):
            head = head.next

        while head:
            head = head.next
            p = p.next
        
        p.next = p.next.next

        return dummy.next


class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
