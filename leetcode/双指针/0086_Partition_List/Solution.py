
class Solution(object):
    def partition(self, head, x):
        """
        :type head: ListNode
        :type x: int
        :rtype: ListNode
        """
        dummy = ListNode(-1, head)
        dummy_tmp = ListNode(-1)
        p1 = dummy
        p2 = dummy_tmp

        while head:
            if head.val < x:
                p1 = p1.next
                head = head.next
            else:
                p2.next = head
                p2 = p2.next

                head = head.next
                p1.next = head

                p2.next = None
        
        p1.next = dummy_tmp.next

        return dummy.next


class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
